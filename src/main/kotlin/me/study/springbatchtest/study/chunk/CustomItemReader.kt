package me.study.springbatchtest.study.chunk

import me.study.springbatchtest.domain.Customer
import org.springframework.batch.item.ItemReader

class CustomItemReader(
    private val list: MutableList<Customer>
): ItemReader<Customer> {

    override fun read(): Customer? {
        if(list.isNotEmpty()) {
            return list.removeAt(0)
        }

        return null
    }
}