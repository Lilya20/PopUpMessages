package com.example.popupmessages;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView main_text = findViewById(R.id.main_text);
        Button button2 = findViewById(R.id.button2);
        //вешаем обработчик событий на эту кнопку
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInfo(main_text.getText().toString(), button2);
                showInfoAlert("Вы хотите закрыть приложение?"); //вызываем метод всплывающего окна
            }
        });



    }

    public void btnClick(View v){
        showInfo(((Button) v).getText().toString(), ((Button)v));

    }

//создаем метод, который будем вызывать при нажатии кнопки. Всплывающее окно с текстом
    //Второй параметр нам для изменения цвета
    private void showInfo(String text, Button btn){
        //контекст: класс, в котором мы показываем всплывающее окошко(this - mainActivity)
        //text - текст, который будет показан пользователю
        //длительность всплывающего окна- LENGTH_LONG - где-то 3 секунды, SHORT - 1 секунду
        //show()- метод, который помогает показать само всплывающее окно
        Toast.makeText(this, text,Toast.LENGTH_LONG).show();

        btn.setText("Уже нажали");
        btn.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
    }

    // всплывающие подсказки
    private void showInfoAlert(String text){

        //создаем всплывающую подсказку, выделяем память и пишем контекст
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //обращаемся к объекту и устанавлием настройки
        builder.setTitle("Подсказ очка")
                .setMessage(text)
                //метод, который показывает, можно ли отменить эту всплывающую подсказку
                .setCancelable(false)
                .setPositiveButton("Yep", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish(); //выйдем из приложения
                    }
                })
                .setNegativeButton("Nope", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel(); //закроется всплывающее окно
                    }
                });

        AlertDialog dialog = builder.create(); //создаем окно
        dialog.show(); // показываем окно
    }
}