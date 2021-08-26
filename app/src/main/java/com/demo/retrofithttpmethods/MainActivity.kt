package com.demo.retrofithttpmethods

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        buttonCreate.setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {
        val user  = User("", editTextName.text.toString(), editTextEmail.text.toString(), "Active", "Male")
        viewModel.createNewUser(user)

    }

    private fun initViewModel() {
         viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getCreateNewUserObserver().observe(this, Observer <UserResponse?>{

            if(it  == null) {
                Toast.makeText(this@MainActivity, "Failed to create User", Toast.LENGTH_LONG).show()
            } else {
                //{"code":201,"meta":null,"data":{"id":2877,"name":"xxxxxaaaaabbbbb","email":"xxxxxaaaaabbbbb@gmail.com","gender":"male","status":"active"}}
                Toast.makeText(this@MainActivity, "Successfully created User", Toast.LENGTH_LONG).show()
            }
        })
    }
}