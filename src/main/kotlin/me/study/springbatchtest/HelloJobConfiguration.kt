package me.study.springbatchtest

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HelloJobConfiguration(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory
) {

    @Bean
    fun helloJob(): Job {
        return jobBuilderFactory["helloJob"]
            .start(helloStep1())
            .next(helloStep2())
            .build()
    }

    @Bean
    fun helloStep1(): Step {
        return stepBuilderFactory["helloStep1"]
            .tasklet { contribution, chunkContext ->
                println("=====================")
                println(" Hello Spring Batch !")
                println("=====================")
                RepeatStatus.FINISHED
            }
            .build()

    }

    @Bean
    fun helloStep2(): Step {
        return stepBuilderFactory["helloStep1"]
            .tasklet { contribution, chunkContext ->
                println("=====================")
                println(" Hello Spring Batch 2 !")
                println("=====================")
                RepeatStatus.FINISHED
            }
            .build()

    }
}