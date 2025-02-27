// Mykyta Dymchenko N01624422
package mykyta.dymchenko.n01624422

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NickFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NickFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButton1: RadioButton
    private lateinit var radioButton2: RadioButton
    private lateinit var radioButton3: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_nick, container, false)

        view.setBackgroundColor(getResources().getColor(R.color.nick_frag_bg))

        val fullNameTextView: TextView = view.findViewById(R.id.MykStudentTV)
        fullNameTextView.text = getString(R.string.mykyta_dymchenko)
        fullNameTextView.setTextColor(Color.BLUE)

        radioGroup = view.findViewById(R.id.MykRG)
        radioButton1 = view.findViewById(R.id.MykRB1)
        radioButton2 = view.findViewById(R.id.MykRB2)
        radioButton3 = view.findViewById(R.id.MykRB3)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = view.findViewById<RadioButton>(checkedId)
            val selectedText = selectedRadioButton.text.toString()

            showAlertDialog(selectedText)
        }

        return view
    }

    private fun showAlertDialog(message: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setIcon(R.drawable.images)
        builder.setTitle(getString(R.string.mykyta_dymchenko))
        builder.setMessage(message)
        builder.setCancelable(false)
        builder.setPositiveButton(getString(R.string.alert_ok)) { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NickFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NickFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}