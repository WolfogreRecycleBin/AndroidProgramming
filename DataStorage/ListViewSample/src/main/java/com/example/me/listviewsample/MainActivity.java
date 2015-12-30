package com.example.me.listviewsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ArrayList<HashMap> m_list;
    private CkAdapter madapter;

    ListView		m_ListView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        loadUserList();

    }

    private void loadUserList(){
        m_ListView=(ListView)this.findViewById(R.id.listview01);

        //使用simpleAdapter的例子
        //ListAdapter adapter=getSimpleAdapter();
        //使用Arrayadapter类的例子
        //ListAdapter adapter=getArrayAdapter();
		/* 将adapter添加到m_ListView中 */
        madapter=getCkAdapter();
        m_ListView.setAdapter(madapter);
        setListViewEvent();
    }

    private CkAdapter getCkAdapter(){
        String[] districts= getResources().getStringArray(R.array.district_array);
        String[] postcodes= getResources().getStringArray(R.array.postcode_array);


        m_list=new ArrayList<HashMap>();

        for(int i=0;i<=districts.length-1;i++){

            HashMap mp= new HashMap();
            mp.put("district", districts[i]);
            mp.put("postcode", postcodes[i]);
            mp.put("true",false);
            m_list.add(mp);
        }
        return new CkAdapter(m_list,this);
    }

    //使用simpleadapter绑定数据和显示格式
    private ListAdapter getSimpleAdapter(){

        String[] districts= getResources().getStringArray(R.array.district_array);
        String[] postcodes= getResources().getStringArray(R.array.postcode_array);


        ArrayList list=new ArrayList<HashMap>();

        for(int i=0;i<=districts.length-1;i++){

            HashMap mp= new HashMap();
            mp.put("district", districts[i]);
            mp.put("postcode", postcodes[i]);
            mp.put("true",false);
            list.add(mp);
        }



        SimpleAdapter adapter=new SimpleAdapter(this,list,
                R.layout.list_view,
                new String[]{"district","postcode","true"},
                new int[]{R.id.tv_district,R.id.tv_postcode,R.id.ck_select});
        return adapter;
    }

    //使用Arrayadapter绑定数据和显示格式
    private ListAdapter getArrayAdapter(){

        String[] countries = getResources().getStringArray(R.array.countries_array);
        ListAdapter adapter=new ArrayAdapter<String>(this, R.layout.list_view2, countries);
        //ListAdapter adapter=new ArrayAdapter<String>(this, R.id.tv_country, countries);
        return adapter;
    }


    //listview事件处理函数的定义
    private void setListViewEvent(){
		/* 为m_ListView视图添加setOnItemSelectedListener监听 */

        m_ListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {

                m_ListView.setItemChecked(arg2, true);
                DisplayToast("滚动到第"+Long.toString(arg0.getSelectedItemId())+"项");
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
                //没有选中
            }
        });

		/* 为m_ListView视图添加setOnItemClickListener监听 */
        m_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                Intent it=new Intent(MainActivity.this,FlagListActivity.class);
                startActivity(it);
                //于对选中的项进行处理
                if(m_list!=null){

                    Boolean cbs=!(Boolean)((HashMap)m_list.get(arg2)).get("true");

                    CheckBox cb=(CheckBox)arg1.findViewById(R.id.ck_select);
                    cb.setSelected(cbs);

                    ((HashMap)m_list.get(arg2)).put("true", cbs);
                    madapter.notifyDataSetChanged();
                }
                DisplayToast("选中了第"+Integer.toString(arg2+1)+"项");
            }

        });
    }


    private Boolean toggle(boolean ischecked){
        return !ischecked;
    }
    /* 显示Toast  */
    public void DisplayToast(String str)
    {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
