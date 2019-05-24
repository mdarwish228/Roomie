package com.darwish.roomie.presentation.group.common

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.darwish.roomie.R

class UserIconViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var userIcon: ImageView = itemView.findViewById(R.id.userIcon)
}