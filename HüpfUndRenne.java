

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
        spiel= new SPIEL(1100, 700, true);
        //level=new Level1();
        //spielfigur = new FIGUR("muss noch ein bild eingefügt werden");
        spielfigur=new KREIS(0.3);
    }
    //@Override
    public void tasteReagieren( int taste ) 
    {
        if(taste==39){
            spielfigur.verschieben(0, 1);
        }
    }

    
}
