package com.example.napat.movieapp.model

import android.os.Parcel
import android.os.Parcelable

data class ListViewData(
    var id: Int? = null,
    var viewCount: Int? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeValue(viewCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ListViewData> {
        override fun createFromParcel(parcel: Parcel): ListViewData {
            return ListViewData(parcel)
        }

        override fun newArray(size: Int): Array<ListViewData?> {
            return arrayOfNulls(size)
        }
    }
}