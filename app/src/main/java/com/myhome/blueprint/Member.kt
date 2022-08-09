package com.myhome.blueprint

data class Member(
    var id: Long,
    var name: String,
    var icon: Int,
) {
    constructor() : this(-1L, "", -1)

    fun isSet(): Boolean {
        return name.isEmpty()
    }
}