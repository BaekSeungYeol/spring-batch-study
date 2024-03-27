package me.study.springbatchtest.study.chunk

import org.springframework.batch.item.ExecutionContext
import org.springframework.batch.item.ItemStreamReader

class CustomItemStreamReader(
    private val list: List<String>,
    private var index: Int = 0,
    private var restart: Boolean = false,
): ItemStreamReader<String> {

    override fun open(executionContext: ExecutionContext) {
        if(executionContext.containsKey("index")) {
            index = executionContext.getInt("index")
            this.restart = true
        } else {
            index = 0
            executionContext.put("index", index)
        }
    }

    override fun update(executionContext: ExecutionContext) {
        executionContext.put("index", index)
    }

    override fun close() {
        println("Close")
    }

    override fun read(): String? {

        var item: String? = null

        if(this.index < this.list.size) {
            item = this.list[index]
            index++
        }

        if(this.index == 6 && !restart) {
            throw IllegalStateException("restart is required")
        }

        return item
    }
}