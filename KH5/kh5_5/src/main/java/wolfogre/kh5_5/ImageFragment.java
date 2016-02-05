package wolfogre.kh5_5;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageFragment extends Fragment {
	String dogName;
	/**
	 * Create a new instance of CountingFragment, providing "num"
	 * as an argument.
	 */
	static ImageFragment newInstance(String dogName) {
		ImageFragment imageFragment = new ImageFragment();

		// Supply dogName input as an argument.
		Bundle args = new Bundle();
		args.putString("dog_name", dogName);
		imageFragment.setArguments(args);

		return imageFragment;
	}

	/**
	 * When creating, retrieve this instance's number from its arguments.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dogName = getArguments() != null ? getArguments().getString("dog_name") : "";
	}

	/**
	 * The Fragment's UI is just a simple text view showing its
	 * instance number.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_image, container, false);

		((ImageView)v.findViewById(R.id.imageView)).setImageResource(DogInfo.getResourceId(dogName));
		return v;
	}

}
