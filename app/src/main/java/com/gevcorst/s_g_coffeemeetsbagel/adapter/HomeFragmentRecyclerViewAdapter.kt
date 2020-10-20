package com.gevcorst.s_g_coffeemeetsbagel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gevcorst.s_g_coffeemeetsbagel.databinding.MemberItemBinding
import com.gevcorst.s_g_coffeemeetsbagel.model.TeamMember

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding
 * it also computes diffs between lists.
 * @param onclickListen a lamba will be called with current TeamMember
 */
class HomeFragmentRecyclerViewAdapter(val onClickListener:OnClickListener)
    : ListAdapter<TeamMember, HomeFragmentRecyclerViewAdapter.TeamMemberViewHold>
    (DiffCallback) {
    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of TeamMember
     * has been updated.
     */
    companion object DiffCallback: DiffUtil.ItemCallback<TeamMember>(){
        override fun areItemsTheSame(oldItem: TeamMember, newItem: TeamMember): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: TeamMember, newItem: TeamMember): Boolean {
            return oldItem.id ==  newItem.id
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamMemberViewHold {
        return TeamMemberViewHold(MemberItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TeamMemberViewHold, position: Int) {
        val teamMember = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(teamMember)
        }
        holder.bind(teamMember)
    }
    /**
     * This inner class binds some of TeamMember properties to
     * member_item.xml layout
     */
    class TeamMemberViewHold(private var binding:MemberItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(teamMember: TeamMember){
             binding.teamMember = teamMember
            binding.executePendingBindings()
        }
    }
    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [TeamMember]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [TeamMembers]
     */
    class OnClickListener(val clickListener: (member: TeamMember) -> Unit) {
        fun onClick(member: TeamMember) = clickListener(member)
    }
}