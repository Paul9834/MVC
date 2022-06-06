package com.paul9834.mvc.ui.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.paul9834.mvc.R
import com.paul9834.mvc.data.entities.Coupon
import com.paul9834.mvc.remote.model.ApiAdapter
import com.paul9834.mvc.ui.adapter.RecyclerCouponsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var coupons = ArrayList<Coupon>()
    lateinit var rvCoupons: RecyclerView
    lateinit var apiAdapter: ApiAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiAdapter = ApiAdapter()

        setupRecylerView()
        setupController()

    }

    private fun setupController() {

        val apiService = apiAdapter.getClientService()
        val call = apiService.getCoupons()

        call.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("ERROR: ", t.message!!)
                t.stackTrace
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val offersJsonArray = response.body()?.getAsJsonArray("offers")
                offersJsonArray?.forEach { jsonElement: JsonElement ->
                    var jsonObject = jsonElement.asJsonObject
                    var coupon = Coupon(jsonObject)
                    Log.e("ERROR: ", "${jsonObject} + hola")

                    coupons.add(coupon)
                }
                rvCoupons.adapter = RecyclerCouponsAdapter(coupons, R.layout.card_coupon)
            }
        })

    }

    private fun setupRecylerView() {
        rvCoupons = findViewById(R.id.rvCoupons)
        rvCoupons.layoutManager = LinearLayoutManager(this)
    }


}
