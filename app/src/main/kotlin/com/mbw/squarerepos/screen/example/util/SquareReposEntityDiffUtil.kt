package com.mbw.squarerepos.screen.example.util

import com.mbw.squarerepos.data.model.square.SquareReposEntity
import com.mbw.squarerepos.base.util.BaseDiffUtil

class SquareReposEntityDiffUtil : BaseDiffUtil<SquareReposEntity>(
    areItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
    areContentsTheSame = { oldItem, newItem -> oldItem == newItem }
)
