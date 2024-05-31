package com.github.faening.engsofttmdb.domain.enumeration

@Suppress("unused")
enum class LanguageEnum(val code: String) {
    PORTUGUESE("pt"),
    BRAZILIAN_PORTUGUESE("pt-br"),
    ENGLISH("en"),
    SPANISH("es"),
    FRENCH("fr"),
    GERMAN("de"),
    ITALIAN("it"),
    JAPANESE("ja"),
    KOREAN("ko"),
    MANDARIN("zh"),
    RUSSIAN("ru");

    companion object {
        fun fromCode(code: String): LanguageEnum {
            return entries.first { it.code == code }
        }
    }
}