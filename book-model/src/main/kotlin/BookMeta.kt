package org.gdgcd.demo.service

import com.fasterxml.jackson.annotation.JsonProperty

data class BookMeta(val ID: String?, val title: String?, val subTitle: String?, val description: String?, val image: String?, JsonProperty("isbn") val isbn: String?)