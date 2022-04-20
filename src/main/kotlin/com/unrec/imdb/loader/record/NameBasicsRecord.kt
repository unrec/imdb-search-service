package com.unrec.imdb.loader.record

import com.unrec.imdb.loader.entity.Entity
import com.unrec.imdb.loader.entity.NameBasicsEntity

data class NameBasicsRecord(
    var nconst: String = "",
    var primaryName: String = "",
    var birthYear: String = "",
    var deathYear: String = "",
    var primaryProfession: String = "",
    var knownForTitles: String = "",
) : Record {
    override fun toEntity(): Entity {
        return NameBasicsEntity(
            nconst.removeLeadingChars().toInt(),
            primaryName,
            birthYear.asShort(),
            birthYear.asShort(),
            primaryProfession.extractNonEmptyValue(),
            knownForTitles.extractNonEmptyValue()?.removeLeadingChars()
        )
    }
}