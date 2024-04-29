package me.study.springbatchtest.study.job

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.util.*

@Component
class JobRunner(
    val job: Job,
    val jobLauncher: JobLauncher
    )  : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {

        val jobParameters = JobParametersBuilder()
            .addString("name", "user1")
            .addLong("seq", 2L)
            .addDate("date", Date())
            .addDouble("age", 16.5)
            .addString("requestDate", "20240417")
            .toJobParameters()

        jobLauncher.run(job, jobParameters)
    }
}