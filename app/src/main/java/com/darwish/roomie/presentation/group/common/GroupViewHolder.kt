package com.darwish.roomie.presentation.group.common

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.darwish.roomie.R


class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var groupName: TextView

    init {
        groupName = itemView.findViewById(R.id.groupName)
    }
}