

public class HüpfUndRenne extends SPIEL
{
    
    int level;
    int variante;
    boolean maus;
    //FIGUR spielfigur;
    //KREIS spielfigur;
    RECHTECK spielfigur;
    RECHTECK gewinn;
    RECHTECK[] rechteck;
    RECHTECK[] rechteck2;//bei Berührung tot
    RECHTECK[] knopf;
    TEXT[] text; 
    
    KREIS[] kreise;
    /**
     * Konstruktor für Objekte der Klasse HüpfUndRenne
     */
    public HüpfUndRenne()
    {
        maus=false;
        
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
        knopf= new RECHTECK[10];
        text = new TEXT[10];
        for(int i=0;i<rechteck.length;i++){
        rechteck[i]= new RECHTECK(1, 1);
        rechteck[i].setzeMittelpunkt(0, 100);
        }
        for(int i=0;i<rechteck2.length;i++){
        rechteck2[i]=new RECHTECK(1, 1);
        rechteck2[i].setzeMittelpunkt(0, 100);
        rechteck2[i].setzeFarbe("Rot");
        }
        for(int i=0;i<knopf.length;i++){
        knopf[i]= new RECHTECK(5, 2);
        knopf[i].setzeMittelpunkt(0, 100);
        }
        for(int i=0;i<text.length;i++){
        text[i]= new TEXT(0,100,1,"a");
        text[i].setzeMittelpunkt(0, 100);
        }
        
        level=1;
        variante=1;
        level1();
        
        bildAktualisierungReagieren(0.02);
        setzeKamerafokus(spielfigur);
        
        setzeSchwerkraft(15);
        
        //zeigeKoordinatensystem(true);   
    }
    
    public void klickReagieren(double x, double y) 
    {
        if (x>knopf[0].nenneMx()-knopf[0].nenneBreite()/2&&x<knopf[0].nenneMx()+knopf[0].nenneBreite()/2){
            maus=false;
            if(level==1){
                variante=2;
            
                level1();
            
            }
            setzeKamerafokus(spielfigur);
            knopf[0].setzeMittelpunkt(0, 100);
            text[0].setzeMittelpunkt(0, 100);
        }
        
    }
    public void bildAktualisierungReagieren(double sekunden){
        //bewegen
        if(istTasteGedrueckt(39)==true){
             spielfigur.verschieben(0.07, 0);
        }
        if(istTasteGedrueckt(37)==true){
            spielfigur.verschieben(-0.07, 0);
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
        //maus
        if ( maus ) 
        {
            registriereMausKlickReagierbar( this );
            registriereMausRadReagierbar( this );
        }
    }
    public void tasteReagieren(int taste){
        //springen
        if(taste==38){
            spielfigur.springe(9);
            
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
        maus=true;
        knopf[0].setzeMittelpunkt(0, 0);
        
        for(int i=0;i<rechteck.length;i++){
            rechteck[i].setzeMittelpunkt(0, 100);
        }
        for(int i=0;i<rechteck2.length;i++){
            rechteck2[i].setzeMittelpunkt(0, 100);
            rechteck2[i].setzeFarbe("Rot");
        }
        gewinn.setzeMittelpunkt(0, 100);
        
        setzeKamerafokus(knopf[0]);
        text[0].setzeInhalt("Neustart");
        text[0].setzeMittelpunkt(0, 0);
        
        
        
        
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
        gewinn.setzeMittelpunkt(14.5, 10.5);
        
        //boden
        rechteck[0].setzeGroesse(50, 1);
        rechteck[0].setzeMittelpunkt(20, -7);
        //1.stufe
        rechteck[1].setzeGroesse(2, 0.5);
        rechteck[1].setzeMittelpunkt(5, -4.25);
        //2.stufe
        rechteck[2].setzeGroesse(2, 0.5);
        rechteck[2].setzeMittelpunkt(8, -1.75);
        //3.stufe
        rechteck[3].setzeGroesse(2, 0.5);
        rechteck[3].setzeMittelpunkt(5, 0.75);
        //4.stufe
        if(variante==2){
            rechteck[4].setzeGroesse(2, 0.5);
            rechteck[4].setzeMittelpunkt(8, 3.25);
        }
        //2.Ebene
        rechteck[5].setzeGroesse(20, 0.5);
        rechteck[5].setzeMittelpunkt(20, 4.25);
        //wand an hindernis
        rechteck[6].setzeGroesse(0.5, 4.55);
        rechteck[6].setzeMittelpunkt(18.75, 7.225);
        //ebene über hindernis
        rechteck[7].setzeGroesse(5, 0.5);
        rechteck[7].setzeMittelpunkt(16, 9.25);
        
        //hindernisse(0-9)
        //lern hindernis
        rechteck2[0].setzeGroesse(2, 0.5);
        rechteck2[0].setzeMittelpunkt(2, 2.25);
        //bodenhindernis
        rechteck2[1].setzeGroesse(10, 1);
        rechteck2[1].setzeMittelpunkt(20, -7);
        //ducken lernen
        rechteck2[6].setzeGroesse(5, 4);
        rechteck2[6].setzeMittelpunkt(16, 7);
        //rahmen(10-20)
        rechteck2[10].setzeGroesse(200,0.1);
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
