package me.study.springbatchtest.study.chunk

import org.springframework.batch.item.ExecutionContext
import org.springframework.batch.item.ItemStreamWriter

class CustomItemStreamWriter: ItemStreamWriter<String> {

    override fun open(executionContext: ExecutionContext) {
        println("open")
    }

    override fun update(executionContext: ExecutionContext) {
        println("update")
    }

    override fun close() {
        println("close")
    }

    override fun write(items: MutableList<out String>) {
        items.forEach {
            println(it)
        }
    }
}