package com.samar.newapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import api.NewApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "NewFragment"
class NewFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newViewModel = ViewModelProviders.of(this).get(NewViewModel::class.java)

    }
    private lateinit var newViewModel: NewViewModel
    private lateinit var newRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_new, container, false)
        newRecyclerView = view.findViewById(R.id.new_recycler_view)
        newRecyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newViewModel.newItemLiveData.observe(
            viewLifecycleOwner,
            Observer { newItems ->
                newRecyclerView.adapter = NewAdapter(newItems)

            })
    }

    private class NewHolder(itemTextView: View)
        : RecyclerView.ViewHolder(itemTextView) {

        val titleTextView = itemTextView.findViewById(R.id.title) as TextView
        val detailsTextView=itemTextView.findViewById(R.id.details ) as TextView

        fun bind(new:NewItem){
            titleTextView.setText(new.news_title)
            detailsTextView.setText(new.news_details)

        }
    }
    private class NewAdapter(private val newItems: List<NewItem>)
        : RecyclerView.Adapter<NewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): NewHolder {
            val View = LayoutInflater.from(parent.context).inflate(R.layout.new_det,parent,false)
            return NewHolder(View)
        }
        override fun getItemCount(): Int = newItems.size
        override fun onBindViewHolder(holder: NewHolder, position: Int) {
            val newItem = newItems[position]
            holder.bind(newItem)
        }
    }

    companion object {
        fun newInstance() = NewFragment()
    }
}




