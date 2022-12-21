package com.sideki.imdb_app.util.recycler

import java.util.UUID

abstract class AdapterItem {
    val id: String = UUID.randomUUID().toString()
}
