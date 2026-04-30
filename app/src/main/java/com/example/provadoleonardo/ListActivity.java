package com.example.provadoleonardo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.provadoleonardo.database.ProductDatabase;
import com.example.provadoleonardo.model.Product;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    LinearLayout listContainer;
    Button btnBack;
    ProductDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db = ProductDatabase.getInstance(this);
        listContainer = findViewById(R.id.listContainer);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());

        loadProducts();
    }

    private void loadProducts() {
        List<Product> products = db.productDao().getAll();

        if (products.isEmpty()) {
            TextView tv = new TextView(this);
            tv.setText("Nenhum produto cadastrado.");
            listContainer.addView(tv);
            return;
        }

        for (Product p : products) {
            TextView tv = new TextView(this);
            tv.setText("Produto: " + p.name +
                    "\nCódigo: " + p.code +
                    "\nPreço: R$ " + String.format("%.2f", p.price));
            tv.setPadding(0, 8, 0, 16);
            tv.setTextSize(16);
            listContainer.addView(tv);
        }
    }
}