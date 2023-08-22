package com.sourav.app.listener;

import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;

@Component
public class SkipListener {
    @OnSkipInRead
    public void skipInRead(Throwable th) {
        if (th instanceof FlatFileParseException) {
            createFile("First Job/First Step/reader/CSVlogs.txt", ((FlatFileParseException) th).getInput());
        } if (th instanceof ParseException) {
            createFile("First Job/First Step/reader/JSONlogs.txt", th.getMessage());
        }
    }

    public void createFile(String filePath, String data) {
        try (FileWriter fileWriter = new FileWriter(new File(filePath), true)) {
            fileWriter.write(data + "\n");
        } catch (Exception e) {

        }
    }
}
