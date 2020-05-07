package aefrh.es.core.domain

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rdf", strict = false)
data class RssFeedSingle(
    @field:Element(name = "item")
    var item: Noticia? = null
)