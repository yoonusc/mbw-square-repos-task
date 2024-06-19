package com.mbw.squarerepos.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import com.mbw.squarerepos.base.holder.BaseViewHolder
import com.mbw.squarerepos.base.util.BaseDiffUtil

/**
 * BasePagingAdapter is an abstract PagingDataAdapter class that supports data-binding.
 * It simplifies the process of binding view holders to a paging data set.
 *
 * @param T The type of the paging data.
 * @param VB The type of ViewDataBinding.
 * @param diffCallback The diff callback used for the paging data.
 */
abstract class BasePagingAdapter<T : Any, VB : ViewDataBinding>(
    diffCallback: BaseDiffUtil<T>
) : PagingDataAdapter<T, BaseViewHolder<T, VB>>(diffCallback) {

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T, VB> {
        val binding = createBinding(LayoutInflater.from(parent.context), parent)
        return createViewHolder(binding)
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *               item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: BaseViewHolder<T, VB>, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    /**
     * Creates the ViewDataBinding instance for the given layout.
     * This method should be implemented by subclasses to provide the specific layout binding.
     *
     * @param inflater The LayoutInflater to inflate the binding layout.
     * @param parent The ViewGroup to which the new View will be added after it is bound to
     *               an adapter position.
     * @return The instance of ViewDataBinding for the given layout.
     */
    abstract fun createBinding(inflater: LayoutInflater, parent: ViewGroup): VB

    /**
     * Creates the ViewHolder for the given binding.
     * This method should be implemented by subclasses to provide the specific ViewHolder.
     *
     * @param binding The ViewDataBinding instance.
     * @return The instance of BaseViewHolder for the given binding.
     */
    abstract fun createViewHolder(binding: VB): BaseViewHolder<T, VB>
}
