package com.example.myapplication12;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.res.ColorStateList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textEmail;
    TextView textHaslo;
    TextView komunikat;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textEmail = findViewById(R.id.textemail);
        textHaslo = findViewById(R.id.teksthaslo);
        komunikat = findViewById(R.id.wyswietl);
        button = findViewById(R.id.button);

        komunikat.setText("Witaj! Aplikacja wykonana przez: Kacper Maciaszek");

        button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00BCD4")));
        button.setTextColor(Color.WHITE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEmailDialog();
            }
        });
    }

    private void showEmailDialog() {
        final EditText editText = new EditText(this);
        editText.setHint("Nowy email");
        editText.setText(textEmail.getText());

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Zmień Email")
                .setMessage("Podaj nowy email:")
                .setView(editText)
                .setPositiveButton("Zapisz", (dialog1, which) -> {
                    String nowyEmail = editText.getText().toString().trim();
                    if (!nowyEmail.contains("@")) {
                        komunikat.setTextColor(Color.RED);
                        komunikat.setText("Błąd: Nieprawidłowy format emaila.");
                    } else {
                        textEmail.setText(nowyEmail);
                        showPasswordDialog(nowyEmail);
                    }
                })
                .setNegativeButton("Anuluj", (dialog1, which) -> {
                    komunikat.setTextColor(Color.GRAY);
                    komunikat.setText("Edycja profilu anulowana.");
                })
                .show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00BCD4"));
    }

    private void showPasswordDialog(String nowyEmail) {
        View layout = getLayoutInflater().inflate(R.layout.password_dialog_layout, null);
        EditText haslo1 = layout.findViewById(R.id.password1);
        EditText haslo2 = layout.findViewById(R.id.password2);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Zmień Hasło")
                .setView(layout)
                .setPositiveButton("Zapisz", (dialog1, which) -> {
                    String p1 = haslo1.getText().toString().trim();
                    String p2 = haslo2.getText().toString().trim();

                    if (!p1.equals(p2)) {
                        komunikat.setTextColor(Color.RED);
                        komunikat.setText("Błąd: Hasła nie pasują do siebie.");
                    } else {
                        textHaslo.setText(p1);
                        komunikat.setTextColor(Color.GREEN);
                        komunikat.setText("Profil zaktualizowany! Nowy email: " + nowyEmail);
                    }
                })
                .setNegativeButton("Anuluj", (dialog1, which) -> {
                    komunikat.setTextColor(Color.GRAY);
                    komunikat.setText("Edycja profilu anulowana.");
                })
                .show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00BCD4"));
    }
}
