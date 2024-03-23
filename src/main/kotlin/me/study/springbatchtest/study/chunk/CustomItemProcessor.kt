package me.study.springbatchtest.study.chunk

import me.study.springbatchtest.domain.Customer
import org.springframework.batch.item.ItemProcessor

class CustomItemProcessor: ItemProcessor<Customer, Customer> {

    override fun process(customer: Customer): Customer? {
        return customer.copy(
            name = customer.name.uppercase()
        )
    }
}