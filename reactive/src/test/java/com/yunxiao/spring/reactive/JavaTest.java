package com.yunxiao.spring.reactive;

import com.yunxiao.spring.reactive.utils.TestUtil;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

/**
 * @author LuoYunXiao
 * @since 2023/9/10 13:24
 */
public class JavaTest {
    @Test
    void test1() {
        Flux<Integer> publisher = Flux.just(1, 2, 3, 4, 5);
        TestUtil.create(publisher).printResult();
    }
}
