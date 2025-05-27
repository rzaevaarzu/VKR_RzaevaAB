package com.example.vkr_rzaevaab.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class EventRegistration(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "event_id")
    val eventId: Int?,
    @ColumnInfo(name = "user_id")
    val userId: Int,
): Parcelable {
    //преобразует объект в строку для удобной передачи между фрагментами
    constructor(parcel: Parcel) : this(
        id = parcel.readInt(),
        eventId = parcel.readInt(),
        userId = parcel.readInt()
    )

    companion object CREATOR : Parcelable.Creator<EventRegistration> {
        override fun createFromParcel(parcel: Parcel): EventRegistration {
            return EventRegistration(parcel)
        }

        override fun newArray(size: Int): Array<EventRegistration?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        //преобразует строку обратно в объект
        dest.writeInt(id!!)
        dest.writeInt(eventId!!)
        dest.writeInt(userId)
    }
}
