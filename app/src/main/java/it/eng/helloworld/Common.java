package it.eng.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * Created by academy on 06/09/2016.
 */

public class Common
{
    public final static String EXTRA_MESSAGE = "TEXT MESSAGE";

    public final static int GET_IMAGE = 1;

    public final static String EXTRA_FOTO = "FOTO";

    // controlla se esista uno (o piu') app che possano svolgere l'intent
    public static boolean isAnActivity(Context ctx, Intent intent)
    {
        PackageManager packageManager = ctx.getPackageManager();
        List activities = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        return (activities.size() > 0);
    }
}
