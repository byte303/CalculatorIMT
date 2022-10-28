package n71.inc.calculatorimt.ui.home

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import io.paperdb.Paper
import n71.inc.calculatorimt.R
import n71.inc.calculatorimt.model.Model
import n71.inc.calculatorimt.model.Units
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel : ViewModel() {

    private val HEIGHT = Paper.book().read("HEIGHT", 0)
    private val WEIGHT = Paper.book().read("WEIGHT", 0)
    private var data = Model()

    var edtHeight : ObservableField<String> = ObservableField<String>("")
    var edtWeight : ObservableField<String> = ObservableField<String>("")

    var txtResult : ObservableField<String> = ObservableField<String>("")

    var txtMiniIdel : ObservableField<String> = ObservableField<String>("")
    var txtMaxIdeal : ObservableField<String> = ObservableField<String>("")

    var txtResultLong : ObservableField<String> = ObservableField<String>("")

    var linearResultVisible : ObservableField<Boolean> = ObservableField<Boolean>(false)

    var txtResultColor = ObservableInt()

    var idKg : ObservableField<String> = ObservableField<String>(Units.unitsHeight(HEIGHT))
    var idSm : ObservableField<String> = ObservableField<String>(Units.unitsWeight(WEIGHT))

    fun clickFavorite(context : Context){
        if(data.date != ""){
            val array = Paper.book().read("HISTORY", ArrayList<Model>())
            array.add(data)
            Paper.book().write("HISTORY", array)
            Toast.makeText(context,context.resources.getText(R.string.add_favorite),Toast.LENGTH_SHORT).show()
        }
    }

    fun btnDoneClick(view : View) {
        if(edtHeight.get().toString().isNotEmpty()){
            if(edtWeight.get().toString().isNotEmpty()) {

                data = Model()

                //loadAd()

                linearResultVisible.set(true)

                var height = edtHeight.get().toString().toDouble()
                var weight = edtWeight.get().toString().toDouble()

                data.weight = edtWeight.get() + " " + view.context.resources.getStringArray(R.array.array_weight)[WEIGHT]
                data.height = edtHeight.get() + " " + view.context.resources.getStringArray(R.array.array_height)[WEIGHT]

                if(HEIGHT == 1) height = (height / 0.393701) / 100
                else height /= 100

                if(WEIGHT == 1) weight /= 2.20462

                val value = weight / (height * height)

                txtResult.set(String.format("%.1f", value))

                txtMiniIdel.set(String.format("%.0f %s", height * height * 18.5,view.context.resources.getStringArray(R.array.array_weight)[WEIGHT]))
                txtMaxIdeal.set(String.format("%.0f %s", height * height * 25,view.context.resources.getStringArray(R.array.array_weight)[WEIGHT]))

                data.imb = txtResult.get().toString()

                if (value <= 16) {
                    txtResultLong.set(view.context.getString(R.string.text_stadia_1))
                    txtResultColor.set(view.context.resources.getColor(R.color.stadia1))
                    data.colors = view.context.resources.getColor(R.color.stadia1)
                    data.result = view.context.getString(R.string.text_stadia_1)
                }
                else if (value > 16 && value <= 18.5){
                    txtResultLong.set(view.context.getString(R.string.text_stadia_2))
                    txtResultColor.set(view.context.resources.getColor(R.color.stadia2))
                    data.colors = view.context.resources.getColor(R.color.stadia2)
                    data.result = view.context.getString(R.string.text_stadia_2)
                }
                else if(value > 18.5 && value <= 25) {
                    txtResultLong.set(view.context.getString(R.string.text_stadia_3))
                    txtResultColor.set(view.context.resources.getColor(R.color.stadia3))
                    data.colors = view.context.resources.getColor(R.color.stadia3)
                    data.result = view.context.getString(R.string.text_stadia_3)
                }
                else if(value > 25 && value <= 30) {
                    txtResultLong.set(view.context.getString(R.string.text_stadia_4))
                    txtResultColor.set(view.context.resources.getColor(R.color.stadia4))
                    data.colors = view.context.resources.getColor(R.color.stadia4)
                    data.result = view.context.getString(R.string.text_stadia_4)
                }
                else if(value > 30 && value <= 35) {
                    txtResultLong.set(view.context.getString(R.string.text_stadia_5))
                    txtResultColor.set(view.context.resources.getColor(R.color.stadia5))
                    data.colors = view.context.resources.getColor(R.color.stadia5)
                    data.result = view.context.getString(R.string.text_stadia_5)
                }
                else if(value > 35 && value <= 40) {
                    txtResultLong.set(view.context.getString(R.string.text_stadia_6))
                    txtResultColor.set(view.context.resources.getColor(R.color.stadia6))
                    data.colors = view.context.resources.getColor(R.color.stadia6)
                    data.result = view.context.getString(R.string.text_stadia_6)
                }
                else if(value > 40) {
                    txtResultLong.set(view.context.getString(R.string.text_stadia_7))
                    txtResultColor.set(R.color.stadia7)
                    data.colors = view.context.resources.getColor(R.color.stadia7)
                    data.result = view.context.getString(R.string.text_stadia_7)
                }
                val sdf = SimpleDateFormat("dd.M.yyyy")

                data.date = sdf.format(Date())
            }
            else Toast.makeText(view.context,view.context.getString(R.string.not_weight),Toast.LENGTH_SHORT).show()
        }
        else Toast.makeText(view.context,view.context.getString(R.string.not_height),Toast.LENGTH_SHORT).show()
    }
}