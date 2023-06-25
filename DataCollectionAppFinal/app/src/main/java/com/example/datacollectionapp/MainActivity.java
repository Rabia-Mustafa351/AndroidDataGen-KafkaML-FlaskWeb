package com.example.datacollectionapp;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.google.gson.JsonArray;

import java.io.FileOutputStream;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    RequestQueue requestQueue;


    float a = 0;
    float b = 0;
    float c = 0;
    float d = 0;
    float e = 0;
    float f = 0;


    private static final String TAG = "MainActivity";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/projec.pr";
    private static final String USER = "root";
    private static final String PASS = "";
    private SensorManager sesnsorManager;
    Sensor accelerometer;
    Sensor gyroscope;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);


        final Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Log.d(TAG, "onCreate: Initializing Sensor Manager");
                sesnsorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
                accelerometer = sesnsorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                sesnsorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                Log.d(TAG, "onCreate:Registered accelerometer listener");


                Log.d(TAG, "onCreate: Initializing Sensor Manager");
                sesnsorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
                gyroscope = sesnsorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                sesnsorManager.registerListener(MainActivity.this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
                Log.d(TAG, "onCreate:Registered gyroscope listener");

            }
        });


        final Button button1 = findViewById(R.id.button5);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                onPause();
            }
        });


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Sensor sensor = sensorEvent.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Log.d(TAG, "onSensorChanged: X:" + sensorEvent.values[0] + "    Y:" + sensorEvent.values[1] + "        Z: " + sensorEvent.values[2]);

            textView1.setText("X:" + sensorEvent.values[0]);
            textView2.setText("Y:" + sensorEvent.values[1]);
            textView3.setText("Z:" + sensorEvent.values[2]);

            System.out.println("a");
            a = sensorEvent.values[0];
            System.out.println(a);
            System.out.println("b");
            b = sensorEvent.values[1];
            System.out.println(b);
            System.out.println("c");
            c = sensorEvent.values[2];
            System.out.println(c);

           // makingcsv1(a,b,c);


        }
        if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            Log.d(TAG, "onSensorChanged: X1:" + sensorEvent.values[0] + "Y1:" + sensorEvent.values[1] + "Z1:" + sensorEvent.values[2]);
            textView4.setText("X1:" + sensorEvent.values[0]);
            textView5.setText("Y1:" + sensorEvent.values[1]);
            textView6.setText("Z1:" + sensorEvent.values[2]);

            System.out.println("d");
            d = sensorEvent.values[0];
            System.out.println(d);
            System.out.println("e");
            e = sensorEvent.values[1];
            System.out.println(e);
            System.out.println("f");
            f = sensorEvent.values[2];
            System.out.println(f);

        }

        String msg1;
        makingcsv1(a,b,c,d,e,f);

    }

    public void startsensors(View view) {
        Toast.makeText(this, "Sensors has Started", Toast.LENGTH_SHORT).show();

    }


    protected void makingcsv1(float a,float b, float c,float d,float e,float f) {
        if (d != 0 && e != 0 && f != 0) {
            System.out.println("To CSV");
            String FILENAME = "Practice.csv";
            String entry = Float.toString(a) + "," + Float.toString(b) + "," + Float.toString(c) + "," +
                    Float.toString(d) + "," + Float.toString(e) + "," + Float.toString(f) + "\n";
            try {
                FileOutputStream out = openFileOutput(FILENAME, Context.MODE_APPEND);
                out.write((entry.getBytes()));
                out.close();
                System.out.println("done");
            } catch (Exception e5) {
                e5.printStackTrace();
            }


            DataModel dataModel = new DataModel();
            dataModel.setX(a);
            dataModel.setY(b);
            dataModel.setZ(c);
            dataModel.setGroupname("RabiaNeha");
            dataModel.setLabel("lABEL");

            RetrofitClient.get().setDataModel(dataModel, new Callback<JsonArray>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void success(JsonArray jsonElements, Response response) {
                    Log.d("SUCESS", response.toString());
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.d("FAILURE", error.toString());


                }
            });
        }
    }

    protected void onPause() {
        super.onPause();
        sesnsorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gyroscope = sesnsorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sesnsorManager.unregisterListener(MainActivity.this);

    }



}