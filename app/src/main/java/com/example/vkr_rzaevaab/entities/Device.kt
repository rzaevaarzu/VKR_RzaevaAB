package com.example.vkr_rzaevaab.entities

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Device (
   // val iconResId: Int,
    //val title: String
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "cpu")
    val cpu: String,
    @ColumnInfo(name = "ram")
    val ram: String,
    @ColumnInfo(name = "monitor")
    val monitor: String,
    @ColumnInfo(name = "videocard")
    val videocard: String
): Parcelable {
    //преобразует объект в строку для удобной передачи между фрагментами
    constructor(parcel: Parcel) : this(
        id = parcel.readInt(),
        name = parcel.readString().toString(),
        cpu = parcel.readString().toString(),
        ram = parcel.readString().toString(),
        monitor = parcel.readString().toString(),
        videocard = parcel.readString().toString()
    )

    companion object CREATOR : Parcelable.Creator<Device> {
        override fun createFromParcel(parcel: Parcel): Device {
            return Device(parcel)
        }

        override fun newArray(size: Int): Array<Device?> {
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
        dest.writeString(cpu)
        dest.writeString(ram)
        dest.writeString(monitor)
        dest.writeString(videocard)
    }
}