package com.example.bookstore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class CustomAdapter extends BaseAdapter {

    private final Context context;
    private final List<Book> books;

    public CustomAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.card_item_layout, viewGroup, false);
        }
        TextView titleText = view.findViewById(R.id.titleText);
        TextView authorText = view.findViewById(R.id.authorText);
        TextView pageAmountText = view.findViewById(R.id.pageAmountText);
        MaterialButton deleteButton = view.findViewById(R.id.deleteButton);

        String title = books.get(i).getTitle();
        String author = books.get(i).getAuthor();
        int pageAmount = books.get(i).getPageAmount();

        titleText.setText(title);
        authorText.setText(author);
        pageAmountText.setText(String.valueOf(pageAmount));

        view.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("author", author);
            intent.putExtra("pageAmount", String.valueOf(pageAmount));
            intent.putExtra("year", String.valueOf(new Random().nextInt((LocalDate.now().getYear() - 1000) + 1) + 1000));
            context.startActivity(intent);
        });

        deleteButton.setOnClickListener(deleteView -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("Delete book");
            alertDialog.setMessage("Are you sure you want to delete this book?");
            alertDialog.setPositiveButton("Yes", (dialog, which) -> {
                books.remove(i);
                notifyDataSetChanged();
            });
            alertDialog.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
            alertDialog.show();
        });

        return view;
    }
}
