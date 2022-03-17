package ayo.semangat.tahfidzv2.di

import android.content.Context
import ayo.semangat.tahfidzv2.data.TahfidzRepository
import ayo.semangat.tahfidzv2.data.source.local.room.LocalRepository
import ayo.semangat.tahfidzv2.data.source.local.room.TahfidzDatabase
import ayo.semangat.tahfidzv2.data.source.remote.RemoteRepository
import ayo.semangat.tahfidzv2.utils.AppExecutors

object Injection {
    fun providerRepository(context: Context, req: String): TahfidzRepository{

        val database = TahfidzDatabase.getInstance(context)

        val remoteRepository = RemoteRepository.getInstance(req)
        val localRepository = LocalRepository.getInstance(database.tahfidzDao())
        val appExecutors = AppExecutors()

        return TahfidzRepository.getInstance(localRepository, remoteRepository, appExecutors)

    }
}