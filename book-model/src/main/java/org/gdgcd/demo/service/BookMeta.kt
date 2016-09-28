package org.gdgcd.demo.service

import com.fasterxml.jackson.annotation.JsonProperty

class BookMeta {
    @JsonProperty("ID") var id: String = ""

    @JsonProperty("Title") var title: String = ""

    @JsonProperty("SubTitle")
    var subTitle: String = ""

    @JsonProperty("Description")
    var description: String = ""

    @JsonProperty("Image")
    var imageUrl: String = ""
    var isbn: String = ""

}
