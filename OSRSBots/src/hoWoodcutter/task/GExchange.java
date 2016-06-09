package hoWoodcutter.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class GExchange {

	private static BufferedReader bReader;
	private static StringBuffer buffer;

	private static String getData(int itemID) {
		try {
			buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			URL url = new URL(
					"http://services.runescape.com/m=itemdb_oldschool/api/catalogue/detail.json?item=" + itemID);
			bReader = new BufferedReader(new InputStreamReader(url.openStream()));

			while ((read = bReader.read(chars)) != -1) {
				buffer.append(chars, 0, read);
			}

			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bReader != null) {
					bReader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static int getPrice(int itemID) {
		String[] priceInfo;
		String[] itemInfo = getData(itemID).split(",");
		
		try {
			priceInfo = itemInfo[8].replace("}", "").split(":");
			return Integer.parseInt(priceInfo[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}
