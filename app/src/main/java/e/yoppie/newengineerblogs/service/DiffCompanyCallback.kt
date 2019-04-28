package e.yoppie.newengineerblogs.service

import android.support.v7.util.DiffUtil
import e.yoppie.newengineerblogs.model.data.Company

class DiffCompanyCallback(private val oldList: MutableList<Company>, private val newList: MutableList<Company>) : DiffUtil.Callback() {
    override fun areContentsTheSame(oldPosition: Int, newPosition: Int) = oldList[oldPosition] == (newList[newPosition])

    override fun areItemsTheSame(oldPosition: Int, newPosition: Int) = oldList[oldPosition].id == (newList[newPosition]).id

    override fun getNewListSize() = newList.size

    override fun getOldListSize() = oldList.size
}