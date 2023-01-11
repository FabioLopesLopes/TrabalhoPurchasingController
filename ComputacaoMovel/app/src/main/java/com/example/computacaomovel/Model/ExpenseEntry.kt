package com.example.computacaomovel.Model

data class ExpenseEntry(val name: String = "",val value: String = "",val description: String = "",val month: String = "") {
    fun toHashMap(): HashMap<String, Comparable<Any>?> {
        val ExpenseEntry = hashMapOf(
            "name" to name,
            "value" to value,
            "description" to description,
            "month" to month
        )
        return ExpenseEntry as HashMap<String, Comparable<Any>?>
    }

    companion object {
        fun fromHashMap(hash: Map<String, Any>): ExpenseEntry = ExpenseEntry(
            name = hash["name"] as String,
            value = hash["value"] as String,
            description = hash["description"] as String,
            month = hash["month"] as String
        )
    }
}