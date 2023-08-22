package com.sourav.app.json.processor;

import com.sourav.app.json.model.Student;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class JsonProcessor implements ItemProcessor<Student, Student> {
    @Override
    public Student process(Student item) throws Exception {
        System.out.println("Inside Item Processor");
        Student student = new Student();

        if(item.getId() == 4) {
            throw new NullPointerException();
        }
        student.setId(item.getId());
        student.setEmail(item.getEmail());
        student.setFirstName(item.getFirstName());
        student.setLastName(item.getLastName());
        return student;
    }
}
