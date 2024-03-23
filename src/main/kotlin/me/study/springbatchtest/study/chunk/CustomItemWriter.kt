package me.study.springbatchtest.study.chunk

import me.study.springbatchtest.domain.Customer
import org.springframework.batch.item.ItemWriter

class CustomItemWriter: ItemWriter<Customer> {

    override fun write(items: MutableList<out Customer>) {
        items.forEach {
            println(it)
        }
    }
}