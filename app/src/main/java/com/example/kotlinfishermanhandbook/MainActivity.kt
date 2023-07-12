package com.example.kotlinfishermanhandbook

import android.content.res.TypedArray
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinfishermanhandbook.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    var adapter: MyAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = R.layout.activity_main
        setContentView(view)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        var list = ArrayList<ListItem>()

        list.addAll(
            fillArrays(
                resources.getStringArray(R.array.fish),
                resources.getStringArray(R.array.fish_content),
                getImageId(R.array.fish_image_array)
            )
        )

        list.addAll(
            fillArrays(
                resources.getStringArray(R.array.bait),
                resources.getStringArray(R.array.bait_content),
                getImageId(R.array.bait_image_array)
            )
        )

        list.addAll(
            fillArrays(
                resources.getStringArray(R.array.tackle),
                resources.getStringArray(R.array.tackle_content),
                getImageId(R.array.tackle_image_array)
            )
        )

        val rcView: RecyclerView = findViewById(R.id.rcView)
        rcView.hasFixedSize()
        rcView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(list, this)
        rcView.adapter = adapter


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.id_fish -> {
                Toast.makeText(this, "Виды рыб", Toast.LENGTH_SHORT).show()
                adapter?.updateAdapter(
                    fillArrays(
                        resources.getStringArray(R.array.fish),
                        resources.getStringArray(R.array.fish_content),
                        getImageId(R.array.fish_image_array)
                    )
                )
            }

            R.id.id_history -> Toast.makeText(this, "Истории", Toast.LENGTH_SHORT).show()

            R.id.id_bait -> {
                Toast.makeText(this, "Приманки", Toast.LENGTH_SHORT).show()
                adapter?.updateAdapter(
                    fillArrays(
                        resources.getStringArray(R.array.bait),
                        resources.getStringArray(R.array.bait_content),
                        getImageId(R.array.bait_image_array)
                    )
                )
            }

            R.id.id_tackle -> {
                Toast.makeText(this, "Снасти", Toast.LENGTH_SHORT).show()

                adapter?.updateAdapter(
                    fillArrays(
                        resources.getStringArray(R.array.tackle),
                        resources.getStringArray(R.array.tackle_content),
                        getImageId(R.array.tackle_image_array),
                    )
                )
            }
        }
        val drawerLayout:DrawerLayout = findViewById(R.id.drawerLayout)

        drawerLayout.closeDrawer(GravityCompat.START)



        return true
    }

    fun fillArrays(
        titleArray: Array<String>,
        contentArray: Array<String>,
        imageArray: IntArray
    ): List<ListItem> {
        var listItemArray = ArrayList<ListItem>()
        for (n in 0..titleArray.size - 1) {
            var listItem = ListItem(imageArray[n], titleArray[n], contentArray[n])
            listItemArray.add(listItem)
        }
        return listItemArray
    }

    fun getImageId(imageArrayId: Int): IntArray {
        var tArray: TypedArray = resources.obtainTypedArray(imageArrayId)
        val count = tArray.length()
        val ids = IntArray(count)
        for (i in ids.indices) {
            ids[i] = tArray.getResourceId(i, 0)
        }
        tArray.recycle()
        return ids
    }



}