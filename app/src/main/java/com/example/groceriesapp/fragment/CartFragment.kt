package com.example.groceriesapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceriesapp.databinding.FragmentCartBinding
import com.example.groceriesapp.utils.CartAdapter
import com.example.groceriesapp.utils.DataModelClass
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CartFragment : Fragment(){
    private lateinit var _binding: FragmentCartBinding
    private val binding get() = _binding
    val arr:ArrayList<DataModelClass> = ArrayList()
    val arrKey:ArrayList<String> = ArrayList()

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
        _binding= FragmentCartBinding.inflate(inflater,container,false)

        binding.rcCart.layoutManager=LinearLayoutManager(context)
        FirebaseDatabase.getInstance().reference.child("data").addValueEventListener(object : ValueEventListener {
            @SuppressLint("SetTextI18n", "SuspiciousIndentation")
            override fun onDataChange(snapshot: DataSnapshot) {
                arr.clear()
                var sum=0
              for (i in snapshot.children){
                  val message = i.getValue(DataModelClass::class.java)
                  Log.e("TAG", "child: ${i.child("price").value }", )
                val count=i.child("price").value.toString().toInt()
                  Log.e("TAG", "onDataChange: $count")
                  sum+=count
                  arr.add(message!!)
              }
                Log.e("TAG", "arr: $arr")
                Log.e("TAG", "onDataChange: $sum")
                binding.TvTotal.text="Go To CheckOut $sum₹"
                if (arr.isEmpty()){
                    binding.txtMyCart.visibility=View.GONE
                    binding.btnSum.visibility=View.GONE
                    binding.animationCart.visibility=View.VISIBLE
                    binding.rcCart.visibility=View.GONE
                }else {
                    binding.animationCart.visibility=View.GONE
                    val adapter = CartAdapter(arr)
                    binding.rcCart.adapter = adapter
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}