package aefrh.es.core.domain

data class ContactoItem(
    val drawable: Int,
    val text: String,
    val content: String,
    val case: ContactoCase
)

enum class ContactoCase {
    PHONE,
    EMAIL,
    WEB
}