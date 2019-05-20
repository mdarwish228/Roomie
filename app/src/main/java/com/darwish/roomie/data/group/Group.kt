package com.darwish.roomie.data.group

import java.util.*

class Group {
    var group_name: String? = null

    var group_members: List<String>? = null

    var last_modified: Date? = null

    constructor() {} // Needed for Firebase

    constructor(groupName: String, groupMembers: List<String>, lastModified: Date) {
        this.group_name = groupName
        this.group_members = groupMembers
        this.last_modified = lastModified
    }
}