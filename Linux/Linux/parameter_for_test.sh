#!/bin/bash
# 测试$*
echo '========$*======='
for para in $*
do
   echo $para
done

# 测试$@
echo '========$@======='
for para in $@
do
   echo $para
done







