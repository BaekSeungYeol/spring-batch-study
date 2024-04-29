package me.study.springbatchtest.app.batch.processor

import me.study.springbatchtest.app.batch.domain.Product
import me.study.springbatchtest.app.batch.domain.ProductRequest
import org.springframework.batch.item.file.mapping.FieldSetMapper
import org.springframework.batch.item.file.transform.FieldSet

class ProductFieldSetMapper: FieldSetMapper<ProductRequest> {


    override fun mapFieldSet(fieldSet: FieldSet): ProductRequest {
        return ProductRequest(
            fieldSet.readLong("id"),
            fieldSet.readString("name"),
            fieldSet.readInt("price"),
            fieldSet.readString("type"),
        )
    }
}