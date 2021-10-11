package com.artikov.modul_5_lesson_4_3

import UserOtheradapter.UserOtherAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import models.User

class MainActivity : AppCompatActivity() {

    lateinit var userList: ArrayList<User>
    lateinit var userOtherAdapter: UserOtherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        userList = ArrayList()
        userOtherAdapter = UserOtherAdapter(this,userList)
        rv.adapter = userOtherAdapter
        loadData()

        val itemTouchHelper = object : ItemTouchHelper.Callback(){
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(dragFlags,swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                userOtherAdapter.onItemMove(viewHolder.adapterPosition,target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                userOtherAdapter.onItemDismiss(viewHolder.adapterPosition)
            }

        }
        val itemTouch_Helper = ItemTouchHelper(itemTouchHelper)
        itemTouch_Helper.attachToRecyclerView(rv)
    }

    private fun loadData() {
        for (i in 0..1000){
            userList.add(User("Janob Rasul","Captiva"))
        }
    }

}