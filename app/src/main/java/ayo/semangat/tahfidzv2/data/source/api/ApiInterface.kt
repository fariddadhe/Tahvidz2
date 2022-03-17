package ayo.semangat.tahfidzv2.data.source.api

import ayo.semangat.tahfidzv2.data.source.remote.response.ChapterResponse
import ayo.semangat.tahfidzv2.data.source.remote.response.JadwalResponse
import ayo.semangat.tahfidzv2.data.source.remote.response.VerseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("surah")
    fun getChapters(): Call<ChapterResponse>

    @GET("surah/{surah}")
    fun getVerses(
            @Path("surah") surah: String,
    ): Call<VerseResponse>

    @GET("sholat/jadwal/{id}/{tahun}/{bulan}/{tanggal}")
    fun getJadwalHari(@Path("id") id: String,
                      @Path("tahun") tahun: String,
                      @Path("bulan") bulan: String,
                      @Path("tanggal") tanggal: String
    ): Call<JadwalResponse>
}