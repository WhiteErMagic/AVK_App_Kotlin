package com.developmentavk.avk_app_kotlin.presentation.suppliers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.developmentavk.avk_app_kotlin.databinding.ItemSupplierBinding
import com.developmentavk.avk_app_kotlin.domain.entity.SupplierObjectForList

class SuppliersAdapter(private val onClickToSupplier: OnClickToSupplier): RecyclerView.Adapter<SuppliersAdapter.SuppliersHolder>(), View.OnClickListener {

    var objectsList = listOf<SupplierObjectForList>()
        set(value){
            val diffCallBack = SupplierObjectForListDiffCallBack(field, value)
            val diffResult = DiffUtil.calculateDiff(diffCallBack)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    interface OnClickToSupplier {
        fun toSupplier(codeSupplier: SupplierObjectForList)
    }

    class SupplierObjectForListDiffCallBack(
        private val oldList: List<SupplierObjectForList>,
        private val newList: List<SupplierObjectForList>): DiffUtil.Callback(){
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldObject = oldList[oldItemPosition].code_supplier
            val newObject = newList[oldItemPosition].code_supplier
            return oldObject == newObject
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldObject = oldList[oldItemPosition]
            val newObject = newList[oldItemPosition]
            return oldObject == newObject
        }

    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    class SuppliersHolder(val binding: ItemSupplierBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuppliersHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSupplierBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)

        return SuppliersHolder(binding)
    }

    override fun onBindViewHolder(holder: SuppliersHolder, position: Int) {
        val objectList = objectsList[position]
        holder.binding.name.text = objectList.name
        holder.binding.qty.text = objectList.qty
        holder.binding.name.tag = objectList
        holder.binding.qty.tag = objectList
        holder.binding.root.tag = objectList
    }

    override fun getItemCount(): Int {
        return objectsList.size
    }

    override fun onClick(v: View) {
        val objectList = v.tag as SupplierObjectForList
        onClickToSupplier.toSupplier(objectList)
    }
}