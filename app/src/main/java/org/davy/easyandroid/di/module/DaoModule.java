package org.davy.easyandroid.di.module;

import dagger.Module;

@Module
public class DaoModule {

    public static final String DATABASE_NAME = "easy_android.db";

//    @Singleton
//    @Provides
//    DaoMaster.OpenHelper provideOpenHelper() {
//        File path = new File(App.sAppContext.getExternalFilesDir(""), "/" + DATABASE_NAME);
//        return new DBOpenHelper(App.sAppContext, path.getAbsolutePath(), null);
//    }
//
//    @Singleton
//    @Provides
//    DaoMaster provideDaoMaster(DaoMaster.OpenHelper helper) {
//        Database db = helper.getWritableDb();
//        return new DaoMaster(db);
//    }
//
//    @Provides
//    DaoSession provideDaoSession(DaoMaster daoMaster) {
//        return daoMaster.newSession();
//    }
//
//    @Singleton
//    @Provides
//    Database provideDatabase(DaoMaster daoMaster) {
//        return daoMaster.getDatabase();
//    }
//
//    private static class DBOpenHelper extends DaoMaster.OpenHelper {
//
//        DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
//            super(context, name, factory);
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            StandardDatabase standardDatabase = new StandardDatabase(db);
//        }
//    }
}