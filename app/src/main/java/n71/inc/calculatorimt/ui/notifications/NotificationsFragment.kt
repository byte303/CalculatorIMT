package n71.inc.calculatorimt.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.paperdb.Paper
import n71.inc.calculatorimt.R
import n71.inc.calculatorimt.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        notificationsViewModel =
            ViewModelProvider(this)[NotificationsViewModel::class.java]

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Paper.init(requireContext())

        binding.spinnerHeight.setSelection(Paper.book().read("HEIGHT", 0))
        binding.spinnerWeight.setSelection(Paper.book().read("WEIGHT", 0))

        binding.btnSave.setOnClickListener {

            Paper.book().write("HEIGHT", binding.spinnerHeight.selectedItemPosition)
            Paper.book().write("WEIGHT", binding.spinnerWeight.selectedItemPosition)
            
            Toast.makeText(context,getString(R.string.data_save),Toast.LENGTH_SHORT).show()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}