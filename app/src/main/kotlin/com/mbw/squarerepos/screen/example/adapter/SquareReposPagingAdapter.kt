package com.mbw.squarerepos.screen.example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mbw.squarerepos.base.adapter.BasePagingAdapter
import com.mbw.squarerepos.base.holder.BaseViewHolder
import com.mbw.squarerepos.data.model.square.SquareReposEntity
import com.mbw.squarerepos.databinding.ItemSquareReposBinding
import com.mbw.squarerepos.screen.example.holder.SquareReposViewHolder
import com.mbw.squarerepos.screen.example.util.SquareReposEntityDiffUtil

class SquareReposPagingAdapter(private val onClick:(SquareReposEntity)->Unit) : BasePagingAdapter<SquareReposEntity, ItemSquareReposBinding>(
    SquareReposEntityDiffUtil()
){

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): ItemSquareReposBinding {
        return ItemSquareReposBinding.inflate(inflater, parent, false)
    }

    override fun createViewHolder(binding: ItemSquareReposBinding): BaseViewHolder<SquareReposEntity, ItemSquareReposBinding> {
        return SquareReposViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<SquareReposEntity, ItemSquareReposBinding>,
        position: Int
    ) {
        super.onBindViewHolder(holder, position)
        holder.binding.rootView.setOnClickListener {
            onClick.invoke(holder.binding.model!!)
        }

    }


}
