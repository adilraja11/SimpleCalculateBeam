package com.example.praktikum1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvResult;
    private EditText etWidth, etLength, etHeight;
    private Button btnCalculateVolume, btnCalculateLuas, btnCalculateKeliling;
    private String KEY_RESULT = "key_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tv_result);
        etWidth = findViewById(R.id.et_width);
        etLength = findViewById(R.id.et_length);
        etHeight = findViewById(R.id.et_height);
        btnCalculateVolume = findViewById(R.id.btn_calculate_volume);
        btnCalculateLuas = findViewById(R.id.btn_calculate_luas);
        btnCalculateKeliling = findViewById(R.id.btn_calculate_keliling);

        btnCalculateVolume.setOnClickListener(this);
        btnCalculateLuas.setOnClickListener(this);
        btnCalculateKeliling.setOnClickListener(this);

        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(KEY_RESULT);
            tvResult.setText(result);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String calculationResult = tvResult.getText().toString();
        outState.putString(KEY_RESULT, calculationResult);
    }

    @Override
    public void onClick(View view) {
        // Ambil nilai yang diberikan pengguna pada seluruh EditText
        String inputLength = etLength.getText().toString().trim();
        String inputWidth = etWidth.getText().toString().trim();
        String inputHeight = etHeight.getText().toString().trim();

        boolean isEmptyFields = false;
        if (inputLength.isEmpty()) {
            isEmptyFields = true;
            etLength.setError("Field ini tidak boleh kosong");
        }
        if (inputWidth.isEmpty()) {
            isEmptyFields = true;
            etWidth.setError("Field ini tidak boleh kosong");
        }
        if (inputHeight.isEmpty()) {
            isEmptyFields = true;
            etHeight.setError("Field ini tidak boleh kosong");
        }

        // Perintah hanya akan dieksekusi jika btn_calculate ditekan
        if (view.getId() == R.id.btn_calculate_volume) {
            if (!isEmptyFields) {
                double volume = Double.parseDouble(inputLength) * Double.parseDouble(inputWidth) * Double.parseDouble(inputHeight);
                tvResult.setText(String.format("Volume: %s", String.valueOf(volume)));
            }
        }
        if (view.getId() == R.id.btn_calculate_luas) {
            if (!isEmptyFields) {
                double pl = Double.parseDouble(inputLength) * Double.parseDouble(inputWidth);
                double lt = Double.parseDouble(inputWidth) * Double.parseDouble(inputHeight);
                double pt = Double.parseDouble(inputLength) * Double.parseDouble(inputHeight);
                double luas = 2 * (pl + lt + pt);
                tvResult.setText(String.format("Luas Permukaan: %s", String.valueOf(luas)));
            }
        }
        if (view.getId() == R.id.btn_calculate_keliling) {
            if (!isEmptyFields) {
                double keliling = 4 * (Double.parseDouble(inputHeight) + Double.parseDouble(inputLength) + Double.parseDouble(inputWidth));
                tvResult.setText(String.format("Keliling: %s", String.valueOf(keliling)));
            }
        }
    }
}