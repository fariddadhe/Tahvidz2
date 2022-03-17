package ayo.semangat.tahfidzv2.data.source.local.room

import androidx.lifecycle.LiveData
import ayo.semangat.tahfidzv2.data.source.remote.response.ChapterEntity
import ayo.semangat.tahfidzv2.data.source.remote.response.VersesEntity

class LocalRepository private constructor(private val mTahfidzDao: TahfidzDao){
    companion object{
        private var INSTANCE: LocalRepository? = null

        fun getInstance(tahfidzDao: TahfidzDao): LocalRepository{
            if (INSTANCE == null){
                INSTANCE = LocalRepository(tahfidzDao)
            }
            return INSTANCE as LocalRepository
        }
    }

    fun getChapters(): LiveData<List<ChapterEntity>> =
            mTahfidzDao.getChapter()

    fun insertChapters(chater: List<ChapterEntity>){
        mTahfidzDao.insertChapter(chater)
    }

    fun getVerses(number: String): LiveData<VersesEntity?> =
            mTahfidzDao.getVerses(number)

    fun inserVerses(verses: VersesEntity){
        mTahfidzDao.insertVerses(verses)
    }
}