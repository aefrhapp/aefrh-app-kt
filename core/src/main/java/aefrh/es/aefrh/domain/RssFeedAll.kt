package aefrh.es.aefrh.domain

import java.nio.channels.Channel
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "rss")
data class RssFeedAll(

//    @field:XmlAttribute(name = "version")
//    val version: String,

//    @field:XmlElement(name = "?xml")
//    val xml: Any,
    @XmlElement(name = "channel")
    val channel: Channel,
    @XmlAttribute(name = "version")
    val version: String
//    @get:XmlElement(name = "version")
//    val version: String


//    @field:XmlElement(name = "channel")
//    val channel: RssChannel

)