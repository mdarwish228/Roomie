package com.darwish.roomie.data.user

import com.darwish.roomie.data.common.FireStoreResponse
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class UserApi() {
    companion object {
        fun get(userId: String): FireStoreResponse<DocumentSnapshot> {
            try {
                val user = Tasks.await(
                    FirebaseFirestore.getInstance()
                        .collection("users")
                        .document(userId)
                        .get()
                )

                return if (user.exists()) {
                    FireStoreResponse(
                        200, "Successfully retrieved user data", user
                    )
                } else {
                    FireStoreResponse(
                        404, "User not found", null
                    )
                }
            } catch (exception: Throwable) {
                return FireStoreResponse(
                    500, exception.message, null
                )
            }
        }
    }
}