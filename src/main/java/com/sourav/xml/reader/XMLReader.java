package com.sourav.xml.reader;

import com.sourav.jdbc.model.Student;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class XMLReader {

    public StaxEventItemReader<Student> staxEventItemReader(
            //@Value("#{jobParameters ['inputFile']}") FileSystemResource file)
    ) {
        StaxEventItemReader<Student> staxEventItemReader =
                new StaxEventItemReader<Student>();
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        return new StaxEventItemReaderBuilder<Student>()
                .name("itemReader")
                .resource(new FileSystemResource("InputFiles/students.xml"))
                .addFragmentRootElements("student")
                .unmarshaller(studentMarshaller())
                .build();
    }

    public XStreamMarshaller studentMarshaller() {
        Map<String, Class> aliases = new HashMap<>();
        aliases.put("student", Student.class);
        aliases.put("id", Long.class);
        aliases.put("firstName", String.class);
        aliases.put("lastName", String.class);
        aliases.put("email", String.class);

        XStreamMarshaller marshaller = new XStreamMarshaller();

        marshaller.setAliases(aliases);
        marshaller.getXStream().allowTypes(new Class[]{Student.class});
        return marshaller;
    }
}


