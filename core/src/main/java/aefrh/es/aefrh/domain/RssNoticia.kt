package aefrh.es.aefrh.domain

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "item")
data class RssNoticia(
//    @SerializedName("post-id")
//    val id: int,
    @get:XmlElement(name = "title")
    val title: String,
    @get:XmlElement(name = "pubDate")
    val pubDate: String,
    @get:XmlElement(name = "description")
    val description: String
)