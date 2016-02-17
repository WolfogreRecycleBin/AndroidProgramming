package wolfogre.kh6_1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

public class NewFileActivity extends AppCompatActivity {

	private File path;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_file);
		setTitle("新建");
		path = new File(getIntent().getStringExtra("file_path"));
		final EditText etName = (EditText)findViewById(R.id.etName);
		final EditText etContent = (EditText)findViewById(R.id.etContent);
		final CheckBox cbIsFolder = (CheckBox)findViewById(R.id.cbIsFolder);
		final Button btnCommit = (Button)findViewById(R.id.btnCommit);
		final Context context = this;
		final NewFileActivity This = this;

		cbIsFolder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(cbIsFolder.isChecked())
					etContent.setEnabled(false);
			}
		});

		btnCommit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				File newFile = new File(path.getAbsolutePath() + "/" + etName.getEditableText().toString());
				try{
					if(cbIsFolder.isChecked()){
						if(!newFile.mkdir()){
							Toast.makeText(context, "创建" + newFile.getAbsolutePath() + "失败",Toast.LENGTH_LONG).show();
						}else {
							Toast.makeText(context, "创建" + newFile.getAbsolutePath() + "成功", Toast.LENGTH_LONG).show();
						}
					}else {
						if(!newFile.createNewFile())
							Toast.makeText(context, "创建" + newFile.getAbsolutePath() + "失败",Toast.LENGTH_LONG).show();
						else{
							Toast.makeText(context, "创建" + newFile.getAbsolutePath() + "成功",Toast.LENGTH_LONG).show();
							FileWriter fileWriter = new FileWriter(newFile);
							fileWriter.write(etContent.getEditableText().toString().toCharArray());
							fileWriter.close();
						}
					}
				}catch (Exception ex){
					//Toast.makeText(context, ex.getMessage() + "\n" + newFile.getAbsolutePath(),Toast.LENGTH_LONG).show();
					Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
				}
				This.finish();
			}
		});

	}
}
