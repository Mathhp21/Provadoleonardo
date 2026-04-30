package com.example.provadoleonardo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.provadoleonardo.database.ProductDatabase;
import com.example.provadoleonardo.model.Product;

public class MainActivity extends AppCompatActivity {

    EditText etName, etCode, etPrice, etQuantity;
    Button btnSave, btnList;
    ProductDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = ProductDatabase.getInstance(this);

        etName     = findViewById(R.id.etName);
        etCode     = findViewById(R.id.etCode);
        etPrice    = findViewById(R.id.etPrice);
        etQuantity = findViewById(R.id.etQuantity);
        btnSave    = findViewById(R.id.btnSave);
        btnList    = findViewById(R.id.btnList);

        btnSave.setOnClickListener(v -> saveProduct());
        btnList.setOnClickListener(v ->
                startActivity(new Intent(this, ListActivity.class)));
    }

    private void saveProduct() {
        String name     = etName.getText().toString().trim();
        String code     = etCode.getText().toString().trim();
        String priceStr = etPrice.getText().toString().trim();
        String qtyStr   = etQuantity.getText().toString().trim();

        if (name.isEmpty() || code.isEmpty() || priceStr.isEmpty() || qtyStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        if (price <= 0) {
            Toast.makeText(this, "Preço deve ser positivo!", Toast.LENGTH_SHORT).show();
            return;
        }

        int qty = Integer.parseInt(qtyStr);
        if (qty <= 0) {
            Toast.makeText(this, "Quantidade deve ser positiva!", Toast.LENGTH_SHORT).show();
            return;
        }

        Product product = new Product(name, code, price, qty);
        db.productDao().insert(product);
        Toast.makeText(this, "Produto salvo!", Toast.LENGTH_SHORT).show();

        etName.setText(""); etCode.setText("");
        etPrice.setText(""); etQuantity.setText("");
    }
}