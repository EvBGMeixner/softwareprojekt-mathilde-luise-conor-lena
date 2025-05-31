

public class HüpfUndRenne extends SPIEL
{
    SPIEL spiel;
    int level;
    //FIGUR spielfigur;
    //KREIS spielfigur;
    RECHTECK spielfigur;
    RECHTECK gewinn;
    RECHTECK[] rechteck;
    RECHTECK[] rechteck2;//bei Berührung tot
    
    KREIS[] kreise;
    /**
     * Konstruktor für Objekte der Klasse HüpfUndRenne
     */
    public HüpfUndRenne()
    {
        
        //level=new Level1();
        //spielfigur = new FIGUR("muss noch ein bild eingefügt werden");
        //spielfigur=new KREIS(0.3);
        spielfigur=new RECHTECK(0.6,0.8);
        spielfigur.macheAktiv();
        spielfigur.setzeFarbe("grün");
        gewinn=new RECHTECK(1,1);
        gewinn.setzeFarbe("grün");
        
        rechteck=new RECHTECK[10];
        rechteck2=new RECHTECK[20];
        kreise = new KREIS[10];
        for(int i=0;i<rechteck.length;i++){
        rechteck[i]= new RECHTECK(1, 1);
        }
        for(int i=0;i<rechteck2.length;i++){
        rechteck2[i]=new RECHTECK(1, 1);
        rechteck2[i].setzeFarbe("Rot");
        }
        
        level=1;
        level1();
        
        bildAktualisierungReagieren(0.02);
        setzeKamerafokus(spielfigur);
        
        setzeSchwerkraft(12);
        
        zeigeKoordinatensystem(true);   
    }
    
    
    public void bildAktualisierungReagieren(double sekunden){
        //bewegen
        if(istTasteGedrueckt(39)==true){
             spielfigur.verschieben(0.05, 0);
        }
        if(istTasteGedrueckt(37)==true){
            spielfigur.verschieben(-0.05, 0);
        }
        //sterben
        for(int i=0;i<rechteck2.length;i++){
            if(spielfigur.beruehrt(rechteck2[i])){
            sterben();
        }
        }
        //gewinnen
        if(spielfigur.beruehrt(gewinn)){
            gewinnen();
        }
    }
    public void tasteReagieren(int taste){
        //springen
        if(taste==38){
            spielfigur.springe(8);
            
        }
        //ducken -> evtl nicht möglich mit bild als figur
        if(taste==40){
            spielfigur.setzeGroesse(0.6, 0.4);
        }
        }
    public void tasteLosgelassenReagieren(int taste){
        //aufrichten
        if(taste==40){
            spielfigur.setzeGroesse(0.6, 0.8);
        }
        }
    
    //sterben-> noch verschönern
    public void sterben(){
        if(level==1){
            level1();
        }
    }
    
    //gewinnen -> methode für level2
    public void gewinnen(){
        level++;
        if(level==2){
            level2();
        }
    }
    
        
    //level
    public void level1(){
        spielfigur.setzeMittelpunkt(0, 0);
        gewinn.setzeGroesse(1, 1);
        gewinn.setzeMittelpunkt(4, 4);
        //boden
        rechteck[0].setzeGroesse(20, 1);
        rechteck[0].setzeMittelpunkt(0, -7);
        //block um zu springen
        rechteck[1].setzeGroesse(5, 1);
        rechteck[1].setzeMittelpunkt(2, -6);
        //block um zu ducken
        rechteck[2].setzeGroesse(5, 10);
        rechteck[2].setzeMittelpunkt(4, -3);
        //block um an wand zu springen
        rechteck[3].setzeGroesse(5, 0.5);
        rechteck[3].setzeMittelpunkt(2, -6.25);
        
        //hindernisse(0-9)
        rechteck2[0].setzeGroesse(5, 0.4);
        rechteck2[0].setzeMittelpunkt(1, -6.25);
        //rahmen(10-20)
        rechteck2[10].setzeGroesse(70,0.1);
        rechteck2[10].setzeMittelpunkt(0, -20);
    }
    
    public void level2(){
        spielfigur.setzeMittelpunkt(0, 0);
        //boden
        rechteck[0].setzeGroesse(20, 1);
        rechteck[0].setzeMittelpunkt(0, -7);
        //2.stufe
        rechteck[1].setzeGroesse(5, 0.5);
        rechteck[1].setzeMittelpunkt(3, -5);
        //3.stufe
        rechteck[2].setzeGroesse(5, 10);
        rechteck[2].setzeMittelpunkt(4, -3);
        //1.stufe
        rechteck[3].setzeGroesse(5, 0.5);
        rechteck[3].setzeMittelpunkt(2, -6.25);
        
        //hindernisse(0-9)
        rechteck2[0].setzeGroesse(5, 0.4);
        rechteck2[0].setzeMittelpunkt(1, -6.25);
        //rahmen(10-20)
        rechteck2[10].setzeGroesse(70,0.1);
        rechteck2[10].setzeMittelpunkt(0, -20);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
