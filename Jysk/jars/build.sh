#!/bin/sh
javac -d ../bin jni/*.java jysk/*.java services/*.java tier1/*.java tier2/*.java tier3/*.java
javah -d jni/ jni.NativeMethods
i686-w64-mingw32-gcc -D__int64="long long" -I'c:/Progra~1/Java/jdk1.7.0_45/include' -I'c:/Progra~1/Java/jdk1.7.0_45/include/win32' -Wl,--add-stdcall-alias -shared -o c:/eclipse/Workspaces/SDJI3Workspace/Jysk/src/jni/NativeLib.dll c:/eclipse/Workspaces/SDJI3Workspace/Jysk/src/jni/sum.c c:/eclipse/Workspaces/SDJI3Workspace/Jysk/src/jni/NativeMethods.c

