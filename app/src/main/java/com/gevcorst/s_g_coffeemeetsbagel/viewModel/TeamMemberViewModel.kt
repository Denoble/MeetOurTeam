package com.gevcorst.s_g_coffeemeetsbagel.viewModel

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.lifecycle.*
import com.gevcorst.s_g_coffeemeetsbagel.model.TeamMember
import com.gevcorst.s_g_coffeemeetsbagel.model.TeamMembers
import com.gevcorst.s_g_coffeemeetsbagel.utility.ReadLocalJsonFile
import kotlinx.coroutines.*
import java.lang.Exception

enum class TeamJsonPassingStatus { LOADING, ERROR, DONE }
class TeamMemberViewModel(app: Application) : AndroidViewModel(app) {
    private  val _response = MutableLiveData<String>()
    val response: LiveData<String> = _response
    private val _status = MutableLiveData<TeamJsonPassingStatus>()
     val status: LiveData<TeamJsonPassingStatus> = _status
     private val _teamMembers = MutableLiveData<List<TeamMember>>()
     val teamMembers: LiveData<List<TeamMember>> = _teamMembers
     private val _navigateToSelectedMember = MutableLiveData<TeamMember>()
     val navigateToSelectedMember: LiveData<TeamMember> = _navigateToSelectedMember
     private val _isTablet = MutableLiveData<Boolean>()
     val isTablet: LiveData<Boolean> = _isTablet

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
         _isTablet.value = isLargerScreen(app)
        getTeamMembers()

    }

    private fun getTeamMembers() {

        coroutineScope.launch {
            val deferedMemberList: Deferred<List<TeamMember>> = async(Dispatchers.IO){
                val jsonString =
                    ReadLocalJsonFile.readJsonLocally(getApplication(), "team.json")
                ReadLocalJsonFile.parseJson(jsonString)
            }
            try{
                _status.value = TeamJsonPassingStatus.LOADING
                val membersList = deferedMemberList.await()
                _status.value = TeamJsonPassingStatus.DONE
                _teamMembers.value = membersList
            }catch (e: Exception){
                _status.value = TeamJsonPassingStatus.ERROR
                _teamMembers.value = TeamMembers()
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TeamMemberViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TeamMemberViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

    fun isLargerScreen(context: Context): Boolean {
        return ((context.resources.configuration.screenLayout
                and Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE)
    }
    /**
     * When the teamMember is clicked, set the [  _navigateToSelectedMember] [MutableLiveData]
     * @param teamMember The [TeamMember] that was clicked on.
     */
    fun displayTeamMemberDetails(teamMember: TeamMember) {
        _navigateToSelectedMember.value = teamMember
    }

    /**
     * After the navigation has taken place, make sure  navigateToSelectedMember.value is set to null
     */
    fun displayTeamMemberDetailComplete() {
        _navigateToSelectedMember.value = null
    }
}