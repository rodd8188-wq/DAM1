<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html>
        <head>
            <meta charset="UTF-8"/>
            <title>Concesionario</title>
            <style>
                body { font-family: Arial; background: white; margin: 20px; }
                h1 { color: navy; }
                table { border-collapse: collapse; width: 100%; }
                th { background-color: navy; color: white; padding: 8px; text-align: left; }
                td { border: 1px solid black; padding: 6px; }
                tr:nth-child(even) { background-color: #dddddd; }
                .caro { color: red; font-weight: bold; }
                .electrico { background-color: lightgreen; }
            </style>
        </head>
        <body>
            <h1>Catalogo de coches</h1>
            <p>Hay <xsl:value-of select="count(//coche)"/> coches en total.</p>
            <br/>

            <table>
                <tr>
                    <th>Marca</th>
                    <th>Modelo</th>
                    <th>Año</th>
                    <th>Estado</th>
                    <th>Combustible</th>
                    <th>Motor</th>
                    <th>Precio</th>
                    <th>Equipamiento</th>
                </tr>

                <!-- REQUISITO 1: xsl:for-each para recorrer todos los coches -->
                <!-- REQUISITO 2: xsl:sort para ordenar por precio de mayor a menor -->
                <xsl:for-each select="concesionario/coche">
                    <xsl:sort select="number(precio)" order="descending" data-type="number"/>

                    <!-- REQUISITO 3: xsl:choose para dar clase al tr si es electrico -->
                    <xsl:variable name="claseFilaElectrico">
                        <xsl:choose>
                            <xsl:when test="motor/@combustible = 'electrico'">electrico</xsl:when>
                            <xsl:otherwise></xsl:otherwise>
                        </xsl:choose>
                    </xsl:variable>

                    <tr class="{$claseFilaElectrico}">
                        <td><xsl:value-of select="marca"/></td>
                        <td><xsl:value-of select="modelo"/></td>
                        <td><xsl:value-of select="@anio"/></td>
                        <td>
                            <!-- xsl:if para mostrar texto segun estado -->
                            <xsl:if test="@estado = 'nuevo'">Nuevo</xsl:if>
                            <xsl:if test="@estado = 'ocasion'">Segunda mano</xsl:if>
                        </td>
                        <td><xsl:value-of select="motor/@combustible"/></td>
                        <td><xsl:value-of select="motor"/></td>
                        <td>
                            <!-- xsl:choose para poner en rojo si precio mayor de 30000 -->
                            <xsl:choose>
                                <xsl:when test="number(precio) > 30000">
                                    <span class="caro"><xsl:value-of select="precio"/> EUR (caro!)</span>
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:value-of select="precio"/> EUR
                                </xsl:otherwise>
                            </xsl:choose>
                        </td>
                        <td>
                            <xsl:if test="equipamiento">
                                <xsl:value-of select="equipamiento"/>
                            </xsl:if>
                            <xsl:if test="not(equipamiento)">
                                -
                            </xsl:if>
                        </td>
                    </tr>
                </xsl:for-each>
            </table>

            <br/>
            <p>Coches nuevos: <xsl:value-of select="count(//coche[@estado='nuevo'])"/></p>
            <p>Coches de ocasion: <xsl:value-of select="count(//coche[@estado='ocasion'])"/></p>
        </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
