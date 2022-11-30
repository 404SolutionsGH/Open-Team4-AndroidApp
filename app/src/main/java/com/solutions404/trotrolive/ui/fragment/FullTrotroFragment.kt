package com.solutions404.trotrolive.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solutions404.trotrolive.R
import com.solutions404.trotrolive.TrotroFareApplication
import com.solutions404.trotrolive.adapter.TrotroStopAdapter
import com.solutions404.trotrolive.database.trotro.TrotroDao
import com.solutions404.trotrolive.databinding.FragmentFullTrotroBinding
import com.solutions404.trotrolive.viewModel.TrotroFareViewModel
import com.solutions404.trotrolive.viewModel.TrotroViewModelFactory
import kotlinx.coroutines.launch


class FullTrotroFragment : Fragment() {

    private var _binding : FragmentFullTrotroBinding? = null
    private  val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private val viewModel:TrotroFareViewModel by activityViewModels {
        TrotroViewModelFactory(
            (activity?.application as TrotroFareApplication).database.trotroDao()
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFullTrotroBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val trotroStopAdater = TrotroStopAdapter {
            val action = FullTrotroFragmentDirections.actionFullTrotroFragmentToStopTrotroFragment(
                stopName = it.stopName
            )
            view.findNavController().navigate(action)
        }
        recyclerView.adapter = trotroStopAdater
        lifecycle.coroutineScope.launch {
            viewModel.fullTrotro().collect() {
                trotroStopAdater.submitList(it)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

