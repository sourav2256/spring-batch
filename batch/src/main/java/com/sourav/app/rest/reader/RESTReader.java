package com.sourav.app.rest.reader;

import com.sourav.app.rest.model.StudentResponse;
import com.sourav.app.rest.service.StudentService;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Configuration
public class RESTReader {

    @Autowired
    private StudentService studentService;

    @Bean
    public ItemReaderAdapter<StudentResponse> itemReaderAdapter() { // need custom logic
        ItemReaderAdapter<StudentResponse> itemReaderAdapter = new ItemReaderAdapter<>();
        itemReaderAdapter.setTargetObject(studentService);
        itemReaderAdapter.setTargetMethod("getStudent");
        return itemReaderAdapter;
    }
}


