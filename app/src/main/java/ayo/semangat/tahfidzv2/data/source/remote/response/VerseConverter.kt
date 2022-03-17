package ayo.semangat.tahfidzv2.data.source.remote.response

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class VerseConverter {

    companion object{
        @TypeConverter
        @JvmStatic
        fun fromVerseItem(verseItem: List<VersesItem?>?): String? {
            if (verseItem == null) {
                return null
            }
            val gson = Gson()
            val type = object : TypeToken<List<VersesItem?>?>() {}.type
            return gson.toJson(verseItem, type)
        }

        @TypeConverter
        @JvmStatic
        fun toVerseItemList(vereString: String?): List<VersesItem>? {
            if (vereString == null) {
                return null
            }
            val gson = Gson()
            val type = object : TypeToken<List<VersesItem?>?>() {}.type
            return gson.fromJson<List<VersesItem>>(vereString, type)
        }
    }
}

class RevelationConverter{
    companion object{
        @TypeConverter
        @JvmStatic
        fun revelationToString(revelation: Revelation): String?{
            val type = object : TypeToken<Revelation?>() {}.type
            val gson = Gson()
            return gson.toJson(revelation, type)
        }

        @TypeConverter
        @JvmStatic
        fun toRevelation(rev : String?): Revelation?{
            val gson = Gson()
            val type = object : TypeToken<Revelation?>() {}.type
            return gson.fromJson<Revelation>(rev, type)
        }
    }
}

class NameConverter{
    companion object{

        @TypeConverter
        @JvmStatic
        fun nameToString(name: Name): String?{
            val type = object : TypeToken<Name?>() {}.type
            val gson = Gson()
            return gson.toJson(name, type)
        }

        @TypeConverter
        @JvmStatic
        fun toName(name : String?): Name?{
            val gson = Gson()
            val type = object : TypeToken<Name?>() {}.type
            return gson.fromJson<Name>(name, type)
        }
    }
}

//class TafsirConverter{
//    companion object{
//
//        @TypeConverter
//        @JvmStatic
//        fun tafsirToString(tafsir: Tafsir): String?{
//            val type = object : TypeToken<Tafsir?>() {}.type
//            val gson = Gson()
//            return gson.toJson(tafsir, type)
//        }
//
//        @TypeConverter
//        @JvmStatic
//        fun toName(tafsir: String?): Tafsir?{
//            val gson = Gson()
//            val type = object : TypeToken<Tafsir?>() {}.type
//            return gson.fromJson<Tafsir>(tafsir, type)
//        }
//    }
//}