package com.example.nguyenxuantruong.myproject.Graph;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.nguyenxuantruong.myproject.Data.MyDB;
import com.example.nguyenxuantruong.myproject.R;
import com.example.nguyenxuantruong.myproject.targets.Bill;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;

/**
 * Created by nguyenxuantruong on 5/8/17.
 */

public class GraphActivity extends Activity {

    LinearLayout linearLayout;
    ArrayList<Bill> dataGraphex,dataGraphim;
    MyDB db;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);
        db = new MyDB(this);
        dataGraphex= new ArrayList<>();
        dataGraphim = new ArrayList<>();

        linearLayout = (LinearLayout) findViewById(R.id.graph);

        Intent getdata = getIntent();
        int nam = getdata.getIntExtra("nam",2015);
        int thang = getdata.getIntExtra("thang",1);

        Log.e("nam",nam+"");
        Log.e("thang",thang+"");

        ArrayList<Float> x1 = new ArrayList<>();

        for (int i=1;i<=thang;i++){
            float result =0;
            dataGraphex.clear();
            dataGraphex = db.getDataEX(i,nam);
            for (int j=0;j<dataGraphex.size();j++){
                result += Float.valueOf(dataGraphex.get(j).getSoTien());
            }
            x1.add(result);

        }

        ArrayList<Float> x2 = new ArrayList<>();

        for (int m=1;m<=thang;m++){
            float result =0;
            dataGraphim.clear();
            dataGraphim = db.getDataEX(m,nam);
            for (int n=0;n<dataGraphim.size();n++){
                result += Float.valueOf(dataGraphim.get(n).getSoTien());
                x2.add(result);
            }
        }



        TimeSeries series = new TimeSeries("Thu nhập");

        for (int j=0;j<x1.size();j++){
            series.add(j+1,x1.get(j));
        }


        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();


        XYMultipleSeriesRenderer mrenderer = new XYMultipleSeriesRenderer();
        XYSeriesRenderer renderer = new XYSeriesRenderer();

        renderer.setLineWidth(1);
        renderer.setColor(Color.GREEN);
        // Include low and max value
        renderer.setDisplayBoundingPoints(true);
        // we add point markers
        renderer.setPointStyle(PointStyle.CIRCLE);
        renderer.setPointStrokeWidth(6);

//        //line1
//        int[] x1 = {0,1,2,3,4,5,6,7,8,9,10,11,12};
//        int[] y1 = {0,0,0,0,0,0,0,0,0,0,0,0,0};
//        for(int k=1;k<=12;k++){
//        }

        TimeSeries series1 = new TimeSeries("Chi tiêu");

        for (int k=0;k<x2.size();k++){
            series1.add(k+1,x2.get(k));
        }

        dataset.addSeries(series1);
        dataset.addSeries(series);

        XYSeriesRenderer renderer1 = new XYSeriesRenderer();
        renderer1.setLineWidth(1);
        renderer1.setColor(Color.RED);
        // Include low and max value
        renderer1.setDisplayBoundingPoints(true);
        // we add point markers
        renderer1.setPointStyle(PointStyle.CIRCLE);
        renderer1.setPointStrokeWidth(6);


        //build
        mrenderer.setMarginsColor(Color.argb(0x00, 0xff, 0x00, 0x00));
// Disable Pan on two axis
        mrenderer.setPanEnabled(false, false);
        mrenderer.setYAxisMin(0);
        mrenderer.setShowGrid(true);
        mrenderer.setMarginsColor(Color.WHITE);
        mrenderer.setXAxisMin(1);
        mrenderer.setXAxisMax(12);
        mrenderer.setXLabels(12);

        mrenderer.setAxisTitleTextSize(60);
        mrenderer.setXLabels(16);
        mrenderer.addSeriesRenderer(renderer);
        mrenderer.addSeriesRenderer(renderer1);
        mrenderer.setChartTitle("Thống kê chi tiêu");

        GraphicalView graphicalView = ChartFactory.getLineChartView(this,dataset,mrenderer);
        linearLayout.addView(graphicalView);
    }

}