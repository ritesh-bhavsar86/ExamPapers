package com.riteshbhavsar.exampapers.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by ritesh.bhavsar on 23-08-2017.
 */

/*
Create a database holder called AppDatabase extends RoomDatabase,
we will define list of entities and database version.
Class is annotated with @Database annotation.
It is good practice to use singleton approach for the database,
so you need to create an static method which will return instance of AppDatabase.*/


@Database(entities = {University.class, Branch.class, Course.class, Papers.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract UniversityDao universityDao();
    public abstract BranchDao branchDao();
    public abstract CourseDao courseDao();
    public abstract PapersDao papersDao();


    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                            // To simplify the codelab, allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "exampaper-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


}
