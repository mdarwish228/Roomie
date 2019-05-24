package com.darwish.roomie.data.group

import java.util.*

class Group {

    var id: String? = null

    var group_name: String? = null

    var group_members: List<String>? = null

    var last_modified: Date? = null

    var description: String? = null

    constructor() {} // Needed for Firebase

    constructor(id: String, groupName: String, groupMembers: List<String>, lastModified: Date, description: String) {
        this.id = id
        this.group_name = groupName
        this.group_members = groupMembers
        this.last_modified = lastModified
        this.description = description
    }
}