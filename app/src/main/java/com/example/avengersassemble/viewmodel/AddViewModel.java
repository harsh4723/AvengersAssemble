package com.example.avengersassemble.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.avengersassemble.model.ItemDataBase;
import com.example.avengersassemble.model.ItemsDao;
import com.example.avengersassemble.model.ListItem;

import java.util.ArrayList;
import java.util.List;

public class AddViewModel extends AndroidViewModel {

    public MutableLiveData<ListItem> liveItemData;
    private AsyncTask<Void, Void,Void> insertTask;
    public AddViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<ListItem> getLiveCookData() {
        if (liveItemData == null) {
            liveItemData = new MutableLiveData<>();
        }
        return liveItemData;
    }


    public void addCharacters(String chTitle, String description, String superPower,String imageSrc) {
        ListItem listItems = new ListItem(chTitle,description,superPower,imageSrc);
        liveItemData.setValue(listItems);
        insertTask = new InsertItemsTask();
        insertTask.execute();
    }

    private class InsertItemsTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            ItemsDao dao = ItemDataBase.getInstance(getApplication()).itemsDao();
            dao.insertAll(liveItemData.getValue());
            return null;
        }
    }

}
