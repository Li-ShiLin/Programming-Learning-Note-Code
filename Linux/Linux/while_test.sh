#!/bin/bash
#方法一
a=1
while [ $a -le $1 ]
do
   sum=$[ $sum + $a]
   a=$[$a + 1]
done
echo $sum

#方法二
b=1
while [ $b -le $1 ]
do
    let sum2+=b
    let b++
done
echo $sum2

