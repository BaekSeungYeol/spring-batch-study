package me.study.springbatchtest.app.batch.classifier

import me.study.springbatchtest.app.batch.domain.ApiRequest
import me.study.springbatchtest.app.batch.domain.ProductRequest
import org.springframework.batch.item.ItemProcessor
import org.springframework.classify.Classifier

class ProcessorClassifier<C,T>: Classifier<C,T> {

    var processorMap = HashMap<String, ItemProcessor<ProductRequest, ApiRequest>>()

    override fun classify(classifiable: C): T {
        return (processorMap[(classifiable as ProductRequest).type]) as T
    }
}