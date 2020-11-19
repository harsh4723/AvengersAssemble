package com.example.avengersassemble.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avengersassemble.R;
import com.example.avengersassemble.model.ItemDataBase;
import com.example.avengersassemble.model.ItemsDao;
import com.example.avengersassemble.model.ListItem;

import java.util.ArrayList;
import java.util.List;

public class CustomItemAdapter extends RecyclerView.Adapter<CustomItemAdapter.ViewHolder> {


    private ArrayList<ListItem> listItems;

    public CustomItemAdapter(ArrayList<ListItem> listItem) {
        this.listItems = listItem;
    }


    public void updateListItems(List<ListItem> newListItems) {
        listItems.clear();
        listItems.addAll(newListItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);
        holder.textViewHead.setText(listItem.getHead());
        holder.textViewPower.setText(listItem.getPower());
        holder.textViewDesc.setText(listItem.getDescription());
        holder.imageViewCh.setImageBitmap(listItem.getSimage());
        holder.delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItems.remove(listItem);
                notifyDataSetChanged();
                deleteFromDatabase(listItem,v);
            }
        });
    }

    private void deleteFromDatabase(ListItem item,View v) {
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                ItemsDao dao = ItemDataBase.getInstance(v.getContext()).itemsDao();
                dao.delete(item);
            }
        });
        th.start();
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewHead;
        TextView textViewDesc;
        TextView textViewPower;
        ImageView imageViewCh;
        ImageButton delButton;
        LinearLayout itemsLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHead = itemView.findViewById(R.id.chName);
            textViewDesc = itemView.findViewById(R.id.descriptionCh);
            textViewPower = itemView.findViewById(R.id.powerCh);
            imageViewCh = itemView.findViewById(R.id.chImg);
            delButton = itemView.findViewById(R.id.del);
            itemsLayout = itemView.findViewById(R.id.itemsLayout);

        }
    }
}
