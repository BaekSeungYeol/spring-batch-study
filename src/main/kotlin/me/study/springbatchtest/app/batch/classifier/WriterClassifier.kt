package me.study.springbatchtest.app.batch.classifier

import me.study.springbatchtest.app.batch.domain.ApiRequest
import org.springframework.batch.item.ItemWriter
import org.springframework.classify.Classifier

class WriterClassifier<C,T>: Classifier<C,T> {

    var writerMap = HashMap<String, ItemWriter<ApiRequest>>()

    override fun classify(classifiable: C): T {
        return (writerMap[(classifiable as ApiRequest).productRequest.type]) as T
    }
}