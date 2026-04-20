#!/bin/bash
salir=0

while [ $salir -eq 0 ]
do
    clear
    
    echo "=========================================="
    echo "    MENU DE EJERCICIOS (Bucle Activo)"
    echo "=========================================="
    echo "1. Comparar 3 palabras (Igualdad)"
    echo "2. Filtrar líneas por vocal (Grep)"
    echo "3. Contador de directorios y archivos"
    echo "4. Salir"
    echo "=========================================="
    echo "Selecciona una opción:"
    read opc

    case $opc in
        1)
            echo "--- EJERCICIO 1 ---"
            echo "Introduce palabra 1:"
            read p1
            echo "Introduce palabra 2:"
            read p2
            echo "Introduce palabra 3:"
            read p3
            
            if [ "$p1" == "$p2" ] && [ "$p2" == "$p3" ]; then
                echo "Las tres son iguales"
            elif [ "$p1" == "$p2" ]; then
                echo "Son iguales primera y segunda"
            elif [ "$p1" == "$p3" ]; then
                echo "Son iguales primera y tercera"
            elif [ "$p2" == "$p3" ]; then
                echo "Son iguales segunda y tercera"
            else
                echo "Son las tres distintas"
            fi
           
            echo "Pulsa Enter para volver al menú..."
            read
            ;;

        2)
            echo "--- EJERCICIO 2 ---"
            echo "Archivo de entrada:"
            read ent
            echo "Archivo de salida:"
            read sal
            
            grep '^[AEIOUaeiou]' "$ent" > "$sal"
            echo "Líneas filtradas con éxito en $sal"
            
            echo "Pulsa Enter para volver al menú..."
            read
            ;;

        3)
            echo "--- EJERCICIO 3 ---"
            echo "Introduce las rutas separadas por espacio:"
            read lista
            
            c_dir=0
            c_arc=0
            
            for item in $lista; do
                if [ -d "$item" ]; then
                    c_dir=$((c_dir + 1))
                elif [ -f "$item" ]; then
                    c_arc=$((c_arc + 1))
                fi
            done
            
            echo "Resultados: $c_dir directorios y $c_arc archivos."
            echo "Pulsa Enter para volver al menú..."
            read
            ;;

        4)
            echo "Saliendo del programa..."
            salir=1
            ;;

        *)
            echo "Opción no válida."
            echo "Pulsa Enter para reintentar..."
            read
            ;;
    esac
done