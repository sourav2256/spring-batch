package com.sourav.app.csv.reader;

import com.sourav.app.csv.model.Student;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
//@StepScope
public class CSVItemReader {

    public FlatFileItemReader<Student> flatFileItemReader(
            //@Value("#{jobParameters ['inputFile']}") FileSystemResource file
    ) {
        FlatFileItemReader<Student> flatFileItemReader = new FlatFileItemReader<Student>();
        File file = new File("InputFiles/students.csv");
        flatFileItemReader.setResource(new FileSystemResource(file));
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        String[] tokens = {"ID", "First Name", "Last Name", "Email"};
        tokenizer.setNames(tokens);
        DefaultLineMapper<Student> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new BeanWrapperFieldSetMapper<Student>() {
            {
                setTargetType(Student.class);
            }
        });
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper);

        flatFileItemReader.setLinesToSkip(1);
        return flatFileItemReader;
//  compact way

//        flatFileItemReader.setLineMapper(new DefaultLineMapper<Student>() {
//            {
//                setLineTokenizer (new DelimitedLineTokenizer {
//                {
//                    setNames("ID", "First Name", "Last Name", "Email");
//                }
//            });
//                setFieldSetMapper (new BeanWrapperFieldSetMapper<Student>() {
//                    {
//                        setTargetType(Student.class);
//                    }
//                });
//
//            }
//        });
    }
}


