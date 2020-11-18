package com.example.avengersassemble.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.avengersassemble.R;
import com.example.avengersassemble.model.ListItem;
import com.example.avengersassemble.viewmodel.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemsFragment extends Fragment {
    private ItemViewModel itemViewModel;

    private CustomItemAdapter adapter = new CustomItemAdapter(new ArrayList<ListItem>());
    @BindView(R.id.itemsRecycler)
    RecyclerView recyclerView;

    @BindView(R.id.onRefresh)
    SwipeRefreshLayout onRefresh;

    @BindView(R.id.loadingView)
    ProgressBar loadView;

    @BindView(R.id.listError)
    TextView errorView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        itemViewModel.refresh();
        onRefresh.setOnRefreshListener(() -> {
            itemViewModel.refresh();
            onRefresh.setRefreshing(false);
        });
        observeViewModel();

    }


    private void observeViewModel() {
        itemViewModel.displayItems.observe(getViewLifecycleOwner(), new Observer<List<ListItem>>() {
            @Override
            public void onChanged(List<ListItem> listItems) {
                if (listItems != null) {
                    recyclerView.setVisibility(View.VISIBLE);
                    adapter.updateListItems(listItems);
                }
            }
        });
    }

}
