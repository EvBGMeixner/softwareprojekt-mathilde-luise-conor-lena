
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
    public double nenneMx(){
        return(rechteck.nenneMx());
    }
    public double nenneMy(){
        return(rechteck.nenneMy());
    }
    public double nenneTransparenz(){
        return(rechteck.nenneTransparenz());
    }
    public void setzeTransparenz(double x){
        rechteck.setzeTransparenz(x);
    }
    public void setzeInhalt(String x){
        text.setzeInhalt(x);
    }
    public void setzeAlles(int i, int x, int y){
        text.setzeInhalt(i);
        setzeMittelpunkt(x, y);
    }
    public void setzeAlles(String i, int x, int y){
        text.setzeInhalt(i);
        setzeMittelpunkt(x, y);
    }
    public boolean klick(double x, double y){
        if(x<nenneMx()+rechteck.nenneBreite()&&x>nenneMx()-rechteck.nenneBreite()&&
            x<nenneMy()+rechteck.nenneHoehe()&&x>nenneMy()-rechteck.nenneHoehe()){
                return true;
            }
        else{
            return false;
        }
    }
}
