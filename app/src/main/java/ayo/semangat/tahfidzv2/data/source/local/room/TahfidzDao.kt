package ayo.semangat.tahfidzv2.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*

import ayo.semangat.tahfidzv2.data.source.remote.response.ChapterEntity
import ayo.semangat.tahfidzv2.data.source.remote.response.VersesEntity

@Dao
interface TahfidzDao{

    @Query("SELECT * FROM chapterentities")
    fun getChapter(): LiveData<List<ChapterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChapter(chapter: List<ChapterEntity>)

    @Update
    fun updateChapter(chapter: ChapterEntity)

    @Query("SELECT * FROM versesentitites WHERE number = :number")
    fun getVerses(number: String): LiveData<VersesEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVerses(verses: VersesEntity)

    @Update
    fun updateVerses(veres: VersesEntity)

}