package com.myhome.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.myhome.R
import com.myhome.databinding.FragmentMembersBinding


import com.myhome.custom.adapter.member.GridAdapter
import java.lang.Exception

import android.widget.AdapterView.OnItemClickListener
import com.myhome.blueprint.Member
import com.myhome.databinding.FragmentRoomViewItemBinding
import com.myhome.other.Session
import com.myhome.service.api.components.impl.FetchMemberService

/**
 * @author Z-100
 */ //TODO idk
class RoomViewItemFragment : Fragment() {

    private var _binding: FragmentRoomViewItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {


        _binding = FragmentRoomViewItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
