package com.example.groceriesapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceriesapp.R
import com.example.groceriesapp.databinding.FragmentCartBinding
import com.example.groceriesapp.databinding.FragmentFavouriteBinding
import com.example.groceriesapp.utils.CartAdapter
import com.example.groceriesapp.utils.DataModelClass
import com.example.groceriesapp.utils.FavouriteAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavouriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavouriteFragment : Fragment() {
    private lateinit var _binding: FragmentFavouriteBinding
    private val binding get() = _binding
    val arr:ArrayList<DataModelClass> = ArrayList()
    val arrKey:ArrayList<String> = ArrayList()

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
        _binding= FragmentFavouriteBinding.inflate(inflater,container,false)
        binding.rcFavourite.layoutManager= LinearLayoutManager(context)
        FirebaseDatabase.getInstance().reference.child("FavouriteData").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                arr.clear()
                for (i in snapshot.children){
                    val message = i.getValue(DataModelClass::class.java)
                    arr.add(message!!)
                }
                Log.e("TAG", "arr: $arr")
                if (arr.isEmpty()){
                    binding.animationFavourite.visibility=View.VISIBLE
                    binding.btnFav.visibility=View.GONE
                    binding.TvFav.visibility=View.GONE
                    binding.txtFav.visibility=View.GONE
                    binding.rcFavourite.visibility
                }else{

                    val adapter = FavouriteAdapter(arr)
                    binding.rcFavourite.adapter=adapter
                }
            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        binding.btnFav.setOnClickListener {
         FirebaseDatabase.getInstance().reference.child("FavouriteData").addValueEventListener(object : ValueEventListener {
             override fun onDataChange(snapshot: DataSnapshot) {
                 for (i in snapshot.children){
                     val name=i.child("ftitle").value

                     FirebaseDatabase.getInstance().reference.child("data").child(name.toString()).setValue(i.value).addOnCompleteListener {
                         task->
                         if (task.isSuccessful){
                             FirebaseDatabase.getInstance().reference.child("FavouriteData").removeValue().addOnCompleteListener { task->
                                if ( task.isSuccessful){
                                   Toast.makeText(context,"remove",Toast.LENGTH_SHORT).show()
                                }
                             }

                         }
                     }
                 }
             }

             override fun onCancelled(error: DatabaseError) {

             }
         })
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavouriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}