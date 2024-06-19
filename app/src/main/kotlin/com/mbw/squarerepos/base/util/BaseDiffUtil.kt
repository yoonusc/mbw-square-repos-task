package com.mbw.squarerepos.base.util

import androidx.recyclerview.widget.DiffUtil

/**
 * A generic implementation of DiffUtil.ItemCallback which requires definition of how to check if items and their contents are the same.
 * 
 * @param T The type of items this DiffUtil will be comparing.
 * @property areItemsTheSame A function that takes old and new item and checks whether they represent the same item.
 * @property areContentsTheSame A function that takes old and new item and checks whether their contents are the same.
 */
open class BaseDiffUtil<T : Any>(
    private val areItemsTheSame: (oldItem: T, newItem: T) -> Boolean,
    private val areContentsTheSame: (oldItem: T, newItem: T) -> Boolean
) : DiffUtil.ItemCallback<T>() {

    /**
     * Checks whether two items are the same.
     * @param oldItem The old item.
     * @param newItem The new item.
     * @return True if the items are the same, false otherwise.
     */
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return areItemsTheSame.invoke(oldItem, newItem)
    }

    /**
     * Checks whether the contents of two items are the same.
     * @param oldItem The old item.
     * @param newItem The new item.
     * @return True if the contents of the items are the same, false otherwise.
     */
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return areContentsTheSame.invoke(oldItem, newItem)
    }
}
