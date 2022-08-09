package com.myhome.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.myhome.R
import com.myhome.databinding.FragmentDashboardBinding

/**
 * @author z-100
 */
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val args: DashboardFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateNavBindings()
        generateViewBindings()
    }

    private fun generateNavBindings() {
        binding.navigationBar.navigationBar.setOnItemSelectedListener { item ->
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

        binding.topNavbar.accountSettingsButton.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections
                .dashboardToSettings().setBackButton(R.id.settings_to_dashboard))
        }
    }

    //TODO Can be filled with cleaning, upcoming meals, more | Recyclerview with switchable content
    private fun generateViewBindings() {

        binding.title.title.text = getString(R.string.dashboard)
        binding.title.titleIcon.setImageDrawable(getDrawable(this.requireContext(), R.drawable.home_icon))

        binding.topBigBox.setOnClickListener {
        }

        binding.bottomLeftBox.setOnClickListener {
        }

        binding.bottomRightBox.setOnClickListener {
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
