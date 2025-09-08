package com.linguaflow.myapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class QuizActivity extends Activity {

    private TextView questionText;
    private Button[] optionButtons = new Button[4];
    private TextView resultText;
    private List<QuizGenerator.QuizQuestion> quizList;
    private int currentIndex = 0;
    private int score = 0;
    private boolean answered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        questionText = findViewById(R.id.questionText);
        optionButtons[0] = findViewById(R.id.option1);
        optionButtons[1] = findViewById(R.id.option2);
        optionButtons[2] = findViewById(R.id.option3);
        optionButtons[3] = findViewById(R.id.option4);
        resultText = findViewById(R.id.resultText);

        quizList = QuizGenerator.generateFromCache(this);
        showNextQuestion();
    }

    private void showNextQuestion() {
        if (currentIndex >= quizList.size()) {
            questionText.setText("Quiz beendet! Punkte: " + score + "/" + quizList.size());
            for (Button btn : optionButtons) {
                btn.setVisibility(View.GONE);
            }
            return;
        }

        QuizGenerator.QuizQuestion question = quizList.get(currentIndex);
        questionText.setText(question.questionText);
        answered = false;

        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(question.options.get(i));
            optionButtons[i].setBackgroundColor(Color.LTGRAY);
            optionButtons[i].setEnabled(true);

            final int index = i;
            optionButtons[i].setOnClickListener(v -> {
                if (answered) return;
                answered = true;

                for (Button btn : optionButtons) {
                    btn.setEnabled(false);
                }

                if (index == question.correctIndex) {
                    optionButtons[index].setBackgroundColor(Color.GREEN);
                    resultText.setText("Richtig!");
                    score++;
                } else {
                    optionButtons[index].setBackgroundColor(Color.RED);
                    optionButtons[question.correctIndex].setBackgroundColor(Color.GREEN);
                    resultText.setText("Falsch!");
                }

                currentIndex++;
                optionButtons[0].postDelayed(this::showNextQuestion, 1500);
            });
        }
    }
}