package com.example.groceriesapp.fragment

import android.content.Context
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceriesapp.activity.LoginActivity
import com.example.groceriesapp.databinding.FragmentAccountBinding
import com.example.groceriesapp.utils.ParantAdapter
import com.example.multityperecycleview.ChildModelClass
import com.example.multityperecycleview.ParentModelClass
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private val PREFS_NAME = "shared preferences"
private lateinit var preferences: SharedPreferences
/**
 * A simple [Fragment] subclass.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : Fragment() {
    private lateinit var _binding: FragmentAccountBinding
    var latestlist:ArrayList<ChildModelClass> = ArrayList()
    var latestlist1:ArrayList<ChildModelClass> = ArrayList()
    var latestlist2:ArrayList<ChildModelClass> = ArrayList()
    var latestlist3:ArrayList<ChildModelClass> = ArrayList()
    var latestlist4:ArrayList<ChildModelClass> = ArrayList()
    var latestlist5:ArrayList<ChildModelClass> = ArrayList()
    var latestlist6:ArrayList<ChildModelClass> = ArrayList()

    private val parentModelClass:ArrayList<ParentModelClass> = ArrayList()
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
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun loadData() {
        preferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = preferences.getString("Fruits", null)
        val type: Type = object : TypeToken<ArrayList<ChildModelClass?>?>() {}.getType()
        latestlist = gson.fromJson(json, type)

        val json1 = preferences.getString("Grossary", null)
        val type1: Type = object : TypeToken<ArrayList<ChildModelClass?>?>() {}.getType()
        latestlist1 = gson.fromJson(json1, type1)

        val json2 = preferences.getString("Backery", null)
        val type2: Type = object : TypeToken<ArrayList<ChildModelClass?>?>() {}.getType()
        latestlist2 = gson.fromJson(json2, type2)

        val json3 = preferences.getString("Vegetable", null)
        val type3: Type = object : TypeToken<ArrayList<ChildModelClass?>?>() {}.getType()
        latestlist3 = gson.fromJson(json3, type3)

        val json4 = preferences.getString("Bevagers", null)
        val type4: Type = object : TypeToken<ArrayList<ChildModelClass?>?>() {}.getType()
        latestlist4 = gson.fromJson(json4, type4)

        val json5 = preferences.getString("Dryfruits", null)
        val type5: Type = object : TypeToken<ArrayList<ChildModelClass?>?>() {}.getType()
        latestlist5 = gson.fromJson(json5, type5)

        val json6 = preferences.getString("Snacks", null)
        val type6: Type = object : TypeToken<ArrayList<ChildModelClass?>?>() {}.getType()
        latestlist6 = gson.fromJson(json6, type6)
    }
    private fun buildRecyclerView() {
        parentModelClass.add(ParentModelClass("Fruits",latestlist))
        parentModelClass.add(ParentModelClass("Grossary",latestlist1))
        parentModelClass.add(ParentModelClass("Grossary",latestlist2))
        parentModelClass.add(ParentModelClass("Grossary",latestlist3))
        parentModelClass.add(ParentModelClass("Grossary",latestlist4))
        parentModelClass.add(ParentModelClass("Grossary",latestlist5))
        parentModelClass.add(ParentModelClass("Grossary",latestlist6))


        val parantAdapter= ParantAdapter(parentModelClass, requireContext())
        binding.RC20.layoutManager= LinearLayoutManager(requireContext())
        //    binding.RC25.layoutManager = GridLayoutManager(applicationContext,2)
        binding.RC20.adapter=parantAdapter
        parantAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        loadData()
        buildRecyclerView()
        super.onResume()
    }
}