package com.example.avengersassemble.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.avengersassemble.model.ItemDataBase;
import com.example.avengersassemble.model.ListItem;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {
    public MutableLiveData<List<ListItem>> displayItems = new MutableLiveData<List<ListItem>>();
    public MutableLiveData<Boolean> itemLoading = new MutableLiveData<Boolean>();
    private AsyncTask<Void, Void, List<ListItem>> retrieveTask;

    public ItemViewModel(@NonNull Application application) {
        super(application);
    }

    public void refresh() {
        fetchFromDatabase();
    }

    private void fetchFromDatabase() {
        itemLoading.setValue(true);
        retrieveTask = new RetrieveItemTask();
        retrieveTask.execute();
    }

    private void itemsRetrieved(List<ListItem> listItems) {
        itemLoading.setValue(false);
        displayItems.setValue(listItems);
    }

    private class RetrieveItemTask extends AsyncTask<Void, Void, List<ListItem>> {

        @Override
        protected List<ListItem> doInBackground(Void... voids) {
            return ItemDataBase.getInstance(getApplication()).itemsDao().getAllItems();
        }

        @Override
        protected void onPostExecute(List<ListItem> listItems) {
            itemsRetrieved(listItems);
        }
    }
}
