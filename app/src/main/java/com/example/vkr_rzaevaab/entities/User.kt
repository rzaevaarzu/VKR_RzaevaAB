package com.example.vkr_rzaevaab.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*

data class User (
    //val name: String
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "fio")
    val fio: String,
    @ColumnInfo(name = "birth_date")
    val birth_date: String,
    @ColumnInfo(name = "telephone")
    val telephone: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "password")
    val password: String,
    @ColumnInfo(name = "is_admin")
    val is_admin: Boolean
): Parcelable {
    //преобразует объект в строку для удобной передачи между фрагментами
    constructor(parcel: Parcel) : this(
        id = parcel.readInt(),
        fio = parcel.readString().toString(),
        birth_date = parcel.readString().toString(),
        telephone = parcel.readString().toString(),
        email = parcel.readString().toString(),
        password = parcel.readString().toString(),
        is_admin = parcel.readInt()==1
    )

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        //преобразует строку обратно в объект
        dest.writeInt(id!!)
        dest.writeString(fio)
        dest.writeString(birth_date)
        dest.writeString(telephone)
        dest.writeString(email)
        dest.writeString(password)
        dest.writeInt(if (is_admin) 1 else 0)
    }
}