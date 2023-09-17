package com.yunxiao.spring.reactive.utils;

import lombok.*;
import org.reactivestreams.Publisher;
import reactor.test.StepVerifier;

/**
 * @author LuoYunXiao
 * @since 2023/9/10 13:15
 */
@Getter
@AllArgsConstructor
public class TestReactor {

    private Publisher<?> publisher;

    public void printResult() {
        StepVerifier.create(publisher)
                .thenConsumeWhile(any -> true, System.out::println)
                .verifyComplete();
    }

    public static TestReactor create(Publisher<?> publisher) {
        return new TestReactor(publisher);
    }
}
