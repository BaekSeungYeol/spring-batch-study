package me.study.springbatchtest.app.batch.tasklet

import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus

class ApiStartTasklet: Tasklet {

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        println(" >> ApiService is started ")
        return RepeatStatus.FINISHED
    }
}