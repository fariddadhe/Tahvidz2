package ayo.semangat.tahfidzv2.ui.verses

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ayo.semangat.tahfidzv2.R
import ayo.semangat.tahfidzv2.data.source.remote.response.VersesItem
import kotlinx.android.synthetic.main.item_verse.view.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class VersesAdapter internal constructor(): RecyclerView.Adapter<VersesAdapter.VerseViewHolder>(){
    private val listVerse = ArrayList<VersesItem>()

    internal fun setVerses(verse: List<VersesItem>?){
        if (verse == null)return
        this.listVerse.clear()
        this.listVerse.addAll(verse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerseViewHolder {
        return VerseViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_verse,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VerseViewHolder, position: Int) {
        val verse = listVerse[position]
        holder.bind(verse)
    }

    override fun getItemCount(): Int = listVerse.size

    inner class VerseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(verse: VersesItem?){
            val num = verse?.number?.inSurah
            val nfa: NumberFormat = NumberFormat.getInstance(Locale.forLanguageTag("ar"))
            val temp = "  "+"\u06dd"+nfa.format(num)+" "
            itemView.tvArabic.text = "${verse?.text?.arab} ﴿${nfa.format(num)}﴾"
            itemView.tvArabic.typeface = Typeface.createFromAsset(
                itemView.context.assets,
                "font/noorehira.ttf"
            )
            itemView.tvTransliteration.text = "$num. ${verse?.text?.transliteration?.en}"
            itemView.tvTerjemahan.text = "$num. ${verse?.translation?.id}"
        }
    }
}