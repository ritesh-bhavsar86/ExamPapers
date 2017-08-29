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
@Dao
public interface CourseDao {

    @Query("SELECT * FROM course")
    List<Course> getAllCourses();

    @Query("select * from course where cid = :id")
    Course loadCourseById(int id);

    @Insert(onConflict = IGNORE)
    void insertCourse(Course course);

    @Delete
    void deleteCourse(Course course);

}
