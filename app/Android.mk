LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := libgl2jni
LOCAL_LDFLAGS := -Wl,--build-id
LOCAL_LDLIBS := \
	-llog \
	-lGLESv2 \

LOCAL_SRC_FILES := \
	/Users/geoffday/Documents/CallingCard/callingcard-android/app/src/main/jni/gl_code.cpp \

LOCAL_C_INCLUDES += /Users/geoffday/Documents/CallingCard/callingcard-android/app/src/main/jni
LOCAL_C_INCLUDES += /Users/geoffday/Documents/CallingCard/callingcard-android/app/src/debug/jni

include $(BUILD_SHARED_LIBRARY)
