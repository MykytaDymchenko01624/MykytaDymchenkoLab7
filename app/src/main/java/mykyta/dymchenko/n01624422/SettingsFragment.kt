// Mykyta Dymchenko N01624422
package mykyta.dymchenko.n01624422

import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var clockTextView: TextView
    private lateinit var provinceTextView: TextView
    private lateinit var indexTextView: TextView
    private val handler = Handler(Looper.getMainLooper())
    private val updateClockRunnable = object : Runnable {
        override fun run() {
            updateClock()
            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        clockTextView = view.findViewById(R.id.MykClockTV)
        provinceTextView = view.findViewById(R.id.MykProvinceTV)
        indexTextView = view.findViewById(R.id.MykIndexTV)

        provinceTextView.text = getString(R.string.province)
        indexTextView.text = getString(R.string.index)

        view.setBackgroundColor(getResources().getColor(R.color.settings_frag_bg))

        parentFragmentManager.setFragmentResultListener("requestKey", this) { _, bundle ->
            val selectedItem = bundle.getString("selectedItem")
            val selectedIndex = bundle.getInt("selectedIndex")

            provinceTextView.text = selectedItem
            indexTextView.text = selectedIndex.toString()

            setIndexColor(selectedItem)
        }

        return view
    }

    private fun updateClock() {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val currentTime = dateFormat.format(calendar.time)
        clockTextView.text = currentTime
    }

    private fun setIndexColor(province: String?) {
        val provinces = resources.getStringArray(R.array.provinces_array)
        val colors = resources.getIntArray(R.array.province_colors)

        val provinceIndex = provinces.indexOf(province)

        if (provinceIndex != -1 && provinceIndex < colors.size) {
            indexTextView.setTextColor(colors[provinceIndex])
        } else {
            indexTextView.setTextColor(Color.BLACK)
        }
    }

    override fun onResume() {
        super.onResume()
        handler.post(updateClockRunnable)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(updateClockRunnable)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}