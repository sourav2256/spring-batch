package com.sourav.json.reader;

import com.sourav.csv.model.Student;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;

@Configuration
public class JsonReader {

    @Bean
    public JsonItemReader<Student> jsonItemReader(
            //@Value("#{jobParameters ['inputFile']}") FileSystemResource file
    ) {
        JsonItemReader<Student> jsonItemReader = new JsonItemReader<>();
        File file = new File("InputFiles/students.json");
        jsonItemReader.setResource(new FileSystemResource(file));
        jsonItemReader.setJsonObjectReader (new JacksonJsonObjectReader<>(Student.class));
        return jsonItemReader;
    }
}


