package me.study.springbatchtest.study.job

import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
class ExecutionContextTasklet: Tasklet {
    fun test(a1: Int, a2: Int) {

    }
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        println("step1 was executed")

        val jobExecutionContext = contribution.stepExecution.jobExecution.executionContext
        val stepExecutionContext = contribution.stepExecution.executionContext

        val jobName = chunkContext.stepContext.stepExecution.jobExecution.jobInstance.jobName
        val stepName = chunkContext.stepContext.stepExecution.stepName

        if(jobExecutionContext["jobName"] == null) {
            jobExecutionContext.put("jobName", jobName)
        }

        if(stepExecutionContext["stepName"] == null) {
            stepExecutionContext.put("stepName", stepName)
        }

        return RepeatStatus.FINISHED
    }
}