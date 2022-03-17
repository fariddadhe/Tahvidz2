package ayo.semangat.tahfidzv2.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ayo.semangat.tahfidzv2.data.source.remote.response.ChapterResponse
import ayo.semangat.tahfidzv2.data.source.remote.response.VerseResponse
import ayo.semangat.tahfidzv2.data.source.remote.response.VersesEntity
import ayo.semangat.tahfidzv2.data.source.api.ApiClient
import ayo.semangat.tahfidzv2.data.source.api.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository private constructor(
        private val apiInterace: ApiInterface
) {
    companion object {
        private var INSTANCE: RemoteRepository? = null

        @JvmStatic
        fun getInstance(req: String) =
                INSTANCE ?: RemoteRepository(ApiClient.getClient(req).create(ApiInterface::class.java))
    }

    fun getChapter(): LiveData<ApiResponse<ChapterResponse>> {
        val resultChapter: MutableLiveData<ApiResponse<ChapterResponse>> = MutableLiveData<ApiResponse<ChapterResponse>>()
        apiInterace.getChapters().enqueue(object : Callback<ChapterResponse> {
            override fun onResponse(call: Call<ChapterResponse>, response: Response<ChapterResponse>) {
                if (response.code() == 200) {
                    response.body().let {
                        resultChapter.value = ApiResponse.success(it)
                    }
                }
            }

            override fun onFailure(call: Call<ChapterResponse>, t: Throwable) {

            }
        })
        return resultChapter
    }

    fun getVerses(
            surah: String
    ): LiveData<ApiResponse<VerseResponse>> {
        val resultVerses = MutableLiveData<ApiResponse<VerseResponse>>()
        apiInterace.getVerses(surah).enqueue(object : Callback<VerseResponse> {
            override fun onResponse(call: Call<VerseResponse>, response: Response<VerseResponse>) {
                if (response.code() == 200) {
                    println("woikau ${response.body()}")
                    resultVerses.value = ApiResponse.success(response.body())
                }
            }

            override fun onFailure(call: Call<VerseResponse>, t: Throwable) {
                println("woikau ${t.message}")
            }
        })
        return resultVerses
    }

}