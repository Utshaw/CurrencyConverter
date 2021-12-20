#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_eg_android_currencyconverterfinal_viewmodel_MainActivityViewModel_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string key1 = "2b3c5c4886b24f13b8196289f93adf50";
    return env->NewStringUTF(key1.c_str());
}



extern "C" JNIEXPORT jstring JNICALL
Java_com_eg_android_currencyconverterfinal_viewmodel_MainActivityViewModel_stringFromJNI2(
        JNIEnv* env,
        jobject /* this */) {
    std::string key1 = "59e54773e161aae488e031f2d1fa1063";
    return env->NewStringUTF(key1.c_str());
}
