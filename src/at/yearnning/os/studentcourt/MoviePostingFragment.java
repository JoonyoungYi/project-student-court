package at.yearnning.os.studentcourt;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.actionbarsherlock.app.SherlockFragment;

public final class MoviePostingFragment extends SherlockFragment {

	static final int MENU_SET_MODE = 0;

	/**
	 * UI Reference
	 */

	private WebView mWv;

	/** Called when the activity is first created. */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.movie_posting_fragment, container,
				false);

		Intent mIntent = getActivity().getIntent();

		String url = mIntent.getExtras().getString("url");
		/*
		 * String answer_where = mIntent.getExtras().getString("answer_where");
		 * String answer_how = mIntent.getExtras().getString("answer_how");
		 * String answer_else = mIntent.getExtras().getString("answer_else");
		 * String answer_what = mIntent.getExtras().getString("answer_what");
		 * 
		 * String answer_open = mIntent.getExtras().getString("answer_open");
		 * String answer_close = mIntent.getExtras().getString("answer_close");
		 * 
		 * /** Data Input to TextView
		 */

		WebView mWv = (WebView) v.findViewById(R.id.wv);

		mWv.getSettings().setJavaScriptEnabled(true);
		mWv.getSettings().setBuiltInZoomControls(false);
		mWv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		mWv.getSettings().setLightTouchEnabled(true);

		mWv.setWebViewClient(new MoviePostingWebViewClient());
		mWv.loadUrl(url);

		/*
		 * TextView mAnswerWhereTv = (TextView) v
		 * .findViewById(R.id.answer_where_tv); TextView mAnswerHowTv =
		 * (TextView) v.findViewById(R.id.answer_how_tv); TextView mAnswerElseTv
		 * = (TextView) v.findViewById(R.id.answer_else_tv); TextView
		 * mAnswerWhatTv = (TextView) v.findViewById(R.id.answer_what_tv);
		 * 
		 * mAnswerWhereTv.setText(answer_where);
		 * mAnswerHowTv.setText(answer_how); mAnswerElseTv.setText(answer_else);
		 * mAnswerWhatTv.setText(answer_what);
		 * 
		 * if (answer_open != null) { TextView mAnswerOpenTv = (TextView) v
		 * .findViewById(R.id.answer_open_tv); TextView mAnswerOpenLabelTv =
		 * (TextView) v .findViewById(R.id.answer_open_label_tv);
		 * 
		 * mAnswerOpenTv.setText(answer_open);
		 * mAnswerOpenTv.setVisibility(View.VISIBLE);
		 * mAnswerOpenLabelTv.setVisibility(View.VISIBLE); }
		 * 
		 * if (answer_close != null) { TextView mAnswerCloseTv = (TextView) v
		 * .findViewById(R.id.answer_close_tv); TextView mAnswerCloseLabelTv =
		 * (TextView) v .findViewById(R.id.answer_close_label_tv);
		 * 
		 * mAnswerCloseTv.setText(answer_close);
		 * mAnswerCloseTv.setVisibility(View.VISIBLE);
		 * mAnswerCloseLabelTv.setVisibility(View.VISIBLE); }
		 * 
		 * TextView mAnswerCloseTv = (TextView) v
		 * .findViewById(R.id.answer_close_tv);
		 */
		return v;
	}

	private class MoviePostingWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

}
