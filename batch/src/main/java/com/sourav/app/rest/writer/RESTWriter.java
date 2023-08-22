package com.sourav.app.rest.writer;

import com.sourav.app.rest.model.StudentResponse;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;


@Component
public class RESTWriter implements ItemWriter<StudentResponse> {
    @Override
    public void write(Chunk<? extends StudentResponse> chunk) throws Exception {
        System.out.println("Inside Item Writer");
        chunk.getItems().stream().forEach(System.out::println);
    }
}
