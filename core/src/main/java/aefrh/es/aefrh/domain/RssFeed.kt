package aefrh.es.aefrh.domain

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
class RssFeed {
    @field:Element(name = "channel")
    var channel: FeedChannel? = null
    override fun toString(): String {
        return "$channel"
    }
}

@Root(name = "channel", strict = false)
class FeedChannel {
    @field:ElementList(entry = "item", inline = true)
    var itemList: List<Noticia>? = null
    override fun toString(): String {
        return "$itemList"
    }
}