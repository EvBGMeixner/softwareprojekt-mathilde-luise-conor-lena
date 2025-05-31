

public class HüpfUndRenne extends SPIEL
{
    SPIEL spiel;
    Level1 level;
    //FIGUR spielfigur;
    KREIS spielfigur;
    /**
     * Konstruktor für Objekte der Klasse HüpfUndRenne
     */
    public HüpfUndRenne()
    {
        
        level=new Level1();
        //spielfigur = new FIGUR("muss noch ein bild eingefügt werden");
        spielfigur=new KREIS(0.3);
        spielfigur.macheAktiv();
        bildAktualisierungReagieren(0.02);
        zeigeKoordinatensystem(true);
        
    }
    
    public void bildAktualisierungReagieren(double sekunden){
        if(istTasteGedrueckt(39)==true){
             spielfigur.verschieben(0.05, 0);
             verschiebeKamera(0.05, 0);
        }
        if(istTasteGedrueckt(37)==true){
            spielfigur.verschieben(-0.05, 0);
            verschiebeKamera(-0.05, 0);
        }
    }
    
    public void tasteReagieren(int taste){
        if(taste==38){
            spielfigur.springe(7);
            
        }
        }
    
    
}
