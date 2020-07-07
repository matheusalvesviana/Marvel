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

class CharacthersListAdapter(private val items: List<Result>) :
    RecyclerView.Adapter<CharacterViewHolder>() {

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {

        Picasso.get().load(items[position].resourceURI)
            .placeholder(R.drawable.ic_launcher_background)
            .resize(600, 200)
            .centerCrop()
            .into(holder.imgCharacter)

        holder.tvName.text = items[position].name
//        holder.itemView.setOnClickListener { view ->
//            val intent =
//                Intent(view.context.applicationContext, GameActivity::class.java)
//            intent.putExtra("game_image", dataList.get(position).getGame().getBox().getLarge())
//            intent.putExtra("game_name", dataList.get(position).getGame().getName())
//            intent.putExtra(
//                "game_pop",
//                String.valueOf(dataList.get(position).getGame().getPopularity())
//            )
//            view.context.applicationContext.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View =
            layoutInflater.inflate(R.layout.characther_item, parent, false)
        return CharacterViewHolder(view)
    }
}

class CharacterViewHolder(itemView: View) : ViewHolder(itemView) {
    var imgCharacter: ImageView = itemView.findViewById<View>(R.id.iv_character) as ImageView
    var tvName: TextView = itemView.findViewById<View>(R.id.tv_name) as TextView
}