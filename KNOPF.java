
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
    boolean klick;
    public KNOPF(double x, double y, String inhalt)
    {
        //registriereMausKlickReagierbar( this );
        rechteck = new RECHTECK(5,2);
        rechteck.setzeMittelpunkt(x, y);
        text = new TEXT(x,y,1,inhalt);
        
    }
    public void setzeMittelpunkt(int x, int y){
        rechteck.setzeMittelpunkt(x, y);
        text.setzeMittelpunkt(x, y);
    }
    
}
