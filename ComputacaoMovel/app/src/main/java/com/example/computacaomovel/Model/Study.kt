package com.example.computacaomovel.Model

import kotlin.collections.HashMap
import kotlin.collections.hashMapOf as hashMapOf1

data class Study(val name: String = "") {
    fun toHashMap(): HashMap<String, Comparable<Any>?> {
        val study = hashMapOf1(
            "name" to name
        )
        return study as HashMap<String, Comparable<Any>?>
    }

    companion object {
        fun fromHashMap(hash: Map<String, Any>): Study = Study(
            name = hash["name"] as String
        )
    }
}