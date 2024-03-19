package me.study.springbatchtest.study.job//package me.study.springbatchtest

import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer
import org.springframework.boot.autoconfigure.batch.BatchProperties
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class CustomBatchConfigurer(
    private val properties: BatchProperties,
    private val dataSource: DataSource,
    private val transactionManagerCustomizers: TransactionManagerCustomizers
) : BasicBatchConfigurer(properties, dataSource, transactionManagerCustomizers) {

    override fun createJobRepository(): JobRepository {
        val factory = JobRepositoryFactoryBean()
        factory.setDataSource(dataSource)
        return super.createJobRepository()
    }
}