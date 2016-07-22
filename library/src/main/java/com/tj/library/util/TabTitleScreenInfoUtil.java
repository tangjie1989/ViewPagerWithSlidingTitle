package com.tj.library.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class TabTitleScreenInfoUtil {

	private static DisplayMetrics getDisplayMetrics(Context ctx){
		Resources re = ctx.getResources();
		return re.getDisplayMetrics();
	}
	
	public static int getScreenWidth(Context ctx){
        return getDisplayMetrics(ctx).widthPixels;
	}
}
