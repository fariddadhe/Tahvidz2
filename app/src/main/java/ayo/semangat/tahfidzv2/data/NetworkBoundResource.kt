package ayo.semangat.tahfidzv2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import ayo.semangat.tahfidzv2.data.source.remote.ApiResponse
import ayo.semangat.tahfidzv2.data.source.remote.StatusResponse
import ayo.semangat.tahfidzv2.data.source.remote.response.ChapterResponse
import ayo.semangat.tahfidzv2.utils.AppExecutors
import ayo.semangat.tahfidzv2.vo.Resource

abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType?>>()

    init {
        result.value = Resource.loading(null)

        @Suppress("Leakingthis")
        val dbSource = loadFromDB()

        result.addSource(dbSource) { data: ResultType ->
            result.removeSource(dbSource)
            if (sholudFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newdata: ResultType -> result.setValue(Resource.success(newdata)) }
            }
        }
    }

    protected fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun sholudFetch(data: ResultType): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {

        val apiResponse = createCall()
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response.status) {
                StatusResponse.SUCCESS ->
                    mExecutors.diskIO().execute {
                        response.body?.let { saveCallResult(it) }
                        mExecutors.mainThread().execute {
                            result.addSource(loadFromDB()
                            ) { newData: ResultType -> result.setValue(Resource.success(newData)) }
                        }
                    }
                StatusResponse.EMPTY -> mExecutors.mainThread().execute {
                    result.addSource(loadFromDB()
                    ) { newData: ResultType -> result.setValue(Resource.success(newData)) }
                }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData: ResultType -> result.setValue(Resource.error(response.message, newData)) }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType?>> {
        return result
    }
}
