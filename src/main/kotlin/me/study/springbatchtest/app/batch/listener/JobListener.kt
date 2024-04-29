package me.study.springbatchtest.app.batch.listener

import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener

class JobListener: JobExecutionListener {

    override fun beforeJob(jobExecution: JobExecution) {
        TODO("Not yet implemented")
    }

    override fun afterJob(jobExecution: JobExecution) {
        val time = jobExecution.endTime!!.time - jobExecution.startTime!!.time
        println(time)
    }
}