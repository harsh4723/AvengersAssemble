package com.example.avengersassemble.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.example.avengersassemble.R;
import com.example.avengersassemble.model.ListItem;
import com.example.avengersassemble.model.RoomConverters;
import com.example.avengersassemble.viewmodel.AddViewModel;

import java.io.IOException;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class AddFragment extends Fragment {

    @BindView(R.id.postCh)
    Button postCh;

    @BindView(R.id.chTitle)
    EditText chTitle;

    @BindView(R.id.addDescription)
    EditText descriptionTitle;

    @BindView(R.id.superPower)
    EditText superPower;

    @BindView(R.id.loadingView)
    ProgressBar progressBar;

    @BindView(R.id.scrollPostCh)
    ScrollView addScreen;

    @BindView(R.id.postAgain)
    LinearLayout postAgain;

    @BindView(R.id.postAgainBtn)
    Button postAgainBtn;

    @BindView(R.id.addImg)
    Button addImage;

    private AddViewModel addViewModel;
    Uri imageUri;
    String imageSrc;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addViewModel = ViewModelProviders.of(this).get(AddViewModel.class);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImagesFromGallery();
            }
        });
        postCh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addViewModel.addCharacters(chTitle.getText().toString(), descriptionTitle.getText().toString(),
                        superPower.getText().toString(), imageSrc);
                addScreen.setVisibility(View.GONE);
                postAgain.setVisibility(View.VISIBLE);
            }
        });
        postAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addScreen.setVisibility(View.VISIBLE);
                postAgain.setVisibility(View.GONE);
            }
        });
        observeViewModel();
    }

    private void observeViewModel() {
        addViewModel.getLiveCookData().observe(getViewLifecycleOwner(), new Observer<ListItem>() {
            @Override
            public void onChanged(ListItem listItem) {
                Log.d("livedataobserver", "listItem");
            }
        });
    }

    private void loadImagesFromGallery() {

        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(gallery, "Select Picture"), 1);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                        Objects.requireNonNull(getActivity()).getContentResolver(), imageUri);
                imageSrc = RoomConverters.BitMapToString(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
