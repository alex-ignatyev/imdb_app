package com.sideki.imdb_app.model.model

import com.sideki.imdb_app.util.recycler.AdapterItem
import java.util.UUID

data class MoviesGroupTitleModel(
    override var id: String = UUID.randomUUID().toString(),
    val titleName: String
) : AdapterItem()
