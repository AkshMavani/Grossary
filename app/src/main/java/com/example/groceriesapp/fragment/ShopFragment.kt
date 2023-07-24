package com.example.groceriesapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.groceriesapp.ParantAdapter
import com.example.groceriesapp.R
import com.example.groceriesapp.databinding.FragmentShopBinding
import com.example.multityperecycleview.ChildModelClass
import com.example.multityperecycleview.ParentModelClass


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ShopFragment : Fragment() {
    private lateinit var _binding: FragmentShopBinding
    private val binding get() = _binding
    val parentModelClass:ArrayList<ParentModelClass> = ArrayList()
    val childModelClass:ArrayList<ChildModelClass> = ArrayList()

    val favoriteList:ArrayList<ChildModelClass> = ArrayList()
    val recentlist:ArrayList<ChildModelClass> = ArrayList()
    val latestlist:ArrayList<ChildModelClass> = ArrayList()
    val backeryList:ArrayList<ChildModelClass> = ArrayList()
    val beawareList:ArrayList<ChildModelClass> = ArrayList()
    val dryFruits:ArrayList<ChildModelClass> = ArrayList()
    val snacks:ArrayList<ChildModelClass> = ArrayList()




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
        _binding= FragmentShopBinding.inflate(inflater,container,false)
        val slidermodel = ArrayList<SlideModel>()
        slidermodel.add(SlideModel(R.drawable.discount,ScaleTypes.FIT))
        slidermodel.add(SlideModel(R.drawable.dis1,ScaleTypes.FIT))
        slidermodel.add(SlideModel(R.drawable.dis3,ScaleTypes.FIT))

        binding.imageslider.setImageList(slidermodel)
        val layoutManager = LinearLayoutManager(context)
        binding.rc.layoutManager = layoutManager
        latestlist.add(ChildModelClass(R.drawable.banana,"100","Apple","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        latestlist.add(ChildModelClass(R.drawable.mango,"20","Mango","1kg","Organic Yellow Mango  ","A mango is a sweet tropical fruit, and it's also the name of the trees on which the fruit grows","800gr","3"))
        latestlist.add(ChildModelClass(R.drawable.apple,"10","Banana","12pcs"," Natural Banana","Bananas are long, curved fruits with smooth, yellow, and sometimes slightly green skin","50gr","2"))
        latestlist.add(ChildModelClass(R.drawable.orange,"30","Orange","1kg","Organic Red orange","orange, any of several species of small trees or shrubs of the genus Citrus of the family Rutaceae and their nearly round fruits, which have leathery and oily rinds and edible, juicy inner flesh.","50gr","5"))
        latestlist.add(ChildModelClass(R.drawable.stawbary,"10","stawbary","12pcs","Organic Red Stawberry","The strawberry is a succulent and fragrant fruit of bright red colour, obtained from the plant with the same name.","100gr","5"))
        parentModelClass.add(ParentModelClass("Fruits",latestlist))

        recentlist.add(ChildModelClass(R.drawable.dal,"10","Yellow pigeon peas","500gr","Organic pigeon peas","Toor dal is also called pigeon peas and belongs to the family of legumes. It is rich in proteins and fibres. Toor dal is dried and split peas (seeds) of pigeon peas plant","30gr","4.5"))
        recentlist.add(ChildModelClass(R.drawable.rice,"12","Rice","500gr","Organic Rice","rice, (Oryza sativa), edible starchy cereal grain and the grass plant (family Poaceae) by which it is produced. ","95gr","4"))
        recentlist.add(ChildModelClass(R.drawable.chaipatti,"15","Tea Leaf","1kg","Natural Tea Leaf","Tea plant is an evergreen shrub with bright green, shiny leaves that are often hairy on their underside. The tea plants' white, scented flowers occur either on their own or in clusters of two to four.","80gr","2"))
        recentlist.add(ChildModelClass(R.drawable.suger,"10","suger","500gr","Suger" ,"a sweet substance that is made up wholly or mostly of sucrose, is colorless or white when pure, is obtained from plants","50gr","4.5"))
        recentlist.add(ChildModelClass(R.drawable.wheat,"20","Wheat","1kg","Organic Wheat  ","Wheat is a bunch grass with upright tillers. The leaves are rolled in the whorl. Leaf blades are smooth near the base and rough near tip on the upper side.","100gr","5"))
        parentModelClass.add(ParentModelClass("Groceries",recentlist))

        favoriteList.add(ChildModelClass(R.drawable.brinjal,"10","Brinjal","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        favoriteList.add(ChildModelClass(R.drawable.carrot,"10","Carrot","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        favoriteList.add(ChildModelClass(R.drawable.corn,"10","Corn","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        favoriteList.add(ChildModelClass(R.drawable.tometo,"10","Tometo","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        favoriteList.add(ChildModelClass(R.drawable.poteto1,"10","Poteto","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        parentModelClass.add(ParentModelClass("Fresh vegetables ",favoriteList))

        backeryList.add(ChildModelClass(R.drawable.bread,"10","Bread","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        backeryList.add(ChildModelClass(R.drawable.baget,"10","Baget","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        backeryList.add(ChildModelClass(R.drawable.muffin,"10","Muffin","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        backeryList.add(ChildModelClass(R.drawable.cokkie,"10","Cokkie","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        backeryList.add(ChildModelClass(R.drawable.backery,"10","Backery","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        parentModelClass.add(ParentModelClass("Backery ",backeryList))

        beawareList.add(ChildModelClass(R.drawable.applejuice,"10","Bread","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        beawareList.add(ChildModelClass(R.drawable.maaza,"10","Baget","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        beawareList.add(ChildModelClass(R.drawable.fanta,"10","Muffin","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        beawareList.add(ChildModelClass(R.drawable.pepsi,"10","Cokkie","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        beawareList.add(ChildModelClass(R.drawable.sprite,"10","Backery","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        parentModelClass.add(ParentModelClass("Beverages ",beawareList))

        dryFruits.add(ChildModelClass(R.drawable.badam,"10","Badam","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        dryFruits.add(ChildModelClass(R.drawable.kaju,"10","Kaju","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        dryFruits.add(ChildModelClass(R.drawable.walunt,"10","Walunt","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        dryFruits.add(ChildModelClass(R.drawable.pista,"10","Pista","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        dryFruits.add(ChildModelClass(R.drawable.raisnis,"10","Raisnis","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        parentModelClass.add(ParentModelClass("Dry fruits & Nuts ",dryFruits))

        snacks.add(ChildModelClass(R.drawable.kurkure1,"10","Kurkure","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        snacks.add(ChildModelClass(R.drawable.layschips,"10","chips","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        snacks.add(ChildModelClass(R.drawable.tags,"10","tagschips","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        snacks.add(ChildModelClass(R.drawable.nachoes,"10","nachoes","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        snacks.add(ChildModelClass(R.drawable.ragisticks,"10","sticks","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        parentModelClass.add(ParentModelClass("snacks ",snacks))
//        childModelClass.clear()
//        childModelClass.add(ChildModelClass(R.drawable.mango,"20","Mango","12pcs","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
//        childModelClass.add(ChildModelClass(R.drawable.apple,"20","Mango","12pcs","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
//        childModelClass.add(ChildModelClass(R.drawable.mango,"20","Mango","12pcs","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
//        childModelClass.add(ChildModelClass(R.drawable.orange,"20","Mango","12pcs","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
//        childModelClass.add(ChildModelClass(R.drawable.splash,"20","Mango","12pcs","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
//        childModelClass.add(ChildModelClass(R.drawable.banana,"20","Mango","12pcs","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
//        childModelClass.add(ChildModelClass(R.drawable.mango,"20","Mango","12pcs","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
//        childModelClass.add(ChildModelClass(R.drawable.banana,"20","Mango","12pcs","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
//        childModelClass.add(ChildModelClass(R.drawable.apple,"20","Mango","12pcs","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
//        childModelClass.add(ChildModelClass(R.drawable.orange,"20","Mango","12pcs","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
//
//        parentModelClass.add(ParentModelClass("Recent",childModelClass))
//        parentModelClass.add(ParentModelClass("Recent1",childModelClass))
//        parentModelClass.add(ParentModelClass("Recent2",childModelClass))
//        parentModelClass.add(ParentModelClass("Recent3",childModelClass))
//        parentModelClass.add(ParentModelClass("Recent4",childModelClass))
//        parentModelClass.add(ParentModelClass("Recent5",childModelClass))
//        val myLinearLayoutManager = object : LinearLayoutManager(context) {
//            override fun canScrollVertically(): Boolean {
//                return false
//            }
//        }
        val parantAdapter= ParantAdapter(parentModelClass, requireContext())
       // binding.rc.layoutManager = myLinearLayoutManager
        binding.rc.layoutManager=LinearLayoutManager(context)
       binding.rc.adapter=parantAdapter
        parantAdapter.notifyDataSetChanged()
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShopFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}