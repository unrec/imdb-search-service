package com.unrec.imdb.search.config.batch

data class BatchSettings(
    val reader: String,
    val processor: String,
    val writer: String,
    val step: String,
    val job: String
)

