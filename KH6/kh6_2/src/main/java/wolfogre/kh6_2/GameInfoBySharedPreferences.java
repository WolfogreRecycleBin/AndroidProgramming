package wolfogre.kh6_2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jason Song(wolfogre@outlook.com) on 02/21/2016.
 */
public class GameInfoBySharedPreferences {
	static Context context;
	static SharedPreferences sp;
	static SharedPreferences.Editor spe;

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
		Toast.makeText(context, "Error!", Toast.LENGTH_LONG).show();
		return "";
	}




	static void setContext(Context context){
		GameInfoBySharedPreferences.context = context;
		sp = PreferenceManager.getDefaultSharedPreferences(context);
		spe = sp.edit();
		spe.apply();
	}

	static String[] getGameNames(){
		Set<String> stringSet = sp.getStringSet(getKey(DateType.GAME_NAMES, null, null, null, null), new HashSet<String>());
		return stringSet.toArray(new String[stringSet.size()]);
	}

	static void addGameName(String newGameName){
		Set<String> stringSet = sp.getStringSet(getKey(DateType.GAME_NAMES, null, null, null, null), new HashSet<String>());
		stringSet.add(newGameName);
		if(stringSet.size() == 1){
			spe.putStringSet(getKey(DateType.GAME_NAMES, null, null, null, null), stringSet);
			spe.apply();
		}
	}

	static Integer getGroupNumber(String gameName) {
		String key = getKey(DateType.GROUP_NUMBER, gameName, null, null, null);
		return sp.getInt(key, 0);
	}

	static void setGroupNumber(String gameName, int groupNumber) {
		String key = getKey(DateType.GROUP_NUMBER, gameName, null, null, null);
		spe.putInt(key, groupNumber);
		spe.apply();
	}

	static Integer getMaxPeopleNumber(String gameName) {
		String key = getKey(DateType.MAX_PEOPLE_NUMBER, gameName, null, null, null);
		return sp.getInt(key, 0);
	}

	static void setMaxPeopleNumber(String gameName, int maxPeopleNumber) {
		String key = getKey(DateType.MAX_PEOPLE_NUMBER, gameName, null, null, null);
		spe.putInt(key, maxPeopleNumber);
		spe.apply();
	}

	static String[] getPeopleNames(String gameName, int groupId){
		String key = getKey(DateType.PEOPLE_NAMES, gameName, groupId, null, null);
		Set<String> stringSet = sp.getStringSet(key, new HashSet<String>());
		return stringSet.toArray(new String[stringSet.size()]);
	}

	static void addPeopleName(String gameName, int groupId, String newPeopleName){
		String key = getKey(DateType.PEOPLE_NAMES, gameName, groupId, null, null);
		Set<String> stringSet = sp.getStringSet(key, new HashSet<String>());
		stringSet.add(newPeopleName);
		if(stringSet.size() == 1){
			spe.putStringSet(key, stringSet);
			spe.apply();
		}
	}

	static String[] getOpponentNames(String gameName, int groupId, String selfName){
		String key = getKey(DateType.PEOPLE_NAMES, gameName, groupId, null, null);
		Set<String> stringSet = sp.getStringSet(key, new HashSet<String>());
		HashSet<String> stringHashSet = new HashSet<String>();
		stringHashSet.addAll(stringSet);
		stringHashSet.remove(selfName);
		return stringHashSet.toArray(new String[stringHashSet.size()]);
	}

	static String getGrade(String gameName, int groupId, String selfName, String opponentGrade) {
		String key = getKey(DateType.GRADE, gameName, groupId, selfName, opponentGrade);
		return sp.getString(key, "0:0");
	}

	static void setGrade(String gameName, int groupId, String selfName, String opponentGrade, String newGrade) {
		String key = getKey(DateType.GRADE, gameName, groupId, selfName, opponentGrade);
		spe.putString(key, newGrade);
		spe.apply();
	}
}
