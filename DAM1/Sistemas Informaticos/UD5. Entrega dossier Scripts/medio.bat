#!/bin/bash

echo "Dime el directorio: " 
read dir
echo "Dime la palabra: " 
read palabra

if [ -d "$dir" ];
then

    cd "$dir"

    for archivo in *"$palabra"*;
   do
        if [ -f "$archivo" ];
then
            echo "Contenido de $archivo:"
            cat "$archivo"

            echo "¿Borrar? (s/n): "
		read opc
            if [ "$opc" == "s" ];
then
                rm "$archivo"
                if [ ! -f "$archivo" ];
then
                    echo "Borrado con éxito."
                fi
            fi
fi
    done
else
    echo "El directorio no existe. Fin del script."
fi