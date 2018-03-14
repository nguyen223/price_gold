package com.appofmy.android.goldprice1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
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

public class PriceOilMainActivity extends AppCompatActivity implements OnChartGestureListener,
        OnChartValueSelectedListener {

    private LineChart mChart;
    private int numbersValue;

    private TextView valueUsd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // To make full screen layout
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_priceoil);




        numbersValue = PriceOilActivity.dateNumbers;

        valueUsd = (TextView) findViewById(R.id.text_usdcurrency) ;
        if (valueUsd != null) {
            valueUsd.setVisibility(View.GONE);
        }


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

        if (mChart != null) {
            mChart.clear();
        }
        setData2();

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);

        // no description text
        mChart.setDescription("Price Oil Line Chart");
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

    }

    private ArrayList<String> setXAxisValues(){
            ArrayList<String> date = QueryPriceOil.dateString;

            ArrayList<String> xVals = new ArrayList<String>();

            for (int i = numbersValue - 1; i > 0; i--) {
                String dateBef = date.get(i);
                dateBef = dateBef.substring(5, 10);
                xVals.add(dateBef);

            }
            String dateBef = date.get(0);
            dateBef = dateBef.substring(5, 10);
            xVals.add(dateBef);
        return xVals;

        }




    private ArrayList<Entry> setXYPriceGold() {
        ArrayList<Entry> xYVals = new ArrayList<Entry>();
        ArrayList<String> oilPrice = QueryPriceOil.priceOilList;
        int sizeValue = numbersValue;
        float[] xyUsd = new float[sizeValue];
        for (int i = 0; i < sizeValue; i++) {
            String usd = oilPrice.get(i);
            float usdI = Float.parseFloat(usd);
            xyUsd[i] = usdI;
        }
        int j = 0;
        for (int i = sizeValue; i > 0; i--) {

            xYVals.add(new Entry(xyUsd[i - 1], j));
            j = j + 1;

        }


        return xYVals;
    }


    private void setData2(){
        ArrayList<String> xVals = setXAxisValues();
        ArrayList<Entry> yUsdVals = setXYPriceGold();
        LineDataSet set2;

        // create a dataset and give it a type
        set2 = new LineDataSet(yUsdVals, "OIL");

        set2.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        //   set1.enableDashedLine(10f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        set2.setColor(Color.RED);
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

        Log.i("Gesture", "START, x: " + me.getX() + ", y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me,
                                  ChartTouchListener.ChartGesture
                                          lastPerformedGesture) {

        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);

        // un-highlight values after the gesture is finished and no single-tap
        if(lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            // or highlightTouch(null) for callback to onNothingSelected(...)
            mChart.highlightValues(null);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2,
                             float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: "
                + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
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
        Log.i("Nothing selected", "Nothing selected.");
    }
}