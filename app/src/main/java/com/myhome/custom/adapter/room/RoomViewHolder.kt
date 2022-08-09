package com.myhome.custom.adapter.room

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myhome.R

class RoomViewHolder(roomCard: View) : RecyclerView.ViewHolder(roomCard) {

    var titleView: TextView = roomCard.findViewById(R.id.roomTitle) as TextView
    var descriptionView: TextView = roomCard.findViewById(R.id.roomDescription) as TextView
    var thumbnailView: ImageView = roomCard.findViewById(R.id.roomThumbnail) as ImageView
    var notesView: TextView = roomCard.findViewById(R.id.roomNotes) as TextView
}