package br.com.gascheck.util

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.gascheck.R
import br.com.gascheck.databinding.CircleButtonValueBinding
import com.google.android.material.card.MaterialCardView


class CircleButtonValue @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAtt: Int = 0
) : ConstraintLayout(context, attrs, defStyleAtt) {

    private var value: String? = null
    private val binding = CircleButtonValueBinding.inflate(LayoutInflater.from(context), this, true)
    var listener: (() -> Unit)? = null

    init {
        setLayout(attrs)
    }

    fun setOnClickListenerCircleButton(listener: ()-> Unit){
        this.listener = listener
    }

    private fun setLayout(attrs: AttributeSet?) {
        attrs?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(
                attributeSet, R.styleable.CircleButtonValue
            )
            val valueId =
                attributes.getResourceId(R.styleable.CircleButtonValue_circle_button_value, 0)
            if (valueId != 0) {
                value = context.getString(valueId)
                binding.textValue.text = value
            }
            binding.materialCard.setOnClickListener {
                listener?.invoke()
            }
            attributes.recycle()
        }
    }


}