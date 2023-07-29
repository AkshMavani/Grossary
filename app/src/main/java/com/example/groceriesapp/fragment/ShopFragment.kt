package com.example.groceriesapp.fragment

import android.Manifest
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
import com.smarteist.autoimageslider.SliderView
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
    private val binding get() = _binding
    private var url1 = "https://tse3.mm.bing.net/th?id=OIP.G3_sUJktiRNomSCbnuMstgHaDt&pid=Api&P=0&h=180"
    private var url2 = "https://img.freepik.com/free-psd/social-media-promo-template-sales_23-2149533430.jpg?w=1060&t=st=1690455289~exp=1690455889~hmac=8e4adcd34ab28c5714f6d8d5c7023008a7b14929044b9387eac697cd1a76ab3f"
    private var url3 = "https://dealroup.com/wp-content/uploads/2020/05/Grocery-Offers.jpg"
    private val parentModelClass:ArrayList<ParentModelClass> = ArrayList()
    private val product:ArrayList<ProductModelClass> = ArrayList()
    val childModelClass:ArrayList<ChildModelClass> = ArrayList()

    private val favoriteList:ArrayList<ChildModelClass> = ArrayList()
    private val recentlist:ArrayList<ChildModelClass> = ArrayList()
    private val latestlist:ArrayList<ChildModelClass> = ArrayList()
    private val backeryList:ArrayList<ChildModelClass> = ArrayList()
    private val beawareList:ArrayList<ChildModelClass> = ArrayList()
    private val dryFruits:ArrayList<ChildModelClass> = ArrayList()
    private val snacks:ArrayList<ChildModelClass> = ArrayList()




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
        val adapter = SliderAdapter(context, sliderDataArrayList)

        product.add(ProductModelClass(R.drawable.allfruitss,"Fruits"))
        product.add(ProductModelClass(R.drawable.backery1,"Backery"))
        product.add(ProductModelClass(R.drawable.vegetables,"Vegetable"))
        product.add(ProductModelClass(R.drawable.bevagerss,"Beverages"))
        product.add(ProductModelClass(R.drawable.grossary,"Grossary"))
        product.add(ProductModelClass(R.drawable.df,"Dry Fruits & Nuts"))
        product.add(ProductModelClass(R.drawable.snacks
            ,"snacks"))





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
        latestlist.add(ChildModelClass(R.drawable.banana,"100","Apple","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
        latestlist.add(ChildModelClass(R.drawable.mango1,"20","Mango","1kg","Organic Yellow Mango  ","A mango is a sweet tropical fruit, and it's also the name of the trees on which the fruit grows","800gr","3"))
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
        val parantAdapter= ParantAdapter(parentModelClass, requireContext())
       // binding.rc.layoutManager = myLinearLayoutManager
        binding.rc.layoutManager=LinearLayoutManager(context)
       binding.rc.adapter=parantAdapter
        parantAdapter.notifyDataSetChanged()
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


}