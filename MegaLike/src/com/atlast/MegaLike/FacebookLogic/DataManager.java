package com.atlast.MegaLike.FacebookLogic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import android.util.Log;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

public class DataManager {
		
	private Map<String, Vector<Photo>> photos;
	private Map<String, Vector<Photo>> allPhotos;
	private Map<String, Vector<Photo>> combPhotos;
	
	private Vector<Photo> starPhotos;
	private Map<String, Photo> starPhotoId;
	
	private Vector<Photo> lastShown;
	private Map<String, Vector<Link>> links;
	
	private Vector<String> userIds;		
	
	private FacebookClient facebookClient;
	
	private String ownerId = null;
	private String accessToken = null;
	
	public String getOwnerId() {
		if (ownerId != null) return ownerId;
		if (accessToken != null) {			
			ownerId = com.atlast.MegaLike.FacebookLogic.Test.getUserId(facebookClient);			
		}
		return ownerId;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public DataManager(String accessToken) {
		this.accessToken = accessToken;
		this.facebookClient = new DefaultFacebookClient(accessToken);
		photos = new HashMap<String, Vector<Photo>>();
		allPhotos = new HashMap<String, Vector<Photo>>();
		combPhotos = new HashMap<String, Vector<Photo>>();
		links = new HashMap<String, Vector<Link>>();
		userIds = new Vector<String>();
		starPhotos = new Vector<Photo>();
		lastShown = new Vector<Photo>();
		starPhotoId = null;		
		firstTime = false;
	}
	
	public Vector<String> getDiscoveredUserIds() {
		return userIds;
	}
	
	public void setLastShown(Vector<Photo> last) {
		lastShown.clear();
		for (Photo p : last) lastShown.add(p);
	}
	
	public List<FQLFriend> getFriends() {
		return com.atlast.MegaLike.FacebookLogic.Test.getFriends(facebookClient);
	}
	
	public Vector<Photo> getStarPhotos() {
		return starPhotos;
	}
	
	public Vector<Photo> getAllUserPhotos(String userId) {		
		if (!"me".equals(userId)) {
			userIds.removeElement(userId);
			userIds.add(userId);
		}
		//if (allPhotos.get(userId) != null) return allPhotos.get(userId);
		List<FQLPhoto> ps = com.atlast.MegaLike.FacebookLogic.Test.getAllUserPhotos(facebookClient, userId);
		Log.d("TAG", "DataManager - getAllUserPhotos parsed " + ps.size() + " images");
		Vector<Photo> ret = new Vector<Photo>();
		HashMap<String, Integer> oidToIndex = new HashMap<String, Integer>();
		for (FQLPhoto p : ps) {
			oidToIndex.put(p.object_id, ret.size());
			ret.add(new Photo(p.object_id, p.src, p.src_big, 0, p.link));			
			//System.out.println("$ "+p.object_id+" "+p.pid);
		}		
		for (FQLlikes l : com.atlast.MegaLike.FacebookLogic.Test.getLikesForUserPhotos(facebookClient, userId)) {		
			ret.get(oidToIndex.get(l.object_id)).likes++;
		}		
		allPhotos.put(userId, ret);
		return ret;
	}
	
	public Vector<Photo> getAllUserTaggedPhotos(String userId) {
		if (!"me".equals(userId)) {
			userIds.removeElement(userId);
			userIds.add(userId);
		}
		if (photos.get(userId) != null) return photos.get(userId);
		List<FQLPhoto> ps = com.atlast.MegaLike.FacebookLogic.Test.getAllUserTaggedPhotos(facebookClient, userId);
		Vector<Photo> ret = new Vector<Photo>();
		HashMap<String, Integer> oidToIndex = new HashMap<String, Integer>();
		for (FQLPhoto p : ps) {
			oidToIndex.put(p.object_id, ret.size());
			ret.add(new Photo(p.object_id, p.src, p.src_big, 0, p.link));			
			//System.out.println("$ "+p.object_id+" "+p.pid);
		}		
		for (FQLlikes l : com.atlast.MegaLike.FacebookLogic.Test.getLikesForUserTaggedPhotos(facebookClient, userId)) {		
			ret.get(oidToIndex.get(l.object_id)).likes++;
		}		
		photos.put(userId, ret);
		return ret;
	}
	
	public Vector<Photo> getAllUserCombinedPhotosByPart(String userId) {
		HashSet<Photo> p = new HashSet<Photo>();
		p.addAll(getAllUserPhotos(userId));
		p.addAll(getAllUserTaggedPhotos(userId));
		Vector<Photo> ret = new Vector<Photo>();
		for (Photo photo : p) {
			ret.add(photo);
			//System.out.println("! "+photo.pid);
		}
		Log.d("TAG", "DataManager parsed " + ret.size() + " images");
		return ret;
	}
	
	public Vector<Photo> getAllUserCombinedPhotos(String userId) {
		
		if (!"me".equals(userId)) {
			userIds.removeElement(userId);
			userIds.add(userId);
		}
		if (combPhotos.get(userId) != null) return combPhotos.get(userId);
		List<FQLPhoto> ps = com.atlast.MegaLike.FacebookLogic.Test.getAllUserCombinedPhotos(facebookClient, userId);
		Vector<Photo> ret = new Vector<Photo>();
		HashMap<String, Integer> oidToIndex = new HashMap<String, Integer>();
		for (FQLPhoto p : ps) {
			oidToIndex.put(p.object_id, ret.size());
			ret.add(new Photo(p.object_id, p.src, p.src_big, 0, p.link));			
			//System.out.println("$ "+p.object_id+" "+p.pid);
		}		
		for (FQLlikes l : com.atlast.MegaLike.FacebookLogic.Test.getLikesForUserCombinedPhotos(facebookClient, userId)) {		
			ret.get(oidToIndex.get(l.object_id)).likes++;
		}		
		combPhotos.put(userId, ret);
		return ret;
	}
	
	public Vector<Photo> getStarredPhotosByList(String list) {	
		
		if ("".equals(list)) return new Vector<Photo>();
		List<FQLPhoto> ps = com.atlast.MegaLike.FacebookLogic.Test.getStarredPhotos(facebookClient, list);
		Vector<Photo> ret = new Vector<Photo>();
		HashMap<String, Integer> oidToIndex = new HashMap<String, Integer>();
		for (FQLPhoto p : ps) {
			oidToIndex.put(p.object_id, ret.size());
			ret.add(new Photo(p.object_id, p.src, p.src_big, 0, p.link));			
		}		
		for (FQLlikes l : com.atlast.MegaLike.FacebookLogic.Test.getLikesForStarredPhotos(facebookClient, list)) {		
			ret.get(oidToIndex.get(l.object_id)).likes++;
		}				
		return ret;
	}
	
	public Vector<Photo> getPhotos(String userId) {
		
		if (!"me".equals(userId)) {
			userIds.removeElement(userId);
			userIds.add(userId);
		}
		if (photos.get(userId) != null) return photos.get(userId);
		Vector<String> popularPhotoId = new Vector<String>();
		Vector<String> popularPhotoImage = new Vector<String>();
		Vector<Integer> popularPhotoCount = new Vector<Integer>();			
		com.atlast.MegaLike.FacebookLogic.Test.getPopularPhotos(popularPhotoId, popularPhotoImage, popularPhotoCount, userId, facebookClient);	
		while (popularPhotoId.size() > 16) {
			popularPhotoId.remove(popularPhotoId.size()-1);
			popularPhotoImage.remove(popularPhotoImage.size()-1);
			popularPhotoCount.remove(popularPhotoCount.size()-1);
		}
		Map<String, String> photoSrc = com.atlast.MegaLike.FacebookLogic.Test.getPhotosSources(facebookClient, popularPhotoId);
		Vector<Photo> ret = new Vector<Photo>();
		for (int i = 0; i < popularPhotoId.size(); i++) {
			ret.add(new Photo(popularPhotoId.get(i), popularPhotoImage.get(i), photoSrc.get(popularPhotoId.get(i)), popularPhotoCount.get(i), ""));
		}
		photos.put(userId, ret);
		return ret;
	}
	
	public Map<String, Photo> getStarPhotoIds() {
		if (starPhotoId == null) {
			starPhotoId = new HashMap<String, Photo>();
			SqlTest sql = new SqlTest();
			String list = "";
			for (String p :sql.getBookmarkList(getOwnerId())) {
				System.out.println("starred pid: "+p);
				if (!"".equals(list)) list +=",";
				list += p;
			}
			Vector<Photo> vp = getStarredPhotosByList(list);
			starPhotos = vp;
			for (Photo p : vp) {
				starPhotoId.put(p.pid, p);
			}
			return starPhotoId;
		} else 
		return starPhotoId;
	}
	
	public Vector<Link> getUserLinks(String userId) {
		
		if (!"me".equals(userId)) {
			userIds.removeElement(userId);
			userIds.add(userId);
		}
		if (links.get(userId) != null) return links.get(userId);
		
		List<FQLLink> ps = com.atlast.MegaLike.FacebookLogic.Test.getUserLinks(facebookClient, userId);
		Vector<Link> ret = new Vector<Link>();
		HashMap<String, Integer> oidToIndex = new HashMap<String, Integer>();
		for (FQLLink p : ps) {
			oidToIndex.put(p.link_id, ret.size());
			ret.add(new Link(p.link_id, p.owner, p.owner_comment, p.title, p.url, /*p.image_urls != null && p.image_urls.length > 0 ? p.image_urls[0] : null,*/ p.created_time));			
			//System.out.println("$ "+p.object_id+" "+p.pid);
		}		
		for (FQLlikes l : com.atlast.MegaLike.FacebookLogic.Test.getLikesForUserLinks(facebookClient, userId)) {		
			ret.get(oidToIndex.get(l.object_id)).likes++;
		}		
		
		List<FQLStatus> ss = com.atlast.MegaLike.FacebookLogic.Test.getUserStatuses(facebookClient, userId);				
		for (FQLStatus p : ss) {
			oidToIndex.put(p.status_id, ret.size());
			ret.add(new Link(p.status_id, p.uid, p.message, null, null, /*p.image_urls != null && p.image_urls.length > 0 ? p.image_urls[0] : null,*/ p.time));			
			//System.out.println("$ "+p.object_id+" "+p.pid);
		}		
		for (FQLlikes l : com.atlast.MegaLike.FacebookLogic.Test.getLikesForUserStatuses(facebookClient, userId)) {		
			ret.get(oidToIndex.get(l.object_id)).likes++;
		}
		
		links.put(userId, ret);
		return ret;
	}
	
	public boolean starPhoto(String uid, String pid) {		
		if (pid == null) return false;	
		if (starPhotoId.containsKey(pid)) {
			starPhotos.remove(starPhotoId.get(pid));
			starPhotoId.remove(pid);			
			SqlTest sql = new SqlTest();
			sql.removeBookmark(uid, pid);		
			return true;
		}										
		for (Photo photo : lastShown) {			
			if (pid.equals(photo.pid)) {
				starPhotos.add(photo);
				starPhotoId.put(pid, photo);
				SqlTest sql = new SqlTest();
				sql.addBookmark(uid, pid);
				return true;
			}
		}
		return false;
	}
	
	private int premium = -100;
	
	private boolean firstTime;
	
	public boolean isFirstTime() {
		return firstTime;
	}
	
	public void setFirstTimeNo() {
		firstTime = false;
	}
	
	// returns number of searches still allowed
	// premium - 5 =  how manty are left.
	// if premium == 5 you, user can share the link and gets new 4 searches.
	// if buy, it sets premium to a lot :)
	public int getPremium() {
		if (premium == -100) {
			// read data from DB, if it is not there set it to 9			
			SqlTest sql = new SqlTest();
			premium = sql.getPremium(ownerId);
			if (sql.isPremium(ownerId)) premium = 999999;
			if (premium == -100) {
				premium = 9;
				firstTime = true;
				sql.insertPremium(ownerId, premium);
			}
		}		
		return 10000;
	}		
	
	public void decreasePremium() {
		premium --;
		// update number in DB
		SqlTest sql = new SqlTest();
		sql.updatePremium(ownerId, premium);
	}
	
	public void setPremium() {
		premium = 10000;
	}
	
	public static void buyPremium(String userId) {
		// update number in DB for user to 100,000
		SqlTest sql = new SqlTest();
		sql.updatePremium(userId, 1000000);
	}
		
	public boolean shareLink(String link, String text) {
				
				  facebookClient.publish("me/feed", FacebookType.class,
				    Parameter.with("message", text),				    				    
				    Parameter.with("link", link));
		return true;
	}
	
	public boolean sharePhoto(String pid, String text) {
		String url = "";
		for (Photo p : lastShown) {
			if (p.pid.equals(pid)) url = p.link;
		}		
				  facebookClient.publish("me/feed", FacebookType.class,
				    Parameter.with("message", text+" -- via mega-like.com"),				    				    
				    Parameter.with("link", url));
		return true;
	}
	
	public boolean commentPhoto(String pid, String text) {		
				  facebookClient.publish(pid+"/comments", FacebookType.class,
				    Parameter.with("message", text+" -- via mega-like.com"));
		return true;
	}
	
	public boolean likePhoto(String pid) {				
		facebookClient.publish(pid+"/likes", FacebookType.class);
		return true;
	}
		
}
