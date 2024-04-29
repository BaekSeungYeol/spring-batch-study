package me.study.springbatchtest.app.batch.domain

data class ProductRequest(
    val id: Long,
    val name: String,
    val price: Int,
    val type: String
) {

    fun toProduct(): Product {
        return Product(
            id = id,
            name = name,
            price = price,
            type = type
        )
    }
}