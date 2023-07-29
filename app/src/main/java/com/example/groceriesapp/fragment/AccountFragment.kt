package com.example.groceriesapp.fragment

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.groceriesapp.activity.LoginActivity
import com.example.groceriesapp.databinding.FragmentAccountBinding
import com.example.multityperecycleview.ChildModelClass
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : Fragment() {
    private lateinit var _binding: FragmentAccountBinding
    private val binding get() = _binding
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentAccountBinding.inflate(inflater,container,false)
        binding.tvLogout.setOnClickListener {
            var courseModalArrayList: ArrayList<ChildModelClass?>
            val sp = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = sp.edit()
            editor.putBoolean("data", false)
            editor.apply()


            FirebaseAuth.getInstance().signOut()
            val intent= Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }
       return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}