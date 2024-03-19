package me.study.springbatchtest.study.job

import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
class ExecutionContextTasklet2: Tasklet {
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        println("step2 was executed")

        val jobExecutionContext = contribution.stepExecution.jobExecution.executionContext
        val stepExecutionContext = contribution.stepExecution.executionContext

        println("jobName = ${jobExecutionContext.get("jobName")}")
        println("stepName = ${stepExecutionContext.get("stepName")}") // 공유 안됨

        val stepName = chunkContext.stepContext.stepExecution.stepName
        if(stepExecutionContext.get("stepName") == null) {
            stepExecutionContext.put("stepName", stepName)
        }
        return RepeatStatus.FINISHED
    }
}