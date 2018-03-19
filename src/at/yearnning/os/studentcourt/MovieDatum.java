package at.yearnning.os.studentcourt;

import android.graphics.Bitmap;

public class MovieDatum {

	private String title;
	private String subtitle;
	private String url;
	private String thumbnail_img_url;
	private Bitmap thumbnail_img;

	private String intro;

	/**
	 * SetData
	 */

	public MovieDatum(String title, String subtitle, String url,
			String thumbnail_img_url, String intro) {

		this.title = title;
		this.subtitle = subtitle;

		this.url = url;
		this.thumbnail_img_url = thumbnail_img_url;
		this.thumbnail_img = null;

		this.intro = intro;
	}
	
	public void setThumbnailImg(Bitmap thumbnail_img) {
		this.thumbnail_img = thumbnail_img;
	}


	/**
	 * Get Data
	 */

	public String getTitle() {
		return this.title;
	}

	public String getSubtitle() {
		return this.subtitle;
	}

	public String getUrl() {
		return this.url;

	}

	public String getThumbnailImgUrl() {
		return this.thumbnail_img_url;
	}

	public Bitmap getThumbnailImg() {
		return this.thumbnail_img;
	}

	public String getIntro() {
		return this.intro;
	}
}
