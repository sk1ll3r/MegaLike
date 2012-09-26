package com.atlast.MegaLike.FacebookLogic;
import com.restfb.Facebook;

public class FQLPhotoSrc {
		  @Facebook
		  String photo_id;
		  
		  @Facebook
		  String src;

		  @Override
		  public String toString() {
		    return String.format("%s (%s)", photo_id, src);
		  }
}

