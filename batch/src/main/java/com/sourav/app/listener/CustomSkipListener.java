package com.sourav.app.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sourav.app.json.model.Student;
import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;

@Component
public class CustomSkipListener {
    @OnSkipInRead
    public void skipInRead(Throwable th) {
        if (th instanceof FlatFileParseException) {
            createFile("First Job/First Step/reader/CSVlogs.txt", ((FlatFileParseException) th).getInput());
        } if (th instanceof JsonProcessingException) {
            createFile("First Job/First Step/reader/JSONlogs.txt", th.getMessage());
        }
    }

    @OnSkipInProcess
    public void skipInProcess (Student students, Throwable th) {
        createFile("First Job/First Step/processor/jsonLogs.txt", students.toString());
    }

    public void createFile(String filePath, String data) {
        try (FileWriter fileWriter = new FileWriter(new File(filePath), true)) {
            fileWriter.write(data + "\n");
        } catch (Exception e) {

        }
    }
}
