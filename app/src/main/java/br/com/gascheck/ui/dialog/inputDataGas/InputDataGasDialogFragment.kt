package br.com.gascheck.ui.dialog.inputDataGas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.gascheck.R
import br.com.gascheck.databinding.DialogInputDataGasBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class InputDataGasDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: DialogInputDataGasBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogInputDataGasBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.materialCardOtherValue.setOnClickListener {
            findNavController().navigate(R.id.action_inputDataGasDialogFragment_to_inputOtherValueDialogFragment)
        }

    }


}