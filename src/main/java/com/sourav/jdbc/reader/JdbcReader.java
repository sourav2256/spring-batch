package com.sourav.jdbc.reader;

import com.sourav.jdbc.model.Student;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class JdbcReader {

    @Autowired
    private DataSource datasource;

    @Bean
    public JdbcCursorItemReader<Student> jdbcCursorItemReader() {
        JdbcCursorItemReader<Student> jdbcCursorItemReader =
                new JdbcCursorItemReader<Student>();

        jdbcCursorItemReader.setDataSource(datasource);
        jdbcCursorItemReader.setSql(
                "select id, first_name as firstName, last_name as lastName,"
                        + "email");

        jdbcCursorItemReader.setRowMapper(new BeanPropertyRowMapper<Student>() {
            {
                setMappedClass(Student.class);
            }
        });

        return jdbcCursorItemReader;
    }
}


