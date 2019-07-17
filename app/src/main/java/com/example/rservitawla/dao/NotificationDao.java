package com.example.rservitawla.dao;

import android.app.Notification;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;
import  android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface NotificationDao {

  @Query("SELECT * FROM notification")
    List<Notification> getAll();

    //@Insert
   // void insert(Notification task);

//    @Query("DELETE FROM notification")
//    //void deleteAll();
//
//    @Query("DELETE FROM notification WHERE title = :title")
//    void delete(String title);
//
    @Update
    void update(Notification task);
}
