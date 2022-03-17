package ayo.semangat.tahfidzv2.data.source.remote.response

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VerseResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val verseEntity: VersesEntity,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
) : Parcelable

@Entity(tableName = "versesentitites")
@Parcelize
data class VersesEntity(
	@PrimaryKey
	@NonNull
	@ColumnInfo(name = "number")
	@field:SerializedName("number")
	val number: Int,

	@ColumnInfo(name = "sequence")
	@field:SerializedName("sequence")
	val sequence: Int,

	@ColumnInfo(name = "numberOfVerses")
	@field:SerializedName("numberOfVerses")
	val numberOfVerses: Int,

	@TypeConverters(RevelationConverter::class)
	@ColumnInfo(name = "revelation")
	@field:SerializedName("revelation")
	val revelation: Revelation,

	@TypeConverters(NameConverter::class)
	@ColumnInfo(name = "name")
	@field:SerializedName("name")
	val name: Name,

//	@TypeConverters(TafsirConverter::class)
//	@ColumnInfo(name = "tafsir")
//	@field:SerializedName("tafsir")
//	val tafsir: Tafsir,

	@TypeConverters(VerseConverter::class)
	@ColumnInfo(name = "verses")
	@field:SerializedName("verses")
	val verses: List<VersesItem>
) : Parcelable

@Parcelize
data class Text(

	@field:SerializedName("transliteration")
	val transliteration: Transliteration,

	@field:SerializedName("arab")
	val arab: String
) : Parcelable

@Parcelize
data class Audio(

	@field:SerializedName("secondary")
	val secondary: List<String?>,

	@field:SerializedName("primary")
	val primary: String
) : Parcelable

@Parcelize
data class Meta(

	@field:SerializedName("hizbQuarter")
	val hizbQuarter: Int,

	@field:SerializedName("ruku")
	val ruku: Int,

	@field:SerializedName("manzil")
	val manzil: Int,

	@field:SerializedName("page")
	val page: Int,

//	@field:SerializedName("sajda")
//	val sajda: Boolean,

	@field:SerializedName("juz")
	val juz: Int
) : Parcelable

@Parcelize
data class Id(

	@field:SerializedName("short")
	val jsonMemberShort: String,


	@field:SerializedName("long")
	val jsonMemberLong: String
) : Parcelable

@Parcelize
data class Number(

	@field:SerializedName("inQuran")
	val inQuran: Int,

	@field:SerializedName("inSurah")
	val inSurah: Int? = null
) : Parcelable

@Parcelize
data class VersesItem(

	@field:SerializedName("number")
	val number: Number,

	@field:SerializedName("meta")
	val meta: Meta,

	@field:SerializedName("translation")
	val translation: Translation,

//	@field:SerializedName("tafsir")
//	val tafsir: Tafsir,

	@field:SerializedName("text")
	val text: Text,

	@field:SerializedName("audio")
	val audio: Audio
) : Parcelable