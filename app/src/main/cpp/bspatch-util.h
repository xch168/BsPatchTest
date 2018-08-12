//
// Created by 徐灿辉 on 2018/8/12.
//
#include <jni.h>
#include <malloc.h>
#include <string.h>
#include <stdio.h>
#include "bzip2/bzlib_private.h"

int executePatch(int argc, char *argv[]);

JNIEXPORT jint JNICALL
Java_com_github_xch168_bspatchtest_BsPatchUtil_patch(JNIEnv *env, jclass type, jstring oldApk_, jstring newApk_, jstring patch_);
