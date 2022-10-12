package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TransFileReader {


//------------------------Create dynamic link to the File--------------------------

	public String getLanguageFile(String language, String section) {

		String file = System.getProperty("user.dir") + File.separator + "resources" + File.separator + language
				+ File.separator + section + ".txt";
		return file;
	}

//-------------------------------------Read Data From File-------------------------------------

	public List getDataFromFile(String language, String section) {
		try {
			List<String> li = new ArrayList<String>();
			FileReader fr = new FileReader(getLanguageFile(language, section),StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(fr);
			while (true) {
				String value = br.readLine();
				if (value.equals("end"))
					break;
				li.add(value);
			}
			return li;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

//------------------------Returning Section Data from file ---------------------------------------

	public List<String> getHeaderDataFromFile(String language, String section) {

		return getDataFromFile(language, section);

	}

	public List<String> getFooterDataFromFile(String language, String section) {

		return getDataFromFile(language, section);

	}

}
