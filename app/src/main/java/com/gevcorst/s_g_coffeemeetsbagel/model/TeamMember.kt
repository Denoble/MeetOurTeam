package com.gevcorst.s_g_coffeemeetsbagel.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamMember(
    val id: String,
    val name: String,
    val position: String,
    @SerializedName("profile_image")
    val profileImage: String,
    val personality: String,
    val interests: String,
    @SerializedName("dating_preferences")
    val datingPreferences: String
): Parcelable
class TeamMembers:ArrayList<TeamMember>()