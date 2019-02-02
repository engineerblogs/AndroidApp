package e.yoppie.newengineerblogs.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import e.yoppie.newengineerblogs.BR
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.databinding.SelectCompanyItemBinding
import e.yoppie.newengineerblogs.model.data.Company
import e.yoppie.newengineerblogs.view.viewHolder.CompanyViewHolder
import e.yoppie.newengineerblogs.viewmodel.SelectCompanyItemViewModel
import e.yoppie.newengineerblogs.viewmodel.SelectCompanyViewModel

class CompanyRecyclerAdapter(private val context: AppCompatActivity, viewModel: SelectCompanyViewModel) : RecyclerView.Adapter<CompanyViewHolder>() {
    private var items: MutableList<Company> = mutableListOf()

    init {
        viewModel.companyListData.observe({ context.lifecycle }, { it?.apply { update(this) } })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SelectCompanyItemBinding>(layoutInflater, R.layout.select_company_item, parent, false)
        binding.setLifecycleOwner(context)
        return CompanyViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        val selectCompanyItemViewModel = SelectCompanyItemViewModel()
        selectCompanyItemViewModel.setCompany(items[position])
        holder.binding.apply {
            setVariable(1, selectCompanyItemViewModel)
            executePendingBindings()
        }
    }

    private fun update(companyList: MutableList<Company>) {
        val diff = DiffUtil.calculateDiff(DiffCallback(items, companyList))
        diff.dispatchUpdatesTo(this)
        this.items.clear()
        this.items.addAll(companyList)
    }

    class DiffCallback(private val oldList: MutableList<Company>, private val newList: MutableList<Company>) : DiffUtil.Callback() {
        override fun areContentsTheSame(oldPosition: Int, newPosition: Int) = oldList[oldPosition] == (newList[newPosition])

        override fun areItemsTheSame(oldPosition: Int, newPosition: Int) = oldList[oldPosition].id == (newList[newPosition]).id

        override fun getNewListSize() = newList.size

        override fun getOldListSize() = oldList.size
    }
}