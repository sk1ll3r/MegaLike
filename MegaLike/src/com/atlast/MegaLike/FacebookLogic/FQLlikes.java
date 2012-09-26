package com.atlast.MegaLike.FacebookLogic;

import com.restfb.Facebook;

public class FQLlikes {
	  @Facebook
	  String uid;
	  
	  @Facebook
	  String object_id;

	  @Override
	  public String toString() {
	    return String.format("%s (%s)", uid);
	  }
}