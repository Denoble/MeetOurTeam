package com.gevcorst.s_g_coffeemeetsbagel.utility

import android.content.Context
import com.gevcorst.s_g_coffeemeetsbagel.model.TeamMember
import com.gevcorst.s_g_coffeemeetsbagel.model.TeamMembers
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

object ReadLocalJsonFile{
    fun readJsonLocally(context: Context, fileName:String):String {
       var jsonString:String
        try {
            jsonString  = context.assets.open(fileName).bufferedReader().use {
                it.readText()
            }
        }catch (e:IOException){
            jsonString = e.localizedMessage?:"Problem reading json file"
        }
        return jsonString
    }
   fun parseJson(json:String): List<TeamMember>{
            val gson = Gson()
           val memberList  = object : TypeToken<TeamMembers>() {}.type
           val teamMembers:List<TeamMember> = gson.fromJson(json,memberList)
           return teamMembers

    }
}