package com.sourav.app.rest.service;

import com.sourav.app.rest.model.StudentResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    List<StudentResponse> list;
    public List<StudentResponse> fetchStudents() {
        RestTemplate restTemplate = new RestTemplate();
        StudentResponse[] studentResponses = restTemplate.getForObject(
                "http://localhost:8085/api/v1/students",
                StudentResponse[].class);
        list = new ArrayList<>();

        for (StudentResponse sr: studentResponses) {
            list.add(sr);
        }
        return list;
    }

    public StudentResponse getStudent() {
        if(list == null || list.isEmpty()) {
            fetchStudents();
        }
        if(list != null && !list.isEmpty()) {
            return list.remove(0);
        }
        return null;
    }
}
