package com.example.didyouknow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    EditText nameInput;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        nameInput = findViewById(R.id.name_input);
        startButton = findViewById(R.id.start_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = nameInput.getText().toString();
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                intent.putExtra("USER_NAME", userName);
                startActivity(intent);
                finish();
            }
        });
    }
}
