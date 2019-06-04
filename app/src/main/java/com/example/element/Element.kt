package com.example.element

import android.os.Parcel
import android.os.Parcelable
import java.io.Externalizable
import java.io.ObjectInput
import java.io.ObjectOutput

data class Element(
    var name: String,
    var value: Int
) : Parcelable, Externalizable {

    override fun readExternal(input: ObjectInput?) {
        name = input?.readObject() as String
        value = input?.readObject() as Int
    }

    override fun writeExternal(out: ObjectOutput?) {
        out?.writeObject(name)
        out?.writeObject(value)
    }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Element> {
        override fun createFromParcel(parcel: Parcel): Element {
            return Element(parcel)
        }

        override fun newArray(size: Int): Array<Element?> {
            return arrayOfNulls(size)
        }
    }
}