package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random as Random1

class SecondFragment : Fragment() {

    private var backButton: Button? = null
    private var result: TextView? = null
    private var listener: OnBackButtonPressedListener? = null
    private var randomNum = 0

    interface OnBackButtonPressedListener {
        fun onBackButtonPressed(previousNumber: Int)
        fun onSystemBackButtonPressed(previousNumber: Int)
       // fun onBackPressed()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnBackButtonPressedListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        randomNum = generate(min, max)
        listener?.onSystemBackButtonPressed(randomNum)

        result?.text = randomNum.toString()

        backButton?.setOnClickListener {
            // TODO: implement back
            listener?.onBackButtonPressed(randomNum)
        }
    }

    private fun generate(min: Int, max: Int): Int {
        // TODO: generate random number
        return ((Math.random() * (max - min + 1) + min).toInt())
    }

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            // TODO: implement adding arguments
            args.putInt(MIN_VALUE_KEY, min)
            args.putInt(MAX_VALUE_KEY, max)
            fragment.arguments = args
            return fragment
        }
        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}
