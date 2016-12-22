package com.redcamel.heriansf.neardeal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Button button = (Button) findViewById(R.id.btnOke);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int itemId = v.getId();
        if (itemId == R.id.btnOke) {
            Toast.makeText(TestActivity.this, "Hello World", Toast.LENGTH_LONG).show();
        }
    }
}
