package ayo.semangat.tahfidzv2.ui.chapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ayo.semangat.tahfidzv2.R
import ayo.semangat.tahfidzv2.data.source.remote.response.ChapterEntity
import ayo.semangat.tahfidzv2.ui.verses.VersesActivity
import kotlinx.android.synthetic.main.item_chapter.view.*

class ChapterAdapter internal constructor(): RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder>(){
    private val listChapter = ArrayList<ChapterEntity>()

    internal fun setChapters(chapter: List<ChapterEntity>?){
        if (chapter == null)return
        this.listChapter.clear()
        this.listChapter.addAll(chapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        return ChapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_chapter, parent,false))
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        val chapter = listChapter[position]
        holder.bind(chapter)
    }

    override fun getItemCount(): Int = listChapter.size

    inner class ChapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(chapt: ChapterEntity){
            itemView.tvNumber.text = chapt.chapterId
            itemView.tvName.text = chapt.name.jsonMemberShort
            itemView.tvAyat.text = chapt.name.transliteration.id
            itemView.tvInfo.text = "${chapt.name.translation.id} - ${chapt.numberOfVerses} AYAT"
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, VersesActivity::class.java)
                intent.putExtra(VersesActivity.EXTRA_ID, chapt.chapterId)
                itemView.context.startActivity(intent)
            }
        }
    }
}