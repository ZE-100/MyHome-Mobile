package com.myhome.custom.adapter.room

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myhome.R
import com.myhome.blueprint.Room
import androidx.navigation.Navigation.findNavController
import com.myhome.fragment.RoomViewFragmentDirections

class RoomsViewAdapter(private val context: Context?, roomsIn: List<Room>) :
    RecyclerView.Adapter<RoomViewHolder>() {

    private val rooms: List<Room> = roomsIn
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    private var avatars = intArrayOf(
        R.drawable.avatar_0, R.drawable.avatar_1, R.drawable.avatar_2,
        R.drawable.avatar_3, R.drawable.avatar_4, R.drawable.avatar_5,
        R.drawable.avatar_6, R.drawable.avatar_7, R.drawable.avatar_8,
        R.drawable.avatar_9, R.drawable.avatar_10, R.drawable.avatar_11,
        R.drawable.avatar_12, R.drawable.avatar_13
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {

        val recyclerViewItem = inflater.inflate(
            R.layout.fragment_room_view_item, parent, false)

        recyclerViewItem.setOnClickListener {
            v -> onClick(parent as RecyclerView, v, context)
        }

        return RoomViewHolder(recyclerViewItem)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room: Room = rooms[position]

        holder.titleView.text = room.title
        holder.thumbnailView.setImageResource(avatars[room.image])
        holder.descriptionView.text = room.description
        holder.notesView.text = room.notes
    }

    override fun getItemCount(): Int {
        return rooms.size
    }

    private fun onClick(recyclerView: RecyclerView, itemView: View, context: Context?) {
        val itemPos = recyclerView.getChildLayoutPosition(itemView)

        findNavController(itemView).navigate(RoomViewFragmentDirections.roomsToRoom()
            .setItemPos(itemPos)
            .setBackButton(R.id.room_to_rooms)
        )
    }
}