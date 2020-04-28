package aefrh.es.aefrh.domain

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "channel")
data class RssChannel(
    @get:XmlElement(name = "title")
    val title: String
//    @field:XmlElement(name = "item")
//    val noticiasList: List<RssNoticia>
)