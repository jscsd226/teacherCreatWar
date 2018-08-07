package jscsd.teachercreatwar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MepictureActivity extends AppCompatActivity {

/*
    public int[] imageID = new int[]{R.drawable.touxiang12,R.drawable.touxiang10,R.drawable.touxiang1,R.drawable.touxiang9,R.drawable.touxiang8,R.drawable.touxiang7,
    R.drawable.touxiang5,R.drawable.touxiang4,R.drawable.touxiang3,R.drawable.touxiang2,R.drawable.touxaing11};
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mepicture);
/*
        GridView gridView =(GridView) findViewById(R.id.gridView);//获取组件
        BaseAdapter adapter=new BaseAdapter() {
            @Override
            public int getCount() {

                return imageID.length;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView;
                if (convertView==null){
                    imageView=new ImageView(MepictureActivity.this);
                    //***********设置图像的宽度和高度
                    imageView.setAdjustViewBounds(true);
                    imageView.setMaxHeight(150);
                    imageView.setMaxWidth(158);
                    //************
                    imageView.setPadding(5,5,5,5);
                }else {
                    imageView=(ImageView)convertView;
                }
                imageView.setImageResource(imageID[position]);//为IMAGEVIEW设置要显示的图片
                return imageView;//返回imageView
            }
        };

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = getIntent();//获取INTENT对象
                Bundle bundle= new Bundle();//落实化要传递的数据包
                bundle.putInt("imageID",imageID[position]);//显示选中的图片
                intent.putExtras(bundle);//将数据包保存到intent中
                setResult(0x11,intent);

                finish();
            }
        });*/

    }//onCreate 方法结束
}
