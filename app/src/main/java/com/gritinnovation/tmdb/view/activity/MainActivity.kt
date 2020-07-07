package com.gritinnovation.tmdb.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gritinnovation.tmdb.R
import com.gritinnovation.tmdb.databinding.ActivityMainBinding
import com.gritinnovation.tmdb.network.RepositoryFactory
import com.gritinnovation.tmdb.util.Constant
import com.gritinnovation.tmdb.view.BaseActivity
import com.gritinnovation.tmdb.view.adapter.RecyclerDataAdapter
import com.gritinnovation.tmdb.viewmodel.DataViewModel
import com.gritinnovation.tmdb.viewmodel.ViewModelFactory

class MainActivity : BaseActivity<ActivityMainBinding>(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var dataViewModel: DataViewModel

    private lateinit var mDataAdapter: RecyclerDataAdapter

    private var isSwipeRefresh = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_main)
        init()
    }

    private fun init() {

        initToolbar(
            mBinding.toolbar.toolbar,
            getString(R.string.app_name),
            mBinding.toolbar.txtHeaderTitle,
            showBackButton = false
        )

        mBinding.swipeRefreshLayout.setOnRefreshListener(this)

        // initialize viewModel class object
        dataViewModel =
            ViewModelProviders.of(this, ViewModelFactory(RepositoryFactory.createDataRepository()))
                .get(DataViewModel::class.java)

        // API call to get data from server
        webCallGetData()
    }

    private fun webCallGetData() {

        if (!isNetworkAvailable(this)) {
            displayShortToast(getString(R.string.msg_no_internet))
            isSwipeRefresh = false
            mBinding.swipeRefreshLayout.isRefreshing = false
            return
        }

        if (isSwipeRefresh) {
            mBinding.swipeRefreshLayout.isRefreshing = true
        } else {
            // start loader
            showLoader(this)
        }

        // make an API call
        if (::dataViewModel.isInitialized)
            dataViewModel.getDataFromServer(
                "${Constant.COLUMN_POPULARITY}${Constant.SORT_BY_DESC}",
                Constant.API_KEY
            )

        dataViewModel.responseModel.observe(this, Observer { it ->

            if (isSwipeRefresh) {
                isSwipeRefresh = false
                mBinding.swipeRefreshLayout.isRefreshing = false
            } else {
                // dismiss loader
                dismissLoader()
            }

            //displayShortToast("Data Received Successfully...")

            mDataAdapter = RecyclerDataAdapter(this@MainActivity, it.results)
            mBinding.recyclerViewDataList.adapter = mDataAdapter

            showNoData(
                mBinding.recyclerViewDataList,
                mBinding.layoutNoData,
                it.results.isNullOrEmpty()
            )
        })
    }

    override fun onRefresh() {
        isSwipeRefresh = true
        // API call to get data
        webCallGetData()
    }
}
