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
    private val adapter: HistoricAdapter by lazy {
        HistoricAdapter().apply {

            onClickDislikeListener = {
                enableDislike(it)
            }
            onClickLikeListener = {
                enableLike(it)
            }
        }
    }

    private fun enableDislike(it: GasDataUi) {
        val data = it.apply {
            dislike = true
            like = false
        }

        Log.e("data", data.toString())
        viewModel.updateLike(data)
    }

    private fun enableLike(it: GasDataUi) {
        val data = it.apply {
            like = true
            dislike = false
        }

        viewModel.updateLike(data)
    }

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

        setupListener()
        setupObserver()

        binding.recyclerViewHistoric.adapter = adapter


    }

    private fun setupObserver() {
        with(viewModel) {
            gasDataList.observe(viewLifecycleOwner) { list ->
                adapter.submitList(list)
            }
            monthLiveData.observe(viewLifecycleOwner) { month ->
                binding.textHistoricMonth.text = month
            }

            valueTotalLiveData.observe(viewLifecycleOwner) {
                binding.textHistoricTotal.text = getString(R.string.value_total, it)
            }
        }

    }

    private fun setupListener() {
        with(binding) {
            materialCardHistoricPrevious.setOnClickListener {
                viewModel.previousMonth()
            }

            materialCardHistoricNext.setOnClickListener {
                viewModel.nextMonth()
            }
        }
    }

}