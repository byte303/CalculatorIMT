package n71.inc.calculatorimt.model

object Units {

    fun unitsHeight(position : Int) : String{

        return when(position){
            0 -> "kg"
            else -> "ib"

        }
    }

    fun unitsWeight(position : Int) : String{
        return when(position){
            0 -> "cm"
            else -> "in"
        }
    }
}
