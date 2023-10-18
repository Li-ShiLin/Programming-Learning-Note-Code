#!/bin/bash
if [ "$1"x = "root"x ]
then
      echo "welcome,root"
fi
# 输入第二个参数，判断属于哪个年龄段
if [ $2 -lt 18 ]
then
      echo "未成年人"
elif [ $2 -lt 35 ]
then
    echo "青年人"
elif [ $2 -lt 60 ]
then
    echo "中年人"
else
    echo "老年人"
fi


