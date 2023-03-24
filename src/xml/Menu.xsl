<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>Liste Menu</title>
            </head>
            <body>
                <table border="1" cellspacing="0"
                    cellpadding="3">
                    <tr
                        bgcolor="#34c924">
                        <td>Entree</td>
                        <td>Plat</td>
                        <td>Dessert</td>
                        <td>Prix</td>
                    </tr>
                    <xsl:for-each select="menus/menu">
                        <tr>
                            <td><xsl:value-of select="entree/nom"/></td>
                            <td><xsl:value-of select="plat/nom"/></td>
                            <td><xsl:value-of select="dessert/nom"/></td>
                            <td><xsl:value-of select="@prix"/></td>>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
    
</xsl:stylesheet>