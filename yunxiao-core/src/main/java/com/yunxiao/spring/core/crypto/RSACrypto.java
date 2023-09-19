package com.yunxiao.spring.core.crypto;

import lombok.AllArgsConstructor;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author LuoYunXiao
 * @since 2023/9/19 15:19
 */
public class RSACrypto {
    private final KeyPair keyPair;

    public static final String RSA_INSTANCE = "RSA";

    public RSACrypto(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    /**
     * @param encodedPublic  BASE64编码的公钥
     * @param encodedPrivate BASE64编码的私钥
     * @return RSACrypto
     */
    public static RSACrypto create(String encodedPublic, String encodedPrivate) {
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA_INSTANCE);
            PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(decoder.decode(encodedPublic)));
            PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decoder.decode(encodedPrivate)));
            KeyPair pair = new KeyPair(publicKey, privateKey);
            return new RSACrypto(pair);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static RSACrypto newRSACrypto(KeyPair keyPair) {
        return new RSACrypto(keyPair);
    }

    public static RSACrypto newRandomRSACrypto() {
        try {
            KeyPair pair = KeyPairGenerator.getInstance(RSA_INSTANCE).generateKeyPair();
            return new RSACrypto(pair);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    private final Cipher cipher;

    {
        try {
            cipher = Cipher.getInstance(RSA_INSTANCE);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public EncodedBytesConverter encrypt(byte[] data) {
        byte[] bytes;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
            bytes = cipher.doFinal(data);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
        return new EncodedBytesConverter(bytes);
    }

    public EncodedBytesConverter encrypt(String data) {
        return encrypt(data.getBytes());
    }

    public DecodedBytesConverter decrypt(byte[] encoded) {
        byte[] bytes;
        try {
            cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
            bytes = cipher.doFinal(encoded);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
        return new DecodedBytesConverter(bytes);
    }

    public DecodedBytesConverter decrypt(String encodedBase64) {
        return decrypt(Base64.getDecoder().decode(encodedBase64));
    }

    @AllArgsConstructor
    public static class EncodedBytesConverter {
        private byte[] bytes;

        public byte[] toBytes() {
            return bytes;
        }

        public String toBase64String() {
            return Base64.getEncoder().encodeToString(bytes);
        }
    }

    @AllArgsConstructor
    public static class DecodedBytesConverter {
        private byte[] bytes;

        public byte[] toBytes() {
            return bytes;
        }

        public String toString() {
            return new String(bytes);
        }
    }

}
