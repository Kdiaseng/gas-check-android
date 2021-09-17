package br.com.gascheck.ui.dialog.inputDataGas

import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.gascheck.R
import br.com.gascheck.databinding.DialogInputDataGasBinding
import br.com.gascheck.ui.MainActivity
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
            circleButton20.setOnClickListenerCircleButton { actionCircleButton(20) }
            circleButton30.setOnClickListenerCircleButton { actionCircleButton(30) }
            circleButton50.setOnClickListenerCircleButton { actionCircleButton(50) }
            circleButton70.setOnClickListenerCircleButton { actionCircleButton(70) }
            circleButton100.setOnClickListenerCircleButton { actionCircleButton(100) }
            circleButton150.setOnClickListenerCircleButton { actionCircleButton(150) }
            circleButton200.setOnClickListenerCircleButton { actionCircleButton(200) }
            circleButton250.setOnClickListenerCircleButton { actionCircleButton(250) }

        }

    }

    private fun actionCircleButton(value: Int) {

       val hasLocation =  binding.switchLocation.isChecked
        if (hasLocation){
            (activity as MainActivity).getLastLocation()
            val locationCurrent = (activity as MainActivity).locationCurrent
            locationCurrent?.let {
                val address = getAddress(it.latitude, it.longitude)
                viewModel.insertGasData(value, address)
                popBackHome()
            }
        }else{
            viewModel.insertGasData(value, "Desconhecido")
            popBackHome()
        }

    }

    private fun popBackHome(): Boolean {
        val navController = findNavController()
        navController.previousBackStackEntry?.savedStateHandle?.set("key", "pop")
        return navController.popBackStack(R.id.homeFragment, false)
    }


    private fun getAddress(latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        val address = geocoder.getFromLocation(latitude, longitude, 1)
        return address[0].getAddressLine(0)
    }

    private fun goToInputOtherValue() {
        findNavController().navigate(R.id.action_inputDataGasDialogFragment_to_inputOtherValueDialogFragment)
    }
}