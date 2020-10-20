package com.gevcorst.s_g_coffeemeetsbagel.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gevcorst.s_g_coffeemeetsbagel.adapter.HomeFragmentRecyclerViewAdapter
import com.gevcorst.s_g_coffeemeetsbagel.databinding.FragmentHomeBinding
import com.gevcorst.s_g_coffeemeetsbagel.databinding.MemberItemBinding
import com.gevcorst.s_g_coffeemeetsbagel.viewModel.TeamMemberViewModel


class HomeFragment : Fragment() {
    lateinit var layoutManager:RecyclerView.LayoutManager
    private val viewModel: TeamMemberViewModel by lazy {
        ViewModelProvider(this, TeamMemberViewModel.Factory(requireActivity().application))
            .get(TeamMemberViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding  = FragmentHomeBinding.inflate(inflater,container,
            false)
        binding.lifecycleOwner = this
        binding.teamMemberViewModel = viewModel
        binding.homeFragmentRecyclerView.adapter =
            HomeFragmentRecyclerViewAdapter(HomeFragmentRecyclerViewAdapter.OnClickListener {
            viewModel.displayTeamMemberDetails(it)
        })
        viewModel.navigateToSelectedMember.observe(viewLifecycleOwner, Observer {
            if(it != null){
                this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMemberDetailsFragment(it))
                viewModel.displayTeamMemberDetailComplete()
            }


        })

        viewModel.isTablet.observe(viewLifecycleOwner, Observer {
            if(it){
                layoutManager = GridLayoutManager(requireContext(),3)
                binding.homeFragmentRecyclerView.layoutManager = layoutManager
            }
        })
        setHasOptionsMenu(true)
        return binding.root
    }
}