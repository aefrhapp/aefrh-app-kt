package aefrh.es.aefrh.domain

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
class Noticia {

    @field:Element(name = "title")
    var title: String? = null

    @field:Element(name = "link")
    var link: String? = null

    @field:Element(name = "pubDate")
    var pubDate: String? = null

    @field:Element(name = "description")
    var description: String? = null

    @field:Element(name = "encoded")
    var content: String? = null

    override fun toString(): String {
        return """Noticia: 
                title: $title
                link: $link
                pubDate: $pubDate
                description: $description
                content: $content
                """
    }

}