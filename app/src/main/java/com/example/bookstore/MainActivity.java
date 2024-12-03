package com.example.bookstore;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText titleInput;
    private EditText authorInput;
    private EditText pageAmountInput;
    private final List<Book> books = new ArrayList<>();
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        this.titleInput = findViewById(R.id.titleInput);
        this.authorInput = findViewById(R.id.authorInput);
        this.pageAmountInput = findViewById(R.id.pageAmountInput);
        ListView bookList = findViewById(R.id.bookList);
        this.adapter = new CustomAdapter(this, books);
        bookList.setAdapter(adapter);
        MaterialButton addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(view -> addToListView());
    }

    private void addToListView()
    {
        String title;
        String author;
        int pageAmount;
        try {
            title = titleInput.getText().toString();
            author = authorInput.getText().toString();
            pageAmount = Integer.parseInt(pageAmountInput.getText().toString());
        }
        catch (Exception e)
        {
            title = "";
            author = "";
            pageAmount = 0;
        }
        if (!title.isEmpty() && !author.isEmpty() && pageAmount > 0)
        {
            if (pageAmount > 50)
            {
                books.add(new Book(title, author, pageAmount));
                this.adapter.notifyDataSetChanged();
                Reset();
            }
            else
            {
                Toast.makeText(this, "Page amount must be greater than 50", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_LONG).show();
        }
    }
    private void Reset()
    {
        titleInput.setText("");
        authorInput.setText("");
        pageAmountInput.setText("");
    }
}