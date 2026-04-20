#!/bin/bash
opc=0
while [ $opc -ne 5 ]
do
clear
echo "MENU"
echo "1- Crear directorio"
echo "2- Borrar directorio"
echo "3- Crear fichero"
echo "4- Borrar fichero"
echo "5- Salir"

echo "Elige una opcion: "
read opc
echo
echo
case $opc in
1) echo -n  "Como quieres que se llame: "
read nombre
echo "Creando directorio $nombre ..."
if [ -d $nombre ]
then 
echo "ERROR:Ya existe ese directorio"
else
mkdir $nombre
fi;;
2) echo -n "Que directorio quieres borrar: "
read directorio
 echo "Borrando directorio $directorio "
if [ -d $directorio ]
then
rm -r $directorio
else
 echo "ERROR:No existe tal directorio"
fi;;
3) echo -n "Como quieres que se llame: "
read fichero
echo  "Creando fichero $fichero..."
if [ -f $fichero ]
then
echo "ERROR:Ya existe un fichero con ese nombre"
else
touch $fichero
fi;;
4) echo "Que fichero quieres borrar:"
read fichero
echo "Borrando $fichero..."
if [ -f $fichero ]
then
rm $fichero
else 
echo "ERROR:No puedo borrar un fichero que no existe"
fi;;
5)clear;;
*) echo "Elige una opcion entre 1-5"
echo "Pulsa enter para continuar"
read tecla;;
esac
done