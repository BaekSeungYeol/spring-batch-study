//package me.study.springbatchtest.study.chunk
//
//import me.study.springbatchtest.domain.Customer
//import org.springframework.batch.core.Job
//import org.springframework.batch.core.Step
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
//import org.springframework.batch.core.configuration.annotation.JobScope
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
//import org.springframework.batch.item.ItemProcessor
//import org.springframework.batch.item.ItemWriter
//import org.springframework.batch.item.support.ListItemReader
//import org.springframework.batch.repeat.RepeatStatus
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//
//
//@Configuration
//class ChunkOrientedTaskletConfiguration(
//    private val jobBuilderFactory: JobBuilderFactory,
//    private val stepBuilderFactory: StepBuilderFactory
//) {
//
//    @Bean
//    fun job(): Job {
//        return jobBuilderFactory.get("batchJob")
//            .start(step1())
//            .next(step2())
//            .next(step3())
//            .next(step4())
//            .build()
//    }
//
//    @Bean
//    @JobScope
//    fun step1(): Step {
//        return stepBuilderFactory.get("step1")
//            .chunk<String,String>(2)
//            .reader(ListItemReader(listOf("item1","item2","item3","item4","item5","item6")))
//            .processor(ItemProcessor<String, String> { item -> "my_$item" })
//            .writer { items -> items.forEach { println(it) } }
//            .build()
//    }
//
//    @Bean
//    fun step2(): Step {
//        return stepBuilderFactory.get("step2")
//            .tasklet { contribution, chunkContext ->
//                println("step2 has executed")
//                RepeatStatus.FINISHED
//            }
//            .build()
//    }
//
//    @Bean
//    fun step3(): Step {
//        return stepBuilderFactory.get("Step3")
//            .chunk<Customer, Customer>(3)
//            .reader(CustomItemReader(mutableListOf( Customer("name1"), Customer("name2"))))
//            .processor(CustomItemProcessor())
//            .writer(CustomItemWriter())
//            .build()
//    }
//
//    @Bean
//    fun step4(): Step {
//        return stepBuilderFactory.get("Step4")
//            .chunk<String, String>(1)
//            .reader(CustomItemStreamReader(listOf("1","2")))
//            .writer(CustomItemStreamWriter())
//            .build()
//    }
//
//}