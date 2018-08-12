#include "bspatch-util.h"


JNIEXPORT jint JNICALL
Java_com_github_xch168_bspatchtest_BsPatchUtil_patch(JNIEnv *env, jclass type, jstring oldApk_, jstring newApk_, jstring patch_) {

    int argc = 4;
    char *argv[argc];
    argv[0] = "bspatch";
    argv[1] = (char*)((*env)->GetStringUTFChars(env, oldApk_, 0));
    argv[2] = (char*)((*env)->GetStringUTFChars(env, newApk_, 0));
    argv[3] = (char*)((*env)->GetStringUTFChars(env, patch_, 0));

    int ret = executePatch(argc, argv);

    (*env)->ReleaseStringUTFChars(env, oldApk_, argv[1]);
    (*env)->ReleaseStringUTFChars(env, newApk_, argv[2]);
    (*env)->ReleaseStringUTFChars(env, patch_, argv[3]);

    return ret;
}
