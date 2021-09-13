package br.com.gascheck.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.gascheck.R
import br.com.gascheck.databinding.FragmentHistoryBinding
import br.com.gascheck.ui.history.adapter.HistoricAdapter
import br.com.gascheck.ui.model.GasDataUi


class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HistoricAdapter()
        binding.recyclerViewHistoric.adapter = adapter

        adapter.submitList(
            listOf(
                GasDataUi(
                    1,
                    typeGas = "GASOLINA",
                    day = "05",
                    month = "JAN",
                    like = true,
                    dislike = false,
                    time = "02:55",
                    value = "80.0"
                ), GasDataUi(
                    1,
                    typeGas = "GASOLINA",
                    day = "05",
                    month = "JAN",
                    like = true,
                    dislike = false,
                    time = "02:55",
                    value = "80.0"
                ), GasDataUi(
                    1,
                    typeGas = "GASOLINA",
                    day = "05",
                    month = "JAN",
                    like = true,
                    dislike = false,
                    time = "02:55",
                    value = "80.0"
                ), GasDataUi(
                    1,
                    typeGas = "GASOLINA",
                    day = "05",
                    month = "JAN",
                    like = true,
                    dislike = false,
                    time = "02:55",
                    value = "80.0"
                ), GasDataUi(
                    1,
                    typeGas = "GASOLINA",
                    day = "05",
                    month = "JAN",
                    like = true,
                    dislike = false,
                    time = "02:55",
                    value = "80.0"
                ), GasDataUi(
                    1,
                    typeGas = "GASOLINA",
                    day = "05",
                    month = "JAN",
                    like = true,
                    dislike = false,
                    time = "02:55",
                    value = "80.0"
                ), GasDataUi(
                    1,
                    typeGas = "GASOLINA",
                    day = "05",
                    month = "JAN",
                    like = true,
                    dislike = false,
                    time = "02:55",
                    value = "80.0"
                )
            ),
        )

    }

}