
/**
 * Beschreiben Sie hier die Klasse Knopf.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class KNOPF
{
    RECHTECK rechteck;
    TEXT text;
    public KNOPF(double x, double y, String inhalt)
    {
        rechteck = new RECHTECK(5,2);
        rechteck.setzeMittelpunkt(x, y);
        text = new TEXT(x,y,1,inhalt);
    }

}
