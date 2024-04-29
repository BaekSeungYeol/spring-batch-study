package me.study.springbatchtest.app.batch.partition

import me.study.springbatchtest.app.batch.job.api.QueryGenerator
import org.springframework.batch.core.partition.support.Partitioner
import org.springframework.batch.item.ExecutionContext
import javax.sql.DataSource

class ProductPartitioner(
    private val datasource: DataSource
): Partitioner {

    override fun partition(gridSize: Int): MutableMap<String, ExecutionContext> {
        val list = QueryGenerator.getProductList(datasource)
        val result = HashMap<String, ExecutionContext>()
        list.forEachIndexed { i, productRequest ->
            val value = ExecutionContext()
            value.put("product", productRequest)
            result["partition$i"] = value
        }
        return result
    }

}