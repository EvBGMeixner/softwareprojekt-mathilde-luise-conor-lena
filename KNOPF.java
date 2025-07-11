
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
        
        rechteck = new RECHTECK(8,3);
        rechteck.setzeMittelpunkt(x, y);
        rechteck.setzeFarbe("grün");
        text = new TEXT(x,y,1,inhalt);
        
    }
    public void setzeMittelpunkt(double x, double y){
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
    public void setzeAlles(int i, double x, double y){
        text.setzeInhalt(i);
        setzeMittelpunkt(x, y);
    }
    public void setzeAlles(String i, double x, double y){
        text.setzeInhalt(i);
        setzeMittelpunkt(x, y);
    }
    public boolean klick(double x, double y){
        if(x<nenneMx()+rechteck.nenneBreite()/2&&x>nenneMx()-rechteck.nenneBreite()/2&&
            y<nenneMy()+rechteck.nenneHoehe()/2&&y>nenneMy()-rechteck.nenneHoehe()/2){
            return true;
        }
        else{
            return false;
        }
    }
    public void setzeSichtbar(boolean x){
        rechteck.setzeSichtbar(x);
        text.setzeSichtbar(x);
    }
}
