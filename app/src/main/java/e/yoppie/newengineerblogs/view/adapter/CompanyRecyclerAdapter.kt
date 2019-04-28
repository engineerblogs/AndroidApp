package e.yoppie.newengineerblogs.view.adapter

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.clicks
import e.yoppie.newengineerblogs.R
import e.yoppie.newengineerblogs.databinding.SelectCompanyItemBinding
import e.yoppie.newengineerblogs.listener.OnCompanyRecyclerListener
import e.yoppie.newengineerblogs.model.data.Company
import e.yoppie.newengineerblogs.service.DiffCompanyCallback
import e.yoppie.newengineerblogs.view.viewHolder.CompanyViewHolder
import e.yoppie.newengineerblogs.viewmodel.SelectCompanyItemViewModel
import e.yoppie.newengineerblogs.viewmodel.SelectCompanyViewModel

class CompanyRecyclerAdapter(private val context: AppCompatActivity, viewModel: SelectCompanyViewModel, private var onCompanyRecyclerListener: OnCompanyRecyclerListener) : RecyclerView.Adapter<CompanyViewHolder>() {
    private var items: MutableList<Company> = mutableListOf()
    private var companyIdList: MutableList<String> = mutableListOf()

    init {
        viewModel.companyListData.observe({ context.lifecycle }, { it?.apply { update(this) } })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SelectCompanyItemBinding>(layoutInflater, R.layout.select_company_item, parent, false)
        binding.lifecycleOwner = context
        return CompanyViewHolder(binding)
    }

    override fun getItemCount() = items.size

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        val selectCompanyItemViewModel = SelectCompanyItemViewModel()
        selectCompanyItemViewModel.setCompany(items[position])
        holder.binding.apply {
            setVariable(1, selectCompanyItemViewModel)
            executePendingBindings()
        }
        holder.itemView
                .clicks()
                .subscribe {
                    val companyId = items[position].id
                    if (this.companyIdList.contains(companyId)) {
                        holder.itemView.setBackgroundResource(R.color.colorNoSelectBackGround)
                        this.companyIdList.remove(companyId)
                    } else {
                        holder.itemView.setBackgroundResource(R.color.colorSelectBackGround)
                        this.companyIdList.add(companyId)
                    }
                    onCompanyRecyclerListener.onRecyclerViewClick(items[position].id)
                }
    }

    private fun update(companyList: MutableList<Company>) {
        val diff = DiffUtil.calculateDiff(DiffCompanyCallback(items, companyList))
        diff.dispatchUpdatesTo(this)
        this.items.clear()
        this.items.addAll(companyList)
    }
}