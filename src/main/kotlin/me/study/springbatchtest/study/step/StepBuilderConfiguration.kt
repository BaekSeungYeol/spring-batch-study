//package me.study.springbatchtest.study.step
//
//import me.study.springbatchtest.study.*
//import org.springframework.batch.core.Job
//import org.springframework.batch.core.Step
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
//import org.springframework.batch.core.job.builder.FlowBuilder
//import org.springframework.batch.core.job.flow.Flow
//import org.springframework.batch.item.ItemProcessor
//import org.springframework.batch.repeat.RepeatStatus
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//
//@Configuration
//class StepBuilderConfiguration(
//    private val jobBuilderFactory: JobBuilderFactory,
//    private val stepBuilderFactory: StepBuilderFactory,
//) {
//
//
//
//    @Bean
//    fun job(): Job {
//        return jobBuilderFactory["stepJob"]
//            .start(stepBuilderStep1())
//            .next(stepBuilderStep2())
//            .build()
//    }
//    @Bean
//    fun stepBuilderStep1(): Step {
//        return stepBuilderFactory["StepBuilderStep1"]
//            .tasklet { _, _ ->
//                println("Step1 Executed")
//                RepeatStatus.FINISHED
//            }
//            .build()
//
//    }
//
//    @Bean
//    fun stepBuilderStep2(): Step {
//        return stepBuilderFactory["StepBuilderStep2"]
//            .chunk<String,String>(3)
//            .reader { TODO("Not yet implemented") }
//            .processor(ItemProcessor<String, String> { TODO("Not yet implemented") })
//            .writer { TODO("Not yet implemented") }
//            .build()
//    }
//
//    @Bean
//    fun stepBuilderStep3(): Step {
//        return stepBuilderFactory["StepBuilderStep3"]
//            .partitioner(stepBuilderStep1())
//            .gridSize(2)
//            .build()
//    }
//
//    @Bean
//    fun stepBuilderStep4(): Step {
//        return stepBuilderFactory["StepBuilderStep4"]
//            .job(job())
//            .build()
//    }
//
//    @Bean
//    fun stepBuilderStep5(): Step {
//        return stepBuilderFactory["StepBuilderStep5"]
//            .flow(flow())
//            .build()
//    }
//
//    @Bean
//    fun flow(): Flow {
//        val flowBuilder = FlowBuilder<Flow>("flow")
//        flowBuilder.start(stepBuilderStep2()).end()
//        return flowBuilder.build()
//    }
//
//
//
//}
//
