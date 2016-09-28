package org.gdgcd.demo.service


import com.fasterxml.jackson.annotation.JsonProperty

import java.util.ArrayList

class BookMetaEnvelope : Page<BookMeta> {
    @JsonProperty("Error")
    var errorNo: String = ""

    @JsonProperty("Time")
    var responseTime: Float? = null

    @JsonProperty("Total")
    var totalCount: String = ""

    @JsonProperty("Page")
    var pageNo: Int? = null

    @JsonProperty("Books")
    var bookList: List<BookMeta> = ArrayList()

    override fun getContentList(): List<BookMeta> {
        return bookList
    }
}
