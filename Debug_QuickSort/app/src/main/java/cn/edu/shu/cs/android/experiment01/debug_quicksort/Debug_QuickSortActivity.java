package cn.edu.shu.cs.android.experiment01.debug_quicksort;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Debug_QuickSortActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        QuickSort ms=new QuickSort();
        ms.StartQs();
    }
    
 
    

}