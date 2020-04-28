package aefrh.es.aefrh.domain

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "rss")
data class RssFeed(

//    @field:XmlAttribute(name = "version")
//    val version: String,

//    val title: String
//
//    @field:XmlAttribute(name = "title")
//val title: String

    @get:XmlElement(name = "channel")
    val channel: RssChannel

)