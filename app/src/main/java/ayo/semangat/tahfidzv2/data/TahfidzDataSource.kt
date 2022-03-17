package ayo.semangat.tahfidzv2.data.source

import androidx.lifecycle.LiveData
import ayo.semangat.tahfidzv2.data.source.remote.response.ChapterEntity
import ayo.semangat.tahfidzv2.data.source.remote.response.VersesEntity
import ayo.semangat.tahfidzv2.vo.Resource

interface TahfidzDataSource {

    fun getChapters(): LiveData<Resource<List<ChapterEntity>?>>

    fun getVerses(verse: String): LiveData<Resource<VersesEntity?>>
}