package aefrh.es.core.domain

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

@Root(name = "item", strict = false)
data class Noticia(
    @field:Element(name = "post-id", required = false)
    var id: String = "",
    @field:Element(name = "title", required = true)
    var title: String = "",
    @field:Element(name = "link", required = true)
    var link: String = "",
    @field:Element(name = "pubDate", required = false)
    var pubDate: String = "",
    @field:Element(name = "date", required = false)
    var date: String = "",
    @field:Element(name = "description", required = true)
    var description: String = "",
    @field:Element(name = "encoded", required = true)
    var content: String = ""
) {

    override fun toString(): String {
        return """Noticia:
                id: $id
                title: $title
                link: $link
                pubDate: $pubDate
                description: $description
                content: $content
                """
    }

    fun getImage(): String {
        var result = ""
        val content = this.content
        if (content.contains("data-orig-file")) {
            val pattern = Pattern.compile("data-orig-file=\"(.+?)\\?")
            val matcher = pattern.matcher(content)
            while (matcher.find()) {
                result = content.substring(matcher.start(), matcher.end())
                    .replace("data-orig-file=\"", "").replace("?", "")
            }
        }
        return result
    }

    fun getListDate(): String {
        val date = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US).parse(this.pubDate)
        return SimpleDateFormat("dd MMMM yyyy", Locale("es", "ES")).format(date)
    }

    fun getSingleDate(): String {
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).parse(this.date)
        return SimpleDateFormat("dd MMMM yyyy", Locale("es", "ES")).format(date)
    }

}