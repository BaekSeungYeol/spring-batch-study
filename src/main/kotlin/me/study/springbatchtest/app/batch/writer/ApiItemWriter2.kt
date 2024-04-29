package me.study.springbatchtest.app.batch.writer

import me.study.springbatchtest.app.batch.domain.ApiRequest
import org.springframework.batch.item.ItemWriter

class ApiItemWriter2: ItemWriter<ApiRequest> {

    override fun write(items: MutableList<out ApiRequest>) {

    }
}