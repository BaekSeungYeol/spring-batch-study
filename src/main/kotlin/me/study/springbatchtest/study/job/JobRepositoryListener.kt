package me.study.springbatchtest.study.job

import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class JobRepositoryListener : JobExecutionListener {

    @Autowired
    lateinit var jobRepository: JobRepository

    override fun beforeJob(jobExecution: JobExecution) {

    }

    override fun afterJob(jobExecution: JobExecution) {

        val jobName = jobExecution.jobInstance.jobName
        val jobParameters = JobParametersBuilder().addString("name", "user1").toJobParameters()
        val lastJobExecution = jobRepository.getLastJobExecution(jobName, jobParameters)

        lastJobExecution?.let {
            with(it) {
                for (execution in this.stepExecutions) {
                    val status = execution.status
                    val exitStatus = execution.exitStatus
                    println("status = $status exitStatus = $exitStatus stepName = ${execution.stepName}")
                }

            }
        }
    }
}