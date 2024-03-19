package me.study.springbatchtest.study.job

import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
class ExecutionContextTasklet4: Tasklet {
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        println("step4 was executed")

        println("name : ${chunkContext.stepContext.stepExecution.jobExecution.executionContext.get("name")}")
        return RepeatStatus.FINISHED
    }
}