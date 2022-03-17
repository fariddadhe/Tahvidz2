package ayo.semangat.tahfidzv2.ui.chapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import ayo.semangat.tahfidzv2.data.TahfidzRepository
import ayo.semangat.tahfidzv2.data.source.remote.response.ChapterEntity
import ayo.semangat.tahfidzv2.vo.Resource

class ChapterViewModel(
        private val tahfidzRepository: TahfidzRepository
) : ViewModel(){
    fun getChapter(): LiveData<Resource<List<ChapterEntity>?>> = tahfidzRepository.getChapters()
}