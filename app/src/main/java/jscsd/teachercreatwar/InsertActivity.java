package jscsd.teachercreatwar;



import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    private MyDatabaseOpenHelper dbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        dbOpenHelper = new MyDatabaseOpenHelper(this);
        final EditText etname =(EditText) findViewById(R.id.add_name);
        final EditText etID =(EditText) findViewById(R.id.add_ID);
        final EditText ettime =(EditText) findViewById(R.id.zhucetime);
        Button btn_Save =(Button)findViewById(R.id.save_btn);
        Button btn_Cancel =(Button)findViewById(R.id.cancel_btn1);

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =etname.getText().toString();
                String ID =etID.getText().toString();
                String time=ettime.getText().toString();
                if (name.equals("")||ID.equals("")||time.equals("")){
                    Toast.makeText(InsertActivity.this,"输入不全，请重新输入：",Toast.LENGTH_SHORT).show();
                }
                else {
                    //调用insertData方法，实现插入数据
                    insertData(dbOpenHelper.getReadableDatabase(),name,ID,time);
                    //显示提示信息
                    Toast.makeText(InsertActivity.this,"添加成功！",Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InsertActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void insertData(SQLiteDatabase readableDatabase, String name, String ID, String time){
        //生词ContentValues对象
        ContentValues values =new ContentValues();
        //向该对象当中插入键值对，其中键是列名，值是希望插入这一列的值，值必须和数据库的数据类型匹配
        values.put("name",name);//保存联系人
        values.put("ID",ID);//保存电话
        values.put("time",time);
        readableDatabase.insert("dict",null,values);//执行插入操作
    }

    //更新操作
    //第一个参数是要更新的表名  第二个是一个ContentValeus对象 第三个是where字句
    //dbOpenHelper.update("dict",values,"id=?",new String[]{"?"});

}
