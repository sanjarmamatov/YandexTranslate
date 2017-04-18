/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.viewpager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * Fragment that displays "Monday".
 */
public class MondayFragment extends Fragment {
        Button b;
        TextView tv;
    String l1,l2;
    Spinner spinner, spinner2;

    TextView translatedTextTextView;
    EditText textToTranslateEditText;


    //Toolbar toolbar;

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
           View view = inflater.inflate(R.layout.fragment_monday, container, false);




        String[] arrayNorm = {"Азербайджанский", "Албанский", "Амхарский","Английский",
                "Арабский", "Армянский", "Африкаанс", "Баскский", "Башкирский", "Белорусский",
                "Бенгальский", "Болгарский", "Боснийский", "Валлийский",
                "Венгерский", "Вьетнамский", "Гаитянский", "Галисийский", "Голландский",
                "Горномарийский", "Греческий", "Грузинский", "Гуджарати", "Датский",
                "Иврит", "Идиш", "Индонезийский", "Ирландский", "Итальянский", "Исландский",
                "Испанский", "Казахский", "Каннада", "Каталанский", "Киргизский", "Китайский",
                "Корейский", "Коса", "Латынь", "Латышский", "Литовский", "Люксембургский",
                "Малагасийский", "Малайский", "Малаялам", "Мальтийский", "Македонский",
                "Маори", "Маратхи","Марийский", "Монгольский", "Немецкий", "Непальский",
                "Норвежский", "Панджаби", "Папьяменто", "Персидский", "Польский", "Португальский",
                "Румынский", "Русский", "Себуанский", "Сербский", "Сингальский", "Словацкий",
                "Словенский", "Суахили", "Сунданский", "Таджикский", "Тайский", "Тагальский",
                "Тамильский", "Татарский", "Телугу", "Турецкий", "Удмуртский", "Узбекский",
                "Украинский", "Урду", "Финский", "Французский", "Хинди", "Хорватский",
                "Чешский", "Шведский", "Шотландский", "Эстонский", "Эсперанто", "Яванский","Японский"};



        final String[] arraySokr = {"az","sq","am","en", "ar" ,"hy","af", "eu", "ba",
                "be", "bn", "bg", "bs", "cy", "hu", "vi", "ht", "gl", "nl", "mrj", "el",
                "ka", "gu", "da", "he", "yi", "id", "ga", "it", "is", "es", "kk", "kn", "ca",
                "ky", "zh", "ko" ,"xh", "la", "lv", "lt", "lb", "mg", "ms", "ml", "mt", "mk",
                "mi" ,"mr", "mhr", "mn", "de", "ne", "no", "pa", "pap", "fa", "pl", "pt",
                "ro", "ru", "ceb", "sr", "si", "sk", "sl", "sw", "su","tg", "th", "tl" ,"ta",
                "tt" ,"te", "tr", "udm" ,"uz" ,"uk", "ur", "fi", "fr", "hi" ,"hr", "cs" ,"sv",
                "gd", "et", "eo", "jv", "ja"};




        spinner=(Spinner)view.findViewById(R.id.spinner_nav);
        spinner2=(Spinner)view.findViewById(R.id.spinner_nav2);
        translatedTextTextView = (TextView)view.findViewById(R.id.textView);
        textToTranslateEditText =(EditText)view.findViewById(R.id.editText2);


        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, arrayNorm); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner2.setAdapter(spinnerArrayAdapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                int posNum = spinner.getSelectedItemPosition();
                l1 = arraySokr[posNum];
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                int posNum = spinner.getSelectedItemPosition();
                l2 = arraySokr[posNum];
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        textToTranslateEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //сделать аймер, и проверить на изменения
                if (textToTranslateEditText.getText().toString().isEmpty()){
                    Toast.makeText(getActivity().getApplicationContext(), "введите текст для перевода", Toast.LENGTH_SHORT).show();
                }else {
                    if (textToTranslateEditText.isFocused()) {
                        String text = textToTranslateEditText.getText().toString();

                        String language = l1 + "-" + l2;
                        new Task().execute(text, language);

                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                //TranslateWord translateWord = new TranslateWord(textToTranslateEditText.getText().toString(),translatedTextTextView.getText().toString(), R.drawable.off_fav, "en-ru");
                //myRef.push().setValue(translateWord);
            }
        });





        return view;

    }

    class Task extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params) {
            String urlRequest = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20170411T212807Z.453f1236ba984a10.1c90eef56783085c1db1dfde89ed1e15f31926ef";
            String inputText =  params[0];
            String lang = params[1];
            String outputText = "";
            String jsonResponse = "";
            InputStream inputStream = null;
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(urlRequest);
                urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                urlConnection.connect();

                DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());
                dataOutputStream.writeBytes("&text=" + URLEncoder.encode(inputText, "UTF-8") + "&lang=" + lang);



                if(urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                }
            }catch (IOException e) {
                e.printStackTrace();
                // TODO: Handle the exception
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return jsonResponse;
        }

        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        @Override
        protected void onPostExecute(String jsonResponse) {
            String text = "";
            JSONObject baseJsonResponse = null;
            try {
                baseJsonResponse = new JSONObject(jsonResponse);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONArray textArray = null;
            try {
                textArray = baseJsonResponse.getJSONArray("text");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                text = textArray.getString(0);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            super.onPostExecute(jsonResponse);
            translatedTextTextView.setText(text);
        }
    }

}
