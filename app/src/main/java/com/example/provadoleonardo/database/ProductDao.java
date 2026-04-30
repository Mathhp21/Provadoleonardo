package com.example.provadoleonardo.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.provadoleonardo.model.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insert(Product product);

    @Query("SELECT * FROM products ORDER BY id DESC")
    List<Product> getAll();
}
