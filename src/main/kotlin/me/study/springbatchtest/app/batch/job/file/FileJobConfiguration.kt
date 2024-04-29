//package me.study.springbatchtest.app.batch.job.file
//
//import me.study.springbatchtest.app.batch.chunk.processor.FileItemProcessor
//import me.study.springbatchtest.app.batch.chunk.processor.ProductFieldSetMapper
//import me.study.springbatchtest.app.batch.domain.Product
//import me.study.springbatchtest.app.batch.domain.ProductRequest
//import org.springframework.batch.core.Job
//import org.springframework.batch.core.Step
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
//import org.springframework.batch.core.configuration.annotation.StepScope
//import org.springframework.batch.item.ItemProcessor
//import org.springframework.batch.item.ItemWriter
//import org.springframework.batch.item.database.builder.JpaItemWriterBuilder
//import org.springframework.batch.item.file.FlatFileItemReader
//import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.core.io.ClassPathResource
//import javax.persistence.EntityManagerFactory
//
//@Configuration
//class FileJobConfiguration(
//    private val jobBuilderFactory: JobBuilderFactory,
//    private val stepBuilderFactory: StepBuilderFactory,
//    private val entityManagerFactory: EntityManagerFactory
//) {
//
//    @Bean
//    fun fileJob(): Job {
//        return jobBuilderFactory.get("fileJob")
//            .start(fileStep())
//            .build()
//    }
//
//    @Bean
//    fun fileStep(): Step {
//        return stepBuilderFactory.get("fileStep")
//            .chunk<ProductRequest, Product>(5)
//            .reader(fileItemReader(null))
//            .processor(fileItemProcessor())
//            .writer(fileItemWriter())
//            .build()
//    }
//
//    @Bean
//    @StepScope
//    fun fileItemReader(@Value("#{jobParameters['requestDate']}") requestDate: String?): FlatFileItemReader<ProductRequest> {
//
//        return FlatFileItemReaderBuilder<ProductRequest>()
//            .name("flatfile")
//            .resource(ClassPathResource("/product_$requestDate.csv"))
//            .fieldSetMapper(BeanWrapperFieldSetMapper())
//            .targetType(ProductRequest::class.java)
//            .linesToSkip(1)
//            .delimited().delimiter(",")
//            .names("id", "name", "price", "type")
//            .build()
//    }
//
//    @Bean
//    fun fileItemProcessor(): ItemProcessor<ProductRequest, Product> {
//        return FileItemProcessor()
//    }
//
//    @Bean
//    fun fileItemWriter(): ItemWriter<Product> {
//        return JpaItemWriterBuilder<Product>()
//            .entityManagerFactory(entityManagerFactory)
//            .usePersist(true)
//            .build()
//    }
//}