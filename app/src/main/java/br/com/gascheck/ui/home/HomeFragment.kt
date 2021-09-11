package br.com.gascheck.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import br.com.gascheck.R
import br.com.gascheck.databinding.FragmentHomeBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupChart()
        setDataToChart()
    }

    private fun setupChart() {
        with(binding.chartGas) {
            setUsePercentValues(true)
            description.text = ""
            isDrawHoleEnabled = false
            setTouchEnabled(false)
            setDrawEntryLabels(false)
            setExtraOffsets(20f, 0f, 20f, 20f)
            setUsePercentValues(true)
            isRotationEnabled = false
            setDrawEntryLabels(false)
            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.isWordWrapEnabled = true
        }
    }

    private fun setDataToChart() {
        with(binding.chartGas) {
            this.data = buildDataToChart()
            setExtraOffsets(5f, 8f, 5f, 5f)
            animateY(1400, Easing.EaseInOutQuad)
            holeRadius = 50f
            transparentCircleRadius = 60f
            isDrawHoleEnabled = true
            setHoleColor(ContextCompat.getColor(requireContext(), R.color.dutch))
            //add text in center
            setDrawCenterText(true);
            centerText = "Gastos de combustível"
            invalidate()
        }
    }

    private fun buildDataToChart(): PieData {
        val dataSet = PieDataSet(getPieEntries(50f, 50f), "").apply {
            sliceSpace = 9f
            colors = getColorsToChart()
        }
        return PieData(dataSet).apply {
            setValueFormatter(PercentFormatter())
            setValueTextSize(16f)
        }
    }

    private fun getColorsToChart() =
        arrayListOf(
            ContextCompat.getColor(requireContext(), R.color.bone),
            ContextCompat.getColor(requireContext(), R.color.blast)
        )

    private fun getPieEntries(gasoline: Float, alcohol: Float) =
        arrayListOf(
            PieEntry(gasoline, getString(R.string.gasoline_label)),
            PieEntry(alcohol, getString(R.string.alcohol_label))
        )
}