package com.riteshbhavsar.exampapers.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ritesh.bhavsar on 23-08-2017.
 */

@Entity
public class Course {

    @PrimaryKey(autoGenerate = true)
    private int cid;

    String co_id;
    String co_name;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCo_id() {
        return co_id;
    }

    public void setCo_id(String co_id) {
        this.co_id = co_id;
    }

    public String getCo_name() {
        return co_name;
    }

    public void setCo_name(String co_name) {
        this.co_name = co_name;
    }
}
