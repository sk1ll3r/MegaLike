package com.atlast.MegaLike.FacebookLogic;

import com.restfb.Facebook;

public class FQLFriend {
	@Facebook
	public String uid;
	
	@Facebook
	public String name;
	
	public String toString(){
		return name;
	}
}
