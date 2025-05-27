package com.example.vkr_rzaevaab.entities

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Event (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "info")
    val info: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "time")
    val time: String,
    @ColumnInfo(name = "seats_count")
    val seatsCount: Int
): Parcelable {
    //преобразует объект в строку для удобной передачи между фрагментами
    constructor(parcel: Parcel) : this(
        id = parcel.readValue(Int::class.java.classLoader) as? Int,
        name = parcel.readString().toString(),
        info = parcel.readString().toString(),
        date = parcel.readString().toString(),
        time = parcel.readString().toString(),
        seatsCount = parcel.readInt()
    )

    companion object CREATOR : Parcelable.Creator<Event> {
        override fun createFromParcel(parcel: Parcel): Event {
            return Event(parcel)
        }

        override fun newArray(size: Int): Array<Event?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        //преобразует строку обратно в объект
        dest.writeInt(id!!)
        dest.writeString(name)
        dest.writeString(info)
        dest.writeString(date)
        dest.writeString(time)
        dest.writeInt(seatsCount)
    }
}
