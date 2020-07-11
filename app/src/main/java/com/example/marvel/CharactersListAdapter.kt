package com.example.marvel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.marvel.model.Result
import com.squareup.picasso.Picasso

class CharactersListAdapter(private val items: List<Result>) :
    RecyclerView.Adapter<CharacterViewHolder>() {

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = items[position]

        val imageUrl = character.thumbnail.path.plus(".${character.thumbnail.extension}")

        Picasso.get().load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.imgCharacter)

        holder.tvName.text = character.name
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View =
            layoutInflater.inflate(R.layout.character_item, parent, false)
        return CharacterViewHolder(view)
    }
}

class CharacterViewHolder(itemView: View) : ViewHolder(itemView) {
    var imgCharacter: ImageView = itemView.findViewById<View>(R.id.iv_character) as ImageView
    var tvName: TextView = itemView.findViewById<View>(R.id.tv_name) as TextView
}