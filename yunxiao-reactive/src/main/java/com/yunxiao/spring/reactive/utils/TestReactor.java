package com.yunxiao.spring.reactive.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.reactivestreams.Publisher;
import org.springframework.lang.Nullable;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LuoYunXiao
 * @since 2023/9/10 13:15
 */
@Getter
@AllArgsConstructor
public class TestReactor<T> {

    private Publisher<T> publisher;

    public List<T> collectToList(@Nullable Integer capacity) {
        List<T> res;
        if (capacity == null) {
            res = new ArrayList<>();
        } else {
            res = new ArrayList<>(capacity);
        }
        StepVerifier.create(publisher)
                .thenConsumeWhile(any -> true, res::add)
                .verifyComplete();
        return res;
    }

    public List<T> collectToList() {
        if (publisher instanceof Flux) {
            return collectToList(null);
        } else {
            return collectToList(1);
        }
    }

    public T collectToOne() {
        return collectToList().get(0);
    }

    public static <T> TestReactor<T> create(Publisher<T> publisher) {
        return new TestReactor<>(publisher);
    }
}
