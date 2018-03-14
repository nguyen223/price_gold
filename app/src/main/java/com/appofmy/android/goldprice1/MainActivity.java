package com.appofmy.android.goldprice1;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnChartGestureListener,
        OnChartValueSelectedListener {

    private LineChart mChart;
    private int numbersValue;
    private TextView valueUsd;
    private ArrayList<String> usdStringNull;
    private String nullUsd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // To make full screen layout
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        usdStringNull = QueryUtils.usdString;
        nullUsd = usdStringNull.get(0);


        numbersValue = EarthquakeActivity.dateNumbers;

        //hiden.

        mChart = (LineChart) findViewById(R.id.linechart);
        if (mChart != null) {
            mChart.setOnChartGestureListener(this);
        }
        if (mChart != null) {
            mChart.setOnChartValueSelectedListener(this);
        }
        if (mChart != null) {
            mChart.setDrawGridBackground(false);
        }

        // add data
        YAxis leftAxis = null;
        if (mChart != null) {
            leftAxis = mChart.getAxisLeft();
        }


        valueUsd = (TextView) findViewById(R.id.text_usdcurrency) ;


        Button buttonUsd = (Button)findViewById(R.id.buttun_usd);
        Button buttonGbp = (Button)findViewById(R.id.buttun_gbp);
        Button buttonEuro = (Button)findViewById(R.id.button_euro);
        valueUsd.setVisibility(View.GONE);
        mChart.clear();
        setData2();

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);

        // no description text
        mChart.setDescription("Price Gold Line Chart");
        mChart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        // mChart.setScaleXEnabled(true);
        // mChart.setScaleYEnabled(true);

        YAxis leftAxis1 = mChart.getAxisLeft();
        leftAxis1.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines

        //leftAxis.setYOffset(20f);
        leftAxis1.enableGridDashedLine(10f, 10f, 0f);
        leftAxis1.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        leftAxis1.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);

        //mChart.getViewPortHandler().setMaximumScaleY(2f);
        //mChart.getViewPortHandler().setMaximumScaleX(2f);

        mChart.animateX(3500, Easing.EasingOption.EaseInOutQuart);

        //  dont forget to refresh the drawing
        mChart.invalidate();
        if (buttonUsd != null) {
            buttonUsd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    valueUsd.setVisibility(View.GONE);
                    mChart.clear();
                    setData2();

                    // get the legend (only possible after setting data)
                    Legend l = mChart.getLegend();

                    // modify the legend ...
                    // l.setPosition(LegendPosition.LEFT_OF_CHART);
                    l.setForm(Legend.LegendForm.LINE);

                    // no description text
                    mChart.setDescription("Price Gold Line Chart");
                    mChart.setNoDataTextDescription("You need to provide data for the chart.");

                    // enable touch gestures
                    mChart.setTouchEnabled(true);

                    // enable scaling and dragging
                    mChart.setDragEnabled(true);
                    mChart.setScaleEnabled(true);
                    // mChart.setScaleXEnabled(true);
                    // mChart.setScaleYEnabled(true);

                    YAxis leftAxis = mChart.getAxisLeft();
                    leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines

                    //leftAxis.setYOffset(20f);
                    leftAxis.enableGridDashedLine(10f, 10f, 0f);
                    leftAxis.setDrawZeroLine(false);

                    // limit lines are drawn behind data (and not on top)
                    leftAxis.setDrawLimitLinesBehindData(true);

                    mChart.getAxisRight().setEnabled(false);

                    //mChart.getViewPortHandler().setMaximumScaleY(2f);
                    //mChart.getViewPortHandler().setMaximumScaleX(2f);

                    mChart.animateX(3500, Easing.EasingOption.EaseInOutQuart);

                    //  dont forget to refresh the drawing
                    mChart.invalidate();

                }
            });
        }
        if (buttonGbp != null) {
            buttonGbp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    valueUsd.setVisibility(View.GONE);
                    mChart.clear();
                    setData3();
                    // get the legend (only possible after setting data)
                    Legend l = mChart.getLegend();

                    // modify the legend ...
                    // l.setPosition(LegendPosition.LEFT_OF_CHART);
                    l.setForm(Legend.LegendForm.LINE);

                    // no description text
                    mChart.setDescription("Price Gold Line Chart");
                    mChart.setNoDataTextDescription("You need to provide data for the chart.");

                    // enable touch gestures
                    mChart.setTouchEnabled(true);

                    // enable scaling and dragging
                    mChart.setDragEnabled(true);
                    mChart.setScaleEnabled(true);
                    // mChart.setScaleXEnabled(true);
                    // mChart.setScaleYEnabled(true);

                    YAxis leftAxis = mChart.getAxisLeft();
                    leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines

                    //leftAxis.setYOffset(20f);
                    leftAxis.enableGridDashedLine(10f, 10f, 0f);
                    leftAxis.setDrawZeroLine(false);

                    // limit lines are drawn behind data (and not on top)
                    leftAxis.setDrawLimitLinesBehindData(true);

                    mChart.getAxisRight().setEnabled(false);

                    //mChart.getViewPortHandler().setMaximumScaleY(2f);
                    //mChart.getViewPortHandler().setMaximumScaleX(2f);

                    mChart.animateX(3500, Easing.EasingOption.EaseInOutQuart);

                    //  dont forget to refresh the drawing
                    mChart.invalidate();

                }
            });
        }
        if (buttonEuro != null) {
            buttonEuro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    valueUsd.setVisibility(View.GONE);
                    mChart.clear();
                    setData4();
                    // get the legend (only possible after setting data)
                    Legend l = mChart.getLegend();

                    // modify the legend ...
                    // l.setPosition(LegendPosition.LEFT_OF_CHART);
                    l.setForm(Legend.LegendForm.LINE);

                    // no description text
                    mChart.setDescription("Price Gold Line Chart");
                    mChart.setNoDataTextDescription("You need to provide data for the chart.");

                    // enable touch gestures
                    mChart.setTouchEnabled(true);

                    // enable scaling and dragging
                    mChart.setDragEnabled(true);
                    mChart.setScaleEnabled(true);
                    // mChart.setScaleXEnabled(true);
                    // mChart.setScaleYEnabled(true);

                    YAxis leftAxis = mChart.getAxisLeft();
                    leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines

                    //leftAxis.setYOffset(20f);
                    leftAxis.enableGridDashedLine(10f, 10f, 0f);
                    leftAxis.setDrawZeroLine(false);

                    // limit lines are drawn behind data (and not on top)
                    leftAxis.setDrawLimitLinesBehindData(true);

                    mChart.getAxisRight().setEnabled(false);

                    //mChart.getViewPortHandler().setMaximumScaleY(2f);
                    //mChart.getViewPortHandler().setMaximumScaleX(2f);

                    mChart.animateX(3500, Easing.EasingOption.EaseInOutQuart);

                    //  dont forget to refresh the drawing
                    mChart.invalidate();

                }
            });
        }


    }

    private ArrayList<String> setXAxisValues(){
        ArrayList<String> date = QueryUtils.dateString;

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = numbersValue - 1 ; i > 0 ; i--){
            String dateBef = date.get(i );
            dateBef = dateBef.substring(5,10);
            xVals.add(dateBef + "(AM)");
            xVals.add(dateBef + "(PM)");
        }
        String dateBef = date.get(0);
        dateBef = dateBef.substring(5,10);
        xVals.add(dateBef + "(AM)");
        if(!nullUsd.contains("null")){
            xVals.add(dateBef + "(PM)");
        }

        return xVals;
    }

    private ArrayList<Entry> setXYPriceGold(){
        ArrayList<Entry> xYVals = new ArrayList<Entry>();
        ArrayList<String> usdPrice = QueryUtils.usdString;
        int sizeValue = (numbersValue - 1)* 2 + 1;
        float[] xyUsd = new float[sizeValue];
        for(int i = 1; i < sizeValue + 1;i++){
            String usd = usdPrice.get(i);
            float usdI = Float.parseFloat(usd);
            xyUsd[i - 1] = usdI;
        }
        int j = 0;
        for (int i = sizeValue; i > 0; i--){

            xYVals.add(new Entry(xyUsd[i - 1],j));
            j = j + 1;

        }
        if(!nullUsd.contains("null")){
            String usdNull = usdPrice.get(0);
            float usdIfNull = Float.parseFloat(usdNull);
            xYVals.add(new Entry(usdIfNull,sizeValue));
        }

        return xYVals;

    }
    private ArrayList<Entry> setXYPriceGold2(){
        ArrayList<Entry> xYVals = new ArrayList<Entry>();
        ArrayList<String> gbpPrice = QueryUtils.gbpString;
        int sizeValue = (numbersValue - 1)* 2 + 1;
        float[] xyUsd = new float[sizeValue];
        for(int i = 1; i < sizeValue + 1;i++){
            String usd = gbpPrice.get(i);
            float usdI = Float.parseFloat(usd);
            xyUsd[i - 1] = usdI;
        }
        int j = 0;
        for (int i = sizeValue; i > 0; i--){

            xYVals.add(new Entry(xyUsd[i - 1],j));
            j = j + 1;

        }
        if(!nullUsd.contains("null")){
            String usdNull = gbpPrice.get(0);
            float usdIfNull = Float.parseFloat(usdNull);
            xYVals.add(new Entry(usdIfNull,sizeValue));
        }
        return xYVals;

    }
    private ArrayList<Entry> setXYPriceGold3(){
        ArrayList<Entry> xYVals = new ArrayList<Entry>();
        ArrayList<String> euroPrice = QueryUtils.euroString;
        int sizeValue = (numbersValue - 1)* 2 + 1;
        float[] xyUsd = new float[sizeValue];
        for(int i = 1; i < sizeValue + 1;i++){
            String usd = euroPrice.get(i);
            float usdI = Float.parseFloat(usd);
            xyUsd[i - 1] = usdI;
        }
        int j = 0;
        for (int i = sizeValue; i > 0; i--){

            xYVals.add(new Entry(xyUsd[i - 1],j));
            j = j + 1;

        }
        if(!nullUsd.contains("null")){
            String usdNull = euroPrice.get(0);
            float usdIfNull = Float.parseFloat(usdNull);
            xYVals.add(new Entry(usdIfNull,sizeValue));
        }
        return xYVals;

    }

    private void setData2(){
        ArrayList<String> xVals = setXAxisValues();
        ArrayList<Entry> yUsdVals = setXYPriceGold();
        LineDataSet set2;

        // create a dataset and give it a type
        set2 = new LineDataSet(yUsdVals, "USD");

        set2.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        //   set1.enableDashedLine(10f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        set2.setColor(Color.BLUE);
        set2.setLineWidth(2f);
        set2.setCircleRadius(0f);
        set2.setDrawCircleHole(false);
        set2.setValueTextSize(0f);
        set2.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets2 = new ArrayList<ILineDataSet>();
        dataSets2.add(set2); // add the datasets

        // create a data object with the datasets
        LineData data2 = new LineData(xVals, dataSets2);

        // set data
        mChart.setData(data2);
    }
    private void setData3(){
        ArrayList<String> xVals = setXAxisValues();
        ArrayList<Entry> yGbpVals = setXYPriceGold2();
        LineDataSet set2;

        // create a dataset and give it a type
        set2 = new LineDataSet(yGbpVals, "GBP");

        set2.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        //   set1.enableDashedLine(10f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        set2.setColor(Color.BLUE);
        set2.setLineWidth(2f);
        set2.setCircleRadius(0f);
        set2.setDrawCircleHole(false);
        set2.setValueTextSize(0f);
        set2.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets2 = new ArrayList<ILineDataSet>();
        dataSets2.add(set2); // add the datasets

        // create a data object with the datasets
        LineData data2 = new LineData(xVals, dataSets2);

        // set data
        mChart.setData(data2);
    }
    private void setData4(){
        ArrayList<String> xVals = setXAxisValues();
        ArrayList<Entry> yEuroVals = setXYPriceGold3();
        LineDataSet set2;

        // create a dataset and give it a type
        set2 = new LineDataSet(yEuroVals, "EURO");

        set2.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        //   set1.enableDashedLine(10f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        set2.setColor(Color.BLUE);
        set2.setLineWidth(2f);
        set2.setCircleRadius(0f);
        set2.setDrawCircleHole(false);
        set2.setValueTextSize(0f);
        set2.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets2 = new ArrayList<ILineDataSet>();
        dataSets2.add(set2); // add the datasets

        // create a data object with the datasets
        LineData data2 = new LineData(xVals, dataSets2);

        // set data
        mChart.setData(data2);
    }



    @Override
    public void onChartGestureStart(MotionEvent me,
                                    ChartTouchListener.ChartGesture
                                            lastPerformedGesture) {


    }

    @Override
    public void onChartGestureEnd(MotionEvent me,
                                  ChartTouchListener.ChartGesture
                                          lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2,
                             float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        String textValue = e.toString();
        String usdText = textValue.substring(28,textValue.length());
        valueUsd.setText(usdText);
        valueUsd.setVisibility(View.VISIBLE);

    }

    @Override
    public void onNothingSelected() {

    }
}