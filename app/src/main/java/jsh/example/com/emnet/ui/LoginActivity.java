package jsh.example.com.emnet.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import jsh.example.com.emnet.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = (Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // after login network
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
