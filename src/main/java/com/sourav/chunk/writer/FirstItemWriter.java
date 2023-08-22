package com.sourav.chunk.writer;

import org.springframework.batch.item.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class FirstItemWriter implements ItemWriter<Long> {
    @Override
    public void write(Chunk<? extends Long> chunk) throws Exception {
        System.out.println("Inside Item Writer");
        chunk.getItems().stream().forEach(System.out::println);
    }
}
