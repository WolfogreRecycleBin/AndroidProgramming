package com.example.me.fileiosample;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2015/12/28.
 */
public class SecondActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    private boolean mExternalStorageAvailable = false;
    private boolean mExternalStorageWriteable = false;
    private String mSDPath ;
    private String mFilePath;

    private TextView mTvContent;
    private Button mBtAdd,mBtDel;
    private EditText mEtContent, mEtFilename;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mTvContent=(TextView)this.findViewById(R.id.tvcontent);
        mBtAdd=(Button)this.findViewById(R.id.btadd);
        mBtDel=(Button)this.findViewById(R.id.btdelete);
        mEtContent=(EditText)this.findViewById(R.id.etcontent);
        mEtFilename=(EditText)this.findViewById(R.id.etfilename);

        mBtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    testAddOrOpenFile(mEtFilename.getText().toString(),mEtContent.getText().toString());
                    mTvContent.setText(readFile(mEtFilename.getText().toString()));
                    Toast.makeText(getApplicationContext(), "new file be added,path is" + mFilePath, Toast.LENGTH_LONG).show();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        });

        mBtDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testDeleteFile(mEtFilename.getText().toString());
                mTvContent.setText("no file");
                Toast.makeText(getApplicationContext(), "file be deleteed,path is" + mFilePath, Toast.LENGTH_LONG).show();

            }
        });



    }



    /*
     *
     * 检查SD卡状态
     */
    private void checkExternalStorageState(){

        String state = Environment.getExternalStorageState();
        mSDPath = Environment.getExternalStorageDirectory().getPath();
        mFilePath= getFilesDir().getPath();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // We can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            // Something else is wrong. It may be one of many other states, but all we need
            //  to know is we can neither read nor write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }
    }

    /*
     * 创建一个文件并且在文件中输入些内容，BufferedOutputStream是个带缓存的FileOutputStream的包装
     */
    private boolean testAddOrOpenFile(String filename ,String filecontent) throws IOException {
        checkExternalStorageState();
        if(mExternalStorageWriteable){
            OutputStream out = null;
            File file = new File(mSDPath + "//" + filename);
            try{
                if (!file.exists()) {
                    file.createNewFile();
                }
                out = new BufferedOutputStream(new FileOutputStream(file));
                out.write(filecontent.getBytes());
                return true;
            }catch(IOException e){
                return false;
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        }
        return false;
    }
    /*
     * 删除文件测试
     *
     */
    private boolean testDeleteFile(String filename){
        checkExternalStorageState();
        if(mExternalStorageWriteable){
            File file = new File(mSDPath + "//" + filename);
            if (file == null || !file.exists() || file.isDirectory())
                return false;
            return file.delete();
        }
        return false;
    }

    /*
     * 读取文件测试
     */

    private String readFile(String filename){
        checkExternalStorageState();
        if(mExternalStorageWriteable){
            StringBuffer sb = new StringBuffer();
            File file = new File(mSDPath + "//" + filename);
            try {
                FileInputStream fis = new FileInputStream(file);
                int c;
                while ((c = fis.read()) != -1) {
                    sb.append((char) c);
                }
                fis.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }
        return null;
    }
}
