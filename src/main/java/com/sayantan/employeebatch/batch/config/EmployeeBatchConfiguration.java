package com.sayantan.employeebatch.batch.config;

import com.sayantan.employeebatch.batch.mapper.EmployeeMapper;
import com.sayantan.employeebatch.batch.processor.EmployeeProcessor;
import com.sayantan.employeebatch.dto.Employee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.validation.BindException;

import javax.sql.DataSource;

@Configuration
public class EmployeeBatchConfiguration {
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private DataSource dataSource;
    @Bean
    public Job employeeBatchJob(JobRepository jobRepository) throws BindException {
        return new JobBuilder("EmployeeBatch", jobRepository)
                .start(employeeBatchStep(jobRepository))
                .build();
    }
    @Bean
    public Step employeeBatchStep(JobRepository jobRepository) throws BindException {
        return new StepBuilder("EmployeeBatchStep",jobRepository)
                .<Employee, Employee>chunk(10,transactionManager)
                .reader(employeeReader())
                .processor(employeeProcessor())
                .writer(employeeWriter())
                .build();
    }
    @Bean
    public FlatFileItemReader<Employee> employeeReader() throws BindException {
        FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource("src/main/resources/employee.csv"));
        reader.setLineMapper(lineMapper());
        return reader;
    }
    @Bean
    public LineMapper<Employee> lineMapper() throws BindException {
        DefaultLineMapper lineMapper = new DefaultLineMapper();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setStrict(false);
        tokenizer.setNames(new String[] {"firstName", "lastName","dob","gender","nationality"});

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper());

        return lineMapper;
    }
    @Bean
    public FieldSetMapper fieldSetMapper() throws BindException {
        return new EmployeeMapper();
    }
    @Bean
    public EmployeeProcessor employeeProcessor() {
        return new EmployeeProcessor();
    }
    @Bean
    public JdbcBatchItemWriter<Employee> employeeWriter() {
        JdbcBatchItemWriter<Employee> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setSql("insert into Employee(FirstName,LastName,DOB,Gender,Nationality) values(:firstName, :lastName, :dob, :gender, :nationality)");
        ItemSqlParameterSourceProvider<Employee> paramProvider = new BeanPropertyItemSqlParameterSourceProvider<>();
        writer.setItemSqlParameterSourceProvider(paramProvider);
        return writer;
    }

}
