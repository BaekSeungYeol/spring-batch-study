package me.study.springbatchtest.ui

import me.study.springbatchtest.domain.Member
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.core.launch.support.SimpleJobLauncher
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer
import org.springframework.core.task.SimpleAsyncTaskExecutor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class JobLauncherController(
    private val job: Job,
    private val jobLauncher: JobLauncher,
    private val basicBatchConfigurer: BasicBatchConfigurer
) {


    @PostMapping("/batch")
    fun launch(member: Member): String {

        val jobParams = JobParametersBuilder()
            .addString("id", member.id)
            .addDate("date", Date())
            .toJobParameters()

        val jobLauncher = basicBatchConfigurer.jobLauncher as SimpleJobLauncher
        jobLauncher.setTaskExecutor(SimpleAsyncTaskExecutor())
        jobLauncher.run(job, jobParams)

        return "batch completed"
    }
}