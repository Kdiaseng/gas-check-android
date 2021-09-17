package br.com.gascheck.ui.dialog.inputOtherValue

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.gascheck.R
import br.com.gascheck.databinding.DialogInputOtherValueBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class InputOtherValueDialogFragment : DialogFragment() {

    private lateinit var binding: DialogInputOtherValueBinding

   private val args: InputOtherValueDialogFragmentArgs by navArgs()

    private val viewModel by viewModel<InputOtherValueViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogInputOtherValueBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialog)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return super.onCreateDialog(savedInstanceState).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.materialButtonConfirm.setOnClickListener {
            val value = binding.currencyTextValue.getNumericValue()
            val gas = args.gas
            val address = args.address
            if (gas != null && address != null && value != null) {
                viewModel.insertGasData(value, address, gas)
                findNavController().popBackStack()
            }
        }
    }

}