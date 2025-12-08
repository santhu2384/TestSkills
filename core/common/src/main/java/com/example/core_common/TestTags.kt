package com.example.core_common

object TestTags
{
    const val LOADING = "loading_indicator"
    const val ERROR = "error_message"

    const val SECTION = "section"
    fun section(title: String) = "${SECTION}_$title"
}