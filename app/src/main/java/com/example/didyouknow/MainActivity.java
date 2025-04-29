package com.example.didyouknow;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    String userName;
    TextView questionText;
    Button option1, option2, option3, option4;
    Button retryButton;


    String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "Which animal is the largest mammal?",
            "Who wrote Romeo and Juliet?"
    };

    String[][] options = {
            {"Paris", "London", "Berlin", "Rome"},
            {"Mars", "Earth", "Jupiter", "Venus"},
            {"Elephant", "Blue Whale", "Giraffe", "Shark"},
            {"Shakespeare", "Dickens", "Twain", "Rowling"}
    };

    String[] answers = {"Paris", "Mars", "Blue Whale", "Shakespeare"};

    int index = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        userName = getIntent().getStringExtra("USER_NAME");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        retryButton = findViewById(R.id.retry_button);

        retryButton.setOnClickListener(v -> {
            index = 0;
            score = 0;
            retryButton.setVisibility(View.GONE);
            option1.setVisibility(View.VISIBLE);
            option2.setVisibility(View.VISIBLE);
            option3.setVisibility(View.VISIBLE);
            option4.setVisibility(View.VISIBLE);
            loadQuestion();
        });



        questionText = findViewById(R.id.question_text);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        loadQuestion();

        option1.setOnClickListener(v -> checkAnswer(option1.getText().toString()));
        option2.setOnClickListener(v -> checkAnswer(option2.getText().toString()));
        option3.setOnClickListener(v -> checkAnswer(option3.getText().toString()));
        option4.setOnClickListener(v -> checkAnswer(option4.getText().toString()));
    }

    void loadQuestion() {
        if (index < questions.length) {
            questionText.setText(questions[index]);
            option1.setText(options[index][0]);
            option2.setText(options[index][1]);
            option3.setText(options[index][2]);
            option4.setText(options[index][3]);
        } else {
            showScore();
        }
    }

    void checkAnswer(String selectedAnswer) {
        if (selectedAnswer.equals(answers[index])) {
            score++;
        }
        index++;
        loadQuestion();
    }

    void showScore() {
        String result = "Congratulations <font color='#FF4081'>" + userName + "</font>!<br> Your score: " + score + "/" + questions.length;
        questionText.setText(android.text.Html.fromHtml(result));
        option1.setVisibility(View.GONE);
        option2.setVisibility(View.GONE);
        option3.setVisibility(View.GONE);
        option4.setVisibility(View.GONE);
        retryButton.setVisibility(View.VISIBLE);
    }


}
