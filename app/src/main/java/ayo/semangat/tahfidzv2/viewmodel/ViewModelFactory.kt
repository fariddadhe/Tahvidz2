package ayo.semangat.tahfidzv2.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ayo.semangat.tahfidzv2.data.TahfidzRepository
import ayo.semangat.tahfidzv2.di.Injection
import ayo.semangat.tahfidzv2.ui.chapter.ChapterViewModel
import ayo.semangat.tahfidzv2.ui.verses.VersesViewModel

class ViewModelFactory private constructor(private val mTahfidzRepository: TahfidzRepository): ViewModelProvider.NewInstanceFactory(){

    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context, req: String): ViewModelFactory =
                instance ?: synchronized(this){
                    instance ?: ViewModelFactory(Injection.providerRepository(context, req))
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(ChapterViewModel::class.java) -> {
                ChapterViewModel(mTahfidzRepository) as T
            }
            modelClass.isAssignableFrom(VersesViewModel::class.java) -> {
                VersesViewModel(mTahfidzRepository) as T
            }
            else-> throw Throwable("Unkown ViewModel class: " + modelClass.name)
        }
    }
}