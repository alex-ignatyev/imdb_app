package com.sideki.imdb_app.domain.model

import com.sideki.imdb_app.util.recycler.AdapterItem
import java.util.UUID

data class MoviesGroupTitleModel(
    val titleName: String,
    override var id: String =  UUID.randomUUID().toString()
): AdapterItem()
