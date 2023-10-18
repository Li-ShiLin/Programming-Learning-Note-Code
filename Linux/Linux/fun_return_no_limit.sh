#!/bin/bash
function add(){
     s=$[$1 + $2]
     echo $s
}

read -p "请输入第一个参数: " a
read -p "请输入第二个参数: " b

sum=$(add $a $b)
echo $[$sum * $sum] 

