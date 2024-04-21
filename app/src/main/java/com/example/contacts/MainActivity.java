package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.SaveDateListener {

    ImageButton listImageButton, mapImageButton, settingsImageButton, saveButton;
    Button changeBirthdayButton, selectRelationshipButton; // Added button for selecting relationship
    ToggleButton editToggle;
    EditText nameEditText, addressEditText, cityEditText,
            stateEditText, zipEditText, homeEditText,
            cellEditText, emailEditText; // Corrected typo in emailEditText
    TextView birthdayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayoutComponents();
        NavButtonsInitializer.initNavButtons(listImageButton, mapImageButton, settingsImageButton, this);
        initToggleButton();
        setForEditing(false);
        initChangeBirthdayButton();
        initSelectRelationshipButton();
        // Initialize the button for selecting relationship
    }

    private void initToggleButton() {
        editToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setForEditing(editToggle.isChecked());
            }
        });
    }

    private void setForEditing(boolean checked) {
        nameEditText.setEnabled(checked);
        addressEditText.setEnabled(checked);
        cityEditText.setEnabled(checked);
        stateEditText.setEnabled(checked);
        zipEditText.setEnabled(checked);
        homeEditText.setEnabled(checked);
        cellEditText.setEnabled(checked);
        emailEditText.setEnabled(checked); // Corrected typo in emailEditText
        changeBirthdayButton.setEnabled(checked);
        saveButton.setEnabled(checked);
        if (checked) {
            nameEditText.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInput(nameEditText, 0);
        }
    }

    private void initChangeBirthdayButton() {
        changeBirthdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the date picker dialog
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        final Calendar currentDate = Calendar.getInstance();
        int year = currentDate.get(Calendar.YEAR);
        int month = currentDate.get(Calendar.MONTH);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog();
        datePickerDialog.setDate(year, month, day);
        datePickerDialog.setListener(new DatePickerDialog.SaveDateListener() {
            @Override
            public void didFinishDatePickerDialog(Calendar selectedDate) {
                birthdayText.setText(DateFormat.format("dd/MM/yyyy", selectedDate));
            }
        });
        datePickerDialog.show(getSupportFragmentManager(), "DatePicker");
    }

    // Initialize the button for selecting relationship
    private void initSelectRelationshipButton() {
        selectRelationshipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRelationshipDialog();
            }
        });
    }

    // Method to show the custom dialog for selecting relationship
    private void showRelationshipDialog() {
        RelationshipDialog dialog = new RelationshipDialog();
        dialog.show(getSupportFragmentManager(), "RelationshipDialog");
    }

    private void initLayoutComponents() {
        listImageButton = findViewById(R.id.imageButtonList);
        mapImageButton = findViewById(R.id.imageButtonMap);
        settingsImageButton = findViewById(R.id.imageButtonSettings);
        editToggle = findViewById(R.id.toggleButtonEdit);
        nameEditText = findViewById(R.id.editName);
        addressEditText = findViewById(R.id.editAddress);
        cityEditText = findViewById(R.id.editCity);
        stateEditText = findViewById(R.id.editState);
        zipEditText = findViewById(R.id.editZip);
        homeEditText = findViewById(R.id.editHome);
        cellEditText = findViewById(R.id.editCell);
        emailEditText = findViewById(R.id.editEmail); // Corrected typo in emailEditText
        changeBirthdayButton = findViewById(R.id.btnBirthday);
        saveButton = findViewById(R.id.imageButtonSave);
        birthdayText = findViewById(R.id.textViewBirthday);
        selectRelationshipButton = findViewById(R.id.btnSelectRelationship); // Added initialization for the select relationship button
    }

    @Override
    public void didFinishDatePickerDialog(Calendar selectedDate) {
        birthdayText.setText(DateFormat.format("dd/MM/yyyy", selectedDate));
    }
}
