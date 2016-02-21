package wolfogre.kh8_1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Jason Song(wolfogre@outlook.com) on 02/22/2016.
 */
public class GameInfoByJson {

	static JSONObject jsonObject;
	static final File file = new File(Environment.getExternalStorageDirectory().getPath() + "/kh8_1.txt");

	static {
		try {
			FileInputStream inputStream;
			if(!file.exists()){
				if(!file.createNewFile())
					throw new IOException("新建JSON文件失败");
			}
			inputStream = new FileInputStream(file);
			Scanner scanner = new Scanner(inputStream);
			StringBuilder stringBuilder = new StringBuilder();

			while(scanner.hasNextLine()){
				stringBuilder.append(scanner.nextLine());
			}
			scanner.close();

			jsonObject = new JSONObject(stringBuilder.toString());
		} catch (IOException | JSONException e) {
			jsonObject = new JSONObject();
			e.printStackTrace();
		}
	}

	private enum DateType{
		GAME_NAMES,
		GAME_TIME,
		GROUP_NUMBER,
		MAX_PEOPLE_NUMBER,
		PEOPLE_NAMES,
		GRADE
	}

	static private String getKey(DateType dateType, String gameName, Integer groupId, String selfName, String opponentGrade){
		switch (dateType){
			case GAME_NAMES:
				return dateType.toString() + "#";
			case GAME_TIME:
				return dateType.toString() + "#" + gameName + "#";
			case GROUP_NUMBER:
				return dateType.toString() + "#" + gameName + "#";
			case MAX_PEOPLE_NUMBER:
				return dateType.toString() + "#" + gameName + "#";
			case PEOPLE_NAMES:
				return dateType.toString() + "#" + gameName + "#" + groupId + "#";
			case GRADE:
				return dateType.toString() + "#" + gameName + "#" + groupId + "#" + selfName + "#" + opponentGrade;
		}
		return "";
	}

	static private void commit(){
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(jsonObject.toString());
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String[] getGameNames(){
		String key = getKey(DateType.GAME_NAMES, null, null, null, null);
		JSONArray jsonArray = jsonObject.optJSONArray(key);
		if(jsonArray == null)
			jsonArray = new JSONArray();

		String[] result = new String[jsonArray.length()];
		for(int i = 0; i < jsonArray.length(); ++i){
			result[i] = jsonArray.optString(i);
		}
		return result;
	}

	public static void addGameName(String newGameName){
		try {
			String key = getKey(DateType.GAME_NAMES, null, null, null, null);
			String[] oldStrings = getGameNames();
			String[] newStrings = new String[oldStrings.length + 1];
			System.arraycopy(oldStrings, 0, newStrings, 0, oldStrings.length);
			newStrings[newStrings.length - 1] = newGameName;

			JSONArray jsonArray = new JSONArray();
			for(int i = 0; i < newStrings.length; ++i)
				jsonArray.put(i, newStrings[i]);
			jsonObject.put(key, jsonArray);
			commit();

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static Integer getGroupNumber(String gameName) {
		String key = getKey(DateType.GROUP_NUMBER, gameName, null, null, null);
		return jsonObject.optInt(key);
	}

	public static void setGroupNumber(String gameName, int groupNumber) {
		String key = getKey(DateType.GROUP_NUMBER, gameName, null, null, null);
		try {
			jsonObject.put(key, groupNumber);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static Integer getMaxPeopleNumber(String gameName) {
		String key = getKey(DateType.MAX_PEOPLE_NUMBER, gameName, null, null, null);
		return jsonObject.optInt(key);
	}

	public static void setMaxPeopleNumber(String gameName, int maxPeopleNumber) {
		String key = getKey(DateType.MAX_PEOPLE_NUMBER, gameName, null, null, null);
		try {
			jsonObject.put(key, maxPeopleNumber);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static String[] getPeopleNames(String gameName, int groupId){
		String key = getKey(DateType.PEOPLE_NAMES, gameName, groupId, null, null);
		JSONArray jsonArray = jsonObject.optJSONArray(key);
		if(jsonArray == null)
			jsonArray = new JSONArray();

		String[] result = new String[jsonArray.length()];
		for(int i = 0; i < jsonArray.length(); ++i){
			result[i] = jsonArray.optString(i);
		}
		return result;
	}

	public static void addPeopleName(String gameName, int groupId, String newPeopleName){
		try {
			String key = getKey(DateType.PEOPLE_NAMES, gameName, groupId, null, null);
			String[] oldStrings = getPeopleNames(gameName, groupId);
			String[] newStrings = new String[oldStrings.length + 1];
			System.arraycopy(oldStrings, 0, newStrings, 0, oldStrings.length);
			newStrings[newStrings.length - 1] = newPeopleName;

			JSONArray jsonArray = new JSONArray();
			for(int i = 0; i < newStrings.length; ++i)
				jsonArray.put(i, newStrings[i]);
			jsonObject.put(key, jsonArray);
			commit();

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static String[] getOpponentNames(String gameName, int groupId, String selfName){
		HashSet<String> stringHashSet = new HashSet<>();
		Collections.addAll(stringHashSet, getPeopleNames(gameName, groupId));
		stringHashSet.remove(selfName);
		return stringHashSet.toArray(new String[stringHashSet.size()]);
	}

	public static String getGrade(String gameName, int groupId, String selfName, String opponentGrade) {
		String key = getKey(DateType.GRADE, gameName, groupId, selfName, opponentGrade);
		return jsonObject.optString(key, "0:0");
	}

	public static void setGrade(String gameName, int groupId, String selfName, String opponentGrade, String newGrade) {
		String key = getKey(DateType.GRADE, gameName, groupId, selfName, opponentGrade);
		try {
			jsonObject.put(key, newGrade);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
