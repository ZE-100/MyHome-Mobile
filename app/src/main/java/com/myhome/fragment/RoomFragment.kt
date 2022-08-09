package com.myhome.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.myhome.R
import com.myhome.databinding.FragmentRoomBinding

/**
 * @author z-100
 */
class RoomFragment : Fragment() {

    private var _binding: FragmentRoomBinding? = null
    private val binding get() = _binding!!

    private val args: RoomFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentRoomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.roomTitle.text = RoomViewFragment.rooms.get(args.itemPos).title

        generateNavBindings()
        generateViewBindings()
    }

    private fun generateNavBindings() {
        binding.navigationBar.navigationBar.setOnItemSelectedListener { item -> //TODO Might remove
            when (item.itemId) {
                R.id.footer_home_btn -> findNavController().navigate(DashboardFragmentDirections
                    .dashboardToDashboard().setBackButton(R.id.dashboard_to_dashboard))

                R.id.footer_kitchen_btn -> findNavController().navigate(DashboardFragmentDirections
                    .dashboardToKitchen().setBackButton(R.id.kitchen_to_dashboard))

                R.id.footer_rooms_btn -> findNavController().navigate(DashboardFragmentDirections
                    .dashboardToRooms().setBackButton(R.id.rooms_to_dashboard))
            }
            true
        }

        binding.topNavbar.backButton.setOnClickListener {
            if (args.backButton != -1) findNavController().navigate(args.backButton)
        }

        binding.topNavbar.accountSettingsButton.setOnClickListener { //TODO Might remove
            findNavController().navigate(DashboardFragmentDirections
                .dashboardToSettings().setBackButton(R.id.settings_to_dashboard))
        }
    }

    private fun generateViewBindings() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
