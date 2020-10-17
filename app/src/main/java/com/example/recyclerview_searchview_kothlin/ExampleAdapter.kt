package com.example.recyclerview_searchview_kothlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

class ExampleAdapter(val exampleItems : ArrayList<ExampeItem>) : RecyclerView.Adapter<ExampleAdapter.ExampleViewholder>() ,Filterable {

     var  exampleItemsFull = ArrayList<ExampeItem>(exampleItems);


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewholder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.example_item_viewholder,
            parent, false
        )
        return ExampleViewholder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewholder, position: Int) {
        val exampleItem = exampleItems[position]

        holder.imageView.setImageResource(exampleItem.imageResource)
        holder.textView1.setText(exampleItem.text1)
        holder.textView2.setText(exampleItem.text2)

    }

    override fun getItemCount(): Int {
       return exampleItems.size
    }

    class ExampleViewholder(itemView: View) : RecyclerView.ViewHolder(itemView){

         var imageView : ImageView
         var textView1 : TextView
         var textView2 : TextView

        init {
            imageView = itemView.findViewById(R.id.image_view)
            textView1 = itemView.findViewById(R.id.text_view_1)
            textView2 = itemView.findViewById(R.id.text_view_2)
        }


    }

    override fun getFilter(): Filter {
        return getExampleFilter()

    }

    fun getExampleFilter()  = object : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            var filteredList : ArrayList<ExampeItem> = ArrayList();

            if(constraint==null || constraint.length==0){
                filteredList.addAll(exampleItemsFull)
            }else{
                val filterPattern : String = constraint.toString().toLowerCase().trim()

               for(list in exampleItemsFull){
                   if(list.text1.contains(filterPattern)){
                       filteredList.add(list)
                   }
               }
            }
            val results:FilterResults=FilterResults()
            results.values=filteredList
            return results;
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results != null) {
                exampleItems.clear()
                exampleItems.addAll(results.values as Collection<ExampeItem>)
                notifyDataSetChanged()
            }
            notifyDataSetChanged()
        }

    }

}