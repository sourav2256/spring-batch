package com.sourav.app.listener;

import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.PrintWriter;

@Component
public class SkipListener {
    @OnSkipInRead
    public void skipInRead(Throwable th) {
        if (th instanceof FlatFileParseException) {
            createFile("First Job/First Step/reader/logs.txt", ((FlatFileParseException) th).getInput());
        }
    }

    public void createFile(String filePath, String data) {
        try (PrintWriter fileWriter = new PrintWriter(new File(filePath), String.valueOf(true))) {
            fileWriter.write(data + "\n");
        } catch (Exception e) {

        }
    }
}
