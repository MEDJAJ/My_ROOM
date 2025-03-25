package com.example.myroom


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myroom.data.model.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var userList: List<User> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.tvUserName.text = user.name
        holder.tvUserAge.text = user.age.toString()+" ans"
    }


    override fun getItemCount(): Int {
        return userList.size
    }


    fun submitList(users: List<User>) {
        userList = users
        notifyDataSetChanged()
    }


    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

         val tvUserName: TextView = itemView.findViewById(R.id.tvUserName)
         val tvUserAge: TextView = itemView.findViewById(R.id.tvUserAge)



    }
}
