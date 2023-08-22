package com.sourav.csv.writer;

import com.sourav.csv.model.Student;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;


@Component
public class CSVItemWriter implements ItemWriter<Student> {

    @Override
    public void write(Chunk<? extends Student> chunk) throws Exception {
        System.out.println("Inside Item Writer");
        chunk.getItems().stream().forEach(System.out::println);
    }


    public FlatFileItemWriter<Student> flatFileItemWriter(
            //@Value("#{jobParameters ['outputFile']}") FileSystemResource file
    ) {
        FlatFileItemWriter<Student> flatFileItemWriter = new FlatFileItemWriter<>();
        File file = new File("outputFiles/students.csv");
        flatFileItemWriter.setResource(new FileSystemResource(file));

        flatFileItemWriter.setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.write("ID, First Name, Last Name, Email");
            }
        });

        DelimitedLineAggregator<Student> delimitedLineAggregator = new DelimitedLineAggregator<>();
        BeanWrapperFieldExtractor<Student> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<>();

        String[] tokens = {"id", "firstName", "lastName", "email"};
        beanWrapperFieldExtractor.setNames(tokens);
        delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
        flatFileItemWriter.setLineAggregator(delimitedLineAggregator);
        flatFileItemWriter.setFooterCallback(new FlatFileFooterCallback() {
            public void writeFooter(Writer writer) throws IOException {
                writer.write("Created @ "+ new Date());
            }
        });

        return flatFileItemWriter();
    }

    public FlatFileItemWriter<Student> itemWriter(WritableResource outputResource) {
        BeanWrapperFieldExtractor<Student> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[] {"id", "firstName", "lastName", "email"});
        fieldExtractor.afterPropertiesSet();

        DelimitedLineAggregator<Student> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(",");
        lineAggregator.setFieldExtractor(fieldExtractor);

        return new FlatFileItemWriterBuilder<Student>()
                .name("Students")
                .resource(outputResource)
                .lineAggregator(lineAggregator)
                .build();
    }


}