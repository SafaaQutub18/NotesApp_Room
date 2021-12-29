package com.safaa.notesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.safaa.notesapp.databinding.RowRecyclerviewBinding


class RecyclerViewAdapter(private val activity : MainActivity): RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
    class RecyclerViewHolder(val binding: RowRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

    var noteList: ArrayList<Note> = ArrayList()

    fun setNotesList(notesList: ArrayList<Note>) {
        this.noteList = notesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(RowRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        var currentNote = noteList[position]

        holder.binding.apply {
          // cardLayout.setBackgroundResource(R.drawable.custom_button);
           titleTV.text = currentNote.text

            cardLayout.setOnClickListener {
                activity.selectedItem = currentNote
                activity.showDialog(currentNote.text)
            }

        }
    }
    override fun getItemCount() = noteList.size


    fun removeItem(holder: RecyclerView.ViewHolder ) {
        activity.deletedNote = noteList[holder.adapterPosition]
        noteList.removeAt(holder.adapterPosition)
        notifyItemRemoved(holder.adapterPosition)

    }
}
