package me.study.springbatchtest.app.batch.processor

import me.study.springbatchtest.app.batch.domain.Product
import me.study.springbatchtest.app.batch.domain.ProductRequest
import org.springframework.batch.item.ItemProcessor
class FileItemProcessor: ItemProcessor<ProductRequest, Product> {

    override fun process(item: ProductRequest): Product {
        return item.toProduct()
    }
}