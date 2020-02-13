package com.example.perkins.inclasscalcshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AddActivity extends Activity {

    private TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        myTextView = (TextView)findViewById(R.id.tvResult);

        int numb1,numb2=Constants.UNDEFINED;

        Intent myIntent = getIntent();
        if (myIntent !=null){
            numb1= myIntent.getIntExtra(Constants.NUMB1, Constants.UNDEFINED);
            numb2= myIntent.getIntExtra(Constants.NUMB2, Constants.UNDEFINED);

            String myString = Integer.toString(numb1) + " + " + Integer.toString(numb2) + " = " + Integer.toString(numb1+numb2);

            myTextView.setText(myString);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void doShare(View view) {
        String text = myTextView.getText().toString();

        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        myIntent.putExtra(Intent.EXTRA_EMAIL, Constants.EMAIL);
        myIntent.putExtra(Intent.EXTRA_SUBJECT, Constants.SUBJECT);
        myIntent.putExtra(Intent.EXTRA_TEXT, "Did you know that " + text + " ???");

        startActivity(myIntent);
    }
}
