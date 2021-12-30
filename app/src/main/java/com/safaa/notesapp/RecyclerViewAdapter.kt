package com.safaa.notesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.safaa.notesapp.Data.Note
import com.safaa.notesapp.databinding.RowRecyclerviewBinding


class RecyclerViewAdapter(private var noteList: List<Note>, private val activity: MainActivity) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
    class RecyclerViewHolder(val binding: RowRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root)


    fun setNotesList(notesList: List<Note>) {
        this.noteList = notesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(RowRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        animation(holder)
        var currentNote = noteList[position]

        holder.binding.apply {
            titleTV.text = currentNote.text

            cardLayout.setOnClickListener {
                activity.selectedItem = currentNote
                activity.showDialog(currentNote.text)
            }
        }
    }

    override fun getItemCount() = noteList.size

    fun removeItem(holder: RecyclerView.ViewHolder) {
        activity.deletedNote = noteList[holder.adapterPosition]
    }

    private fun animation(holder: RecyclerViewHolder) {
        val anim = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.animation)
        holder.itemView.startAnimation(anim)
    }
}
