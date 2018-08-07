package jscsd.teachercreatwar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MechangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechange);


        EditText lastpw = (EditText) findViewById(R.id.lastpw);
        EditText newpw = (EditText) findViewById(R.id.newpw);
        EditText newpwsure = (EditText) findViewById(R.id.newpwsure);
        Button save = (Button) findViewById(R.id.save);
        Button cancel = (Button) findViewById(R.id.cancel);

        //保存我们输入的密码，保存在数据库中
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        //返回到我的界面
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MechangeActivity.this, MeActivity.class);
                finish();
            }
        });

    }
}
