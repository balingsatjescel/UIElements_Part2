package com.example.uiep2.models

class Song (var id: Int = 0, var title: String, var author: String, var album: String) {
    override fun toString(): String {
        return "Title: ${title}, Author: ${author} Album: ${album}"
    }
}