package ayo.semangat.tahfidzv2.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JadwalResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
) : Parcelable

@Parcelize
data class Jadwal(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("imsak")
	val imsak: String? = null,

	@field:SerializedName("isya")
	val isya: String? = null,

	@field:SerializedName("dzuhur")
	val dzuhur: String? = null,

	@field:SerializedName("subuh")
	val subuh: String? = null,

	@field:SerializedName("dhuha")
	val dhuha: String? = null,

	@field:SerializedName("terbit")
	val terbit: String? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("ashar")
	val ashar: String? = null,

	@field:SerializedName("maghrib")
	val maghrib: String? = null
) : Parcelable

@Parcelize
data class Koordinat(

	@field:SerializedName("lintang")
	val lintang: String? = null,

	@field:SerializedName("lon")
	val lon: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null,

	@field:SerializedName("bujur")
	val bujur: String? = null
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("jadwal")
	val jadwal: Jadwal? = null,

	@field:SerializedName("lokasi")
	val lokasi: String? = null,

	@field:SerializedName("daerah")
	val daerah: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("koordinat")
	val koordinat: Koordinat? = null
) : Parcelable
