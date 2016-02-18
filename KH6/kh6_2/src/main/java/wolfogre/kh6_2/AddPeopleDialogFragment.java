package wolfogre.kh6_2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Jason Song(wolfogre@outlook.com) on 02/18/2016.
 */
public class AddPeopleDialogFragment extends DialogFragment {

	public interface InputListener {
		void onInputComplete(String newPeopleName);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreateDialog(savedInstanceState);
		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		builder.setTitle("输入新选手名字")
				.setMessage(getString(R.string.app_description))
				.setNegativeButton("确认", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				})
				;
		//TODO

		// Create the AlertDialog object and return it
		return builder.create();
	}
}
