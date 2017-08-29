package com.riteshbhavsar.exampapers.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ritesh.bhavsar on 23-08-2017.
 */

@Entity
public class Branch {

    @PrimaryKey(autoGenerate = true)
    private int bid;

    String br_id;
    String br_name;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBr_id() {
        return br_id;
    }

    public void setBr_id(String br_id) {
        this.br_id = br_id;
    }

    public String getBr_name() {
        return br_name;
    }

    public void setBr_name(String br_name) {
        this.br_name = br_name;
    }
}
