#!/bin/bash

if [ -f ficheroClase3 ]
then
        echo "ERROR:Ya existe este directorio"
else
touch ficheroClase1
echo "Fichero 'ficheroClase3' creado"
fi
        while [  $# -gt  0 ]
        do
                echo $1 >> 'ficheroClase3'
                shift
        done