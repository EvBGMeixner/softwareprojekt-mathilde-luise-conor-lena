
/**
 * Beschreiben Sie hier die Klasse probe.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class probe extends SPIEL
{
    RECHTECK rechteck;
    RECHTECK a;
    
    public probe()
    {
        rechteck= new RECHTECK(5,5);
        rechteck.setzeMittelpunkt(0, 0);
        //setzeHintergrundgrafik("extensions2/Bild");
        a=new RECHTECK(5,5);
        a.setzeEbenenposition(-12);
        a.setzeMittelpunkt(0, 0);
    }
    public void a(){
        if(rechteck.beruehrt(a)){
            System.out.println(true);
        }
        else{
        System.out.println(false);
        }
    }
}
