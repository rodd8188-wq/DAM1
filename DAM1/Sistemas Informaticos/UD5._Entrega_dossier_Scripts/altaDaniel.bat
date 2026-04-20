#!/bin/bash
salir=0
while [ salir -eq 0 ]
do
clear
echo "1.Dar de alta a un empleado"
echo "2.Dar de baja a un empleado"
echo "3.Modificar empleado"
echo "4.Bloquear empleado"
echo "5.Desbloquear empleado"
echo "6.Salir"

echo "Elige una opción"
read opc

case $opc in
1)echo "¿Que usuario quieres añadir?"
read Nombre
sudo adduser "$Nombre";;
2)echo "¿Qué usuario quieres borrar?"
read Nombre
sudo deluser "$Nombre";;
3)echo "¿Qué usuario quieres modificar el nombre?"
read Nombreviejo
echo "Cual es el nuevo nombre"
read NombreNuevo
sudo usermod -l "$NombreNuevo" "$Nombreviejo";;
4)echo "¿Qué usuario quieres bloquear?"
read Nombre
sudo usermod -L "$Nombre";;
5)echo "¿Qué usuario quieres desbloquear?"
read Nombre
sudo usermod -U "$Nombre";;
6)salir=1;;
*) echo "Introduce un número válido"
echo "";;

esac
done