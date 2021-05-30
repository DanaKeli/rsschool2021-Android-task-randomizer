package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var listener: OnGenerateButtonPressedListener? = null
    private var minValue: TextView? = null
    private var maxValue: TextView? = null

    interface OnGenerateButtonPressedListener {
        fun onGenerateButtonPressed(min: Int, max: Int)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnGenerateButtonPressedListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        minValue = view.findViewById(R.id.min_value)
        maxValue = view.findViewById(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        generateButton?.setOnClickListener {
        if (minValue?.text?.isNotEmpty() == true && maxValue?.text?.isNotEmpty() == true) {
            try {
                val min = minValue?.text.toString().toInt() ?: 0
                val max = maxValue?.text.toString().toInt() ?: 0
                if (min > max) {
                    Toast.makeText(context, "The minimum should be less or equal than the maximum", Toast.LENGTH_SHORT).show()
                } else if (min < 0 || max < 0) {
                    Toast.makeText(context, "The number must be greater than or equal to zero", Toast.LENGTH_SHORT).show()
                } else listener?.onGenerateButtonPressed(min, max)
            } catch (e: NumberFormatException) {
                Toast.makeText(context, "The numbers should be integers in the range from 0 to ${Integer.MAX_VALUE}", Toast.LENGTH_SHORT).show()
                minValue?.text = ""
                maxValue?.text = ""
            }
        } else {
            Toast.makeText(context, "Both fields must be filled in", Toast.LENGTH_SHORT).show()
        }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}
