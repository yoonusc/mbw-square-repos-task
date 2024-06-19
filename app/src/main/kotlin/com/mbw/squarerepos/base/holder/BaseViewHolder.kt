// BaseViewHolder.kt
package com.mbw.squarerepos.base.holder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mbw.squarerepos.BR

/**
 * A generic ViewHolder that binds the given model to the DataBinding.
 * 
 * @param T The type of the item that will be bound.
 * @param VB The type of the ViewDataBinding.
 * @property binding The ViewDataBinding instance.
 */
abstract class BaseViewHolder<T : Any, VB : ViewDataBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root) {

    /**
     * Bind the given item to the ViewDataBinding.
     *
     * @param item The model to be bound.
     */
    open fun bind(item: T) {
        binding.setVariable(BR.model, item)
    }
}
