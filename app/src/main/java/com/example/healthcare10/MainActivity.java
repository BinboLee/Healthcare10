package com.example.healthcare10;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.*;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;


public class MainActivity extends AppCompatActivity {

    public LineChartView lineChart;



    String[] date = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
    int[] score= {600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600,600};//图表的数据点
    //int[] score= {0,0,0,0,0,0,0,0,0,0};
    public List<PointValue> mPointValues = new ArrayList<PointValue>();
    public List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    private static final String TAG="wow";

    protected void onCreate(Bundle savedInstanceState) {






        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Manager manager=new Manager(this);

        List<Item> testlist = manager.listAll();
        int length=0;
        for (Item i : testlist){
            //Log.i(TAG, "onCreate: id +" +i.getWEIGHT());
            length++;
            //Log.i(TAG, "onCreate: id +" +length);
        }

        int[] temp=new int[length];
        int j=0;
        for (Item m : testlist){
            temp[j]=Integer.parseInt(m.getWEIGHT());
            j++;
            //Log.i(TAG, "onCreate: id +" +"...................................");
        }
        if(j<30){
            for(int i=0;i<j;i++){
                score[i]=temp[i];
            }
        }
        else{
            for(int i=j-30;i<j;i++){
                score[i]=temp[i];
            }
        }

        lineChart = (LineChartView) findViewById(R.id.line_chart);

        getAxisXLables();//获取x轴的标注
        getAxisPoints();//获取坐标点
        initLineChart();//初始化

    }
    //建立页面和菜单栏的连接
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.control,menu);
        return true;
    }
    //菜单项功能设置
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.weight){
            Intent config= new Intent(this, MainActivity.class );
            startActivity(config);
        }
        if(item.getItemId()==R.id.memo){
            Intent config= new Intent(this, Main2Activity.class );
            startActivity(config);
        }
        return  super.onOptionsItemSelected(item);
    }
    public void add(View v){

        EditText et=findViewById(R.id.editText);
        Manager manager=new Manager(this);
        manager.add(new Item(et.getText().toString()));
        Intent config= new Intent(this, MainActivity.class );
        startActivity(config);
    }
    public void clear(View v){
        Manager manager=new Manager(this);
        manager.deleteAll();
        Intent config= new Intent(this, MainActivity.class );
        startActivity(config);
    }
    /**
     * 设置X 轴的显示
     */
    private void getAxisXLables() {
        for (int i = 0; i < date.length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
        }
    }
    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints() {
        for (int i = 0; i < score.length; i++) {
            mPointValues.add(new PointValue(i, score[i]));
        }
    }

    private void initLineChart() {
        Line line = new Line(mPointValues).setColor(Color.parseColor("#FFCD41"));  //折线的颜色（橙色）
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
        //      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);
        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.GRAY);  //设置字体颜色
        //axisX.setName("date");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线
        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("");//y轴标注
        axisY.setTextSize(10);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边
        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setMaxZoom((float) 2);//最大方法比例
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.left = 0;
        v.right = 10;
        lineChart.setCurrentViewport(v);
    }

}
