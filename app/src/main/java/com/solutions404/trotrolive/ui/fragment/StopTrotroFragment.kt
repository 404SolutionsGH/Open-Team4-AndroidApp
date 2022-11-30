package com.solutions404.trotrolive.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solutions404.trotrolive.R
import com.solutions404.trotrolive.TrotroFareApplication
import com.solutions404.trotrolive.adapter.TrotroStopAdapter
import com.solutions404.trotrolive.databinding.FragmentStopTrotroBinding
import com.solutions404.trotrolive.viewModel.TrotroFareViewModel
import com.solutions404.trotrolive.viewModel.TrotroViewModelFactory
import kotlinx.coroutines.launch


class StopTrotroFragment : Fragment() {


    companion object {
        var STOP_NAME = "stopName"
    }

    private var _binding: FragmentStopTrotroBinding? = null
    private  val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    private lateinit var stopName: String

    private val viewModel: TrotroFareViewModel by activityViewModels {
        TrotroViewModelFactory(
            (activity?.application as TrotroFareApplication).database.trotroDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            stopName = it.getString(STOP_NAME).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStopTrotroBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val busStopAdapter = TrotroStopAdapter({})
        // by passing in the stop name, filtered results are returned,
        // and tapping rows won't trigger navigation
        recyclerView.adapter = busStopAdapter
        lifecycle.coroutineScope.launch {
            viewModel.trotroForStopName(stopName).collect() {
                busStopAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}