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
data class ChapterResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: List<ChapterEntity?>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
) : Parcelable

@Entity(tableName = "chapterentities")
@Parcelize
data class ChapterEntity(

	@PrimaryKey
	@NonNull
	@ColumnInfo(name = "chapterId")
	@field:SerializedName("number")
	var chapterId: String,

//	@ColumnInfo(name = "sequence")
//	@field:SerializedName("sequence")
//	val sequence: Int,

	@ColumnInfo(name = "numberOfVerses")
	@field:SerializedName("numberOfVerses")
	val numberOfVerses: Int,

//	@TypeConverters(RevelationConverter::class)
//	@ColumnInfo(name = "revelation")
//	@field:SerializedName("revelation")
//	val revelation: Revelation,

	@TypeConverters(NameConverter::class)
	@ColumnInfo(name = "name")
	@field:SerializedName("name")
	val name: Name,

//	@TypeConverters(TafsirConverter::class)
//	@ColumnInfo(name = "tafsir")
//	@field:SerializedName("tafsir")
//	val tafsir: Tafsir
) : Parcelable

@Parcelize
data class Name(

	@field:SerializedName("translation")
	val translation: Translation,

	@field:SerializedName("short")
	val jsonMemberShort: String,

//	@field:SerializedName("long")
//	val jsonMemberLong: String,

	@field:SerializedName("transliteration")
	val transliteration: Transliteration
) : Parcelable

@Parcelize
data class Revelation(

	@field:SerializedName("en")
	val en: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("arab")
	val arab: String
) : Parcelable

//@Parcelize
//data class Tafsir(
//
//	@field:SerializedName("id")
//	val id: String
//) : Parcelable

@Parcelize
data class Translation(

//	@field:SerializedName("en")
//	val en: String,

	@field:SerializedName("id")
	val id: String
) : Parcelable

@Parcelize
data class Transliteration(

	@field:SerializedName("en")
	val en: String,

	@field:SerializedName("id")
	val id: String
) : Parcelable