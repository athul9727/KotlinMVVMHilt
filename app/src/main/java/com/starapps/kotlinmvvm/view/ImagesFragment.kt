package com.starapps.kotlinmvvm.view

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.starapps.kotlinmvvm.R
import com.starapps.kotlinmvvm.databinding.FragmentImagesBinding
import com.starapps.kotlinmvvm.repository.model.ImageListResponseItem
import com.starapps.kotlinmvvm.util.replaceFragment
import com.starapps.kotlinmvvm.viewmodel.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_images.*

@AndroidEntryPoint
class ImagesFragment : Fragment() , ImageClickListener {

  //  private val ViewModel by viewModel<ViewModel>()
    private val viewModel: ViewModel by viewModels()
    private lateinit var recAdapter: RecAdapter
    private lateinit var mDataBinding: FragmentImagesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        mDataBinding  = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_images,
            container,
            false)

        val mRootView = mDataBinding.root
        mDataBinding.lifecycleOwner = this
        return mRootView
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setView()
        removeBackButton()
        mDataBinding.viewModel = viewModel
        viewModel.getalldata("list")
//        viewModel.connectivityLiveData.observe(viewLifecycleOwner, Observer { isAvailable ->
//            when (isAvailable) {
//                true -> {
//                    if (!viewModel.inputtext.value.isNullOrEmpty()) {
//                        if (viewModel.imageList.value.isNullOrEmpty()) {
//                            viewModel.set_edittext()
//                        }
//                    }
//                }
//                false -> {
//                    //ViewModel.imageList.value = null
//                    Toast.makeText(context,"No Network", Toast.LENGTH_LONG).show()
//                }
//            }
//        })

        viewModel.imageList.observe(viewLifecycleOwner, Observer {

            if (it != null) {
                if (it.isNotEmpty()) {
                    Log.i("checkstatus", it.toString())
                    recAdapter.setlist(it)
                }
            }

        })


        viewModel.message.observe(viewLifecycleOwner, Observer {
                Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
        })

    }

    private fun removeBackButton() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeButtonEnabled(false)
    }

    private fun setView() {
        recAdapter = RecAdapter(context, this)
        recycleview.layoutManager = GridLayoutManager(activity, 3)
        recycleview.setHasFixedSize(true)
        recycleview.setItemViewCacheSize(12)
        recycleview.adapter = recAdapter


    }

    override fun onItemClick(imagedata : ImageListResponseItem) {
        (activity as MainActivity).replaceFragment(
            ImageDetailsFragment.newInstance(imagedata),
            R.id.fragment_container, "imagedetails")
    }

    override fun onStartClick() {

    }



}