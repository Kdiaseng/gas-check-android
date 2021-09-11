package br.com.gascheck.ui.home

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val dataEntries = ArrayList<PieEntry>()
        dataEntries.add(PieEntry(80f, getString(R.string.gasoline_label)))
        dataEntries.add(PieEntry(20f, getString(R.string.alcohol_label)))

        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#4DD0E1"))
        colors.add(Color.parseColor("#FFF176"))

        val dataSet = PieDataSet(dataEntries, "")
        val data = PieData(dataSet)

        with(binding.chartGas) {
            setUsePercentValues(true)
            description.text = ""
            //hollow pie chart
            isDrawHoleEnabled = false
            setTouchEnabled(false)
            setDrawEntryLabels(false)
            //adding padding
            setExtraOffsets(20f, 0f, 20f, 20f)
            setUsePercentValues(true)
            isRotationEnabled = false
            setDrawEntryLabels(false)
            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.isWordWrapEnabled = true

            data.setValueFormatter(PercentFormatter())
            dataSet.sliceSpace = 9f
            dataSet.colors = colors
            this.data = data
            data.setValueTextSize(16f)
            setExtraOffsets(5f, 8f, 5f, 5f)
            animateY(1400, Easing.EaseInOutQuad)

            holeRadius = 50f
            transparentCircleRadius = 60f
            isDrawHoleEnabled = true
            setHoleColor(Color.WHITE)

            //add text in center
            setDrawCenterText(true);
            centerText = "Gastos de combustível"

            invalidate()
        }


    }
}