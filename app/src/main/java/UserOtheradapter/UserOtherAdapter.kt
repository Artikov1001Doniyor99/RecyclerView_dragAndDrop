package UserOtheradapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.artikov.modul_5_lesson_4_3.R
import kotlinx.android.synthetic.main.item_user.view.*
import models.User
import utils.itemTouchHelperAdapter
import java.util.*
import kotlin.collections.ArrayList

class UserOtherAdapter(var context:Context,var userList: ArrayList<User>):RecyclerView.Adapter<UserOtherAdapter.Vh>(),itemTouchHelperAdapter {



    inner class Vh(itemView: View):RecyclerView.ViewHolder(itemView){

        fun onBind(user: User){
            itemView.tv_1.text = user.name
            itemView.tv_2.text = user.password


            itemView.animation = AnimationUtils.loadAnimation(context, R.anim.anim2)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(userList[position])
    }

    override fun getItemCount(): Int  = userList.size
    override fun onItemMove(fromPosition: Int, toPosition: Int) {
       if (fromPosition < toPosition){
           for (i in fromPosition until toPosition){
               Collections.swap(userList,i,i+1)
           }
       }else{
           for (i in fromPosition downTo toPosition+1){
               Collections.swap(userList,i,i-1)
           }
       }
        notifyItemMoved(fromPosition,toPosition)
    }

    override fun onItemDismiss(position: Int) {
        userList.removeAt(position)
        notifyItemRemoved(position)
    }

}