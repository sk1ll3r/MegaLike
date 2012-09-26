package com.atlast.MegaLike.FacebookLogic;
import com.restfb.Facebook;

public class FQLLink {
	@Facebook
	String link_id;
	
	@Facebook
	String owner;
	  
	@Facebook
	String owner_comment;

	@Facebook
	String title;
	
	@Facebook
	String url;
	
	//@Facebook
	//String[] image_urls;
	
	@Facebook
	String created_time;
}
