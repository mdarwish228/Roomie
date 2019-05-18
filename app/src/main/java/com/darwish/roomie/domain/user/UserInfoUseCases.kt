package com.darwish.roomie.domain.user

import com.darwish.roomie.data.common.FireStoreResponse
import com.darwish.roomie.data.user.UserApi
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserInfoUseCases() {
    companion object {
        suspend fun getUserInformation(userId: String): FireStoreResponse<DocumentSnapshot> = withContext(
            Dispatchers.Default){
            return@withContext UserApi.get(userId)
        }
    }
}