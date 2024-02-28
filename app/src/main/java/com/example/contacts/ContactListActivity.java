package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

public class ContactListActivity extends AppCompatActivity {

    ImageButton listImageButton, mapImageButton, settingsImageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        initLayoutComponents();
        NavButtonsInitializer.initNavButtons(listImageButton, mapImageButton, settingsImageButton, this);
    }

    private void initLayoutComponents(){
        listImageButton = findViewById(R.id.imageButtonList);
        mapImageButton = findViewById(R.id.imageButtonMap);
        settingsImageButton = findViewById(R.id.imageButtonSettings);
        listImageButton.setEnabled(false);
    }
}