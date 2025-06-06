package com.example.vkr_rzaevaab.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class DeviceReservation (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "time")
    val time: String,
    @ColumnInfo(name = "device_id")
    val deviceId: Int?,
    @ColumnInfo(name = "user_id")
    val userId: Int,
): Parcelable {
    //преобразует объект в строку для удобной передачи между фрагментами
    constructor(parcel: Parcel) : this(
        id = parcel.readInt(),
        date = parcel.readString().toString(),
        time = parcel.readString().toString(),
        deviceId = parcel.readInt(),
        userId = parcel.readInt()
    )

    companion object CREATOR : Parcelable.Creator<DeviceReservation> {
        override fun createFromParcel(parcel: Parcel): DeviceReservation {
            return DeviceReservation(parcel)
        }

        override fun newArray(size: Int): Array<DeviceReservation?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        //преобразует строку обратно в объект
        dest.writeInt(id!!)
        dest.writeString(date)
        dest.writeString(time)
        dest.writeInt(deviceId!!)
        dest.writeInt(userId)
    }
}
