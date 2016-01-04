package wolfogre.havefun;

import android.widget.TextView;

/**
 * Created by Jason Song(wolfogre@outlook.com) on 01/04/2016.
 */
public class AndroidRoom extends AnotherWayForEvent.Room {
	MainActivity mainActivity;
	public AndroidRoom(String name, int id, MainActivity mainActivity) {
		super(name, id);
		this.mainActivity = mainActivity;
	}

	@Override
	protected void onOpenDoor() {
		TextView tv = (TextView)mainActivity.findViewById(R.id.text);
		tv.setText(tv.getText() + "\n" + "Welcome students!");
	}

	@Override
	protected void onCloseDoor() {
		TextView tv = (TextView)mainActivity.findViewById(R.id.text);
		tv.setText(tv.getText() + "\n" + "Everyone get out!");
	}
}
