#include <stdio.h>
#include <jni.h>
#include <stdlib.h>
#include "getdns/getdns.h"

#define CHECK_NULL_INIT_PTR(javaObj, context) if(NULL != javaObj) context = (struct getdns_context*) (*env)->GetDirectBufferAddress(env, javaObj);
#define CHECK_NULL_AND_INIT_STR(str, nativeStr) if(NULL != str) nativeStr = (*env)->GetStringUTFChars(env, str, 0);
#define CHECK_NULL_AND_CONVERT_TO_JAVA_STRING(str, object) if(NULL != str) object = (*env)->NewStringUTF(env, str);

struct util_methods {
	jclass listClass;
	jmethodID listInit;
	jmethodID listAdd;
	jmethodID listGetIterator;
	jclass mapClass;
	jmethodID mapInit;
	jmethodID mapPut;
	jmethodID mapGetEntrySet;
	jclass setClass;
	jmethodID entrySetGetIterator;
	jclass iteratorClass;
	jmethodID hasNext;
	jmethodID next;
	jclass entryClass;
	jmethodID entryGetKey;
	jmethodID entryGetValue;
	jclass integerClass;
	jmethodID integerInit;
	jmethodID intValue;
	jclass callbackClass;
	jmethodID callbackHandleResponse;
	jclass objectClass;
	jclass stringClass;
	jclass booleanClass;
	jmethodID booleanValue;
	jclass arrayClass;
};

struct util_methods*
init_util_methods(JNIEnv* env, struct util_methods*);

int
throwExceptionOnErrorWithMessage(JNIEnv *env, char* message,
		getdns_return_t ret);

int
throwExceptionOnError(JNIEnv *env, getdns_return_t ret);

void
throwJavaIssue(JNIEnv *env, char *message);

unsigned char*
convertByteArrayToUnsignedCharArray(JNIEnv *env, jbyteArray array, int* len);

jbyteArray
convertUnsignedCharArrayToByteArray(JNIEnv *env, unsigned char* data, int len);

void
getStringFromArray(JNIEnv *env, jobjectArray value, struct util_methods methods,
		const char **stringValue, int index);

void
getIntArrayFromArray(JNIEnv *env, jobjectArray value,
		struct util_methods methods, int *intValue[], int index);

void getDnsDict(JNIEnv *env, jobjectArray value, struct util_methods methods,
		struct getdns_dict **ipDict);
