package com.sayazhan.uiparser.models

data class Element(
    val tag: String,
    val children: List<Element>?
)
