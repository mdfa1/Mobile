package com.example.contacts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerDialog extends DialogFragment {

    private Calendar selectedDate;
    private CalendarView cv;
    private Button select, cancel;
    private SaveDateListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.select_date, container);
        getDialog().setTitle("Select Date");
        initLayouts(view);

        // Set the initial date on the CalendarView if it's set
        if (selectedDate != null) {
            cv.setDate(selectedDate.getTimeInMillis(), true, true);
        }

        return view;
    }

    private void initLayouts(View view) {
        cv = view.findViewById(R.id.calendarView);
        select = view.findViewById(R.id.buttonSelect);
        cancel = view.findViewById(R.id.buttonCancel);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if (selectedDate == null) {
                    selectedDate = Calendar.getInstance();
                }
                selectedDate.set(year, month, dayOfMonth);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null && selectedDate != null) {
                    mListener.didFinishDatePickerDialog(selectedDate);
                }
                getDialog().dismiss();
            }
        });
    }

    // Setter method for setting the date
    public void setDate(Calendar date) {
        selectedDate = date;
    }

    // Setter method for setting the listener
    public void setListener(SaveDateListener listener) {
        mListener = listener;
    }

    @Override
    public void onResume() {
        super.onResume();
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.9);
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setLayout(width, height);
    }

    public void setDate(int year, int month, int day) {
    }

    public interface SaveDateListener {
        void didFinishDatePickerDialog(Calendar selectedDate);
    }
}
