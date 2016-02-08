package wolfogre.kh6_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ReadFileActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_file);
		File file = new File(getIntent().getStringExtra("file_path"));
		setTitle(file.getName());

		final int MAX_LINE = 30;//读太多浪费内存
		int lineCount = 0;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			Scanner scanner = new Scanner(new FileInputStream(file));
			while(lineCount++ < MAX_LINE && scanner.hasNextLine()){
				stringBuilder.append(scanner.nextLine()).append("\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		((TextView)findViewById(R.id.text_content)).setText(stringBuilder.toString());
	}
}
