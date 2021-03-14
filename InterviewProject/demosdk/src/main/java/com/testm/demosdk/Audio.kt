package com.testm.demosdk

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Audio(val id: Int? = null, val name: String? = null, val url: String? = null) : Parcelable {
    override fun toString(): String {
        return "Audio(id=$id, name=$name, url=$url)"
    }
}