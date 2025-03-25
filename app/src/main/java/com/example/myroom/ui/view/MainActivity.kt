package com.example.myroom.ui.view

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myroom.R
import com.example.myroom.data.database.AppDatabase
import com.example.myroom.data.model.User
import com.example.myroom.data.dao.UserDao
import com.example.myroom.respository.UserRepository
import com.example.myroom.UserAdapter
import com.example.myroom.ui.viewmodel.UserViewModel

class MainActivity : AppCompatActivity(){

    private lateinit var etUserName: EditText
    private lateinit var etUserAge: EditText
    private lateinit var btnAddUser: Button
    private lateinit var btnShowUsers: Button
    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var userAdapter: UserAdapter

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

try {


        etUserName = findViewById(R.id.etUserName)
        etUserAge = findViewById(R.id.etUserAge)
        btnAddUser = findViewById(R.id.btnAddUser)
        btnShowUsers = findViewById(R.id.btnShowUsers)
        recyclerViewUsers = findViewById(R.id.recyclerViewUsers)


        userAdapter = UserAdapter()
        recyclerViewUsers.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }


        val database = AppDatabase.getDatabase(applicationContext)
        val userDao: UserDao = database.userDao()


        val userRepository = UserRepository(userDao)


        userViewModel =UserViewModel(userRepository)


        btnAddUser.setOnClickListener {
            val name = etUserName.text.toString()
            val age = etUserAge.text.toString().toIntOrNull()

            if (name.isNotEmpty() && age != null) {
                val user = User(name = name, age = age)
                userViewModel.insert(user)
                Toast.makeText(this,"User ajauter avec success aux base de donner",Toast.LENGTH_SHORT).show()
                etUserName.text.clear()
                etUserAge.text.clear()
            }
        }




        btnShowUsers.setOnClickListener {
            userViewModel.allUsers.observe(this) { users ->
                users?.let { userAdapter.submitList(it) }
            }
        }
}catch (e:Exception){
    Log.d("MainErrore","${e.message}")
}
    }

}
