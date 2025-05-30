

public class HüpfUndRenne extends SPIEL
{
    SPIEL spiel;
    Level1 level;
    //FIGUR spielfigur;
    KREIS spielfigur;
    RECHTECK boden;
    /**
     * Konstruktor für Objekte der Klasse HüpfUndRenne
     */
    public HüpfUndRenne()
    {
        //spiel= new SPIEL(1100, 700, true);
        //level=new Level1();
        //spielfigur = new FIGUR("muss noch ein bild eingefügt werden");
        boden=new RECHTECK(40,1);
        boden.setzeMittelpunkt(0, -5);
        boden.machePassiv();
        spielfigur=new KREIS(0.3);
        spielfigur.macheAktiv();
        //starteTickerNeu(0.02);
        //bildAktualisierungReagieren(0.02);
    }
    public void tick(){
        if(istTasteGedrueckt(39)==true){
             spielfigur.verschieben(0.1, 0);
        }
        if(istTasteGedrueckt(37)==true){
            spielfigur.verschieben(-0.1, 0);
        }
    }
    public void bildAktualisierungReagieren(){
        if(istTasteGedrueckt(39)==true){
             spielfigur.verschieben(0.1, 0);
        }
        if(istTasteGedrueckt(37)==true){
            spielfigur.verschieben(-0.1, 0);
        }
        
    }
    public void tasteReagieren( int taste ) 
    {
        if(taste==38){
            spielfigur.springe(7);
            
        }
        
        
        
        
    }

    
}
