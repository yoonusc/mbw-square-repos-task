package com.mbw.squarerepos.screen.example.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withStarted
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.mbw.squarerepos.R
import com.mbw.squarerepos.base.fragment.BaseFragment
import com.mbw.squarerepos.databinding.FragmentSquareReposBinding
import com.mbw.squarerepos.screen.example.adapter.LoadStateAdapter
import com.mbw.squarerepos.screen.example.adapter.SquareReposPagingAdapter
import com.mbw.squarerepos.screen.example.viewmodel.SquareReposViewModel
import com.mbw.squarerepos.utils.scheduleAnimation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A Fragment class for displaying Example data.
 * This class extends [BaseFragment] with [FragmentSquareReposBinding] for view binding.
 */
@AndroidEntryPoint
class FragmentSquareRepos :
    BaseFragment<FragmentSquareReposBinding>(R.layout.fragment_square_repos) {

    /** ViewModel instance for the Example screen. */
    private val repoViewModel: SquareReposViewModel by activityViewModels()

    /** Paging adapter instance for handling paged data. */
    private lateinit var squareReposPagingAdapter: SquareReposPagingAdapter

    /**
     * Called when the fragment's view is created.
     * Sets up the recycler view and observes data changes.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeData()
    }

    /**
     * Sets up the recycler view with the paging adapter and a footer load state adapter.
     */
    private fun setupRecyclerView() {
        squareReposPagingAdapter = SquareReposPagingAdapter {
            repoViewModel.selectedRepo?.value = it
            findNavController().navigate(R.id.action_repoList_to_repoDetails)


        }
        binding.recyclerView.adapter = squareReposPagingAdapter.withLoadStateFooter(
            footer = LoadStateAdapter { squareReposPagingAdapter.retry() }
        )
        binding.recyclerView.scheduleAnimation(R.anim.rv_animate_home)
    }

    /**
     * Observes data from the ViewModel and submits it to the paging adapter.
     * Also listens for load state changes to handle UI states.
     */
    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            withStarted { }
            repoViewModel.squareReposData.collectLatest { pagingData ->
                squareReposPagingAdapter.submitData(pagingData)
            }
        }


        squareReposPagingAdapter.addLoadStateListener { loadState ->
            val mediatorLoadState: LoadState? = loadState.mediator?.refresh
            if (mediatorLoadState is LoadState.Error) {
              Toast.makeText(requireContext(),mediatorLoadState.error.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
    }
}
