package com.example.projectsubmissionone

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListGoodsAdapter(private val listgoods: ArrayList<Goods>) : RecyclerView.Adapter<ListGoodsAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    // ItemOnClickListener
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_goods, viewGroup, false)
        return ListViewHolder(view)
    }



    override fun getItemCount(): Int {
        return listgoods.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, detail,photo ) = listgoods[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .apply(RequestOptions().override(55, 55))
            .into(holder.imgPhoto)
        holder.tvType.text = name
        holder.tvDetail.text = detail


        val selectgoods = holder.itemView.context

        holder.itemView.setOnClickListener {

            onItemClickCallback.onItemClicked(listgoods[holder.adapterPosition])
            val movedetail = Intent(selectgoods, detail_goods::class.java)
            movedetail.putExtra(detail_goods.EXTRA_NAME, name)
            movedetail.putExtra(detail_goods.EXTRA_DETAIL, detail)
            movedetail.putExtra(detail_goods.EXTRA_PHOTO, photo)
            selectgoods.startActivity(movedetail)
        }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvType: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Goods)
    }
}