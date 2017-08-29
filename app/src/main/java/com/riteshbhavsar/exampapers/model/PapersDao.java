package com.riteshbhavsar.exampapers.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by ritesh.bhavsar on 23-08-2017.
 */

/*
A Data Access Object (DAO) is an abstract class or interface
that includes methods to define database queries.
The annotated methods in this class are used to generate
the corresponding SQL at compile time. This abstraction helps
to reduce the amount of repetitive boilerplate code you need to maintain.
Unlike runtime SQL, these annotated methods are parsed and validated at compile time.

This class is annotated with @Dao annotation.
Room will generate an implementation of defined methods.
There are four annotations @Query, @Insert, @Update, @Delete
to perform CRUD operations.
@Query annotation is used to perform read operation on database.

*/
@Dao
public interface PapersDao {
    @Query("SELECT * FROM papers")
    List<Papers> getAllPapers();

    @Query("select * from papers where pid = :id")
    Papers loadPaperById(int id);

    @Insert(onConflict = IGNORE)
    void insertPaper(Papers papers);

    @Delete
    void deletePaper(Papers papers);

}
