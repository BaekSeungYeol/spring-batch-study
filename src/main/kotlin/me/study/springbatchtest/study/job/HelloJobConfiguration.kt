//package me.study.springbatchtest.study.job
//
//import org.springframework.batch.core.Job
//import org.springframework.batch.core.Step
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
//import org.springframework.batch.core.job.builder.FlowBuilder
//import org.springframework.batch.core.job.flow.Flow
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//
//@Configuration
//class HelloJobConfiguration(
//    private val jobBuilderFactory: JobBuilderFactory,
//    private val stepBuilderFactory: StepBuilderFactory,
//    private val jobRepositoryListener: JobRepositoryListener,
//    private val executionContextTasklet: ExecutionContextTasklet,
//    private val executionContextTasklet2: ExecutionContextTasklet2,
//    private val executionContextTasklet3: ExecutionContextTasklet3,
//    private val executionContextTasklet4: ExecutionContextTasklet4,
//) {
//
//
//
//    @Bean
//    fun helloJob(): Job {
//        return jobBuilderFactory["helloJob"]
//            .validator(CustomJobParametersValidator())
//            .start(helloStep1())
//            .next(helloStep2())
//            .next(helloStep3())
//            .next(helloStep4())
//            .listener(jobRepositoryListener)
//            .incrementer(CustomJobParametersIncrementer())
//            .build()
//    }
//    @Bean
//    fun helloStep1(): Step {
//        return stepBuilderFactory["helloStep1"]
//            .tasklet(executionContextTasklet)
//            .build()
//
//    }
//
//    @Bean
//    fun helloStep2(): Step {
//        return stepBuilderFactory["helloStep2"]
//            .tasklet(executionContextTasklet2)
//            .build()
//
//    }
//    @Bean
//    fun helloStep3(): Step {
//        return stepBuilderFactory["helloStep3"]
//            .tasklet(executionContextTasklet3)
//            .build()
//
//    }
//    @Bean
//    fun helloStep4(): Step {
//        return stepBuilderFactory["helloStep4"]
//            .tasklet(executionContextTasklet4)
//            .build()
//
//    }
//
//    @Bean
//    fun flow(): Flow {
//        val flowBuilder = FlowBuilder<Flow>("flow")
//        flowBuilder
//            .start(helloStep3())
//            .next(helloStep4())
//            .end()
//
//        return flowBuilder.build()
//    }
//
//}
//
