//package me.study.springbatchtest.study.flowjob
//
//import org.h2.util.Task
//import org.springframework.batch.core.Job
//import org.springframework.batch.core.Step
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
//import org.springframework.batch.core.configuration.annotation.JobScope
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
//import org.springframework.batch.core.configuration.annotation.StepScope
//import org.springframework.batch.core.job.builder.FlowBuilder
//import org.springframework.batch.core.job.flow.Flow
//import org.springframework.batch.core.step.builder.TaskletStepBuilder
//import org.springframework.batch.core.step.tasklet.Tasklet
//import org.springframework.batch.repeat.RepeatStatus
//import org.springframework.beans.factory.annotation.Value
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
//            .start(flowA())
//            .next(step3())
//            .next(flowB())
//            .next(step6())
//            .end()
//            .build()
//    }
//
//    @Bean
//    fun flowA(): Flow {
//        val flowBuilder = FlowBuilder<Flow>("flowA")
//
//        flowBuilder
//            .start(step1(null))
//            .next(step2())
//            .end()
//
//        return flowBuilder.build()
//
//    }
//
//    @Bean
//    fun flowB(): Flow {
//        val flowBuilder = FlowBuilder<Flow>("flowA")
//
//        flowBuilder
//            .start(step4())
//            .next(step5())
//            .end()
//
//        return flowBuilder.build()
//
//    }
//
//
//
//
//    @Bean
//    @JobScope
//    fun step1(@Value("#{jobParameters['message']}") message: String?): Step {
//
//        println("message = $message")
//        return stepBuilderFactory["flowStep1"]
//            .tasklet { _, _ ->
//                println("step1 executed")
//                RepeatStatus.FINISHED
//            }
//            .build()
//
//    }
//
//
//    @Bean
//    fun step2(): Step {
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
//    fun step3(): Step {
//        return stepBuilderFactory["flowStep3"]
//            .tasklet { _, _ ->
//                println("step3 executed")
//                RepeatStatus.FINISHED
//            }
//            .build()
//
//    }
//
//    @Bean
//    fun step4(): Step {
//        return stepBuilderFactory["flowStep4"]
//            .tasklet { _, _ ->
//                println("step4 executed")
//                RepeatStatus.FINISHED
//            }
//            .build()
//
//    }
//
//    @Bean
//    fun step5(): Step {
//        return stepBuilderFactory["flowStep5"]
//            .tasklet { _, _ ->
//                println("step5 executed")
//                RepeatStatus.FINISHED
//            }
//            .build()
//
//    }
//
//    @Bean
//    fun step6(): Step {
//        return stepBuilderFactory["flowStep6"]
//            .tasklet { _, _ ->
//                println("step6 executed")
//                RepeatStatus.FINISHED
//            }
//            .build()
//
//    }
//
//}
//
