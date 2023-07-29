package com.example.groceriesapp.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceriesapp.R
import com.example.groceriesapp.databinding.FragmentBlankBinding
import com.example.groceriesapp.databinding.FragmentShopBinding
import com.example.groceriesapp.utils.ParantAdapter
import com.example.multityperecycleview.ChildModelClass
import com.example.multityperecycleview.ParentModelClass
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private val PREFS_NAME = "SharedPreferences"
private lateinit var preferences: SharedPreferences

class BlankFragment : Fragment() {
    private lateinit var _binding: FragmentBlankBinding
    private val binding get() = _binding
    @Expose
    var latestlist:ArrayList<ChildModelClass> = ArrayList()
    @Expose
    var latestlist1:ArrayList<ChildModelClass> = ArrayList()
    private val parentModelClass:ArrayList<ParentModelClass> = ArrayList()
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
        _binding= FragmentBlankBinding.inflate(inflater,container,false)
        loadData()
        buildRecyclerView()
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
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

    }
    private fun buildRecyclerView() {
        parentModelClass.add(ParentModelClass("Fruits",latestlist))
        parentModelClass.add(ParentModelClass("Fruits",latestlist1))
        val parantAdapter= ParantAdapter(parentModelClass, requireContext())
        binding.RC24.layoutManager= LinearLayoutManager(requireContext())
        binding.RC24.adapter=parantAdapter
        parantAdapter.notifyDataSetChanged()
    }
}