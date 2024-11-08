package io.springbatch.springbatchlecture;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class HelloJobConfiguration {

    @Bean
    public Job helloJob(JobRepository jobRepository, Step helloStep1, Step helloStep2) {
        return new JobBuilder("helloJob", jobRepository)
                .start(helloStep1)
                .next(helloStep2)
                .build();
    }

    @Bean
    public Step helloStep1(JobRepository jobRepository, PlatformTransactionManager tx) {
        return new StepBuilder("helloStep1", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("====================================");
                    System.out.println(" helloStep1 executed ");
                    System.out.println("====================================");
                    return RepeatStatus.FINISHED;
                }, tx)
                .build();
    }

    @Bean
    public Step helloStep2(JobRepository jobRepository, PlatformTransactionManager tx) {
        return new StepBuilder("helloStep2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("====================================");
                    System.out.println(" helloStep2 executed ");
                    System.out.println("====================================");
                    return RepeatStatus.FINISHED;
                }, tx)
                .build();
    }
}
