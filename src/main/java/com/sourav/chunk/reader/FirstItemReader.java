package com.sourav.chunk.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class FirstItemReader implements ItemReader<Integer> {
    List<Integer> items = Arrays.asList(1, 2, 3, 4, 5);
    int idx = 0;
    @Override
    public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        System.out.println("Inside Item Reader");
        Integer item;
        if(idx < items.size()) {
            item = items.get(idx);
            idx++;
            return item;

        }
        idx = 0;
        return null;
    }
}
