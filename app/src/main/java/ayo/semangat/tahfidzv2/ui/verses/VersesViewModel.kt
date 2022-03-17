package ayo.semangat.tahfidzv2.ui.verses

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ayo.semangat.tahfidzv2.data.TahfidzRepository
import ayo.semangat.tahfidzv2.data.source.remote.response.VersesEntity
import ayo.semangat.tahfidzv2.vo.Resource

class VersesViewModel(
    private val tahfidzRepository: TahfidzRepository
) : ViewModel(){

    lateinit var id: String

    fun  getVerses(): LiveData<Resource<VersesEntity?>> = tahfidzRepository.getVerses(id)
}