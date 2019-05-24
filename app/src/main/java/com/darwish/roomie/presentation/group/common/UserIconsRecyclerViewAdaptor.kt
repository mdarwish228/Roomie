package com.darwish.roomie.presentation.group.common

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.darwish.roomie.R
import com.google.firebase.storage.FirebaseStorage

class UserIconsRecyclerViewAdaptor
internal constructor(private val context: Context, private val userIds: List<String>, private val firebaseStorage: FirebaseStorage) :
    RecyclerView.Adapter<UserIconViewHolder>() {

    override fun onCreateViewHolder(userIcon: ViewGroup, viewType: Int): UserIconViewHolder {
        val view = LayoutInflater.from(userIcon.context)
            .inflate(R.layout.user_icon, userIcon, false)
        return UserIconViewHolder(view)
    }

    override fun onBindViewHolder(userIconViewHolder: UserIconViewHolder, position: Int) {
        val storageReference = firebaseStorage.reference
        val icon = storageReference.child( userIds[position]+ "/icon.JPG")

        Glide.with(context)
            .load(icon)
            .thumbnail(0.1f)
            .apply(RequestOptions.circleCropTransform())
            .into(userIconViewHolder.userIcon)
    }

    override fun getItemCount(): Int {
        return userIds.size
    }
}