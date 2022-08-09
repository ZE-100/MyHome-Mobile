package com.myhome.blueprint

data class Account(
    var email: String?,
    var password: String?,
    var token: String?,
) {
    constructor() : this("", "", "")

    fun isSet(): Boolean {
        return email != null
                && email!!.isNotEmpty()
                && password != null
                && password!!.isNotEmpty()
                && token != null
                && token!!.isNotEmpty()
    }
}
