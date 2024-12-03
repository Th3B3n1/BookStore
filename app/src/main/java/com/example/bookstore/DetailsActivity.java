package com.example.bookstore;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView titleText = findViewById(R.id.titleText);
        TextView authorText = findViewById(R.id.authorText);
        TextView pageAmountText = findViewById(R.id.pageAmountText);
        TextView yearText = findViewById(R.id.yearText);
        MaterialButton backButton = findViewById(R.id.backButton);
        titleText.setText(getIntent().getStringExtra("title"));
        authorText.setText(getIntent().getStringExtra("author"));
        pageAmountText.setText(getIntent().getStringExtra("pageAmount"));
        yearText.setText(getIntent().getStringExtra("year"));
        backButton.setOnClickListener(view -> finish());
    }
}