package com.example.jeffaccount.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jeffaccount.R
import com.example.jeffaccount.databinding.HomeFragmentBinding


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homeBinding = HomeFragmentBinding.inflate(inflater,container,false)

        val homeRecyclerAdapter = HomeRecyclerAdapter()
        homeBinding.homeRecycler.layoutManager = GridLayoutManager(context,2) as RecyclerView.LayoutManager?
        homeBinding.homeRecycler.adapter = homeRecyclerAdapter

        val homeList = listOf(
            Home(
                R.drawable.customer,
                getString(R.string.add_customer)
            ),
            Home(
                R.drawable.supplier,
                getString(R.string.add_supplier)
            ),
            Home(
                R.drawable.quote,
                getString(R.string.quotation)
            ),
            Home(
                R.drawable.purchase,
                getString(R.string.purchase)
            ),
            Home(
                R.drawable.invoice,
                getString(R.string.invoice)
            ),
            Home(
                R.drawable.worksheet,
                getString(R.string.worksheet)
            )
        )
        homeRecyclerAdapter.submitList(homeList)
        homeRecyclerAdapter.onItemClick ={
            pos, view ->
            val home = homeRecyclerAdapter.getItemName(pos)
            navigateTo(home.title)
        }
        return homeBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    //Function to Navigate to destination
    fun navigateTo(name:String){
        when(name){
             getString(R.string.add_customer)->{
                 this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCustomerFragment())
             }
            getString(R.string.add_supplier)->{

                this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSupplierFragment())
            }
            getString(R.string.quotation)->{
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToQuotationFragment())
            }
            getString(R.string.purchase)->{
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPurchaseFragment())
            }
        }
    }
}