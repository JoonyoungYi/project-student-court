package at.yearnning.os.studentcourt;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

public class AboutFragment extends SherlockFragment {
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.about_fragment, container, false);

		/**
		 * actionbar setting
		 
		getActivity().getSupportActionBar().setDisplayOptions(
				ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_HOME_AS_UP
						| ActionBar.DISPLAY_SHOW_TITLE);
		
		getActivity().getSupportActionBar().setTitle(R.string.menu_event);
	*/
		
		
		
	

		return v;
	}
	
	
}
