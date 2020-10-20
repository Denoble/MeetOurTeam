package com.gevcorst.s_g_coffeemeetsbagel.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gevcorst.s_g_coffeemeetsbagel.databinding.FragmentMemberDetailsBinding
import com.gevcorst.s_g_coffeemeetsbagel.viewModel.MemberDetailViewModelFactory
import com.gevcorst.s_g_coffeemeetsbagel.viewModel.MemberDetailsViewModel


class MemberDetailsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding
                = FragmentMemberDetailsBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        val application =  requireNotNull(activity).application
        val teamMemeber = MemberDetailsFragmentArgs.fromBundle(requireArguments()).selectedTeamMember
        val viewModelFactory = MemberDetailViewModelFactory(teamMemeber, application)
        binding.viewModel = ViewModelProvider(
                this, viewModelFactory).get(MemberDetailsViewModel::class.java)
        (requireActivity() as MainActivity).supportActionBar?.title = teamMemeber.name
        return binding.root
    }
}