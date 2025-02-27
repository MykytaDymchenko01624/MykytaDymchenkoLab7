// Mykyta Dymchenko N01624422
package mykyta.dymchenko.n01624422

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.os.bundleOf

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PersonFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PersonFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var selectedItem: String? = null
    private var selectedIndex: Int = -1
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_person, container, false)

        view.setBackgroundColor(getResources().getColor(R.color.person_frag_bg))

        listView = view.findViewById(R.id.MykLV)

        val provinces = resources.getStringArray(R.array.provinces_array)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_activated_1, provinces)
        listView.adapter = adapter

        listView.choiceMode = ListView.CHOICE_MODE_SINGLE

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            selectedItem = provinces[position]
            selectedIndex = position + 1
            sendDataToSettingsFragment()
        }

        return view
    }

    private fun sendDataToSettingsFragment() {
        val result = bundleOf("selectedItem" to selectedItem, "selectedIndex" to selectedIndex)
        parentFragmentManager.setFragmentResult("requestKey", result)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PersonFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PersonFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}