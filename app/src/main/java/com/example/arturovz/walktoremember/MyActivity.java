package com.example.arturovz.walktoremember;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        /////////////////////// FUEBTE Y SUBRAYADO ///////////////////////
        TextView titulo = (TextView) findViewById(R.id.txtWalkToRemember);
        Typeface font = Typeface.createFromAsset(getAssets(),"font/Comfortaa-Regular.ttf");
        titulo.setTypeface(font);

        ////////////////// ANIMACION //////////////////////////////
        titulo.startAnimation(AnimationUtils.loadAnimation(MyActivity.this, R.anim.abc_slide_in_bottom));


///////////////// SPLASH ////////////////////////////////
        splash();

    }

    private void splash()
    {
        Thread timer = new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(3000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent intent = new Intent(MyActivity.this,podometro.class);
                    startActivity(intent);
                }
            }
        };
        timer.start();




    }



    /*@Override
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
    }*/
}
