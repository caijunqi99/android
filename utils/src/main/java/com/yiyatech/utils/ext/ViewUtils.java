package com.yiyatech.utils.ext;

import android.app.Activity;
import android.view.View;

/**
 * 不需要ViewInject，简化你的findViewById
 * 
 * @author heweiquan
 *
 */
public class ViewUtils {
	@SuppressWarnings("unchecked")
	public static <T extends View> T f(Activity context, int id) {
		return (T) context.findViewById(id);
	}

	@SuppressWarnings("unchecked")
	public static <T extends View> T f(View rootView, int id) {
		return (T) rootView.findViewById(id);
	}
}
