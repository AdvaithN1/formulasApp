package com.advaith.physicsformulas.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.advaith.physicsformulas.R;
import com.jstarczewski.pc.mathview.src.MathView;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class QuizActivity extends AppCompatActivity {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build();
    TextView tvQuizTitle;
    TextView tvProblem;
    MathView choice1;
    MathView choice2;
    MathView choice3;
    MathView choice4;
    CardView cv1;
    CardView cv2;
    CardView cv3;
    CardView cv4;
    Button next;
    ImageButton quizBack;
    String currentAnswer;
    String problem;
    String option1;
    String option2;
    String option3;
    String option4;
    String api2Response;
    View gif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        tvQuizTitle = findViewById(R.id.tvQuizTitle);
        quizBack = findViewById(R.id.btn_quizBack);
        tvProblem = findViewById(R.id.tvProblem);
        choice1 = (MathView) findViewById(R.id.btnChoice1);
        choice2 = (MathView) findViewById(R.id.btnChoice2);
        choice3 = (MathView) findViewById(R.id.btnChoice3);
        choice4 = (MathView) findViewById(R.id.btnChoice4);
        cv1 = findViewById(R.id.cv1);
        cv2 = findViewById(R.id.cv2);
        cv3 = findViewById(R.id.cv3);
        cv4 = findViewById(R.id.cv4);
        gif = findViewById(R.id.gif);
        cv1.setVisibility(View.GONE);
        cv2.setVisibility(View.GONE);
        cv3.setVisibility(View.GONE);
        cv4.setVisibility(View.GONE);
        choice1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    if(currentAnswer.equals("A") || currentAnswer.equals("a")){
                        cv1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                    }
                    else{
                        cv1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                    }
                    return false;
                }
                return false;
            }
        });
        choice2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    if(currentAnswer.equals("B") || currentAnswer.equals("b")){
                        cv2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                    }
                    else{
                        cv2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                    }
                    return false;
                }
                return false;
            }
        });
        choice3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    if(currentAnswer.equals("C") || currentAnswer.equals("c")){
                        cv3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                    }
                    else{
                        cv3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                    }
                    return false;
                }
                return false;
            }
        });
        choice4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    if(currentAnswer.equals("D") || currentAnswer.equals("d")){
                        cv4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                    }
                    else{
                        cv4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                    }
                    return false;
                }
                return false;
            }
        });
        next = findViewById(R.id.btnNext);
        String text = "Problems in " + getIntent().getExtras().getString("name");
        tvQuizTitle.setText(text);
        quizBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPressed();
            }
        });
        nextPressed();
    }

    public void callAPI1(String prompt){
        cv1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        cv2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        cv3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        cv4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        next.setVisibility(View.GONE);
        cv1.setVisibility(View.GONE);
        cv2.setVisibility(View.GONE);
        cv3.setVisibility(View.GONE);
        cv4.setVisibility(View.GONE);
        tvProblem.setVisibility(View.GONE);
        gif.setVisibility(View.VISIBLE);
        String[] result = {"failed"};
        Log.println(Log.ASSERT, "Clicked", "API Called");
        JSONObject jsonBody = new JSONObject();
        JSONArray messagesArray = new JSONArray();
        JSONObject message = new JSONObject();
        try {
            // Add user message to messagesArray
            message.put("role", "user");
            message.put("content", prompt);
            messagesArray.put(message);

            // Add messagesArray and other parameters to jsonBody
            jsonBody.put("model", "gpt-3.5-turbo");
            jsonBody.put("messages", messagesArray);
             jsonBody.put("max_tokens", 500);
             jsonBody.put("temperature", 0.75);
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
        Log.println(Log.ASSERT, "Done", "");

        RequestBody body = RequestBody.create(jsonBody.toString(), JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer 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")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.println(Log.ASSERT, "APICALLFAILED1", e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    JSONObject jsonObject = null;
                    Log.println(Log.ASSERT, "Done2", "");
                    try {
                        Log.println(Log.ASSERT, "Hello?", "");
                        jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        result[0] = jsonArray.getJSONObject(0).getJSONObject("message").getString("content");
                        Log.println(Log.ASSERT, "Hello?", result[0]);
                        runOnUiThread(() -> problem = result[0]);
                        //runOnUiThread(() -> callAPI2());
                        runOnUiThread(() -> tvProblem.setText(problem));
                        runOnUiThread(() -> gif.setVisibility(View.GONE));
                        runOnUiThread(() -> tvProblem.setVisibility(View.VISIBLE));
                        runOnUiThread(() -> next.setVisibility(View.VISIBLE));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Log.println(Log.ASSERT, "APICALLFAILED", "FAILED");
                    Log.println(Log.ASSERT, "FAILED: ", response.body().string());
                }

            }
        });
    }

    public void callAPI2(){
        String[] result = {"failed"};
        Log.println(Log.ASSERT, "Clicked", "API Called");
        JSONObject jsonBody = new JSONObject();
        JSONArray messagesArray = new JSONArray();
        JSONObject message1 = new JSONObject();
        JSONObject message2 = new JSONObject();
        JSONObject message3 = new JSONObject();
        try {
            // Add user message to messagesArray
            message1.put("role", "user");
            message1.put("content", "Just give a problem that includes one or more of following formulas: " + getIntent().getExtras().getString("prompt"));
            message2.put("role", "assistant");
            message2.put("content", problem);
            message3.put("role", "user");
            message3.put("content", "Now just give 4 options for the answer to the problem in this EXACT format:\n\"A) __\nB) __\nC) __\nD) __\"");
            messagesArray.put(message1);
            messagesArray.put(message2);
            messagesArray.put(message3);
            // Add messagesArray and other parameters to jsonBody
            jsonBody.put("model", "gpt-3.5-turbo");
            jsonBody.put("messages", messagesArray);
            jsonBody.put("max_tokens", 1000);
            jsonBody.put("temperature", 0.25);
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
        Log.println(Log.ASSERT, "Done", "");

        RequestBody body = RequestBody.create(jsonBody.toString(), JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer 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")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.println(Log.ASSERT, "APICALLFAILED1", e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    JSONObject jsonObject = null;
                    Log.println(Log.ASSERT, "Done2", "");
                    try {
                        Log.println(Log.ASSERT, "Hello?", "");
                        jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        result[0] = jsonArray.getJSONObject(0).getJSONObject("message").getString("content");
                        api2Response = result[0];
                        Log.println(Log.ASSERT, "Hello2?", result[0]);
                        runOnUiThread(() -> option1 = result[0].substring(result[0].indexOf("A)") + 2, result[0].indexOf("B)")));
                        runOnUiThread(() -> option2 = result[0].substring(result[0].indexOf("B)") + 2, result[0].indexOf("C)")));
                        runOnUiThread(() -> option3 = result[0].substring(result[0].indexOf("C)") + 2, result[0].indexOf("D)")));
                        runOnUiThread(() -> option4 = result[0].substring(result[0].indexOf("D)") + 2));

                        runOnUiThread(() -> option1 = option1.replace("\\times", "*"));
                        runOnUiThread(() -> option1 = option1.replace("\\TIMES", "*"));
                        runOnUiThread(() -> option2 = option2.replace("\\times", "*"));
                        runOnUiThread(() -> option2 = option2.replace("\\TIMES", "*"));
                        runOnUiThread(() -> option3 = option3.replace("\\times", "*"));
                        runOnUiThread(() -> option3 = option3.replace("\\TIMES", "*"));
                        runOnUiThread(() -> option4 = option4.replace("\\times", "*"));
                        runOnUiThread(() -> option4 = option4.replace("\\TIMES", "*"));
                        runOnUiThread(() -> callAPI3());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Log.println(Log.ASSERT, "APICALLFAILED", "FAILED");
                    Log.println(Log.ASSERT, "FAILED: ", response.body().string());
                }

            }
        });
    }

    public void callAPI3(){
        String[] result = {"failed"};
        Log.println(Log.ASSERT, "Clicked", "API Called");
        JSONObject jsonBody = new JSONObject();
        JSONArray messagesArray = new JSONArray();
        JSONObject message1 = new JSONObject();
        JSONObject message2 = new JSONObject();
        JSONObject message3 = new JSONObject();
        JSONObject message4 = new JSONObject();
        JSONObject message5 = new JSONObject();
        try {
            // Add user message to messagesArray
            message1.put("role", "user");
            message1.put("content", "Just give a problem that includes one or more of following formulas: " + getIntent().getExtras().getString("prompt"));
            message2.put("role", "assistant");
            message2.put("content", problem + " ");
            message3.put("role", "user");
            message3.put("content", "Now just give 4 options for the answer to the problem in this EXACT format:\n\"A) __\nB) __\nC) __\nD) __\"");
            message4.put("role", "assistant");
            message4.put("content", api2Response + " ");
            message5.put("role", "user");
            message5.put("content", "Give the answer to the problem in EXACTLY ONE LETTER: " + problem + "\n" + api2Response);
            Log.e("CONTENT: ", "Give the answer to the problem: " + problem + "\n" + api2Response);
            messagesArray.put(message1);
            messagesArray.put(message2);
            messagesArray.put(message3);
            messagesArray.put(message4);
            messagesArray.put(message5);
            // Add messagesArray and other parameters to jsonBody
            jsonBody.put("model", "gpt-3.5-turbo");
            jsonBody.put("messages", messagesArray);
            jsonBody.put("max_tokens", 10);
            jsonBody.put("temperature", 0.10);
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
        Log.println(Log.ASSERT, "Done", "");

        RequestBody body = RequestBody.create(jsonBody.toString(), JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer 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")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.println(Log.ASSERT, "APICALLFAILED1", e.toString());
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    JSONObject jsonObject = null;
                    Log.println(Log.ASSERT, "Done2", "");
                    try {
                        Log.println(Log.ASSERT, "Hello?", "");
                        jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        result[0] = jsonArray.getJSONObject(0).getJSONObject("message").getString("content");
                        Log.println(Log.ASSERT, "Hello3?", result[0]);
                        runOnUiThread(() -> currentAnswer = result[0].trim().substring(0, 1));
                        runOnUiThread(() -> setTexts());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Log.println(Log.ASSERT, "APICALLFAILED", "FAILED");
                    Log.println(Log.ASSERT, "FAILED: ", response.body().string());
                }

            }
        });
    }

    void setTexts(){
        tvProblem.setText(problem);
        tvProblem.setVisibility(View.VISIBLE);
        Log.e("OPTIONS: ", option1 + " " + option2 + " " + option3 + " " + option4);
        option1 = option1.trim();
        option2 = option2.trim();
        option3 = option3.trim();
        option4 = option4.trim();

        choice1.setText(option1);
        choice2.setText(option2);
        choice3.setText(option3);
        choice4.setText(option4);

        cv1.setVisibility(View.VISIBLE);
        cv2.setVisibility(View.VISIBLE);
        cv3.setVisibility(View.VISIBLE);
        cv4.setVisibility(View.VISIBLE);

        next.setVisibility(View.VISIBLE);
        gif.setVisibility(View.GONE);
    }

    void nextPressed(){
        callAPI1("Give a full and VERY DIFFICULT problem that uses some of following formulas: " + getIntent().getExtras().getString("prompt") + ". DON'T STATE THE ANSWER AND DON'T GIVE CHOICES.");
    }

}