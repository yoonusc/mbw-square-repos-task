package com.mbw.squarerepos.screen.example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.mbw.squarerepos.databinding.ItemLoadStateBinding
import com.mbw.squarerepos.screen.example.adapter.LoadStateAdapter.LoadStateViewHolder

/**
 * Adapter to manage the different load states in a paginated list.
 * Utilizes a [LoadStateAdapter] to display loading spinners, error messages, and retry buttons.
 *
 * @property retry Lambda function to retry the load operation when an error occurs.
 */
class LoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<LoadStateViewHolder>() {

    /**
     * Creates a new [LoadStateViewHolder] to represent a LoadState item.
     * 
     * @param parent The parent ViewGroup where the new view will be added after it is bound to an adapter position.
     * @param loadState The current load state.
     * @return A new LoadStateViewHolder that holds a view for the given load state.
     */
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = ItemLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding, retry)
    }

    /**
     * Binds the given load state to the [LoadStateViewHolder].
     * 
     * @param holder The ViewHolder to be updated to represent the contents of the item at the given position in the data set.
     * @param loadState The LoadState item which contents should replace the contents of the holder.
     */
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    /**
     * ViewHolder to present different load states in the RecyclerView.
     * Displays loading spinner, retry button, and error message as needed.
     * 
     * @property binding The ViewDataBinding of the LoadState item.
     * @property retry Lambda function to retry the load operation.
     */
    class LoadStateViewHolder(
        private val binding: ItemLoadStateBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the LoadState to the ItemLoadStateBinding. Sets visibility of progress bar, retry button, and error message based on the state.
         * 
         * @param loadState The LoadState to bind to.
         */
        fun bind(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState is LoadState.Error
            binding.errorMsg.isVisible = loadState is LoadState.Error

            if (loadState is LoadState.Error) {
                binding.errorMsg.text = loadState.error.localizedMessage
            }

            binding.retryButton.setOnClickListener {
                retry.invoke()
            }
        }
    }
}
