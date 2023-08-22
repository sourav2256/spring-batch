package com.sourav.app.rest.reader;

import com.sourav.app.rest.model.StudentResponse;
import com.sourav.app.rest.service.StudentService;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RESTReader {

    @Autowired
    private StudentService studentService;

    public ItemReaderAdapter<StudentResponse> itemReaderAdapter() { // need custom logic
        ItemReaderAdapter<StudentResponse> itemReaderAdapter = new ItemReaderAdapter<>();
        itemReaderAdapter.setTargetObject(studentService);
        itemReaderAdapter.setTargetMethod("getStudent");
        return itemReaderAdapter;
    }
}


