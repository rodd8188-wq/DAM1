#!/bin/bash
limit=10
for (( a=0 ; a<=limit ; a++ ))
do
    for (( b=0 ; b<=limit ; b++ ))
    do
        resultado=$(( a * b ))
        echo "$a * $b = $resultado"
    done
done