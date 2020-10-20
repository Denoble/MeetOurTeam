package com.gevcorst.s_g_coffeemeetsbagel.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.gevcorst.s_g_coffeemeetsbagel.model.TeamMember

class MemberDetailsViewModel(teamMember: TeamMember,app:Application)
    :AndroidViewModel(app) {
    private  val _selectedTeamMember = MutableLiveData<TeamMember>()
    val selectedTeamMember:LiveData<TeamMember> = _selectedTeamMember
    init {
        _selectedTeamMember.value = teamMember
    }
}
class MemberDetailViewModelFactory(
    private val teamMember:TeamMember,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemberDetailsViewModel::class.java)) {
            return MemberDetailsViewModel(teamMember, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
