package com.utilnepal.DateConverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.utilnepal.DateConverter.utils.EachMonthNumberOfDates;
import com.utilnepal.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class DateConverterActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {


    //Widgets
    private Spinner yearNepSpinner;
    private Spinner monthNepSpinner;
    private Spinner dayNepSpinner;

    private Spinner yearEngSpinner;
    private Spinner monthEngSpinner;
    private Spinner dayEngSpinner;

    private Button convertToEnglish;
    private TextView dateConvertedTextView;

    //NEP DATE INITIAL
    int startingNepYear = 2000;
    int startingNepMonth = 1;
    int startingNepDay = 1;
    int dayOfWeek = Calendar.WEDNESDAY; // 2000/1/1 is Wednesday

    //ENG DATE
    int startingEngYear = 1943;
    int startingEngMonth = 4;
    int startingEngDay = 14;


    private String [] nepMonths = new String[12];
    private String [] nepDays = new String[32];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_converter);

        convertToEnglish = findViewById(R.id.buttonConvertToEnglish);
        dateConvertedTextView = findViewById(R.id.dateConvertedTextView);

        yearNepSpinner = findViewById(R.id.yearNepSpinner);
        monthNepSpinner = findViewById(R.id.monthNepSpinner);
        dayNepSpinner = findViewById(R.id.dayNepSpinner);


        // Setting Year
        Set<Integer> nepYearSet = EachMonthNumberOfDates.getNepaliMap().keySet();
        Integer[] nepYearArray =  nepYearSet.toArray(new Integer[nepYearSet.size()]);
        List<Integer> nepYearList = new ArrayList<>();

        String [] nepyearArrayString = new String[nepYearArray.length];

        for (int i =0; i<nepYearArray.length; i++)
        {
            nepYearList.add(nepYearArray[i]);
        }

        Collections.sort(nepYearList);
        for (int i =0; i<nepYearList.size(); i++)
        {
            nepyearArrayString[i] = nepYearList.get(i).toString();
            Log.e("StringNepal",nepyearArrayString[i]);
            nepyearArrayString[i] = nepyearArrayString[i].replace("1","१");
            nepyearArrayString[i] = nepyearArrayString[i].replace("2","२");
            nepyearArrayString[i] = nepyearArrayString[i].replace("3","३");
            nepyearArrayString[i] = nepyearArrayString[i].replace("4","४");
            nepyearArrayString[i] = nepyearArrayString[i].replace("5","५");
            nepyearArrayString[i] = nepyearArrayString[i].replace("6","६");
            nepyearArrayString[i] = nepyearArrayString[i].replace("7","७");
            nepyearArrayString[i] = nepyearArrayString[i].replace("8","८");
            nepyearArrayString[i] = nepyearArrayString[i].replace("9","९");
            nepyearArrayString[i] = nepyearArrayString[i].replace("0","०");
        }



        yearEngSpinner = findViewById(R.id.yearEngSpinner);
        monthEngSpinner = findViewById(R.id.monthEngSpinner);
        dayEngSpinner = findViewById(R.id.dayEngSpinner);

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this,R.layout.each_spinner_item, nepyearArrayString);
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this,R.layout.each_spinner_item, getMonthArray());
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(this,R.layout.each_spinner_item, getDayArray());


        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        yearNepSpinner.setAdapter(yearAdapter);
        monthNepSpinner.setAdapter(monthAdapter);
        dayNepSpinner.setAdapter(dayAdapter);

        yearNepSpinner.setOnItemSelectedListener(this);
        monthNepSpinner.setOnItemSelectedListener(this);
        dayNepSpinner.setOnItemSelectedListener(this);

        convertToEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long daysCount = getTotalDaysCount();
                if (daysCount != 0) {
                    String date = convertToEnglish(daysCount);
                    dateConvertedTextView.setText(date);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Some Error Occured. Please choose date again",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private String convertToEnglish(long dayscount) {
        int engYear = startingEngYear;
        int engMonth = startingEngMonth;
        int engDay = startingEngDay;

        // for leap year
        int[] daysInMonth = new int[] { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        int[] daysInMonthOfLeapYear = new int[] { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        int endDayOfMonth = 0;


        while (dayscount != 0) {
            if (isLeapYear(engYear)) {
                endDayOfMonth = daysInMonthOfLeapYear[engMonth];
            } else {
                endDayOfMonth = daysInMonth[engMonth];
            }
            engDay++;
            dayOfWeek++;
            if (engDay > endDayOfMonth) {
                engMonth++;
                engDay = 1;
                if (engMonth > 12) {
                    engYear++;
                    engMonth = 1;
                }
            }
            if (dayOfWeek > 7) {
                dayOfWeek = 1;
            }
            dayscount--;
        }

        return String.valueOf(engYear) +" "+String.valueOf(engMonth)+" "+ String.valueOf(engDay) ;
    }

    public static boolean isLeapYear(int year) {
        if (year % 100 == 0) {
            return year % 400 == 0;
        } else {
            return year % 4 == 0;
        }
    }

    private long getTotalDaysCount() {
        long totalNepDaysCount = 0;
        String newDate=null;
        int selectedDate =0;
        if(yearNepSpinner.getSelectedItem()!=null && monthNepSpinner.getSelectedItem()!=null && dayNepSpinner.getSelectedItem()!=null)
        {
            newDate= convertNepYearToEnglishWithTextReplacement(yearNepSpinner.getSelectedItem().toString());
            selectedDate = Integer.parseInt(newDate);

            for (int i = startingNepYear; i < selectedDate; i++) {
                for (int j = 1; j <= 12; j++) {
                    totalNepDaysCount += EachMonthNumberOfDates.getNepaliMap().get(i)[j];
                }
            }

            int selectedMonth=  Arrays.asList(nepMonths).indexOf(monthNepSpinner.getSelectedItem().toString())+1;

            for (int j = startingNepMonth; j < selectedMonth ; j++) {
                totalNepDaysCount += EachMonthNumberOfDates.getNepaliMap().get(selectedDate)[j];
            }

            int selectedDay=  Arrays.asList(nepDays).indexOf(dayNepSpinner.getSelectedItem().toString())+1;
            totalNepDaysCount += selectedDay - startingNepDay;

            Log.e("Selected Day", String.valueOf(selectedDay)+ "THIS IS SELECTED DAY");
        }

        else
        {
            Toast.makeText(this, "Some Error Occured. Please Check Your Date", Toast.LENGTH_SHORT).show();
        }

        return totalNepDaysCount;
    }

    private String convertNepYearToEnglishWithTextReplacement(String spinnerEngYear) {

        spinnerEngYear = spinnerEngYear.replace("०","0");
        spinnerEngYear = spinnerEngYear.replace("१","1");
        spinnerEngYear = spinnerEngYear.replace("२","2");
        spinnerEngYear = spinnerEngYear.replace("३","3");
        spinnerEngYear = spinnerEngYear.replace("४","4");
        spinnerEngYear = spinnerEngYear.replace("५","5");
        spinnerEngYear = spinnerEngYear.replace("६","6");
        spinnerEngYear = spinnerEngYear.replace("७","7");
        spinnerEngYear = spinnerEngYear.replace("८","8");
        spinnerEngYear = spinnerEngYear.replace("९","9");
        return spinnerEngYear;
    }

    private String[] getDayArray() {
        nepDays[0]=  "१";
        nepDays[1]=  "२";
        nepDays[2]=  "३";
        nepDays[3]=  "४";
        nepDays[4]=  "५";
        nepDays[5]=  "६";
        nepDays[6]=  "७";
        nepDays[7]=  "८";
        nepDays[8]=  "९";
        nepDays[9]=  "१०";
        nepDays[10]= "११";
        nepDays[11]= "१२";
        nepDays[12]=  "१३";
        nepDays[13]="१४";
        nepDays[14]="१५";
        nepDays[15]="१६";
        nepDays[16]="१७";
        nepDays[17]="१८";
        nepDays[18]="१९";
        nepDays[19]="२०";
        nepDays[20]="२१";
        nepDays[21]="२२";
        nepDays[22]="२३";
        nepDays[23]="२४";
        nepDays[24]="२५";
        nepDays[25]="२६";
        nepDays[26]="२७";
        nepDays[27]="२८";
        nepDays[28]="२९";
        nepDays[29]="३०";
        nepDays[30]="३१";
        nepDays[31]="३२";

        return nepDays;
    }

    private String[] getMonthArray() {

        nepMonths[0]= "बैशाख";
        nepMonths[1]= "जेष्ठ";
        nepMonths[2]= "आषाढ";
        nepMonths[3]= "श्रावण";
        nepMonths[4]= "भाद्र";
        nepMonths[5]= "आश्विन";
        nepMonths[6]= "कार्तिक";
        nepMonths[7]= "मंसिर";
        nepMonths[8]= "पौष";
        nepMonths[9]= "माघ";
        nepMonths[10]= "फाल्गुन ";
        nepMonths[11]= "चैत्र";
        return nepMonths ;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

































