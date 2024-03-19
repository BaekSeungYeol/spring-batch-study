package me.study.springbatchtest.study.job

import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
class ExecutionContextTasklet3: Tasklet {
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        println("step3 was executed")

        val name = chunkContext.stepContext.stepExecution.jobExecution.executionContext.get("name")

        if(name == null) {
            chunkContext.stepContext.stepExecution.jobExecution.executionContext.put("name", "user1")
//            throw IllegalArgumentException("step3 failed")
        }
        return RepeatStatus.FINISHED
    }
}