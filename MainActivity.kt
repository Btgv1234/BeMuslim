package com.icad.bemuslim.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.icad.bemuslim.R
import com.icad.bemuslim.databinding.ActivityMainBinding
import com.icad.bemuslim.doa.DoaActivity
import com.icad.bemuslim.home.adapter.InspirationAdapter
import com.icad.bemuslim.home.data.InspirationData
import com.icad.bemuslim.home.model.InspirationModel
import com.icad.bemuslim.jadwalsholat.JadwalSholatActivity
import com.icad.bemuslim.videokajian.VideoKajianActivity
import com.icad.bemuslim.zakat.ZakatActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    // deklarasi variable koneksi komponen xml ke file kotlin
    private lateinit var binding: ActivityMainBinding

    // perintah dalam onCreate akan dijalankan ketika activity pertama dibuka
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // setContentView(R.layout.activity.main)

        initNavMenu()
        initHeader()
        initRecyclerViewInspiration()
    }


    private fun initRecyclerViewInspiration(){
        val list: ArrayList<InspirationModel> = arrayListOf()
        list.addAll(InspirationData.listData)
        val inspirationAdapter = InspirationAdapter(list)
        binding.rvInspiration.setHasFixedSize(true)
        binding.rvInspiration.layoutManager = LinearLayoutManager(this)
        binding.rvInspiration.adapter = inspirationAdapter
    }

    private fun initHeader(){
        // mengambil waktu sekarang
        val timeNow = Calendar.getInstance()
        // menentukan format jam HH (hour 2 digit)
        val timeFormat = SimpleDateFormat("HH")
        // membentuk waktu sekarang hanya jam saja
        val time = timeFormat.format(timeNow.time)

        // tentukan gambar bedasarkan jam sekarang (data di variable time)
        when {
            // jam 00-06 gambar malam
            time.toInt() in 0..6 -> {
                // ganti gambar jadi malam
                binding.ivHeader.setImageResource(R.drawable.bg_header_dashboard_night)
            }
            time.toInt() in 7..12 -> {
                // ganti gambar jadi malam
                binding.ivHeader.setImageResource(R.drawable.bg_header_dashboard_morning)
            }
            time.toInt() in 13..18 -> {
                // ganti gambar jadi malam
                binding.ivHeader.setImageResource(R.drawable.bg_header_dashboard_afternoon)
            }
            time.toInt() in 19..23 -> {
                // ganti gambar jadi malam
                binding.ivHeader.setImageResource(R.drawable.bg_header_dashboard_night)
            }
        }
    }

    private fun initNavMenu(){
        // memberikan aksi klik pada icon
        binding.iconMenuDoa.setOnClickListener {
            // memulai aktivitas baru
            // memberikan inten untuk menunjukan tujuan pergi
            startActivity(Intent(this, DoaActivity::class.java))
        }
        binding.ivIconMenuVideoKajian.setOnClickListener {
            startActivity(Intent(this, VideoKajianActivity::class.java))
        }
        binding.ivIconMenuSalat.setOnClickListener {
            startActivity(Intent(this, JadwalSholatActivity::class.java))
        }
        binding.ivIconMenuZakat.setOnClickListener {
            startActivity(Intent(this, ZakatActivity::class.java))
        }
    }
    // perintah dalam onPause akan dijalankan ketika activity akan dijalankan
    override fun onPause() {
        super.onPause()
        Log.d("main", "onPause: ")
    }
    // perintah dalam onResume akan dijalankan ketika activity dibuka kembali
    override fun onResume() {
        super.onResume()
        Log.d("main", "onResume: resume")
    }
}
