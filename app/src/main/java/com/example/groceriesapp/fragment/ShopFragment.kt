package com.example.groceriesapp.fragment

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceriesapp.utils.ParantAdapter
import com.example.groceriesapp.R
import com.example.groceriesapp.databinding.FragmentShopBinding
import com.example.groceriesapp.utils.ProductAdapter
import com.example.groceriesapp.utils.ProductModelClass
import com.example.groceriesapp.utils.SliderAdapter
import com.example.groceriesapp.utils.SliderData
import com.example.multityperecycleview.ChildModelClass
import com.example.multityperecycleview.ParentModelClass
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.smarteist.autoimageslider.SliderView
import java.lang.reflect.Type
import java.util.Locale


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ShopFragment : Fragment() {
    private lateinit var _binding: FragmentShopBinding
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private val REQUEST_CHECK_SETTINGS = 214
    private var REQUEST_CODE=100
    private val PREFS_NAME = "shared preferences"
    private lateinit var preferences: SharedPreferences
    private val binding get() = _binding
    private var url1 = "https://tse3.mm.bing.net/th?id=OIP.G3_sUJktiRNomSCbnuMstgHaDt&pid=Api&P=0&h=180"
    private var url2 = "https://img.freepik.com/free-psd/social-media-promo-template-sales_23-2149533430.jpg?w=1060&t=st=1690455289~exp=1690455889~hmac=8e4adcd34ab28c5714f6d8d5c7023008a7b14929044b9387eac697cd1a76ab3f"
    private var url3 = "https://dealroup.com/wp-content/uploads/2020/05/Grocery-Offers.jpg"
    private var url4="https://i.pinimg.com/originals/ef/81/00/ef8100cfcf584de09f75a1c3944e22b3.jpg"
    private val parentModelClass:ArrayList<ParentModelClass> = ArrayList()
    private val product:ArrayList<ProductModelClass> = ArrayList()
    val childModelClass:ArrayList<ChildModelClass> = ArrayList()

    private var favoriteList:ArrayList<ChildModelClass> = ArrayList()
    private var recentlist:ArrayList<ChildModelClass> = ArrayList()
    private var latestlist:ArrayList<ChildModelClass> = ArrayList()
    private var backeryList:ArrayList<ChildModelClass> = ArrayList()
    private var beawareList:ArrayList<ChildModelClass> = ArrayList()
    private var dryFruits:ArrayList<ChildModelClass> = ArrayList()
    private var snacks:ArrayList<ChildModelClass> = ArrayList()




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
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        val sliderDataArrayList = ArrayList<SliderData>()
        sliderDataArrayList.add(SliderData(url1))
        sliderDataArrayList.add(SliderData(url2))
        sliderDataArrayList.add(SliderData(url3))
        sliderDataArrayList.add(SliderData(url4))

        val adapter = SliderAdapter(context, sliderDataArrayList)

        product.add(ProductModelClass(R.drawable.allfruitss,"Fruits"))
        product.add(ProductModelClass(R.drawable.backery1,"Backery"))
        product.add(ProductModelClass(R.drawable.vegetables,"Vegetable"))
        product.add(ProductModelClass(R.drawable.bevagerss,"Beverages"))
        product.add(ProductModelClass(R.drawable.grossary,"Grossary"))
        product.add(ProductModelClass(R.drawable.df,"Dry Fruits & Nuts"))
        product.add(ProductModelClass(R.drawable.snacks,"snacks"))





        // This will pass the ArrayList to our Adapter
        val adapter1 = ProductAdapter(product)
        binding.rc1.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.rc1.adapter = adapter1

       binding.slider.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
        binding.slider.setSliderAdapter(adapter)

        binding.slider.scrollTimeInSec = 3
        binding.slider.isAutoCycle = true
        binding.slider.startAutoCycle()
        val layoutManager = LinearLayoutManager(context)
        binding.rc.layoutManager = layoutManager
//        latestlist.add(ChildModelClass(R.drawable.banana,"100","Apple","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
//        latestlist.add(ChildModelClass(R.drawable.mango1,"20","Mango","1kg","Organic Yellow Mango  ","A mango is a sweet tropical fruit, and it's also the name of the trees on which the fruit grows","800gr","3"))
//        latestlist.add(ChildModelClass(R.drawable.apple,"10","Banana","12pcs"," Natural Banana","Bananas are long, curved fruits with smooth, yellow, and sometimes slightly green skin","50gr","2"))
//        latestlist.add(ChildModelClass(R.drawable.orange,"30","Orange","1kg","Organic Red orange","orange, any of several species of small trees or shrubs of the genus Citrus of the family Rutaceae and their nearly round fruits, which have leathery and oily rinds and edible, juicy inner flesh.","50gr","5"))
//        latestlist.add(ChildModelClass(R.drawable.stawbary,"10","stawbary","12pcs","Organic Red Stawberry","The strawberry is a succulent and fragrant fruit of bright red colour, obtained from the plant with the same name.","100gr","5"))
//        parentModelClass.add(ParentModelClass("Fruits",latestlist))
//        recentlist.add(ChildModelClass(R.drawable.dal,"10","Yellow pigeon peas","500gr","Organic pigeon peas","Toor dal is also called pigeon peas and belongs to the family of legumes. It is rich in proteins and fibres. Toor dal is dried and split peas (seeds) of pigeon peas plant","30gr","4.5"))
//        recentlist.add(ChildModelClass(R.drawable.rice,"12","Rice","500gr","Organic Rice","rice, (Oryza sativa), edible starchy cereal grain and the grass plant (family Poaceae) by which it is produced. ","95gr","4"))
//        recentlist.add(ChildModelClass(R.drawable.chaipatti,"15","Tea Leaf","1kg","Natural Tea Leaf","Tea plant is an evergreen shrub with bright green, shiny leaves that are often hairy on their underside. The tea plants' white, scented flowers occur either on their own or in clusters of two to four.","80gr","2"))
//        recentlist.add(ChildModelClass(R.drawable.suger,"10","suger","500gr","Suger" ,"a sweet substance that is made up wholly or mostly of sucrose, is colorless or white when pure, is obtained from plants","50gr","4.5"))
//        recentlist.add(ChildModelClass(R.drawable.wheat,"20","Wheat","1kg","Organic Wheat  ","Wheat is a bunch grass with upright tillers. The leaves are rolled in the whorl. Leaf blades are smooth near the base and rough near tip on the upper side.","100gr","5"))
//        parentModelClass.add(ParentModelClass("Groceries",recentlist))
//        favoriteList.add(ChildModelClass(R.drawable.brinjal,"90","Brinjal","1kg","Natural Brinjal ","Brinjal or eggplant (Solanum melongena L.) is an important solanaceous crop of sub tropics and tropics.","100gr","5"))
//        favoriteList.add(ChildModelClass(R.drawable.carrot,"60","Carrot","1kg","Natural Red Carrot","Carrot, herbaceous, generally biennial plant that produces an edible taproot. Carrots are commonly grown as annual plants and can be eaten fresh or cooked.","100gr","5"))
//        favoriteList.add(ChildModelClass(R.drawable.corn,"50","Corn","1kg","Natural Corn"," Sweet corn boasts a number of vitamins, including: Pantothenic acid. Also called vitamin B5, this acid is found to some extent in nearly all foods.","100gr","3"))
//        favoriteList.add(ChildModelClass(R.drawable.tometo,"109","Tometo","1kg","Natural Tometo","tomato, Any fruit of the numerous cultivated varieties of Solanum lycopersicum (formerly Lycopersicon esculentum), a plant of the nightshade family. ","100gr","5"))
//        favoriteList.add(ChildModelClass(R.drawable.poteto1,"20","Poteto","1kg","Natural Poteto","It is a small plant with large leaves. The part of the potato that people eat is a tuber that grows under the ground","900gr","5"))
//        parentModelClass.add(ParentModelClass("Fresh vegetables ",favoriteList))
//
//        backeryList.add(ChildModelClass(R.drawable.bread,"50","Bread","12pcs","Fresh Bread","· Bread, baked food product made of flour or meal that is moistened, kneaded, and sometimes fermented.","100gr","4"))
//        backeryList.add(ChildModelClass(R.drawable.baget,"30","Baget","1pcs","Fresh  Baget"," Baget baked food product made of flour or meal that is moistened, kneaded, and sometimes fermented","80gr","5"))
//        backeryList.add(ChildModelClass(R.drawable.muffin,"40","Muffin","1pcs","Fresh Muffin ","A muffin is a small bread - or cake -like baked food. Muffins are similar to cupcakes.","60gr","5"))
//        backeryList.add(ChildModelClass(R.drawable.cokkie,"70","Cokkie","1pcs","Fresh Cokkies","A cookie ( American English ), or a biscuit ( British English ), is a baked or cooked snack or dessert that is typically small, flat and sweet.","100gr","5"))
//        backeryList.add(ChildModelClass(R.drawable.backery,"80","pastries","1pcs","Fresh pastries "," Pastry, stiff dough made from flour, salt, a relatively high proportion of fat, and a small proportion of liquid.","100gr","3"))
//        parentModelClass.add(ParentModelClass("Backery ",backeryList))
//
//        beawareList.add(ChildModelClass(R.drawable.applejuice,"100","Apple Juice","1pcs"," Red Apple Juice","Apple juice is 88% water and 11% carbohydrates (including 9% sugars), with negligible content of protein or fat.","100gr","5"))
//        beawareList.add(ChildModelClass(R.drawable.maaza,"80","Mango Juice","1pcs","Yellow Mango Juice","Mango Juice member of the cashew family and one of the most important and widely cultivated fruits of the tropical world","100gr","4.5"))
//        beawareList.add(ChildModelClass(R.drawable.fanta,"90","Orange Juice","1pcs","Orange Juice","An orange juice from concentrate is a mixture of water with frozen concentrated orange juice or concentrated orange juice for manufacturing. ","100gr","3"))
//        beawareList.add(ChildModelClass(R.drawable.pepsi,"60","Pepsi","1pcs","Black Graphs Juice","The original recipe also included sugar and vanilla. Bradham sought to create a fountain drink that was appealing and would aid in digestion and boost energy.","100gr","3.5"))
//        beawareList.add(ChildModelClass(R.drawable.sprite,"50","Lemon Juice","1pcs","Lemon Juice","Sprite’s crisp taste with hit of lemon-lime flavour gives you that unmatched cut-through refreshment.","100gr","5"))
//        parentModelClass.add(ParentModelClass("Beverages ",beawareList))
//
//        dryFruits.add(ChildModelClass(R.drawable.badam,"1000","Almond","1kg","Natural Almond","The almond is often thought of as a nut, though it's technically a drupe fruit produced by the almond tree (Prunus dulcis; Prunus amygdalus). ","80gr","5"))
//        dryFruits.add(ChildModelClass(R.drawable.kaju,"800","Cashewnuts","1kg","Natural Cashewnuts","Cashew, Anacardium occidentale, is an evergreen tree in the family Anacardiaceae grown for its edible fruits (nuts). ","100gr","4.6"))
//        dryFruits.add(ChildModelClass(R.drawable.walunt,"900","Walunt","1kg","Natural Walunt ","Walnuts are the round, single-seed stone fruits of the walnut tree. They are commonly used for food.","100gr","5"))
//        dryFruits.add(ChildModelClass(R.drawable.pista,"600","Pistachios","1kg","Natural Pistachios ","pistachio, (Pistacia vera), small tree of the cashew family (Anacardiaceae) and its edible seeds, grown in dry lands in warm or temperate climates. ","100gr","5"))
//        dryFruits.add(ChildModelClass(R.drawable.raisnis,"500","Raisnis","1kg","Natural Raisnis ","Raisins are a tasty and convenient food that can add a range of nutrients to your diet. ","100gr","5"))
//        parentModelClass.add(ParentModelClass("Dry fruits & Nuts ",dryFruits))
//
//        snacks.add(ChildModelClass(R.drawable.kurkure1,"10","Kurkure","1pcs","Kurkure"," Kurkure is a brand of corn puffs, developed and produced by Pepsico India. The snack is named after the Hindi word for crunchy.","80gr","4"))
//        snacks.add(ChildModelClass(R.drawable.layschips,"40","chips","1pcs","Chips","A potato chip (North American English and Australian English; often just chip) or crisp (British and Irish English) is a thin slice of potato that has been deep fried, baked, or air fried until crunchy.","100gr","3.8"))
//        snacks.add(ChildModelClass(R.drawable.tags,"60","tagschips","1pcs","Tags Chips","A potato chip (North American English and Australian English; often just chip) or crisp (British and Irish English) is a thin slice of potato that has been deep fried, baked, or air fried until crunchy.","80gr","4"))
//        snacks.add(ChildModelClass(R.drawable.nachoes,"50","nachoes","1pcs","Nachoes","Nachos are a snack food made with tortilla chips and cheese. Sometimes they have jalapeños, sauce, beans, guacamole or meat on them.","90gr","3.4"))
//        snacks.add(ChildModelClass(R.drawable.ragisticks,"20","sticks","1pcs","Sticks","A stick is a thin branch which has fallen off a tree.people carrying bundles of dried sticks to sell for firewood. ","100gr","5"))
//        parentModelClass.add(ParentModelClass("snacks ",snacks))



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


//        val product = ArrayList<ProductModelClass>()
//        product.add(ProductModelClass(R.drawable.fruits,"Fruits"))
//        product.add(ProductModelClass(R.drawable.backery,"Backery"))
//        product.add(ProductModelClass(R.drawable.apple,"Vegetable"))
//        product.add(ProductModelClass(R.drawable.apple,"Beverages"))
//        product.add(ProductModelClass(R.drawable.banana,"Backery"))
//        product.add(ProductModelClass(R.drawable.stawbary,"Dry Fruits & Nuts"))
//        product.add(ProductModelClass(R.drawable.stawbary,"snacks"))
//
//        val adapter1 = ProductAdapter(product)
//        binding.rc1.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
//        binding.rc1.adapter = adapter1
//        val parantAdapter= ParantAdapter(parentModelClass, requireContext())
//       // binding.rc.layoutManager = myLinearLayoutManager
//        binding.rc.layoutManager=LinearLayoutManager(context)
//       binding.rc.adapter=parantAdapter
        //parantAdapter.notifyDataSetChanged()
        loadData()
        buildRecyclerView()
        binding.tvSeeAll.setOnClickListener {
            val fragmentPlay=HomeFragment()
            fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView,fragmentPlay)?.commit()

        }
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
    private fun getlocation(){
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            mFusedLocationClient?.lastLocation?.addOnSuccessListener { task->
                Log.e("TAG", "task: $task")
                if (task==null) {
                    requestNewLocationData()
                }else{
                    try {
                        val geocoder= Geocoder(requireContext(), Locale.getDefault())
                        val add: List<Address>? = geocoder.getFromLocation(task.latitude, task.longitude, 1)
                        //binding.latitudeTextView.text= add?.get(0)?.latitude.toString()
                        // binding.longitTextView.text= add?.get(0)?.longitude.toString()
                        binding.tvLocation25.text= add!![0].getAddressLine(0)
                        // binding.CityTextView.text= add[0].locality
                        // binding.CountryTextView.text= add[0].countryName
                        val locality: String = add[0].locality
                        val subLocality: String = add[0].subLocality
                        val state: String = add[0].adminArea
                        val country: String = add[0].countryName
                        val postalCode: String = add[0].postalCode
                        val knownName: String = add[0].featureName
                        Log.e("TAG", "onLocationResult:${locality} ")
                        Log.e("TAG", "onLocationResult:${subLocality} ")
                        Log.e("TAG", "onLocationResult:${state} ")
                        Log.e("TAG", "onLocationResult:${country} ")
                        Log.e("TAG", "onLocationResult:${postalCode} ")
                        Log.e("TAG", "onLocationResult:${knownName} ")
                    }catch (e:Exception){
                        Log.e("TAG", "getlocation: $e")
                    }
                }

            }
        }else{
            askpermissin()
        }
    }
    private fun askpermissin(){
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),REQUEST_CODE)
    }
    private fun requestNewLocationData() {
        val mLocationRequest = com.google.android.gms.location.LocationRequest()
        mLocationRequest.priority = com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 5
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        mFusedLocationClient!!.requestLocationUpdates(
            mLocationRequest,
            mLocationCallback,
            Looper.myLooper()
        )
    }
    private val mLocationCallback: LocationCallback = object  : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            val mLastLocation = p0.lastLocation
            val geocoder= Geocoder(requireContext(), Locale.getDefault())
            val add: List<Address>? = mLastLocation?.let { geocoder.getFromLocation(it.latitude, mLastLocation.longitude, 1) }
            // binding.txtlocation.text= add?.get(0)?.latitude.toString()
            // binding.longitTextView.text= add?.get(0)?.longitude.toString()
            binding.tvLocation25.text= add!![0].locality
            val address: String = add[0]
                .getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

            val locality: String = add[0].locality
            val subLocality: String = add[0].subLocality
            val state: String = add[0].adminArea
            val country: String = add[0].countryName
            val postalCode: String = add[0].postalCode
            val knownName: String = add[0].featureName
            Log.e("TAG", "onLocationResult:${locality} ")
            Log.e("TAG", "onLocationResult:${subLocality} ")
            Log.e("TAG", "onLocationResult:${state} ")
            Log.e("TAG", "onLocationResult:${country} ")
            Log.e("TAG", "onLocationResult:${postalCode} ")
            Log.e("TAG", "onLocationResult:${knownName} ")

            //  binding.CityTextView.text= add[0].locality
            //  binding.CountryTextView.text= add[0].countryName
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==REQUEST_CODE){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getlocation()
            }else{
                Toast.makeText(requireContext(),"Please reqired permission", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        getlocation()
        super.onResume()
    }
    private fun loadData() {
        preferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = preferences.getString("Fruits", null)
        val type: Type = object : TypeToken<ArrayList<ChildModelClass?>?>() {}.getType()
        latestlist = gson.fromJson(json, type)

        val json1 = preferences.getString("Grossary", null)
        val type1: Type = object : TypeToken<ArrayList<ChildModelClass?>?>() {}.getType()
        recentlist = gson.fromJson(json1, type1)

        val json2 = preferences.getString("Backery", null)
        val type2: Type = object : TypeToken<ArrayList<ChildModelClass?>?>() {}.getType()
        favoriteList = gson.fromJson(json2, type2)

        val json3 = preferences.getString("Vegetable", null)
        val type3: Type = object : TypeToken<ArrayList<ChildModelClass?>?>() {}.getType()
        backeryList = gson.fromJson(json3, type3)

        val json4 = preferences.getString("Bevagers", null)
        val type4: Type = object : TypeToken<ArrayList<ChildModelClass?>?>() {}.getType()
        beawareList = gson.fromJson(json4, type4)

        val json5 = preferences.getString("Dryfruits", null)
        val type5: Type = object : TypeToken<ArrayList<ChildModelClass?>?>() {}.getType()
        dryFruits = gson.fromJson(json5, type5)

        val json6 = preferences.getString("Snacks", null)
        val type6: Type = object : TypeToken<ArrayList<ChildModelClass?>?>() {}.getType()
        snacks = gson.fromJson(json6, type6)
    }
    private fun buildRecyclerView() {
        parentModelClass.add(ParentModelClass("Fruits",latestlist))
        parentModelClass.add(ParentModelClass("Grossary",recentlist))
        parentModelClass.add(ParentModelClass("Backery",favoriteList))
        parentModelClass.add(ParentModelClass("Vegetable",backeryList))
        parentModelClass.add(ParentModelClass("Bevagers",beawareList))
        parentModelClass.add(ParentModelClass("Dry-Fruits",dryFruits))
        parentModelClass.add(ParentModelClass("Snacks",snacks))


        val parantAdapter= ParantAdapter(parentModelClass, requireContext())
        binding.rc.layoutManager= LinearLayoutManager(requireContext())
        //    binding.RC25.layoutManager = GridLayoutManager(applicationContext,2)
        binding.rc.adapter=parantAdapter
        parantAdapter.notifyDataSetChanged()
    }



}