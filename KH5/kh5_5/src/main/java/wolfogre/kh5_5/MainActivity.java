package wolfogre.kh5_5;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	String[] mPlanetTitles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mPlanetTitles = DogInfo.getDogNames();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mPlanetTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView parent, View view, int position, long id) {
			selectItem(position);
		}

		private void selectItem(int position) {
			// Create a new fragment and specify the planet to show based on position
			Fragment fragment = new ImageFragment();
			Bundle args = new Bundle();
			args.putString("dog_name", mPlanetTitles[position]);
			fragment.setArguments(args);

			// Insert the fragment by replacing any existing fragment
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment)
					.commit();

			// Highlight the selected item, update the title, and close the drawer
			mDrawerList.setItemChecked(position, true);
			getSupportActionBar().setTitle(mPlanetTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		}
	}
}

