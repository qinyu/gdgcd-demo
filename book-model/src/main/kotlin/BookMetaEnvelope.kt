package org.gdgcd.demo.service

import com.google.gson.annotations.Expose

import java.util.ArrayList

public class BookMetaEnvelope : org.gdgcd.demo.service.Page<BookMeta> {

    public var error: String? = null
    public var time: Double? = null
    public var total: String? = null
    public var page: Int? = null
    public var books: List<BookMeta> = ArrayList()

    override fun getContentList(): List<BookMeta> {
        return books
    }
}

