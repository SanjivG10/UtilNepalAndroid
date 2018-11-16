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

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DateConverterActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {


    //Widgets
    private Spinner yearNepSpinner;
    private Spinner monthNepSpinner;
    private Spinner dayNepSpinner;

    private Spinner yearEngSpinner;
    private Spinner monthEngSpinner;
    private Spinner dayEngSpinner;

    private Button convertToEnglish;
    private Button  convertToNepali;

    private TextView dateConvertedTextView;

    //NEP DATE INITIAL
    int startingNepYear = 2000;
    int startingNepMonth = 1;
    int startingNepDay = 1;
    int dayOfWeek = Calendar.WEDNESDAY;

    //EQUIVALENT ENG DATE
    int startingEngYear = 1943;
    int startingEngMonth = 4;
    int startingEngDay = 14;



    //starting eng date
    int startingEngYearForSelection = 1944;
    int startingEngMonthForSelection = 1;
    int startingEngDayForSelection = 1;

    //equivalent Nepali date for it
    int startingNepYearForSelection = 2000;
    int startingNepMonthForSelection = 9;
    int startingNepDayForSelection = 17;


    private String [] nepMonths = new String[12];
    private String [] nepDays = new String[32];
    private Map<String, String> shortMonthToLongMonthMatcher;


    int yearSelectedOnClick = 0;
    int noOfDaysOnClick = 30;
    int monthPositionOnClick = 1;

    private ArrayAdapter<String> yearAdapter;
    private ArrayAdapter<String> monthAdapter;
    private ArrayAdapter<String> dayAdapter;

    private ArrayAdapter<String> yearEngToNepAdapter;
    private ArrayAdapter<String> monthEngToNepAdapter;
    private ArrayAdapter<String> dayEngToNepAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_converter);

        convertToEnglish = findViewById(R.id.buttonConvertToEnglish);
        convertToNepali = findViewById(R.id.buttonConvertToNepali);

        dateConvertedTextView = findViewById(R.id.dateConvertedTextView);


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

         yearNepSpinner = findViewById(R.id.yearNepSpinner);
         monthNepSpinner = findViewById(R.id.monthNepSpinner);
         dayNepSpinner = findViewById(R.id.dayNepSpinner);


         yearEngSpinner = findViewById(R.id.yearEngSpinner);
         monthEngSpinner = findViewById(R.id.monthEngSpinner);
         dayEngSpinner = findViewById(R.id.dayEngSpinner);

         yearAdapter = new ArrayAdapter<String>(this,R.layout.each_spinner_item, nepyearArrayString);
         monthAdapter = new ArrayAdapter<String>(this,R.layout.each_spinner_item, getMonthArray());
         dayAdapter = new ArrayAdapter<String>(this,R.layout.each_spinner_item, getDayArray(30));


         yearEngToNepAdapter = new ArrayAdapter<String>(this,R.layout.each_spinner_item, getYearsForEnglish());
         monthEngToNepAdapter = new ArrayAdapter<String>(this,R.layout.each_spinner_item, getMonthForEnglish());

         Calendar mycal = new GregorianCalendar(startingEngYearForSelection,startingEngMonthForSelection, 1);
         int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);


         dayEngToNepAdapter = new ArrayAdapter<String>(this,R.layout.each_spinner_item, getDaysForEnglish(daysInMonth));

         yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


         yearEngToNepAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         monthEngToNepAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         dayEngToNepAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


         yearEngSpinner.setAdapter(yearEngToNepAdapter);
         monthEngSpinner.setAdapter(monthEngToNepAdapter);
         dayEngSpinner.setAdapter(dayEngToNepAdapter);

         yearNepSpinner.setAdapter(yearAdapter);
         monthNepSpinner.setAdapter(monthAdapter);
         dayNepSpinner.setAdapter(dayAdapter);


         yearNepSpinner.setOnItemSelectedListener(this);
         monthNepSpinner.setOnItemSelectedListener(this);
         dayNepSpinner.setOnItemSelectedListener(this);

         yearEngSpinner.setOnItemSelectedListener(this);
         monthEngSpinner.setOnItemSelectedListener(this);
         dayEngSpinner.setOnItemSelectedListener(this);


        convertToEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long daysCount = getTotalDaysCount();
                String date = convertToEnglish(daysCount);
                dateConvertedTextView.setText(date);

            }
        });

        convertToNepali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar currentEngDate = new GregorianCalendar();
                int engYear = startingEngYearForSelection;
                int engMonth = startingEngMonthForSelection;
                int engDay = startingEngDayForSelection;

                if(yearEngSpinner.getSelectedItem()!=null)
                {
                    engYear = Integer.parseInt(yearEngSpinner.getSelectedItem().toString());
                }

                if(monthEngSpinner.getSelectedItem()!=null)
                {
                    Date date = null;
                    try {
                        date = new SimpleDateFormat("MMMM").parse(monthEngSpinner.getSelectedItem().toString());
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);

                        engMonth = Integer.parseInt(String.valueOf(cal.get(Calendar.MONTH)));
                        Log.e("Month Value",engMonth + " < This is the eng month Selected");

                    } catch (ParseException e) {
                        e.printStackTrace();
                        engMonth = 1;
                        Log.e("Error Some Error", e.getMessage());
                    }

                }

                if(dayEngSpinner.getSelectedItem()!=null)
                {
                    engDay = Integer.parseInt(dayEngSpinner.getSelectedItem().toString());
                }

                currentEngDate.set(engYear, engMonth, engDay);

                Calendar baseEngDate = new GregorianCalendar();

                baseEngDate.set(startingEngYearForSelection, startingEngMonthForSelection-1, startingEngDayForSelection);

                long totalEngDaysCount = daysBetween(baseEngDate, currentEngDate);

                String textOfNepaliVersion = convertToNepaliFunction(totalEngDaysCount);

                dateConvertedTextView.setText(textOfNepaliVersion);

            }

        });

    }

    private String convertToNepaliFunction(long totalEngDaysCount) {
        int nepYear = startingNepYearForSelection;
        int nepMonth = startingNepMonthForSelection;
        int nepDay = startingNepDayForSelection;

        while (totalEngDaysCount != 0) {

            // getting the total number of days in month nepMonth in year nepYear
            int daysInIthMonth = EachMonthNumberOfDates.getNepaliMap().get(nepYear)[nepMonth];

            nepDay++; // incrementing nepali day

            if (nepDay > daysInIthMonth) {
                nepMonth++;
                nepDay = 1;
            }
            if (nepMonth > 12) {
                nepYear++;
                nepMonth = 1;
            }

            dayOfWeek++; // count the days in terms of 7 days
            if (dayOfWeek > 7) {
                dayOfWeek = 1;
            }
            totalEngDaysCount--;
        }

        return String.valueOf(nepYear) +" "+String.valueOf(nepMonth)+" "+ String.valueOf(nepDay) ;

    }

    private long daysBetween(Calendar startDate, Calendar endDate) {

        Calendar date = (Calendar) startDate.clone();
        long daysBetweens = 0;
        while (date.before(endDate)) {
            date.add(startDate.DAY_OF_MONTH, 1);
            daysBetweens++;
        }

        return daysBetweens;
    }

    private String [] getMonthForEnglish() {
        shortMonthToLongMonthMatcher = new HashMap<>();
        String[] months = new DateFormatSymbols().getMonths();

        String [] shortMonth = new String[months.length];
        int index = 0;
        for (String month : months)
        {
            shortMonth[index] = month.substring(0,3);
            shortMonthToLongMonthMatcher.put(month.substring(0,3),month);
            index++;
        }

        return shortMonth;
    }

    private String [] getYearsForEnglish() {
        int length = 0;
        String [] years = new String[89];
        for (int i = 1944; i<2033;i++)
        {
            years[length] = String.valueOf(i);
            length++;
        }

        return years;
    }

    private String [] getDaysForEnglish(int noOfdays)
    {
        int length = 0;
        String [] days = new String[90];
        for (int i = 1; i<32;i++)
        {
            days[length] = String.valueOf(i);
            length++;
        }

        return Arrays.copyOfRange(days,0,noOfdays);
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

        }

        else
        {
            Toast.makeText(this, "Some Error for this date, please choose another!", Toast.LENGTH_SHORT).show();
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

    private String[] getDayArray(int totalDays) {
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
                nepDays[12]= "१३";
                nepDays[13]= "१४";
                nepDays[14]= "१५";
                nepDays[15]= "१६";
                nepDays[16]= "१७";
                nepDays[17]= "१८";
                nepDays[18]= "१९";
                nepDays[19]= "२०";
                nepDays[20]= "२१";
                nepDays[21]= "२२";
                nepDays[22]= "२३";
                nepDays[23]= "२४";
                nepDays[24]= "२५";
                nepDays[25]= "२६";
                nepDays[26]= "२७";
                nepDays[27]= "२८";
                nepDays[28]= "२९";
                nepDays[29]= "३०";
                nepDays[30]= "३१";
                nepDays[31]= "३२";
                return Arrays.copyOfRange(nepDays,0,totalDays);
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

        if(monthNepSpinner.getSelectedItem()!=null) {
            monthPositionOnClick = Arrays.asList(nepMonths).indexOf(monthNepSpinner.getSelectedItem().toString()) + 1;
        }


        switch (parent.getId())
        {
            case R.id.yearNepSpinner:
                String yearSelectedOnClickString =  yearNepSpinner.getSelectedItem().toString();
                yearSelectedOnClick = Integer.parseInt(yearSelectedOnClickString);
                noOfDaysOnClick= EachMonthNumberOfDates.getNepaliMap().get(yearSelectedOnClick)[monthPositionOnClick];
                dayAdapter = new ArrayAdapter<String>(this,R.layout.each_spinner_item, getDayArray(noOfDaysOnClick));
                dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dayNepSpinner.setAdapter(dayAdapter);

                break;

            case R.id.monthNepSpinner:
                noOfDaysOnClick= EachMonthNumberOfDates.getNepaliMap().get(yearSelectedOnClick)[monthPositionOnClick];
                dayAdapter = new ArrayAdapter<String>(this,R.layout.each_spinner_item, getDayArray(noOfDaysOnClick));
                dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dayNepSpinner.setAdapter(dayAdapter);

                break;

            case R.id.yearEngSpinner:
                int yearSelectedOnClickStringForEnglishInteger = 1944;
                int monthSelectedOnClickStringEnglishInteger = 1;
                if(yearEngSpinner.getSelectedItem()!=null) {
                    String yearSelectedOnClickStringEnglish = yearEngSpinner.getSelectedItem().toString();
                    yearSelectedOnClickStringForEnglishInteger = Integer.parseInt(yearSelectedOnClickStringEnglish);
                }

                if(monthEngSpinner.getSelectedItem()!=null)
                {
                    Date date = null;//put your month name here
                    try {
                        date = new SimpleDateFormat("MMM").parse( shortMonthToLongMonthMatcher.get(monthEngSpinner.getSelectedItem().toString()));
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        monthSelectedOnClickStringEnglishInteger=cal.get(Calendar.MONTH);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }

                Calendar mycal = new GregorianCalendar(yearSelectedOnClickStringForEnglishInteger, monthSelectedOnClickStringEnglishInteger, 1);
                int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH); // 28

                dayEngToNepAdapter = new ArrayAdapter<String>(this,R.layout.each_spinner_item, getDaysForEnglish(daysInMonth));
                dayEngToNepAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dayEngSpinner.setAdapter(dayEngToNepAdapter);

                break;

            case R.id.monthEngSpinner:
                int yearSelectedOnClickStringForEnglish = 1944;
                int monthSelectedOnClickStringEnglish = 1;
                if(yearEngSpinner.getSelectedItem()!=null) {
                    String yearSelectedOnClickStringEnglish = yearEngSpinner.getSelectedItem().toString();
                    yearSelectedOnClickStringForEnglish = Integer.parseInt(yearSelectedOnClickStringEnglish);
                }

                if(monthEngSpinner.getSelectedItem()!=null)
                {
                    Date date = null;
                    try {
                        date = new SimpleDateFormat("MMM").parse( shortMonthToLongMonthMatcher.get(monthEngSpinner.getSelectedItem().toString()));
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        monthSelectedOnClickStringEnglish=cal.get(Calendar.MONTH);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }

                Calendar cal = new GregorianCalendar(yearSelectedOnClickStringForEnglish, monthSelectedOnClickStringEnglish, 1);
                int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

                dayEngToNepAdapter = new ArrayAdapter<String>(this,R.layout.each_spinner_item, getDaysForEnglish(days));
                dayEngToNepAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dayEngSpinner.setAdapter(dayEngToNepAdapter);
                break;
        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


































