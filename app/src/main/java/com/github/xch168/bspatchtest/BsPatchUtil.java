package com.github.xch168.bspatchtest;

/**
 * Created by XuCanHui on 2018/8/12.
 */
public class BsPatchUtil {

    static {
        System.load("bspatch");
    }

    public static native int patch(String oldApk, String newApk, String patch);

}
