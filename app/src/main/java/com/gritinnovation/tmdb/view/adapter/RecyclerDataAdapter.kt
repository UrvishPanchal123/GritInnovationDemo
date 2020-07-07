package com.gritinnovation.tmdb.view.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gritinnovation.tmdb.databinding.ItemDataBinding
import com.gritinnovation.tmdb.model.DataModel
import com.gritinnovation.tmdb.view.activity.MainActivity

class RecyclerDataAdapter(
    private val mContext: MainActivity,
    private val mList: List<DataModel.Result>?
) :
    RecyclerView.Adapter<RecyclerDataAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val mBinding = ItemDataBinding.inflate(mContext.layoutInflater, parent, false)
        return DataViewHolder(mBinding)
    }

    override fun getItemCount(): Int {
        return mList?.size!!
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bindData(mList?.get(holder.adapterPosition)!!)
    }

    class DataViewHolder(var mBinding: ItemDataBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun bindData(data: DataModel.Result) {
            mBinding.data = data
            mBinding.executePendingBindings()
        }
    }
}