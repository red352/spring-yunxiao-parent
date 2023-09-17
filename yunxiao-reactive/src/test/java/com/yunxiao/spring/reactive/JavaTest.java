package com.yunxiao.spring.reactive;

import com.yunxiao.spring.reactive.utils.TestReactor;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author LuoYunXiao
 * @since 2023/9/10 13:24
 */
public class JavaTest {
    @Test
    void test1() {
        Flux<Integer> f1 = Flux.just(1, 2, 3, 4, 5).delayElements(Duration.ofSeconds(1));
        Flux<Integer> f2 = Flux.just(6, 7, 8, 9, 10).delayElements(Duration.ofSeconds(1));
        Flux<Integer> publisher = Flux.mergeSequential(f1, f2);
        TestReactor.create(publisher).printResult();
    }
}
