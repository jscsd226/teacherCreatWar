package jscsd.teachercreatwar;

/*
    MyDatabaseHelper作为一个访问sqlite的助手类，提供两个方面的功能
    1.getReadableDatabase(),getWriteableDatabase(), 可以获得sqliteDatabase对象。
    2.提供了onCreate()和onUpgrade()两个回调函数，允许我们在创建和升级数据库时，进行自己的操作。
*/
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseOpenHelper extends SQLiteOpenHelper {
    //这里面有三个参数
    /*
        第一个是id 主键，它是自动增长的
        第二个与第三个是我们定义的两个数据 姓名 电话
     */
    final String CREATE_TABLE_SQL = "create table dict(_id integer primary  key autoincrement,name varchar(10),ID varchar(11),time varchar(10),xuliehao varchar(11))";

    //在SQLiteOpenHelper的子类当中，必须有该构造函数。
    public MyDatabaseOpenHelper(Context context){
        //必须通过super调用父类中的构造函数
        super(context,"dict.db",null,1);//重写构造方法并设置工厂为null
    }
    @Override
    //该函数在第一次创建数据库时执行
    public void onCreate(SQLiteDatabase db){
        //execSQL用于执行SQL语句
        /*
        怎么预置数据呢
            我们现在有两种方式，
                1.用studiosqlite新建数据库，然后把表导入到我们的手机上 暂时还没有实现
                2.在代码里面直接写入 如下
         */
        db.execSQL(CREATE_TABLE_SQL);//创建单词信息表

    }

    @Override
    //重写基类的onUpgrade()方法，以便数据库版本更新
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        //提示版本更新并输出旧版本与新版本信息
        System.out.println("---版本更新---"+oldVersion+"--->"+newVersion);
    }


}