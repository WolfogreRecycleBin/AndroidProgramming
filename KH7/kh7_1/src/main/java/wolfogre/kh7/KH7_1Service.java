package wolfogre.kh7;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Jason Song(wolfogre@outlook.com) on 01/06/2016.
 */
public class KH7_1Service extends Service{
	public static final String ACTION = "wolfogre.kh7.KH7_1SERVICE_ACTION";
	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
