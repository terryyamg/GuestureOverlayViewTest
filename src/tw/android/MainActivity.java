package tw.android;

import java.util.ArrayList;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity implements
		OnGesturePerformedListener {

	private GestureOverlayView gesture;
	private GestureLibrary gLibrary;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		gesture = (GestureOverlayView) findViewById(R.id.gestureOverlayView1);

		// GestureOverlayView監聽事件
		gesture.addOnGesturePerformedListener(this);

		// 讀取製作的gestures檔案
		gLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
		gLibrary.load();

	}

	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
		
		ArrayList<Prediction> predictions = gLibrary.recognize(gesture);

		if (predictions.size() > 0) {

			Prediction prediction = predictions.get(0);
			Log.i("1111111", predictions.get(1) + "");
			if (prediction.score > 1.5) {// 符合 顯示對應訊息
				Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT).show();
			} else { // 不符合
				Toast.makeText(this, "無法識別", Toast.LENGTH_SHORT).show();
			}
		}
	}
}