package com.example.bhavesh.taskmanager.base;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

public class ActivityManager {

    private static List<Activity> sActivityList = new LinkedList<>();

    /**
     * 向栈中压入指定Activity
     * @param activity
     */
    public static void addActivityToTask(Activity activity) {
        sActivityList.add(activity);
    }

    /**
     * 从栈中移除指定Activity
     * @param activity
     */
    public static void removeActivityToTask(Activity activity) {
        sActivityList.remove(activity);
    }

    /**
     * @return 返回栈顶Activity
     */
    public static Activity getTaskTopActivity() {
        return sActivityList.get(sActivityList.size() - 1);
    }
}