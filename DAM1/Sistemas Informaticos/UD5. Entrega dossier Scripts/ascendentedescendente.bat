#!/bin/bash
P=$1
shift

for palabra in "$@";
 do
    echo "$palabra"
done |
 if [ "$P" == "ASC" ];
 then
    sort
else
    sort -r
fi