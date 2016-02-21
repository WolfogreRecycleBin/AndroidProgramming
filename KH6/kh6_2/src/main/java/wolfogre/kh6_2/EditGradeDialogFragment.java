package wolfogre.kh6_2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

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
						String groupName = "";
						switch (strings[1]){
							case "0":
								groupName = getActivity().getString(R.string.name_group_0_people);
								break;
							case "1":
								groupName = getActivity().getString(R.string.name_group_1_people);
								break;
							case "2":
								groupName = getActivity().getString(R.string.name_group_2_people);
								break;
							case "3":
								groupName = getActivity().getString(R.string.name_group_3_people);
								break;
							case "4":
								groupName = getActivity().getString(R.string.name_group_4_people);
								break;
							default:
								Toast.makeText(getActivity(),"错误!",Toast.LENGTH_LONG).show();
						}
						SharedPreferences.Editor speGrade = getActivity().getSharedPreferences(groupName, Context.MODE_PRIVATE).edit();
						speGrade.putString(strings[0] + groupName + strings[2] + strings[3], etSelfGrade.getText().toString() + ":" + etOpponentGrade.getText().toString());
						speGrade.apply();

						dialog.cancel();
					}
				});


		// Create the AlertDialog object and return it
		return builder.create();
	}

}
