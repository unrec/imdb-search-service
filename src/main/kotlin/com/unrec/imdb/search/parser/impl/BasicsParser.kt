package com.unrec.imdb.search.parser.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.unrec.imdb.search.exception.FileParseException
import com.unrec.imdb.search.model.BasicsRecord
import com.unrec.imdb.search.parser.FileParser
import org.springframework.stereotype.Component
import java.io.InputStream

@Component
class BasicsParser(
    private val basicsMapper: ObjectMapper,
    private val basicsSchema: CsvSchema
) : FileParser<BasicsRecord> {

    private val headers =
        "tconst\ttitleType\tprimaryTitle\toriginalTitle\tisAdult\tstartYear\tendYear\truntimeMinutes\tgenres\n"

    override fun parseRecords(data: String): List<BasicsRecord> {
        return try {
            basicsMapper.readerFor(BasicsRecord::class.java)
                .with(basicsSchema)
                .readValues<BasicsRecord>(data)
                .readAll()
        } catch (e: MissingKotlinParameterException) {
            throw FileParseException("Wrong format of input data: ${e.message}")
        }
    }

    override fun parseRecords(data: InputStream): List<BasicsRecord> {
        return try {
            basicsMapper.readerFor(BasicsRecord::class.java)
                .with(basicsSchema)
                .readValues<BasicsRecord>(data)
                .readAll()
        } catch (e: MissingKotlinParameterException) {
            throw FileParseException("Wrong format of input data: ${e.message}")
        }
    }
}