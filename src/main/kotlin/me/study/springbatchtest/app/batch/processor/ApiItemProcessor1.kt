package me.study.springbatchtest.app.batch.processor

import me.study.springbatchtest.app.batch.domain.ApiRequest
import me.study.springbatchtest.app.batch.domain.ProductRequest
import org.springframework.batch.item.ItemProcessor

class ApiItemProcessor1: ItemProcessor<ProductRequest, ApiRequest> {

    override fun process(item: ProductRequest): ApiRequest {
        return ApiRequest(
            id = item.id,
            productRequest = item
        )
    }
}