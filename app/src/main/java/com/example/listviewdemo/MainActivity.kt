package com.example.listviewdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import com.example.listviewdemo.databinding.ActivityMainBinding
import com.example.listviewdemo.databinding.ListItemBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var textArray: Array<String>
    lateinit var imageArray: Array<Int>
    lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listView = binding.lvDaten

        textArray = resources.getStringArray(R.array.bild_texte)

        imageArray = arrayOf(
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6,
        )

        listView.adapter = MyAdapter()

        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("text", textArray[position])
            intent.putExtra("image", imageArray[position])
            startActivity(intent)
        }
    }

    inner class MyAdapter : BaseAdapter(){ // ohne inner ist es eine nested class und diese kann auf die äußere nicht zugreifen
        override fun getCount(): Int {
           return textArray.size
        }

        override fun getItem(position: Int): Any {
            return textArray[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var itemView = ListItemBinding.inflate(layoutInflater)
            itemView.tvImageName.text = textArray[position]
            itemView.ivPic.setImageResource(imageArray[position])
            return itemView.root
        }
    }
}