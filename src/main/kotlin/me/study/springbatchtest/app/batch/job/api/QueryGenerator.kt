package me.study.springbatchtest.app.batch.job.api

import ProductRowMapper
import me.study.springbatchtest.app.batch.domain.ProductRequest
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

object QueryGenerator {

    fun getProductList(dataSource: DataSource): List<ProductRequest> {
        val jdbcTemplate = JdbcTemplate(dataSource)
        return jdbcTemplate.query("select type from product group by type", ProductRowMapper())
    }

    fun getParameterForQuery(param: String, value: String): MutableMap<String, Any> {
        val parameters = HashMap<String, Any>()
        parameters[param] = value
        return parameters
    }
}