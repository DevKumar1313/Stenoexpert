package com.agamya.stenoexpert.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.agamya.stenoexpert.R

class OnboardingItemAdapter(private val onboardingItem:List<OnboardingItemClass>):
    RecyclerView.Adapter<OnboardingItemAdapter.OnboardingItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        return OnboardingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.onboarding_item_container,parent,false)
        )
    }

    override fun getItemCount(): Int {
       return onboardingItem.size
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
       holder.bind(onboardingItem[position])
    }

    inner class OnboardingItemViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val imageOnboarding=view.findViewById<ImageView>(R.id.imageOnboarding)
        private val textTitle=view.findViewById<TextView>(R.id.textTitle)
        private val textDescription=view.findViewById<TextView>(R.id.textDiscription)

        fun bind(onboardingItem: OnboardingItemClass){
            imageOnboarding.setImageResource(onboardingItem.onboardingimage)
            textTitle.text=onboardingItem.title
            textDescription.text=onboardingItem.description

        }

    }




}