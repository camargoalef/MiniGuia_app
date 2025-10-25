package com.AC.guiapocket.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.AC.guiapocket.R
import com.AC.guiapocket.adapter.ServiceAdapter
import com.AC.guiapocket.databinding.ActivityMainBinding
import com.AC.guiapocket.model.Service

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val serviceList = mutableListOf<Service>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        setupListView()
        setupListeners()
    }

    private fun loadData() {
        serviceList.clear()
        serviceList.add(Service(
            nameResId = R.string.kidelicia_name,
            categoryResId = R.string.category_food,
            descriptionResId = R.string.kidelicia_description,
            imageId = R.drawable.kidelicia,
            phone = "01639730245",
            websiteUrl = "https://kidelicia.com.br/",
            latitude = -21.49527372975833,
            longitude = -48.03573424218543
        ))
        serviceList.add(Service(
            nameResId = R.string.rubinho_market_name,
            categoryResId = R.string.category_market,
            descriptionResId = R.string.rubinho_market_description,
            imageId = R.drawable.supermercado_rubinho,
            phone = "01639730220",
            websiteUrl = "https://www.instagram.com/supermercadorubinho/",
            latitude = -21.496064854002004,
            longitude = -48.03836008597214
        ))
        serviceList.add(Service(
            nameResId = R.string.rubinho_arena_name,
            categoryResId = R.string.category_leisure,
            descriptionResId = R.string.rubinho_arena_description,
            imageId = R.drawable.arena_rubinho,
            phone = "16999993333",
            websiteUrl = "https://www.instagram.com/rubinhoarena/",
            latitude = -21.498021424057452,
            longitude = -48.03781291533146
        ))
        serviceList.add(Service(
            nameResId = R.string.princce_hotel_name,
            categoryResId = R.string.category_hotel,
            descriptionResId = R.string.princce_hotel_description,
            imageId = R.drawable.princce_hotel,
            phone = "01639730010",
            websiteUrl = "https://princehotel.com.br/",
            latitude = -21.49691336978993,
            longitude = -48.03296348145521
        ))
        serviceList.add(Service(
            nameResId = R.string.quiosque_pastel_name,
            categoryResId = R.string.category_bakery,
            descriptionResId = R.string.quiosque_pastel_description,
            imageId = R.drawable.quiosque_do_pastel,
            phone = "01639731135",
            websiteUrl = "https://www.pastelaria.com.br/",
            latitude = -21.497519805937557,
            longitude = -48.03915670208317
        ))
        serviceList.add(Service(
            nameResId = R.string.danilo_selli_clinic_name,
            categoryResId = R.string.category_dental,
            descriptionResId = R.string.danilo_selli_clinic_description,
            imageId = R.drawable.clinica,
            phone = "016988481143",
            websiteUrl = "https://odontocompany.com/",
            latitude = -21.497808397773714,
            longitude = -48.0375175283452
        ))
    }

    private fun setupListView() {
        val adapter = ServiceAdapter(this, serviceList)
        binding.lvServices.adapter = adapter
    }

    private fun setupListeners() {
        binding.lvServices.setOnItemClickListener { _, _, position, _ ->
            val selectedService = serviceList[position]
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("service_data", selectedService)
            startActivity(intent)
        }

        binding.btnThemeToggle.setOnClickListener {
            toggleDayNightMode()
        }

        binding.btnLanguageToggle.setOnClickListener {
            toggleLanguage()
        }
    }

    private fun toggleDayNightMode() {
        val isNight = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
        val newMode = if (isNight) {
            AppCompatDelegate.MODE_NIGHT_NO
        } else {
            AppCompatDelegate.MODE_NIGHT_YES
        }
        AppCompatDelegate.setDefaultNightMode(newMode)
    }

    private fun toggleLanguage() {
        val currentLocales = AppCompatDelegate.getApplicationLocales()
        val currentLanguage = if (currentLocales.isEmpty) "pt" else currentLocales[0]?.language

        val nextLanguage = if (currentLanguage == "en") "pt" else "en"

        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(nextLanguage)
        )
    }
}