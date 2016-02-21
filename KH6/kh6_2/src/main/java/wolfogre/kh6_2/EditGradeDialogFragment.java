package wolfogre.kh6_2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Jason Song(wolfogre@outlook.com) on 02/21/2016.
 */
public class EditGradeDialogFragment extends DialogFragment {
	@Override
	public Dialog onCreateDialog(final Bundle savedInstanceState) {
		super.onCreateDialog(savedInstanceState);
		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.fragment_edit_grade_dialog, null);

		TextView tvSelfName = (TextView)view.findViewById(R.id.tvSelfName);
		TextView tvOpponentName = (TextView)view.findViewById(R.id.tvOpponentName);

		final EditText etSelfGrade = (EditText)view.findViewById(R.id.etSelfGrade);
		final EditText etOpponentGrade = (EditText)view.findViewById(R.id.etOpponentGrade);

		final String[] strings = getTag().split("###");

		tvSelfName.setText(strings[2]);
		tvOpponentName.setText(strings[3]);

		builder.setTitle("输入比分")
				.setView(view)
				.setNegativeButton("确认", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(etSelfGrade.getText().toString().isEmpty() || etOpponentGrade.getText().toString().isEmpty())
							return;
						GameInfoBySharedPreferences.setGrade(strings[0], Integer.parseInt(strings[1]), strings[2], strings[3], etSelfGrade.getText().toString() + ":" + etOpponentGrade.getText().toString());
					}
				});


		// Create the AlertDialog object and return it
		return builder.create();
	}

}
