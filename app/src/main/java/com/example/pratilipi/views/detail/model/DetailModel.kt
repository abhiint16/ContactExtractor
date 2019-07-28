package com.example.pratilipi.views.detail.model

class DetailModel {

    var id: Long = 0
    var displayName: String = ""
    var displayUri: String = ""
    var phone: String = ""
    var email: String = ""


    constructor(id: Long, displayName: String, displayUri: String, phone: String, email: String) {
        this.id = id
        this.displayName = displayName
        this.displayUri = displayUri
        this.phone = phone
        this.email = email
    }

}