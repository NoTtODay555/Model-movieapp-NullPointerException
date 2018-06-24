package com.example.napat.movieapp.model

import android.os.Parcel
import android.os.Parcelable

data class ListViewData(
        var id: Int,
        var viewCount: Int
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(viewCount)
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
