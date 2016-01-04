package wolfogre.havefun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AndroidRoom androidRoom = new AndroidRoom("Android Room", 123, this);
		androidRoom.openDoor("Zou Guobing");
		androidRoom.closeDoor("Lei Feng");
	}
}
