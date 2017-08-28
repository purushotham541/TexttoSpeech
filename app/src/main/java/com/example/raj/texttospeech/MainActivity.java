package com.example.raj.texttospeech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    EditText et1;
    TextToSpeech textToSpeech;
    int result;
    String txt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=(EditText) findViewById(R.id.et1);
        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i)
            {
                if (i==TextToSpeech.SUCCESS)
                {
                    result=textToSpeech.setLanguage(Locale.US);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Text to Speech not supported...",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void textToSpeech(View v)
    {
       if (result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED)
       {
           Toast.makeText(MainActivity.this,"Text to Speech not supported...",Toast.LENGTH_SHORT).show();
       }
        else
       {
           txt = et1.getText().toString();
           textToSpeech.speak(txt, TextToSpeech.QUEUE_FLUSH, null,null);
       }
    }
    public void Stop(View v)
    {
        if(textToSpeech!=null)
        {
            textToSpeech.stop();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(textToSpeech!=null)
        {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
