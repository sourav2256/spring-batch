package com.sourav.jdbc.reader;

import com.sourav.jdbc.model.Student;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class JdbcReader {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource datasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.universitydatasource")
    public DataSource universitydatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcCursorItemReader<Student> jdbcCursorItemReader() {
        JdbcCursorItemReader<Student> jdbcCursorItemReader =
                new JdbcCursorItemReader<Student>();

        jdbcCursorItemReader.setDataSource(datasource());
        jdbcCursorItemReader.setSql(
                "select id, first_name as firstName, last_name as lastName, email from student");

        jdbcCursorItemReader.setRowMapper(new BeanPropertyRowMapper<Student>() {
            {
                setMappedClass(Student.class);
            }
        });

        return jdbcCursorItemReader;
    }
}


