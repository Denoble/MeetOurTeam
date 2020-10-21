package com.gevcorst.s_g_coffeemeetsbagel.viewModel

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gevcorst.s_g_coffeemeetsbagel.model.TeamMember
import com.gevcorst.s_g_coffeemeetsbagel.utility.ReadLocalJsonFile
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.jetbrains.annotations.NotNull
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class TeamMemberViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    val member1 = TeamMember(name = "James Miller",
        datingPreferences = "I like me some sushi",
       id="23",interests = "Soccer, " +
                "playing pools",
        personality = "cool, calm or coordinated",
        position = "Personnel Assitant",
        profileImage = "https://drive.google.com/file/d/1kKIMt8wnkwPN5nA-eaA6FebDwydsPC_j/view")
    val member2 = TeamMember(name = "Linda Book",
        datingPreferences = "Premier league footballer",
        id="34",interests = "Sing, travelling",
        personality = "beautiful, charming or gorgeous",
        position = "Co-founder",
        profileImage = "https://onedrive.live.com/?v=photos&cid=BB9D87F17D1E8FA3&id=BB9D87F17D1E8FA3%2121789&parId=root&parQt=allmyphotos&o=OneUp")
    val testTeamMemberlist = ArrayList<TeamMember>()
    @Test
    fun getTeamMembers() {
        testTeamMemberlist.add(member1)
        testTeamMemberlist.add(member2)
        val teamMemberViewModelTest =
                TeamMemberViewModel(ApplicationProvider.getApplicationContext())
        val observer = Observer<List<TeamMember>> {}
        try {
            teamMemberViewModelTest.teamMembers.observeForever(observer)
            teamMemberViewModelTest.addTeamMembers(testTeamMemberlist)
            val value = teamMemberViewModelTest.teamMembers.value
            assertEquals(value?.size ,2)
        }catch (e:Exception){

        }finally {
            teamMemberViewModelTest.teamMembers.removeObserver(observer)
        }
    }
    @Test
    fun getJson(){

        val jsonString: String
        try {
            jsonString = ApplicationProvider.getApplicationContext<Context>()
                .assets.open("team.json").bufferedReader().use { it.readText() }
           val teamMembers =  ReadLocalJsonFile.parseJson(jsonString)
            assertEquals(teamMembers[0].name,"Ben Easton")
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }

    }
}