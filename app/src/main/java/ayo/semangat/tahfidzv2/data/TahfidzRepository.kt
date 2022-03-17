package ayo.semangat.tahfidzv2.data

import androidx.lifecycle.LiveData
import ayo.semangat.tahfidzv2.data.source.TahfidzDataSource
import ayo.semangat.tahfidzv2.data.source.local.room.LocalRepository
import ayo.semangat.tahfidzv2.data.source.remote.ApiResponse
import ayo.semangat.tahfidzv2.data.source.remote.RemoteRepository
import ayo.semangat.tahfidzv2.data.source.remote.response.ChapterEntity
import ayo.semangat.tahfidzv2.data.source.remote.response.ChapterResponse
import ayo.semangat.tahfidzv2.data.source.remote.response.VerseResponse
import ayo.semangat.tahfidzv2.data.source.remote.response.VersesEntity
import ayo.semangat.tahfidzv2.utils.AppExecutors
import ayo.semangat.tahfidzv2.vo.Resource

class TahfidzRepository private constructor(
        private val localRepository: LocalRepository,
        private val remoteRepository: RemoteRepository,
        private val appExecutors: AppExecutors
):TahfidzDataSource{

    companion object{
        @Volatile

        private var instance: TahfidzRepository? = null

        fun getInstance(localRepostory: LocalRepository, remoteRepository: RemoteRepository, appExecutors: AppExecutors): TahfidzRepository =
                instance ?: synchronized(this){
                    instance ?: TahfidzRepository(localRepostory, remoteRepository, appExecutors)
                }
    }
    override fun getChapters(): LiveData<Resource<List<ChapterEntity>?>> {
        return object : NetworkBoundResource<List<ChapterEntity>, ChapterResponse>(appExecutors){
            override fun loadFromDB(): LiveData<List<ChapterEntity>> =
                    localRepository.getChapters()

            override fun sholudFetch(data: List<ChapterEntity>): Boolean =
                    data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<ChapterResponse>> =
                    remoteRepository.getChapter()

            override fun saveCallResult(data: ChapterResponse) {
                val chapterEntity = ArrayList<ChapterEntity>()
                for (chapterList in data.data){
                    chapterList?.let { chapterEntity.add(it) }
                }
                localRepository.insertChapters(chapterEntity)
            }
        }.asLiveData()
    }

    override fun getVerses(surah: String): LiveData<Resource<VersesEntity?>> {
        return object : NetworkBoundResource<VersesEntity?, VerseResponse>(appExecutors){
            override fun loadFromDB(): LiveData<VersesEntity?> =
                    localRepository.getVerses(surah)

            override fun sholudFetch(data: VersesEntity?): Boolean =
                    data?.number == null || data.verses.isEmpty()

            override fun createCall(): LiveData<ApiResponse<VerseResponse>> =
                remoteRepository.getVerses(surah)


            override fun saveCallResult(data: VerseResponse) {
                val versesEntityt = data.verseEntity
                localRepository.inserVerses(versesEntityt)
            }
        }.asLiveData()
    }
}