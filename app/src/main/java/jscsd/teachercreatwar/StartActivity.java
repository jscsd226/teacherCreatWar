package jscsd.teachercreatwar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartActivity.this,"登陆成功",Toast.LENGTH_SHORT);
                //建立Intent对象，将两个Activity之间联系起来
                Intent intent = new Intent(StartActivity.this,MainActivity.class);
//开启第二个Activity
                startActivity(intent);
            }
        });

    }
}
