package com.mbw.squarerepos.screen.example.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.mbw.squarerepos.R
import com.mbw.squarerepos.base.fragment.BaseFragment
import com.mbw.squarerepos.databinding.FragmentSquareReposDetailBinding
import com.mbw.squarerepos.screen.example.viewmodel.SquareReposViewModel
import com.mbw.squarerepos.utils.scheduleAnimation
import com.mbw.squarerepos.utils.springAnimate
import dagger.hilt.android.AndroidEntryPoint

/**
 * A Fragment class for displaying Square Repo details.
 * This class extends [BaseFragment] with [FragmentSquareReposDetailBinding] for view binding.
 */
@AndroidEntryPoint
class FragmentSquareRepoDetail :
    BaseFragment<FragmentSquareReposDetailBinding>(R.layout.fragment_square_repos_detail) {

    /** ViewModel instance for the Example screen. */
    private val repoViewModel: SquareReposViewModel by activityViewModels()


    /**
     * Called when the fragment's view is created.
     * Sets up the recycler view and observes data changes.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = repoViewModel
        setOnclickListeners()
        binding.rootView.scheduleAnimation(R.anim.rv_animate_home)

    }



    /**
     * Sets up onclick listener.
     */
    private fun setOnclickListeners() {
        binding.btnBookMark.setOnClickListener {
            it.springAnimate()
            binding.viewModel?.selectedRepo?.value?.is_bookmarked =
                !((binding.viewModel?.selectedRepo?.value?.is_bookmarked) ?: false)
            binding.viewModel?.selectedRepo?.value?.let {
                repoViewModel.updateRepo(it)
            }

            binding.invalidateAll()
        }
    }


}
