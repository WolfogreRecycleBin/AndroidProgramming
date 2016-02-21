package wolfogre.kh6_2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import wolfogre.kh8_1.R;

public class AddPeopleDialogFragment  extends DialogFragment {

	@Override
	public Dialog onCreateDialog(final Bundle savedInstanceState) {
		super.onCreateDialog(savedInstanceState);
		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.fragment_add_people_dialog, null);

		final EditText etNewPeopleName = (EditText)view.findViewById(R.id.etNewPeopleName);

		builder.setTitle("输入新选手名字")
				.setView(view)
				.setNegativeButton("确认", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String[] strings = getTag().split("###");
						GameInfoBySharedPreferences.addPeopleName(strings[0], Integer.parseInt(strings[1]), etNewPeopleName.getText().toString());
						dialog.cancel();
					}
				});

		// Create the AlertDialog object and return it
		return builder.create();
	}
}
