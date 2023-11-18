package com.omongole.fred.composenewsapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.omongole.fred.composenewsapp.data.modal.Source

@ProvidedTypeConverter
class ArticlesTypeConverter {

    @TypeConverter
    fun sourceToString( source: Source ) :  String = "${source.name}"

    @TypeConverter
    fun stringToSource( string: String ) : Source = Source(string)
}