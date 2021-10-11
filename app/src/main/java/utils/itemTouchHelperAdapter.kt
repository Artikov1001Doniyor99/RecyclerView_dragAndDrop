package utils


interface itemTouchHelperAdapter {

    fun onItemMove(fromPosition:Int,toPosition:Int)

    fun onItemDismiss(position: Int)

}