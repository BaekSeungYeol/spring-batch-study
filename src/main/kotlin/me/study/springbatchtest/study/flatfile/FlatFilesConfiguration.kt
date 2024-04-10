package me.study.springbatchtest.study.flatfile

import me.study.springbatchtest.domain.Customer
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.batch.item.file.mapping.DefaultLineMapper
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource

@Configuration
class FlatFilesConfiguration(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory
) {

    @Bean
    fun job(): Job {
        return jobBuilderFactory.get("flatfile")
            .start(step1())
            .next(step2())
            .build()
    }

    @Bean
    @JobScope
    fun step1(): Step {
        return stepBuilderFactory.get("step1")
            .chunk<User, User>(2)
            .reader(itemReader())
            .writer { items -> items.forEach { println(it) } }
            .build()
    }


    @Bean
    fun step2(): Step {
        return stepBuilderFactory.get("step2")
            .tasklet { contribution, chunkContext ->
                println("step2 has executed")
                RepeatStatus.FINISHED
            }
            .build()
    }

    @Bean
    fun itemReader(): ItemReader<User> {
//        val itemReader = FlatFileItemReader<User>()
//        itemReader.setResource(ClassPathResource("/customer.csv"))
//
//        val lineMapper = DefaultLineMapper<User>()
//        lineMapper.setLineTokenizer(DelimitedLineTokenizer())
//        lineMapper.setFieldSetMapper(UserFieldSetMapper())
//
//        itemReader.setLineMapper(lineMapper)
//        itemReader.setLinesToSkip(1)

        return FlatFileItemReaderBuilder<User>()
            .name("flatFile")
            .resource(ClassPathResource("/customer.csv"))
            .fieldSetMapper(UserFieldSetMapper())
            .linesToSkip(1)
            .delimited().delimiter(",")
            .names("name", "age", "year")
            .build()
    }
}