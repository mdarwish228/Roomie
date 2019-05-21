package com.darwish.roomie.presentation.group.common

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.darwish.roomie.R


class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var groupName: TextView = itemView.findViewById(R.id.groupName)
    var settingsIcon: ImageView = itemView.findViewById(R.id.settingsIcon)
}