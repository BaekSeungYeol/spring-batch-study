package me.study.springbatchtest.app.batch.job.api

import me.study.springbatchtest.app.batch.classifier.ProcessorClassifier
import me.study.springbatchtest.app.batch.classifier.WriterClassifier
import me.study.springbatchtest.app.batch.domain.ApiRequest
import me.study.springbatchtest.app.batch.domain.ProductRequest
import me.study.springbatchtest.app.batch.partition.ProductPartitioner
import me.study.springbatchtest.app.batch.processor.ApiItemProcessor1
import me.study.springbatchtest.app.batch.processor.ApiItemProcessor2
import me.study.springbatchtest.app.batch.processor.ApiItemProcessor3
import me.study.springbatchtest.app.batch.writer.ApiItemWriter1
import me.study.springbatchtest.app.batch.writer.ApiItemWriter2
import me.study.springbatchtest.app.batch.writer.ApiItemWriter3
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.database.JdbcPagingItemReader
import org.springframework.batch.item.database.Order
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor
import org.springframework.batch.item.support.ClassifierCompositeItemWriter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskExecutor
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import javax.sql.DataSource

@Configuration
class ApiStepConfiguration(
    private val stepBuilderFactory: StepBuilderFactory,
    private val dataSource: DataSource
) {
    private val chunkSize = 10

    @Bean
    fun apiMasterStep(): Step {
        return stepBuilderFactory.get("apiMasterStep")
            .partitioner(apiSlaveStep().name, partitioner())
            .step(apiSlaveStep())
            .gridSize(3)
            .taskExecutor { }
            .build()
    }

    @Bean
    fun taskExecutor(): TaskExecutor {
        val taskExecutor = ThreadPoolTaskExecutor()
        taskExecutor.corePoolSize = 3
        taskExecutor.setQueueCapacity(1000)
        taskExecutor.maxPoolSize = 6
        return taskExecutor
    }
    @Bean
    fun apiSlaveStep(): Step {
        return stepBuilderFactory.get("apiSlaveStep")
            .chunk<ProductRequest, ApiRequest>(chunkSize)
            .reader(itemReader(null))
            .processor(itemProcessor())
            .writer(itemWriter())
            .build()
    }

    @Bean
    fun partitioner(): ProductPartitioner {
        return ProductPartitioner(dataSource)
    }
    @Bean
    fun itemWriter(): ItemWriter<ApiRequest> {
        val writer = ClassifierCompositeItemWriter<ApiRequest>()
        val classifier = WriterClassifier<ApiRequest, ItemWriter<in ApiRequest>>()
        val writerMap = HashMap<String, ItemWriter<ApiRequest>>()
        writerMap["1"] = ApiItemWriter1()
        writerMap["2"] = ApiItemWriter2()
        writerMap["3"] = ApiItemWriter3()

        classifier.writerMap = writerMap

        writer.setClassifier(classifier)
        return writer
    }

    @Bean
    fun itemProcessor(): ItemProcessor<ProductRequest, ApiRequest> {
        val processor = ClassifierCompositeItemProcessor<ProductRequest, ApiRequest>()
        val classifier = ProcessorClassifier<ProductRequest, ItemProcessor<*, out ApiRequest>>()
        val processorMap = HashMap<String, ItemProcessor<ProductRequest, ApiRequest>>()
        processorMap["1"] = ApiItemProcessor1()
        processorMap["2"] = ApiItemProcessor2()
        processorMap["3"] = ApiItemProcessor3()
        classifier.processorMap = processorMap


        processor.setClassifier(classifier)
        return processor
    }

    @Bean
    @StepScope
    fun itemReader(
        @Value("#{stepExecutionContext['product']}") productRequest: ProductRequest?): ItemReader<ProductRequest> {

        if(productRequest == null) {
            throw IllegalArgumentException()
        }

        val reader = JdbcPagingItemReader<ProductRequest>()
        reader.setDataSource(dataSource)
        reader.pageSize = 10
        reader.setRowMapper(BeanPropertyRowMapper(ProductRequest::class.java))

        val queryProvider = MySqlPagingQueryProvider()
        queryProvider.setSelectClause("id, name, price, type")
        queryProvider.setFromClause("from product")
        queryProvider.setWhereClause("where type = :type")

        val sortKeys = HashMap<String, Order>(1)
        sortKeys["id"] = Order.DESCENDING
        queryProvider.sortKeys = sortKeys

        reader.setParameterValues(QueryGenerator.getParameterForQuery("type", productRequest.type))
        reader.setQueryProvider(queryProvider)
        reader.afterPropertiesSet()

        return reader
    }

}