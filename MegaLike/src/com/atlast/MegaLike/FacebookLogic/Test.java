package com.atlast.MegaLike.FacebookLogic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Page;
import com.restfb.types.Photo;
import com.restfb.types.Post;
import com.restfb.types.User;
//import com.restfb.types.Photo;

public class Test {

	public static String getUserId(FacebookClient facebookClient) {
		User user = facebookClient.fetchObject("me", User.class);
		return user.getId();
	}
	
	private static int numberOfLikes(FacebookClient facebookClient, String object_id) {
		String query = "SELECT user_id, object_id FROM like WHERE object_id=\""+object_id+"\"";
		List<FQLlikes> users = facebookClient.executeQuery(query, FQLlikes.class);		
		return users.size();
	}
	
	public static List<FQLlikes> getLikesForUserPhotos(FacebookClient facebookClient, String uid) {
		if (uid == null || "me".equals(uid)) return new LinkedList<FQLlikes>();
		String query1 = "SELECT object_id FROM photo WHERE aid IN (SELECT aid FROM album WHERE owner="+uid+")";
		String query = "SELECT user_id, object_id FROM like WHERE object_id IN ("+query1+")";		
		return facebookClient.executeQuery(query, FQLlikes.class);
	}
	
	public static List<FQLPhoto> getAllUserPhotos(FacebookClient facebookClient, String uid) {		
		if (uid == null || "me".equals(uid)) return new LinkedList<FQLPhoto>();
		String query = "SELECT object_id, pid, src, src_big, link FROM photo WHERE aid IN (SELECT aid FROM album WHERE owner="+uid+")";
		System.out.println("Executing: "+query);
		return facebookClient.executeQuery(query, FQLPhoto.class);		
	}
	
	public static List<FQLPhoto> getAllUserTaggedPhotos(FacebookClient facebookClient, String uid) {		
		if (uid == null || "me".equals(uid)) return new LinkedList<FQLPhoto>();
		String query = "SELECT object_id, pid, src, src_big, link FROM photo WHERE object_id IN (SELECT object_id FROM photo_tag WHERE subject="+uid+")";
		System.out.println("Executing: "+query);
		return facebookClient.executeQuery(query, FQLPhoto.class);		
	}
	
	public static List<FQLPhoto> getAllUserCombinedPhotos(FacebookClient facebookClient, String uid) {		
		if (uid == null || "me".equals(uid)) return new LinkedList<FQLPhoto>();
		String query = "SELECT object_id, pid, src, src_big, link FROM photo WHERE (object_id IN (SELECT object_id FROM photo_tag WHERE subject="+uid+") OR (aid IN (SELECT aid FROM album WHERE owner="+uid+")))";
		System.out.println("Executing: "+query);
		return facebookClient.executeQuery(query, FQLPhoto.class);		
	}
	
	public static List<FQLLink> getUserLinks(FacebookClient facebookClient, String uid) {		
		if (uid == null || "me".equals(uid)) return new LinkedList<FQLLink>();
		String query = "SELECT link_id, owner, owner_comment, title, url, created_time FROM link WHERE  owner="+uid;
		System.out.println("Executing: "+query);
		return facebookClient.executeQuery(query, FQLLink.class);		
	}
	
	public static List<FQLPhoto> getMostPopularPhotos(FacebookClient facebookClient, Vector<String> pid, Vector<Integer> like) {
		String query = "SELECT user_id, object_id FROM like WHERE object_id IN (SELECT object_id FROM photo WHERE aid IN (SELECT aid FROM album WHERE owner in (SELECT uid2 FROM friend WHERE uid1 = me())))";
		List<FQLlikes> list = facebookClient.executeQuery(query, FQLlikes.class);
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		for (FQLlikes l : list) {
			if (m.containsKey(l.object_id)) {
				int val = (m.get(l.object_id)).intValue();
				m.put(l.object_id, val + 1);
			}
			else m.put(l.object_id, 1);
		}		
		String oid = "";
		for (String s : m.keySet()) {
			if (m.get(s).intValue() > 10) {
				pid.add(s);
				if (!"".equals(oid)) oid+=",";
				oid+=s;
				like.add(m.get(s));
			}
		}
		String query2 = "SELECT object_id, pid, src, src_big, link FROM photo WHERE (object_id IN ("+oid+"))";
		return facebookClient.executeQuery(query2, FQLPhoto.class);
	}
	
	public static List<FQLStatus> getUserStatuses(FacebookClient facebookClient, String uid) {		
		if (uid == null || "me".equals(uid)) return new LinkedList<FQLStatus>();
		String query = "SELECT status_id, uid, message, time FROM status WHERE  uid="+uid;
		System.out.println("Executing: "+query);
		return facebookClient.executeQuery(query, FQLStatus.class);		
	}
	
	public static List<FQLlikes> getLikesForUserLinks(FacebookClient facebookClient, String uid) {
		if (uid == null || "me".equals(uid)) return new LinkedList<FQLlikes>();
		String query1 = "SELECT link_id FROM link WHERE  owner="+uid;
		String query = "SELECT user_id, object_id FROM like WHERE object_id IN ("+query1+")";		
		return facebookClient.executeQuery(query, FQLlikes.class);
	}
	
	public static List<FQLlikes> getLikesForUserStatuses(FacebookClient facebookClient, String uid) {
		if (uid == null || "me".equals(uid)) return new LinkedList<FQLlikes>();
		String query1 = "SELECT status_id FROM status WHERE  uid="+uid;
		String query = "SELECT user_id, object_id FROM like WHERE object_id IN ("+query1+")";		
		return facebookClient.executeQuery(query, FQLlikes.class);
	}
	
	public static List<FQLPhoto> getStarredPhotos(FacebookClient facebookClient, String list) {				
		String query = "SELECT object_id, pid, src, src_big, link FROM photo WHERE (object_id IN ("+list+"))";
		System.out.println("Executing: "+query);
		return facebookClient.executeQuery(query, FQLPhoto.class);		
	}

	public static List<FQLlikes> getLikesForStarredPhotos(FacebookClient facebookClient, String list) {
		String query1 = "SELECT object_id, pid, src, src_big, link FROM photo WHERE (object_id IN ("+list+"))";		
		String query = "SELECT user_id, object_id FROM like WHERE object_id IN ("+query1+")";		
		return facebookClient.executeQuery(query, FQLlikes.class);
	}

	
	public static List<FQLlikes> getLikesForUserTaggedPhotos(FacebookClient facebookClient, String uid) {
		if (uid == null || "me".equals(uid)) return new LinkedList<FQLlikes>();
		String query1 = "SELECT object_id, pid, src, src_big, link FROM photo WHERE object_id IN (SELECT object_id FROM photo_tag WHERE subject="+uid+")";
		String query = "SELECT user_id, object_id FROM like WHERE object_id IN ("+query1+")";		
		return facebookClient.executeQuery(query, FQLlikes.class);
	}
	
	public static List<FQLlikes> getLikesForUserCombinedPhotos(FacebookClient facebookClient, String uid) {
		if (uid == null || "me".equals(uid)) return new LinkedList<FQLlikes>();
		String query1 = "SELECT object_id, pid, src, src_big, link FROM photo WHERE (object_id IN (SELECT object_id FROM photo_tag WHERE subject="+uid+") OR (aid IN (SELECT aid FROM album WHERE owner="+uid+")))";
		String query = "SELECT user_id, object_id FROM like WHERE object_id IN ("+query1+")";		
		return facebookClient.executeQuery(query, FQLlikes.class);
	}	
	
	public static List<FQLFriend> getFriends(FacebookClient facebookClient) {		
		String query = "SELECT uid, name FROM user WHERE uid = me() OR uid IN (SELECT uid2 FROM friend WHERE uid1 = me())";
		List<FQLFriend> friends = facebookClient.executeQuery(query, FQLFriend.class);
		for (FQLFriend friend : friends) {
			friend.name = 
			Normalizer.normalize((friend.name), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		}		        
		return friends;
	}
	
	public static Map<String, String> getPhotosSources(FacebookClient facebookClient, Vector<String> photo_ids) {		
		Map<String, String> m = new HashMap<String, String>();
		String strList = "";
		for (int i = 0; i < photo_ids.size(); i++) {
			if (i!=0) strList +=",";
			strList +=photo_ids.get(i);
		}
		String query = "SELECT photo_id, src FROM photo_src WHERE photo_id IN ("+strList+") AND width = 720";
		List<FQLPhotoSrc> photos = facebookClient.executeQuery(query, FQLPhotoSrc.class);
		for (FQLPhotoSrc photo : photos) {
			m.put(photo.photo_id, photo.src);
			System.out.println(": "+photo.photo_id+", "+photo.src);
		}
		return m;
	}
	
	
	public static String getAccessToken(String code) {
		String ret= "token: ";
		try {
		    // Construct data
		    String data = URLEncoder.encode("key1", "UTF-8") + "=" + URLEncoder.encode("value1", "UTF-8");
		    data += "&" + URLEncoder.encode("key2", "UTF-8") + "=" + URLEncoder.encode("value2", "UTF-8");

		    // Send data
		    URL url = new URL("http://hostname:80/cgi");
		    URLConnection conn = url.openConnection();
		    conn.setDoOutput(true);
		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    wr.write(data);
		    wr.flush();

		    // Get the response
		    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    String line;
		    while ((line = rd.readLine()) != null) {
		        // Process line...
		    	ret += line;
		    }
		    wr.close();
		    rd.close();
		} catch (Exception e) {
		}
		return ret;
	}
		
	
	public static void main(String[] arg) {
		System.out.println("start");
		Vector<String> popularPhotoId = new Vector<String>();
		Vector<String> popularPhotoImage = new Vector<String>();
		Vector<Integer> popularPhotoCount = new Vector<Integer>();
		getPopularPhotos(popularPhotoId, popularPhotoImage, popularPhotoCount, null, null);
	}
	
	public static void getPopularPhotos(Vector<String> popularPhotoId,
			Vector<String> popularPhotoImage,
			Vector<Integer> popularPhotoCount, String userId, FacebookClient facebookClient) {
		if (userId == null || "".equals(userId)) userId = "me";
		
		//FacebookClient publicOnlyFacebookClient = new DefaultFacebookClient();
		//User user = facebookClient.fetchObject("me", User.class);
		//Page page = facebookClient.fetchObject("cocacola", Page.class);
		

		//System.out.println("User name: " + user.getName() + " and hometown: " + user.getHometownName());
		//System.out.println("Page likes: " + page.getLikes());
		
		//Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);	
		
		Connection<Photo> myPhotos = facebookClient.fetchConnection(userId+"/photos", Photo.class, Parameter.with("limit", "0"));
		//Connection<Photo> myPhotos = facebookClient.fetchConnection("me/photos", Photo.class, Parameter.with("limit", "200"));
//		Connection<Post> myFeed = facebookClient.fetchConnection("me/feed", Post.class);

		//System.out.println("Count of my friends: " + myFriends.getData().size());
//		System.out.println("First item in my feed: " + myFeed.getData().get(0));

		// Connections support paging and are iterable
		
		for (List<Photo> photoList : myPhotos)
		  for (Photo photo : photoList) {
			    //System.out.println("Photo ("+ index +") id: " + photo.getId() + " likes: "+ (photo.getLikes().size() < 25 ? photo.getLikes().size() : numberOfLikes(facebookClient, photo.getId())) +" img:"+photo.getPicture());
			  if (photo.getLikes().size() >= 2) {
				  popularPhotoId.add(photo.getId());
				  popularPhotoImage.add(photo.getPicture());
				  popularPhotoCount.add(photo.getLikes().size() < 25 ? photo.getLikes().size() : numberOfLikes(facebookClient, photo.getId()));
			  }
		  }
		
		for (int i = 0; i < popularPhotoId.size(); i++) {
			System.out.println(popularPhotoId.get(i) + " likes: "+popularPhotoCount.get(i)+" at: "+popularPhotoImage.get(i));
		}
		
//		for (List<Post> myFeedConnectionPage : myFeed)
//		  for (Post post : myFeedConnectionPage)
//		    System.out.println("Post: " + post);
		int n = popularPhotoId.size();
		for (int i = 0; i < n; i++) {
			for (int j=1;j<n;j++) {
				if (popularPhotoCount.get(j-1) < popularPhotoCount.get(j) ) {
					int tmp = popularPhotoCount.get(j-1);
					popularPhotoCount.set(j-1, popularPhotoCount.get(j));
					popularPhotoCount.set(j, tmp);
					String tmp2 = popularPhotoId.get(j-1);
					popularPhotoId.set(j-1, popularPhotoId.get(j));
					popularPhotoId.set(j, tmp2);
					String tmp3 = popularPhotoImage.get(j-1);
					popularPhotoImage.set(j-1, popularPhotoImage.get(j));
					popularPhotoImage.set(j, tmp3);
				}
			}
		}
	}
	
}

