package br.com.gascheck.ui.dialog.inputDataGas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.gascheck.R
import br.com.gascheck.databinding.DialogInputDataGasBinding
import br.com.gascheck.domain.model.GasData
import br.com.gascheck.util.TypeGas
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class InputDataGasDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: DialogInputDataGasBinding
    private val viewModel by viewModel<InputDataGasViewModel>()


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
        setupListener()
    }

    private fun setupListener() {
        with(binding) {
            chipGroupGas.setOnCheckedChangeListener { _, checkedId ->
                viewModel.typeGas = when (checkedId) {
                    R.id.chip_gasoline -> TypeGas.GASOLINE
                    R.id.chip_alcohol -> TypeGas.ALCOHOL
                    else -> TypeGas.GASOLINE
                }
            }

            circleButtonOther.setOnClickListenerCircleButton { goToInputOtherValue() }
            circleButton20.setOnClickListenerCircleButton { viewModel.insertGasData(20) }
            circleButton30.setOnClickListenerCircleButton { viewModel.insertGasData(30) }
            circleButton50.setOnClickListenerCircleButton { viewModel.insertGasData(50) }
            circleButton70.setOnClickListenerCircleButton { viewModel.insertGasData(70) }
            circleButton100.setOnClickListenerCircleButton { viewModel.insertGasData(100) }
            circleButton150.setOnClickListenerCircleButton { viewModel.insertGasData(150) }
            circleButton200.setOnClickListenerCircleButton { viewModel.insertGasData(200) }
            circleButton250.setOnClickListenerCircleButton { viewModel.insertGasData(250) }

        }

    }

    private fun goToInputOtherValue() {
        findNavController().navigate(R.id.action_inputDataGasDialogFragment_to_inputOtherValueDialogFragment)
    }
}