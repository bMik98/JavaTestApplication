<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" indent="yes"/>

<xsl:template match="/">

<xsl:text>
</xsl:text>
<entries>
<xsl:for-each select="entries/entry">
    <xsl:text>
    </xsl:text>	
    <xsl:element name = "entry"> 
        <xsl:attribute name="field">
            <xsl:value-of select="field"/>
        </xsl:attribute> 
    </xsl:element>
</xsl:for-each>
</entries>

</xsl:template>
</xsl:stylesheet>
