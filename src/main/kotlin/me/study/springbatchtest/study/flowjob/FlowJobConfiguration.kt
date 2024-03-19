//package me.study.springbatchtest.study.flowjob
//
//import org.springframework.batch.core.Job
//import org.springframework.batch.core.Step
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
//import org.springframework.batch.core.job.builder.FlowBuilder
//import org.springframework.batch.core.job.flow.Flow
//import org.springframework.batch.core.step.tasklet.Tasklet
//import org.springframework.batch.repeat.RepeatStatus
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//
//@Configuration
//class FlowJobConfiguration(
//    private val jobBuilderFactory: JobBuilderFactory,
//    private val stepBuilderFactory: StepBuilderFactory,
//) {
//
//
//
//    @Bean
//    fun flowJob(): Job {
//        return jobBuilderFactory["batchJob"]
//            .start(flowStep1())
//            .on("COMPLETED").to(flowStep3())
//            .from(flowStep1())
//            .on("FAILED").to(flowStep2())
//            .end()
//            .build()
//    }
//
//    @Bean
//    fun flowStep1(): Step {
//        return stepBuilderFactory["flowStep1"]
//            .tasklet { _, _ ->
//                println("step1 executed")
//                RepeatStatus.FINISHED
//            }
//            .build()
//
//    }
//
//    @Bean
//    fun flowStep2(): Step {
//        return stepBuilderFactory["flowStep2"]
//            .tasklet { _, _ ->
//                println("step2 executed")
//                RepeatStatus.FINISHED
//            }
//            .build()
//
//    }
//
//    @Bean
//    fun flowStep3(): Step {
//        return stepBuilderFactory["flowStep3"]
//            .tasklet { _, _ ->
//                println("step3 executed")
//                RepeatStatus.FINISHED
//            }
//            .build()
//
//    }
//
//}
//
