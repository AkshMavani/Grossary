package com.example.groceriesapp.activity


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceriesapp.R
import com.example.groceriesapp.databinding.ActivitySeeAllBinding
import com.example.multityperecycleview.ChildModelClass
import com.example.multityperecycleview.CustomAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class SeeAllActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySeeAllBinding
    private var latestlist:ArrayList<ChildModelClass> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySeeAllBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val position=intent.getIntExtra("position",0)

        when (position) {
            0 -> {
                fruits()
            }
            1 -> {
                backery()
            }
            2 -> {
                vegetable()
            }
            3 -> {
                bevagers()
            }
            4 -> {
                grossary()
            }
            5 -> {
                dryfruit()
            }
            6 -> {
                snacks()
            }
            else -> {

            }
        }
        //saveData()
        val parantAdapter= CustomAdapter(latestlist, this)
        binding.rcSeeAll.layoutManager= LinearLayoutManager(this)
        binding.rcSeeAll.layoutManager = GridLayoutManager(applicationContext,2)
        binding.rcSeeAll.adapter=parantAdapter
        parantAdapter.notifyDataSetChanged()
        }
   private fun fruits(){

       latestlist.add(ChildModelClass(R.drawable.banana,"100","Apple","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
       latestlist.add(ChildModelClass(R.drawable.mango,"150","Mango","1kg","Organic Yellow Mango  ","A mango is a sweet tropical fruit, and it's also the name of the trees on which the fruit grows","800gr","3"))
       latestlist.add(ChildModelClass(R.drawable.apple,"100","Banana","12pcs"," Natural Banana","Bananas are long, curved fruits with smooth, yellow, and sometimes slightly green skin","50gr","2"))
       latestlist.add(ChildModelClass(R.drawable.orange,"130","Orange","1kg","Organic Red orange","orange, any of several species of small trees or shrubs of the genus Citrus of the family Rutaceae and their nearly round fruits, which have leathery and oily rinds and edible, juicy inner flesh.","50gr","5"))
       latestlist.add(ChildModelClass(R.drawable.stawbary,"190","stawbary","12pcs","Organic Red Stawberry","The strawberry is a succulent and fragrant fruit of bright red colour, obtained from the plant with the same name.","100gr","5"))
     //  saveData("Fruits")
   }
    private fun grossary(){
        latestlist.add(ChildModelClass(R.drawable.dal,"80","Yellow pigeon peas","500gr","Organic pigeon peas","Toor dal is also called pigeon peas and belongs to the family of legumes. It is rich in proteins and fibres. Toor dal is dried and split peas (seeds) of pigeon peas plant","30gr","4.5"))
        latestlist.add(ChildModelClass(R.drawable.rice,"90","Rice","500gr","Organic Rice","rice, (Oryza sativa), edible starchy cereal grain and the grass plant (family Poaceae) by which it is produced. ","95gr","4"))
        latestlist.add(ChildModelClass(R.drawable.chaipatti,"50","Tea Leaf","1kg","Natural Tea Leaf","Tea plant is an evergreen shrub with bright green, shiny leaves that are often hairy on their underside. The tea plants' white, scented flowers occur either on their own or in clusters of two to four.","80gr","2"))
        latestlist.add(ChildModelClass(R.drawable.suger,"60","suger","500gr","Suger" ,"a sweet substance that is made up wholly or mostly of sucrose, is colorless or white when pure, is obtained from plants","50gr","4.5"))
        latestlist.add(ChildModelClass(R.drawable.wheat,"70","Wheat","1kg","Organic Wheat  ","Wheat is a bunch grass with upright tillers. The leaves are rolled in the whorl. Leaf blades are smooth near the base and rough near tip on the upper side.","100gr","5"))
     //  saveData("Grossary")
    }
    private fun backery(){
        latestlist.add(ChildModelClass(R.drawable.bread,"50","Bread","12pcs","Fresh Bread","· Bread, baked food product made of flour or meal that is moistened, kneaded, and sometimes fermented.","100gr","4"))
        latestlist.add(ChildModelClass(R.drawable.baget,"30","Baget","1pcs","Fresh  Baget"," Baget baked food product made of flour or meal that is moistened, kneaded, and sometimes fermented","80gr","5"))
        latestlist.add(ChildModelClass(R.drawable.muffin,"40","Muffin","1pcs","Fresh Muffin ","A muffin is a small bread - or cake -like baked food. Muffins are similar to cupcakes.","60gr","5"))
        latestlist.add(ChildModelClass(R.drawable.cokkie,"70","Cokkie","1pcs","Fresh Cokkies","A cookie ( American English ), or a biscuit ( British English ), is a baked or cooked snack or dessert that is typically small, flat and sweet.","100gr","5"))
        latestlist.add(ChildModelClass(R.drawable.backery,"80","pastries","1pcs","Fresh pastries "," Pastry, stiff dough made from flour, salt, a relatively high proportion of fat, and a small proportion of liquid.","100gr","3"))
     // saveData("Backery")
    }
    private fun vegetable(){
        latestlist.add(ChildModelClass(R.drawable.brinjal,"90","Brinjal","1kg","Natural Brinjal ","Brinjal or eggplant (Solanum melongena L.) is an important solanaceous crop of sub tropics and tropics.","100gr","5"))
        latestlist.add(ChildModelClass(R.drawable.carrot,"60","Carrot","1kg","Natural Red Carrot","Carrot, herbaceous, generally biennial plant that produces an edible taproot. Carrots are commonly grown as annual plants and can be eaten fresh or cooked.","100gr","5"))
        latestlist.add(ChildModelClass(R.drawable.corn,"50","Corn","1kg","Natural Corn"," Sweet corn boasts a number of vitamins, including: Pantothenic acid. Also called vitamin B5, this acid is found to some extent in nearly all foods.","100gr","3"))
        latestlist.add(ChildModelClass(R.drawable.tometo,"109","Tometo","1kg","Natural Tometo","tomato, Any fruit of the numerous cultivated varieties of Solanum lycopersicum (formerly Lycopersicon esculentum), a plant of the nightshade family. ","100gr","5"))
        latestlist.add(ChildModelClass(R.drawable.poteto1,"20","Poteto","1kg","Natural Poteto","It is a small plant with large leaves. The part of the potato that people eat is a tuber that grows under the ground","900gr","5"))
      // saveData("Vegetable")
    }
    private fun bevagers(){
        latestlist.add(ChildModelClass(R.drawable.applejuice,"100","Apple Juice","1pcs"," Red Apple Juice","Apple juice is 88% water and 11% carbohydrates (including 9% sugars), with negligible content of protein or fat.","100gr","5"))
        latestlist.add(ChildModelClass(R.drawable.maaza,"80","Mango Juice","1pcs","Yellow Mango Juice","Mango Juice member of the cashew family and one of the most important and widely cultivated fruits of the tropical world","100gr","4.5"))
        latestlist.add(ChildModelClass(R.drawable.fanta,"90","Orange Juice","1pcs","Orange Juice","An orange juice from concentrate is a mixture of water with frozen concentrated orange juice or concentrated orange juice for manufacturing. ","100gr","3"))
        latestlist.add(ChildModelClass(R.drawable.pepsi,"60","Pepsi","1pcs","Black Graphs Juice","The original recipe also included sugar and vanilla. Bradham sought to create a fountain drink that was appealing and would aid in digestion and boost energy.","100gr","3.5"))
        latestlist.add(ChildModelClass(R.drawable.sprite,"50","Lemon Juice","1pcs","Lemon Juice","Sprite’s crisp taste with hit of lemon-lime flavour gives you that unmatched cut-through refreshment.","100gr","5"))
       // saveData("Bevagers")
    }
    private fun dryfruit(){
        latestlist.add(ChildModelClass(R.drawable.badam,"1000","Almond","1kg","Natural Almond","The almond is often thought of as a nut, though it's technically a drupe fruit produced by the almond tree (Prunus dulcis; Prunus amygdalus). ","80gr","5"))
        latestlist.add(ChildModelClass(R.drawable.kaju,"800","Cashewnuts","1kg","Natural Cashewnuts","Cashew, Anacardium occidentale, is an evergreen tree in the family Anacardiaceae grown for its edible fruits (nuts). ","100gr","4.6"))
        latestlist.add(ChildModelClass(R.drawable.walunt,"900","Walunt","1kg","Natural Walunt ","Walnuts are the round, single-seed stone fruits of the walnut tree. They are commonly used for food.","100gr","5"))
        latestlist.add(ChildModelClass(R.drawable.pista,"600","Pistachios","1kg","Natural Pistachios ","pistachio, (Pistacia vera), small tree of the cashew family (Anacardiaceae) and its edible seeds, grown in dry lands in warm or temperate climates. ","100gr","5"))
        latestlist.add(ChildModelClass(R.drawable.raisnis,"500","Raisnis","1kg","Natural Raisnis ","Raisins are a tasty and convenient food that can add a range of nutrients to your diet. ","100gr","5"))
     //   saveData("Dryfruits")
    }
    private fun snacks(){
        latestlist.add(ChildModelClass(R.drawable.kurkure1,"10","Kurkure","1pcs","Kurkure"," Kurkure is a brand of corn puffs, developed and produced by Pepsico India. The snack is named after the Hindi word for crunchy.","80gr","4"))
        latestlist.add(ChildModelClass(R.drawable.layschips,"40","chips","1pcs","Chips","A potato chip (North American English and Australian English; often just chip) or crisp (British and Irish English) is a thin slice of potato that has been deep fried, baked, or air fried until crunchy.","100gr","3.8"))
        latestlist.add(ChildModelClass(R.drawable.tags,"60","tagschips","1pcs","Tags Chips","A potato chip (North American English and Australian English; often just chip) or crisp (British and Irish English) is a thin slice of potato that has been deep fried, baked, or air fried until crunchy.","80gr","4"))
        latestlist.add(ChildModelClass(R.drawable.nachoes,"50","nachoes","1pcs","Nachoes","Nachos are a snack food made with tortilla chips and cheese. Sometimes they have jalapeños, sauce, beans, guacamole or meat on them.","90gr","3.4"))
        latestlist.add(ChildModelClass(R.drawable.ragisticks,"20","sticks","1pcs","Sticks","A stick is a thin branch which has fallen off a tree. ...people carrying bundles of dried sticks to sell for firewood. ","100gr","5"))
       // saveData("Snacks")
    }

//    private fun saveData(name:String){
//        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        val gson = Gson()
//        val json: String = gson.toJson(latestlist)
//        editor.putString(name, json)
//        editor.apply()
//        Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show()
//    }

}
