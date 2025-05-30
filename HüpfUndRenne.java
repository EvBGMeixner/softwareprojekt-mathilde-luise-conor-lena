

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
        //starteTickerNeu(0.05);
    }
    public void tick(){
        if(istTasteGedrueckt(39)==true){
             spielfigur.verschieben(0.05, 0);
        }
        if(istTasteGedrueckt(37)==true){
            spielfigur.verschieben(-0.05, 0);
        }
    }
    
    public void tasteReagieren( int taste ) 
    {
        if(taste==38){
            spielfigur.springe(7);
            
        }
        if(taste==39){
            starteTickerNeu(0.05);
            
        }
        
        }
    
    public void tasteLosgelassenReagieren(int taste){
        if(taste==39){
            stoppeTicker();
            
        }
    }

    
}
