package me.study.springbatchtest

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableBatchProcessing
class SpringBatchTestApplication

fun main(args: Array<String>) {
    runApplication<SpringBatchTestApplication>(*args)
}
