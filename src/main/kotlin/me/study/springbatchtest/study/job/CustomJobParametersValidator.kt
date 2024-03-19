package me.study.springbatchtest.study.job

import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersInvalidException
import org.springframework.batch.core.JobParametersValidator

class CustomJobParametersValidator: JobParametersValidator {
    override fun validate(parameters: JobParameters?) {
        println("validation start")

        parameters?.let {
            if(it.getString("name") == null) {
                throw JobParametersInvalidException("name parameters is not found")
            }
        } ?: throw NoSuchElementException()
    }
}