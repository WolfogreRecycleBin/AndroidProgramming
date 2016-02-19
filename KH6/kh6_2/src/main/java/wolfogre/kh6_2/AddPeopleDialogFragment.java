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
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

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
						SharedPreferences spGroupPeople = getActivity().getSharedPreferences(groupName, Context.MODE_PRIVATE);
						SharedPreferences.Editor speGroupPeople = spGroupPeople.edit();
						Set<String> peopleList = spGroupPeople.getStringSet(strings[0], new HashSet<String>());
						peopleList.add(etNewPeopleName.getText().toString());
						speGroupPeople.putStringSet(strings[0], peopleList);
						speGroupPeople.apply();

						dialog.cancel();
					}
				})
		;
		//TODO

		// Create the AlertDialog object and return it
		return builder.create();
	}
}
