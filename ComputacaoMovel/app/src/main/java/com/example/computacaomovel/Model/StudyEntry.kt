package com.example.computacaomovel.Model

data class StudyEntry(var name: String = "", var storeName: String = "", var value: String = "") {
    fun toHashMap(): HashMap<String, Comparable<Any>?> {
        val studyEntry = hashMapOf(
            "name" to name,
            "storeName" to storeName,
            "value" to value
        )
        return studyEntry as HashMap<String, Comparable<Any>?>
    }

    companion object {
        fun fromHashMap(hash: Map<String, Any>): StudyEntry {
            var studyEntry = StudyEntry();
            studyEntry.name = hash["name"] as String
            studyEntry.storeName = hash["storeName"] as String
            studyEntry.value = hash["storeName"] as String
            return studyEntry
        }
    }
}