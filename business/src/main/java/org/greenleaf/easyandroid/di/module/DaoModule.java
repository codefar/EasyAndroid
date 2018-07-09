package org.greenleaf.easyandroid.di.module;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenleaf.easyandroid.domain.DaoMaster;
import org.greenleaf.easyandroid.domain.DaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.StandardDatabase;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DaoModule {

    public static final String DATABASE_NAME = "easy_android.db";

    private Context mContext;

    public DaoModule(Context context) {
        mContext = context;
    }

    @Singleton
    @Provides
    DaoMaster.OpenHelper provideOpenHelper() {
        File path = new File(mContext.getExternalFilesDir(""), "/" + DATABASE_NAME);
        return new DBOpenHelper(mContext, path.getAbsolutePath(), null);
    }

    @Singleton
    @Provides
    DaoMaster provideDaoMaster(DaoMaster.OpenHelper helper) {
        Database db = helper.getWritableDb();
        return new DaoMaster(db);
    }

    @Provides
    DaoSession provideDaoSession(DaoMaster daoMaster) {
        return daoMaster.newSession();
    }

    @Singleton
    @Provides
    Database provideDatabase(DaoMaster daoMaster) {
        return daoMaster.getDatabase();
    }

    private static class DBOpenHelper extends DaoMaster.OpenHelper {

        DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            StandardDatabase standardDatabase = new StandardDatabase(db);
        }
    }
}