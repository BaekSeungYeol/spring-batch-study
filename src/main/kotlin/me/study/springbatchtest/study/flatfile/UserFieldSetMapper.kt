package me.study.springbatchtest.study.flatfile

import org.springframework.batch.item.file.mapping.FieldSetMapper
import org.springframework.batch.item.file.transform.FieldSet

class UserFieldSetMapper: FieldSetMapper<User> {

    override fun mapFieldSet(fieldSet: FieldSet): User {
        return User(
            fieldSet.readString(0),
            fieldSet.readInt(1),
            fieldSet.readInt(2)
        )
    }
}