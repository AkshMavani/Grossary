package com.example.groceriesapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceriesapp.R
import com.example.groceriesapp.databinding.FragmentHomeBinding
import com.example.groceriesapp.utils.ProductAdapter
import com.example.groceriesapp.utils.ProductModelClass

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var _binding: FragmentHomeBinding
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
        val product = ArrayList<ProductModelClass>()
        _binding= FragmentHomeBinding.inflate(inflater,container,false)
       binding.rcProduct.layoutManager = LinearLayoutManager(context)

        // ArrayList of class ItemsViewModel
       product.add(ProductModelClass(R.drawable.allfruitss,"Fruits"))
       product.add(ProductModelClass(R.drawable.backery1,"Backery"))
       product.add(ProductModelClass(R.drawable.vegetables,"Vegetable"))
       product.add(ProductModelClass(R.drawable.bevagerss,"Beverages"))
       product.add(ProductModelClass(R.drawable.grossary,"Grossarys"))
       product.add(ProductModelClass(R.drawable.df,"Dry Fruits & Nuts"))
       product.add(ProductModelClass(R.drawable.snacks,"snacks"))




        // This will pass the ArrayList to our Adapter
        val adapter = ProductAdapter(product)
        binding.rcProduct.layoutManager = GridLayoutManager(context,3,LinearLayoutManager.VERTICAL,false)
        binding.rcProduct.adapter = adapter
        return binding.root
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}