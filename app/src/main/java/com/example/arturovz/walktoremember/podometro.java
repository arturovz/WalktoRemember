package com.example.arturovz.walktoremember;

import android.content.Context;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.text.SimpleDateFormat;

/**
 * Created by arturovz on 28/09/2015.
 */
public class podometro extends ActionBarActivity {

    //TEXTO DE SENCIBILIDAD
    private TextView sencibilidad;

    //TEXTO CONTEO
    private TextView conteo;
    private TextView fecha;
    private TextView pasos;
    private TextView hoy;

    //BOTON DE RESET
    private ImageButton accion;
    private ImageButton reset;

    //SENSOR MANAGER
    private SensorManager sensorManager;
    private float aceleracion;

    //VALORES PARA CALCUAR LOS PASOS
    private float previoY;
    private float actualY;
    private int numpasos;

    //threshold
    private int threshold;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.podometro);

        fecha=(TextView)findViewById(R.id.txtfecha);
        Typeface font = Typeface.createFromAsset(getAssets(),"font/Comfortaa-Regular.ttf");
        fecha.setTypeface(font);
        hoy=(TextView)findViewById(R.id.hoyllevas);
        hoy.setTypeface(font);
        pasos=(TextView)findViewById(R.id.txtpasos);
        pasos.setTypeface(font);
        conteo=(TextView)findViewById(R.id.txtContador);
        accion=(ImageButton)findViewById(R.id.btnAccion);
        reset=(ImageButton)findViewById(R.id.btnreset);


        //MANEJO DE LA SENSIVILIDAD
        threshold = 2;

        previoY = 0;
        actualY = 0;
        numpasos = 0;

        aceleracion = 0.00f;

        //enableAccelerometerListening();

        num=2;

        long date = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy ");
        String dateString = sdf.format(date);
        fecha.setText(dateString);

        accion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    enableAccelerometerListening();
                    Toast mensaje = Toast.makeText(getApplicationContext(),"inicio", Toast.LENGTH_LONG);
                    mensaje.show();

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetpasos();
                Toast mensaje = Toast.makeText(getApplicationContext(),"reset", Toast.LENGTH_LONG);
                mensaje.show();

            }
        });
    }


    private void resetpasos() {

        ;
        numpasos = 0;
        conteo.setText(String.valueOf(numpasos));


    }

    private void enableAccelerometerListening ()
    {
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            actualY = y;

            if (Math.abs(actualY - previoY) > threshold) {
                numpasos++;
                conteo.setText(String.valueOf(numpasos));
            }

            //txtx.setText(String.valueOf(x));
            //txty.setText(String.valueOf(y));
            //txtz.setText(String.valueOf(z));

            previoY = y;


        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
