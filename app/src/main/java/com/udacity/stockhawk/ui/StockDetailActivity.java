package com.udacity.stockhawk.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.model.StockDto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class StockDetailActivity extends AppCompatActivity {


    private StockDto stock;
    private ArrayList<Entry> entries = new ArrayList<Entry>();
    private ArrayList<String> dates = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_detail);


        Bundle bundle = getIntent().getExtras();

        if(bundle!=null){
            stock = getIntent().getParcelableExtra("StockDto");
            Toast.makeText(this, stock.getHistory(),Toast.LENGTH_SHORT);
        }

        LineChart chart = (LineChart) findViewById(R.id.chart);

        loadChartValues(stock.getHistory().split("\n"));

        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.BLUE);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh
    }

    private void loadChartValues (String[] data){
        for(int i=0; i<20; i++){
            String[] coord = data[i].split(",");
            float valueX = i;
            float valueY = Float.parseFloat(coord[1]);
            entries.add(new Entry(valueX,valueY));
            dates.add(convertToDate(Long.parseLong(coord[0])));
        }
    }

    private String convertToDate(Long date) {
        String dateFormat = "dd/MM/yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return formatter.format(calendar.getTime());
    }
}
