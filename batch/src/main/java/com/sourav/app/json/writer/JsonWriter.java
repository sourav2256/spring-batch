package com.sourav.app.json.writer;

import com.sourav.app.json.model.Student;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;


@Component
public class JsonWriter implements ItemWriter<Student> {
    @Override
    public void write(Chunk<? extends Student> chunk) throws Exception {
        System.out.println("Inside Item Writer");
        chunk.getItems().stream().forEach(System.out::println);
    }

    public JsonFileItemWriter<Student> jsonFileItemWriter() {
        return new JsonFileItemWriterBuilder<Student>()
                .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
                .resource(new FileSystemResource("outputFiles/students.json"))
                //.resource(new ClassPathResource("outputFiles/students.json"))
                .name("studentJsonFileItemWriter")
                .build();
    }
}
