package com.developmentavk.avk_app_kotlin.presentation.goods


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.developmentavk.avk_app_kotlin.databinding.ItemGoodBinding
import com.developmentavk.avk_app_kotlin.domain.entity.GoodObjectForList


class GoodAdapter(private val onClickToSupplier: OnClickToSupplier) :
    RecyclerView.Adapter<GoodAdapter.DataHolder>(), View.OnClickListener {

    interface OnClickToSupplier {
        fun goToCardGood(arg: GoodObjectForList)
    }

    var objectsList = listOf<GoodObjectForList>()
        set(value){
            val diffCallBack = GoodAdapter.GoodObjectForListDiffCallBack(field, value)
            val diffResult = DiffUtil.calculateDiff(diffCallBack)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    class GoodObjectForListDiffCallBack(
        private val oldList: List<GoodObjectForList>,
        private val newList: List<GoodObjectForList>): DiffUtil.Callback(){
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return if(newList.size - 1 < oldItemPosition) {
                false
            }else{
                oldList[oldItemPosition].uid == newList[newItemPosition].uid &&
                        oldList[oldItemPosition].spec == newList[newItemPosition].spec
            }
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldObject = oldList[oldItemPosition]
            val newObject = newList[newItemPosition]
            return oldObject == newObject
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemGoodBinding = ItemGoodBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return DataHolder(binding)
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val objectList = objectsList[position]
        holder.binding.dataGood = objectList
        holder.binding.root.tag = objectList
    }

    override fun getItemCount(): Int = objectsList.size

    class DataHolder(val binding: ItemGoodBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onClick(v: View) {
        val objectList = v.tag as GoodObjectForList
        onClickToSupplier.goToCardGood(objectList)
    }
}
