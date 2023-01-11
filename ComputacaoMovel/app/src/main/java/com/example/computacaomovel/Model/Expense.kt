package com.example.computacaomovel.Model
import kotlin.collections.HashMap
import kotlin.collections.hashMapOf as hashMapOf1



data class Expense (val name: String = "", val month: String = "1"){


    fun toHashMap() : HashMap<String, Comparable<Any>?> {
        val expense = hashMapOf1(
            "name" to name,
            "month" to month

        )

        return expense as HashMap<String, Comparable<Any>?>
    }

    companion object {
        fun fromHashMap(hash: Map<String, Any>): Expense = Expense(
            name = hash["name"] as String,
            month = hash["month"] as String,
        )
    }
}