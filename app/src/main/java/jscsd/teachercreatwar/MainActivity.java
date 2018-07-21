package jscsd.teachercreatwar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends Activity {

    private   MyDatabaseOpenHelper dbOpenHelper;//定义DBOpenHelper
    private SQLiteDatabase dbRead,dbWrite;
    private SimpleCursorAdapter adapter;


/*
    创建AlertDialog的步骤：

    1、创建AlertDialog.Builder对象

　　2、调用Builder对象的setTitle方法设置标题，setIcon方法设置图标

　　3、调用Builder相关方法如setMessage方法、setItems方法、setSingleChoiceItems方法、setMultiChoiceItems方法、setAdapter方法、setView方法设置不同类型的对话框内容。

    4、调用setPositiveButton、setNegativeButton、setNeutralButton设置多个按钮

　　5、调用Builder对象的create()方法创建AlertDialog对象

　　6、调用AlertDialog对象的show()方法将对话框显示出来
*/

    private AdapterView.OnItemLongClickListener listViewItemLongClickListener=new AdapterView.OnItemLongClickListener() {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view,
                                       final int position, long id) {

            new AlertDialog.Builder(MainActivity.this).setTitle("提醒").setMessage("您确定要删除该项吗").setNegativeButton("取消", null).setPositiveButton("确定", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Cursor c = adapter.getCursor();
                    c.moveToPosition(position);

                    int itemId = c.getInt(c.getColumnIndex("_id"));
                    dbWrite.delete("dict", "_id=?", new String[]{itemId+""});
                    refreshListView();
                }
            }).show();

            return true;   //如果是true操作系统执行的反馈（震动或者声音）  如果是false 则不触发
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbOpenHelper = new MyDatabaseOpenHelper(this);
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();


        final ListView listView = (ListView) findViewById(R.id.list);
        final EditText etSearch = (EditText) findViewById(R.id.guanjianzi);
        TextView btnSearch = (TextView) findViewById(R.id.chaxun);
        Button btnSearch_bit = (Button) findViewById(R.id.chaxunbit);
        Button btn_add = (Button) findViewById(R.id.xinzeng);

        Button btn_me = (Button) findViewById(R.id.me);//跳转到我的界面
        Button exit = (Button) findViewById(R.id.tuichu);//跳转到主界面 退出账号

        btn_me.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                //建立Intent对象，将两个Activity之间联系起来
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
//开启第二个Activity
                startActivity(intent);
            }
        });

        //触发方法的实现  长按触发删除某一行
        dbRead = dbOpenHelper.getReadableDatabase();
        dbWrite = dbOpenHelper.getWritableDatabase();
        adapter = new SimpleCursorAdapter(this, R.layout.result_main, null, new String[]{"name", "time","ID"}, new int[]{R.id.name1, R.id.time1,R.id.ID1});
        refreshListView();
        listView.setOnItemLongClickListener(listViewItemLongClickListener);


        //删除的实现
        //方法：
        //      1.假如要删除所有的，把条件和后面的置为空。
        //      2.假如要删除指定的某一个，即加入占位符，后面的把要删除的某一行写上。
        //      3.假如要删除其中的某个咋们输入的自己的数，则先用getTest().toString()方法获取到输入的数据，然后在后面调用，即可。
        /*btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获得数据库的写操作，并声明db，以便用于手续的sql语句操作
                SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
                //定义key，获得输入框输入的数据，便于后续的删除操作
                String key = etSearch.getText().toString();
                //此处一定注意
                //              我们在判断是否相等时，数字方面的用==，而字符与字符串必须要用equals方法
                if (key.equals("")) {
                    Toast.makeText(MainActivity.this, "没有可删除的数据", Toast.LENGTH_LONG).show();
                } else {
                    //db.delete("dict","nulll",null);  这句话是删除表中的所有的数据
                    db.delete("dict", "word=?", new String[]{key});   //删除之前定义的key
                    Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_LONG).show();
                }
            }
        });*/


        //跳转界面用 Intent实现，这是通用的一种方式。
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                //通过Intent跳转添加生词界面
                startActivity(intent);

            }
        });

        //查询其中的某一个，
        btnSearch_bit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = etSearch.getText().toString();//获取要查询的姓名
                //query("user",new String[]{"id","name"},"id=?",new String[]{"1"},null,null,null)
                Cursor cursor = dbOpenHelper.getReadableDatabase().query("dict", null, "word=?", new String[]{key}, null, null, null);
                //创建ArrayList对象，用于保存查询输出的结果
                ArrayList<Map<String, String>> resultList = new ArrayList<Map<String, String>>();// 创建一个结果集 两个String类型
                while (cursor.moveToNext()) {
                    //用while循环一直判断，当下一条为假时，我们的游标循环也就结束了
                    //cursor.movetonext   定义一个游标 它的作用是将游标向下挪动一位 判断当前游标位置的下一条还有没有数据 如果有 就返回真 如果无 就返回假
                    Map<String, String> map = new HashMap<>(); //新开辟一个map的集合空间
                    // map.put("id",cursor.getString(0));
                    map.put("word", cursor.getString(1));//在第一行输出姓名
                    map.put("interpret", cursor.getString(2));//在第二行输出电话
                    resultList.add(map);//在XML文件resultList 显示
                }
                if (resultList == null || resultList.size() == 0) {
                    Toast.makeText(MainActivity.this, "没有相关项", Toast.LENGTH_LONG).show();
                } else {
                    //定义一个simpleAdapter,供列表项使用
                    SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, resultList, R.layout.result_main,
                            new String[]{"_id","name","ID","time","xuliehao"}, new int[]{
                            R.id.xuhao1, R.id.name1,R.id.ID1,R.id.time1,R.id.xuliehao1});
                    listView.setAdapter(simpleAdapter);
                }


            }
        });

        //查询所有的
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String key = etSearch.getText().toString();//获取要查询的姓名
                //query("user",new String[]{"id","name"},"id=?",new String[]{"1"},null,null,null)
                Cursor cursor = dbOpenHelper.getReadableDatabase().query("dict", null, null, null, null, null, null);
                //创建ArrayList对象，用于保存查询输出的结果
                ArrayList<Map<String, String>> resultList = new ArrayList<Map<String, String>>();// 创建一个结果集 两个String类型
                while (cursor.moveToNext()) {
                    //用while循环一直判断，当下一条为假时，我们的游标循环也就结束了
                    //cursor.movetonext   定义一个游标 它的作用是将游标向下挪动一位 判断当前游标位置的下一条还有没有数据 如果有 就返回真 如果无 就返回假
                    Map<String, String> map = new HashMap<>(); //新开辟一个map的集合空间
                    map.put("word", cursor.getString(1));//在第一行输出姓名
                    map.put("interpret", cursor.getString(2));//在第二行输出电话
                    resultList.add(map);//在XML文件resultList 显示
                }
                if (resultList == null || resultList.size() == 0) {
                    Toast.makeText(MainActivity.this, "没有相关记录", Toast.LENGTH_LONG).show();
                } else {
                    //定义一个simpleAdapter,供列表项使用
                    SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, resultList, R.layout.result_main,
                            new String[]{"_id","name","ID","time","xuliehao"}, new int[]{
                            R.id.xuhao1, R.id.name1,R.id.ID1,R.id.time1,R.id.xuliehao1});
                    listView.setAdapter(simpleAdapter);
                }


            }
        });


    }


    private void refreshListView(){
        Cursor c = dbRead.query("dict", null, null, null, null, null, null);
        adapter.changeCursor(c);
    }
    //释放数据库连接
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(dbOpenHelper!=null){
            dbOpenHelper.close();
        }
    }

    //双击退出  使用按下的方式。双击退出一共有三种方式 按下 弹起 定时

    //记录用户首次点击返回键的时间
    private long firstTime=0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis()-firstTime>2000){
                Toast.makeText(MainActivity.this,"再按一次退出程序--->onKeyDown",Toast.LENGTH_SHORT).show();
                firstTime=System.currentTimeMillis();
            }else{
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


}