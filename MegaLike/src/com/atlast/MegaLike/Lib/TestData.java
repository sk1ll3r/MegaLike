package com.atlast.MegaLike.Lib;

import java.util.Vector;

public class TestData {

	public TestData() {

	}

	public Vector<Photo> getPhotosAll(int userID) {
		Vector<Photo> result = new Vector<Photo>();
		switch (userID) {
		case 1598236292:
			// Tuan
			result.add(new Photo(
					"https://sphotos-a.xx.fbcdn.net/hphotos-ash3/s720x720/540296_10150739047043100_139141130_n.jpg",
					"https://sphotos-a.xx.fbcdn.net/hphotos-ash3/s720x720/540296_10150739047043100_139141130_n.jpg",
					10));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-ash3/550871_10151771585210374_403327050_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-ash3/550871_10151771585210374_403327050_n.jpg",
					9));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc7/390677_10150466982918100_310693358_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc7/390677_10150466982918100_310693358_n.jpg",
					8));
			result.add(new Photo(
					"https://sphotos-a.xx.fbcdn.net/hphotos-sh3/s720x720/544809_10150918274549931_1085336623_n.jpg",
					"https://sphotos-a.xx.fbcdn.net/hphotos-sh3/s720x720/544809_10150918274549931_1085336623_n.jpg",
					7));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-sh3/s720x720/579741_10150738195410900_2058182645_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-sh3/s720x720/579741_10150738195410900_2058182645_n.jpg",
					6));
			break;
		case 1400488155:
			// Rasto
			result.add(new Photo(
					"https://sphotos-a.xx.fbcdn.net/hphotos-prn1/s720x720/548757_10150931817966033_1983435846_n.jpg",
					"https://sphotos-a.xx.fbcdn.net/hphotos-prn1/s720x720/548757_10150931817966033_1983435846_n.jpg",
					10));
			result.add(new Photo(
					"https://sphotos-a.xx.fbcdn.net/hphotos-ash3/s720x720/564974_3978955158017_1383444447_n.jpg",
					"https://sphotos-a.xx.fbcdn.net/hphotos-ash3/s720x720/564974_3978955158017_1383444447_n.jpg",
					9));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc7/s720x720/574985_3853784028673_1314807056_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc7/s720x720/574985_3853784028673_1314807056_n.jpg",
					8));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc6/230467_2033984534823_6920097_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc6/230467_2033984534823_6920097_n.jpg",
					7));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-ash3/167500_1779719858365_4929716_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-ash3/167500_1779719858365_4929716_n.jpg",
					6));
			break;
		case 1618444494:
			// Mato
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc7/s720x720/398735_349854428368425_1205833371_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc7/s720x720/398735_349854428368425_1205833371_n.jpg",
					10));
			result.add(new Photo(
					"https://sphotos-a.xx.fbcdn.net/hphotos-snc7/s720x720/391098_2071852486972_64144002_n.jpg",
					"https://sphotos-a.xx.fbcdn.net/hphotos-snc7/s720x720/391098_2071852486972_64144002_n.jpg",
					9));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc6/184642_1719767326855_5104629_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc6/184642_1719767326855_5104629_n.jpg",
					8));
			result.add(new Photo(
					"https://sphotos-a.xx.fbcdn.net/hphotos-ash4/s720x720/293690_296332423727661_487537545_n.jpg",
					"https://sphotos-a.xx.fbcdn.net/hphotos-ash4/s720x720/293690_296332423727661_487537545_n.jpg",
					7));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-prn1/164816_1632540866248_1706376_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-prn1/164816_1632540866248_1706376_n.jpg",
					6));
			break;
		default:
			result = null;
		}
		return result;
	}
	
	public Vector<Photo> getPhotosTagged(int userID) {
		Vector<Photo> result = new Vector<Photo>();
		switch (userID) {
		case 1598236292:
			// Tuan
			result.add(new Photo(
					"https://sphotos-a.xx.fbcdn.net/hphotos-ash3/s720x720/540296_10150739047043100_139141130_n.jpg",
					"https://sphotos-a.xx.fbcdn.net/hphotos-ash3/s720x720/540296_10150739047043100_139141130_n.jpg",
					10));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-ash3/550871_10151771585210374_403327050_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-ash3/550871_10151771585210374_403327050_n.jpg",
					9));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc7/390677_10150466982918100_310693358_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc7/390677_10150466982918100_310693358_n.jpg",
					8));
			result.add(new Photo(
					"https://sphotos-a.xx.fbcdn.net/hphotos-sh3/s720x720/544809_10150918274549931_1085336623_n.jpg",
					"https://sphotos-a.xx.fbcdn.net/hphotos-sh3/s720x720/544809_10150918274549931_1085336623_n.jpg",
					7));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-sh3/s720x720/579741_10150738195410900_2058182645_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-sh3/s720x720/579741_10150738195410900_2058182645_n.jpg",
					6));
			break;
		case 1400488155:
			// Rasto
			result.add(new Photo(
					"https://sphotos-a.xx.fbcdn.net/hphotos-prn1/s720x720/548757_10150931817966033_1983435846_n.jpg",
					"https://sphotos-a.xx.fbcdn.net/hphotos-prn1/s720x720/548757_10150931817966033_1983435846_n.jpg",
					10));
			result.add(new Photo(
					"https://sphotos-a.xx.fbcdn.net/hphotos-ash3/s720x720/564974_3978955158017_1383444447_n.jpg",
					"https://sphotos-a.xx.fbcdn.net/hphotos-ash3/s720x720/564974_3978955158017_1383444447_n.jpg",
					9));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc7/s720x720/574985_3853784028673_1314807056_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc7/s720x720/574985_3853784028673_1314807056_n.jpg",
					8));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc6/230467_2033984534823_6920097_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc6/230467_2033984534823_6920097_n.jpg",
					7));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-ash3/167500_1779719858365_4929716_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-ash3/167500_1779719858365_4929716_n.jpg",
					6));
			break;
		case 1618444494:
			// Mato
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc7/s720x720/398735_349854428368425_1205833371_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc7/s720x720/398735_349854428368425_1205833371_n.jpg",
					10));
			result.add(new Photo(
					"https://sphotos-a.xx.fbcdn.net/hphotos-snc7/s720x720/391098_2071852486972_64144002_n.jpg",
					"https://sphotos-a.xx.fbcdn.net/hphotos-snc7/s720x720/391098_2071852486972_64144002_n.jpg",
					9));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc6/184642_1719767326855_5104629_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc6/184642_1719767326855_5104629_n.jpg",
					8));
			result.add(new Photo(
					"https://sphotos-a.xx.fbcdn.net/hphotos-ash4/s720x720/293690_296332423727661_487537545_n.jpg",
					"https://sphotos-a.xx.fbcdn.net/hphotos-ash4/s720x720/293690_296332423727661_487537545_n.jpg",
					7));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-prn1/164816_1632540866248_1706376_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-prn1/164816_1632540866248_1706376_n.jpg",
					6));
			break;
		default:
			result = null;
		}
		return result;
	}
	
	public Vector<Photo> getPhotosUploaded(int userID) {
		Vector<Photo> result = new Vector<Photo>();
		switch (userID) {
		case 1598236292:
			// Tuan
			result.add(new Photo(
					"https://sphotos-a.xx.fbcdn.net/hphotos-ash3/s720x720/540296_10150739047043100_139141130_n.jpg",
					"https://sphotos-a.xx.fbcdn.net/hphotos-ash3/s720x720/540296_10150739047043100_139141130_n.jpg",
					10));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-ash3/550871_10151771585210374_403327050_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-ash3/550871_10151771585210374_403327050_n.jpg",
					9));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc7/390677_10150466982918100_310693358_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc7/390677_10150466982918100_310693358_n.jpg",
					8));
			result.add(new Photo(
					"https://sphotos-a.xx.fbcdn.net/hphotos-sh3/s720x720/544809_10150918274549931_1085336623_n.jpg",
					"https://sphotos-a.xx.fbcdn.net/hphotos-sh3/s720x720/544809_10150918274549931_1085336623_n.jpg",
					7));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-sh3/s720x720/579741_10150738195410900_2058182645_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-sh3/s720x720/579741_10150738195410900_2058182645_n.jpg",
					6));
			break;
		case 1400488155:
			// Rasto
			result.add(new Photo(
					"https://sphotos-a.xx.fbcdn.net/hphotos-prn1/s720x720/548757_10150931817966033_1983435846_n.jpg",
					"https://sphotos-a.xx.fbcdn.net/hphotos-prn1/s720x720/548757_10150931817966033_1983435846_n.jpg",
					10));
			result.add(new Photo(
					"https://sphotos-a.xx.fbcdn.net/hphotos-ash3/s720x720/564974_3978955158017_1383444447_n.jpg",
					"https://sphotos-a.xx.fbcdn.net/hphotos-ash3/s720x720/564974_3978955158017_1383444447_n.jpg",
					9));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc7/s720x720/574985_3853784028673_1314807056_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc7/s720x720/574985_3853784028673_1314807056_n.jpg",
					8));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc6/230467_2033984534823_6920097_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc6/230467_2033984534823_6920097_n.jpg",
					7));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-ash3/167500_1779719858365_4929716_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-ash3/167500_1779719858365_4929716_n.jpg",
					6));
			break;
		case 1618444494:
			// Mato
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc7/s720x720/398735_349854428368425_1205833371_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc7/s720x720/398735_349854428368425_1205833371_n.jpg",
					10));
			result.add(new Photo(
					"https://sphotos-a.xx.fbcdn.net/hphotos-snc7/s720x720/391098_2071852486972_64144002_n.jpg",
					"https://sphotos-a.xx.fbcdn.net/hphotos-snc7/s720x720/391098_2071852486972_64144002_n.jpg",
					9));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc6/184642_1719767326855_5104629_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-snc6/184642_1719767326855_5104629_n.jpg",
					8));
			result.add(new Photo(
					"https://sphotos-a.xx.fbcdn.net/hphotos-ash4/s720x720/293690_296332423727661_487537545_n.jpg",
					"https://sphotos-a.xx.fbcdn.net/hphotos-ash4/s720x720/293690_296332423727661_487537545_n.jpg",
					7));
			result.add(new Photo(
					"https://sphotos-b.xx.fbcdn.net/hphotos-prn1/164816_1632540866248_1706376_n.jpg",
					"https://sphotos-b.xx.fbcdn.net/hphotos-prn1/164816_1632540866248_1706376_n.jpg",
					6));
			break;
		default:
			result = null;
		}
		return result;
	}
	
	public Vector<Photo> getPhotosStarred(int userID) {
		// TODO
		return null;
	}
	
	public Vector<Link> getLinks(int userID) {
		Vector<Link> result = new Vector<Link>();
		for (int i = 0; i < 5; i++)
			result.add(new Link(userID + "'s status " + i, 10 - i));
		return result;
	}

	public Vector<Friend> getFriends(int userID) {
		Vector<Friend> result = new Vector<Friend>();
		result.add(new Friend(1598236292, "Tuan Anh"));
		result.add(new Friend(1400488155, "Rasto"));
		result.add(new Friend(1618444494, "Mato"));		
		return result;
	}

}
