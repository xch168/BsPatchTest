# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

include_directories(
        ./src/main/cpp
        ./src/main/cpp/bzip2
)

aux_source_directory(./src/main/cpp SRC)
aux_source_directory(./src/main/cpp/bzip2 SRC_OTHER)
# 将 SRC_OTHER 添加到 SRC 中
list(APPEND SRC ${SRC_OTHER})

add_library( bspatch
             SHARED
             ${SRC})

find_library( log-lib
              log )

# 添加头文件路径
#include_directories(src/main/jniLibs/)


target_link_libraries( bspatch
                       ${log-lib} )