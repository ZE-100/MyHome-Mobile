package com.myhome.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.myhome.R
import com.myhome.databinding.FragmentMembersBinding


import com.myhome.other.GridAdapter
import java.lang.Exception

import android.widget.AdapterView.OnItemClickListener
import com.myhome.blueprint.Member
import com.myhome.other.Session
import com.myhome.service.api.components.impl.FetchMemberService

/**
 * @author Z-100
 */
class MembersFragment : Fragment() {

    private var _binding: FragmentMembersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {

        fetchAllMembersFromAPI()

        // Redirect if credentials present
        if (Session.memberSelected())
            findNavController().navigate(R.id.members_to_dashboard)

        _binding = FragmentMembersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateBindings()
    }

    private fun generateBindings() {
        val members: List<Member> = Session.getAllMembers().members

        binding.addMemberButton.setOnClickListener {
            findNavController().navigate(R.id.members_to_add_member)
        }

        val gridAdapter = GridAdapter(context, members, R.layout.member_grid_item)
        binding.gridView.adapter = gridAdapter

        binding.gridView.onItemClickListener = OnItemClickListener {
                _, _, position, _ ->

            Session.setCurrentMember(members[position])

            findNavController().navigate(MembersFragmentDirections
                .membersToDashboard().setBackButton(R.id.dashboard_to_members))
        }
    }

    private fun fetchAllMembersFromAPI() {
        val memberService = FetchMemberService()

        try {
            memberService.fetchAllMembers {
                result -> Session.setAllMembers(result)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
