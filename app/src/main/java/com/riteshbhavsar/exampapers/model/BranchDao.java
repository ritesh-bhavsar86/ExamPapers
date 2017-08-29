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
public interface BranchDao {

    @Query("SELECT * FROM Branch")
    List<Branch> getAllBranches();

    @Query("select * from Branch where bid = :id")
    Branch loadBranchById(int id);


    @Insert(onConflict = IGNORE)
    void insertBranch(Branch branch);

    @Delete
    void deleteBranch(Branch branch);

}
