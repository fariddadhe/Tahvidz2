package ayo.semangat.tahfidzv2.data.source.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    companion object {
        private const val urlQuran = "https://api.quran.sutanlab.id/"
        private const val urlJadwal = "https://api.myquran.com/v1/"

        fun getClient(req: String): Retrofit {

            var url = ""

            val okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(40, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build()

            val gson = GsonBuilder()
                    .setLenient()
                    .create()

            url = if (req=="jadwal") urlJadwal else urlQuran

            return Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
    }
}