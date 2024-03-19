package me.study.springbatchtest.study.job

import org.springframework.batch.core.JobParameter
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.JobParametersIncrementer
import java.text.SimpleDateFormat
import java.util.*

class CustomJobParametersIncrementer: JobParametersIncrementer {

    private val format = SimpleDateFormat("yyyyMMdd-hhmmss")
    override fun getNext(parameters: JobParameters?): JobParameters {
        val id = format.format(Date())

        val value = JobParameter(id)
        parameters?.parameters?.put("run.id", value)
        return parameters!!
//        return JobParametersBuilder().addString("run.id", id).toJobParameters()
    }
}