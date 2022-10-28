package n71.inc.calculatorimt.ui.dashboard

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.paperdb.Paper
import n71.inc.calculatorimt.R
import n71.inc.calculatorimt.adapter.AdapterFavorite
import n71.inc.calculatorimt.databinding.FragmentDashboardBinding
import n71.inc.calculatorimt.helper.IClickModel
import n71.inc.calculatorimt.model.Model

class DashboardFragment : Fragment(), IClickModel {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!
    private var array = ArrayList<Model>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
            ViewModelProvider(this)[DashboardViewModel::class.java]

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        array = Paper.book().read("HISTORY", ArrayList())

        checkSize()

        val list = binding.listHistory
        list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        list.adapter = AdapterFavorite(array,this)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkSize(){
        if(array.size > 0)
            binding.txtLong.visibility = View.GONE
        else
            binding.txtLong.visibility = View.VISIBLE
    }

    override fun onResultModel(model: Model) {
        // setup the alert builder
        val builder = AlertDialog.Builder(context)
        builder.setTitle(resources.getString(R.string.select_action))

        // add a list
        val animals = arrayOf(
            //  resources.getString(R.string.share),
            resources.getString(R.string.delete)
        )
        builder.setItems(animals) { _, which ->
            when (which) {
                //0 -> { /* horse */ }
                0 -> {
                    array.remove(model)
                    Paper.book().write("HISTORY", array)

                    checkSize()
                    binding.listHistory.adapter?.notifyDataSetChanged()
                }
            }
        }
        // create and show the alert dialog
        val dialog = builder.create()
        dialog.show()
    }

}