package br.com.gascheck.ui.history

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.gascheck.R
import br.com.gascheck.databinding.FragmentHistoryBinding
import br.com.gascheck.ui.history.adapter.HistoricAdapter
import br.com.gascheck.ui.model.GasDataUi
import org.koin.androidx.viewmodel.ext.android.viewModel


class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val viewModel by viewModel<HistoricViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDateGasList()

        val adapter = HistoricAdapter()
        binding.recyclerViewHistoric.adapter = adapter

        viewModel.gasDataList.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }

    }

}