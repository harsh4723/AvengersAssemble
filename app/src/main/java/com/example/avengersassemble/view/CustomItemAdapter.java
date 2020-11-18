package com.example.avengersassemble.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avengersassemble.R;
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

        holder.textViewLocationOfChef.setText(listItem.getLocation());
        holder.textViewPrice.setText("Rs "+listItem.getPrice());
//        holder.itemsLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent inPay = new Intent(v.getContext(), PaymentsActivity.class);
//                //inPay.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                inPay.putExtra("PRICE_TAG",listItem.getPrice());
//                v.getContext().startActivity(inPay);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewHead;
        TextView textViewDesc;
        TextView textViewLocationOfChef;
        TextView textViewPrice;
        Button orderNowButton;
        LinearLayout itemsLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHead = (TextView) itemView.findViewById(R.id.dishName);
            textViewDesc = (TextView) itemView.findViewById(R.id.descriptionOfDish);
            textViewLocationOfChef = (TextView) itemView.findViewById(R.id.locationOfChef);
            textViewPrice = (TextView) itemView.findViewById(R.id.priceOfDish);
            //orderNowButton = (Button) itemView.findViewById(R.id.orderNow);
            itemsLayout= itemView.findViewById(R.id.itemsLayout);

        }
    }
}
