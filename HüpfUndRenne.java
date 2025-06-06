

public class HüpfUndRenne extends SPIEL
{
    
    int level;
    int level2; //freigeschaltetes level
    int anzahlLevel;
    int variante;
    boolean maus;
    boolean raktiv;
    FIGUR spielfigur;
    //RECHTECK spielfigur;
    RECHTECK gewinn;
    RECHTECK[] rechteck;
    RECHTECK[] rechteck2;//bei Berührung tot
    KNOPF[] knopf;
    
    INFO[] info;
    
    TEXT[] text; 
    
    KREIS[] kreise;
    /**
     * Konstruktor für Objekte der Klasse HüpfUndRenne
     */
    public HüpfUndRenne()
    {
        maus=true;
        
        spielfigur = new FIGUR("steht","extensions2/bildsteht.png", 1, 1);
        spielfigur.skaliere(0.1);
        spielfigur.fuegeZustandVonSpritesheetHinzu("laufen", "extensions2/bildlaufen.png", 1, 1);
        spielfigur.fuegeZustandVonSpritesheetHinzu("duckt", "extensions2/bildduckt.png", 1, 1);
        
        gewinn=new RECHTECK(1,1);
        gewinn.setzeMittelpunkt(-100, 0);
        //spielfigur=new RECHTECK(0.6,0.8);
        
        
        //spielfigur.setzeFarbe("grün");
        gewinn.setzeFarbe("grün");
        
        rechteck=new RECHTECK[10];
        rechteck2=new RECHTECK[20];
        kreise = new KREIS[10];
        knopf= new KNOPF[10];
        info=new INFO[10];
        text = new TEXT[10];
        for(int i=0;i<rechteck.length;i++){
        rechteck[i]= new RECHTECK(1, 1);
        rechteck[i].setzeMittelpunkt(0, 100);
        rechteck[i].setzeTransparenz(0.5);
        }
        for(int i=0;i<rechteck2.length;i++){
        rechteck2[i]=new RECHTECK(1, 1);
        rechteck2[i].setzeMittelpunkt(0, 100);
        rechteck2[i].setzeFarbe("Rot");
        rechteck2[i].setzeTransparenz(0.5);
        }
        for(int i=0;i<knopf.length;i++){
        knopf[i]= new KNOPF(5, 2, "a");
        knopf[i].setzeMittelpunkt(0, 100);
        }
        for(int i=0;i<text.length;i++){
        text[i]= new TEXT(0,100,1,"a");
        text[i].setzeMittelpunkt(0, 100);
        }
        for(int i=0;i<info.length;i++){
        info[i]= new INFO(0,100,"a");
        }
        ;
        level=0;
        level2=4;//eig 1 aber besser zum level ausprobieren
        variante=1;
        anzahlLevel=5;
        raktiv=false;
        start();
        //setzeHintergrundgrafik("extensions2/Bild");
        
        bildAktualisierungReagieren(0.02);
        
        setzeSchwerkraft(15);
        
        zeigeKoordinatensystem(true);   
    }
    
    
    public void klickReagieren(double x, double y) 
    {
        //knopf reagieren
        if (knopf[0].klick(x,y)==true)
        {
            variante=2;
            level();
            knopf[0].setzeMittelpunkt(0, 100);
        }
        for(int i=1;i<anzahlLevel+1;i++){
            if(knopf[i].klick(x,y)==true&&knopf[i].nenneTransparenz()==0){
                    level=i;
                    level();
                }
        }
    }
    public void bildAktualisierungReagieren(double sekunden){
        //maus
        if ( maus ) 
        {
            registriereMausKlickReagierbar( this );
        }
        
        if(level>0){
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
        
            //2.level
            if(level==2){
                if(spielfigur.beruehrt(rechteck[1])){
                    rechteck[3].macheDynamisch();
                    raktiv=true;
                }
                if(rechteck[3].beruehrt(rechteck[1])){
                    rechteck[3].machePassiv();
                    raktiv=false;
                }
                //zerquetschen
                if(raktiv &&spielfigur.stehtAuf(rechteck[3])){
                    sterben();
                    rechteck[3].machePassiv();
                    raktiv=false;
                }
            }
            for(int i=0;i<info.length;i++){
                if(info[i]!=null &&spielfigur.beruehrt(info[i].figur)){
                    info[i].textAnzeigen();
                }
                else{
                    info[i].textVerbergen();
                }
            }
        }
    }
    public void tasteReagieren(int taste){
        //springen
        switch(taste){
            case 37: spielfigur.setzeZustand("laufen");
            spielfigur.spiegelnHorizontal(true);
            break;
            case 39: spielfigur.setzeZustand("laufen");
            break;
            case 38: spielfigur.springe(9);
            break;
            case 40: spielfigur.skaliere(0.5);
            break;
            
        }
        
        }
    public void tasteLosgelassenReagieren(int taste){
        //aufrichten
        switch(taste){
            case 37: spielfigur.setzeZustand("steht");
            spielfigur.spiegelnHorizontal(false);
            break;
            case 39: spielfigur.setzeZustand("steht");
            break;
            case 40: spielfigur.skaliere(2);
            break;
        }
        
        }
    
    //aufräumen
    public void aufraeumen(){
        spielfigur.machePassiv();
        spielfigur.setzeMittelpunkt(-100, 0);
        for(int i=0;i<rechteck.length;i++){
            rechteck[i].setzeMittelpunkt(0, 100);
        }
        for(int i=0;i<rechteck2.length;i++){
            rechteck2[i].setzeMittelpunkt(0, 100);
        }
        for(int i=0;i<info.length;i++){
            info[i].setzeMittelpunkt(0, 100);
        }
        gewinn.setzeMittelpunkt(0, 100);
    }
    //sterben-> noch verschönern
    public void sterben(){
        maus=true;
        aufraeumen();
        knopf[0].setzeInhalt("Neustart");
        knopf[0].setzeMittelpunkt(0, 0);
        setzeKamerafokus(knopf[0].rechteck);
        
    }
    //gewinnen
    public void gewinnen(){
        if(level==level2){
            level2++;
        }
        start();
    }
    
    //level
    public void start(){
        maus=true;
        aufraeumen();
        setzeKamerafokus(knopf[3].rechteck);
        
        //neu
        for(int i=1; i<anzahlLevel+1; i++){
            knopf[i].setzeAlles(i, 5*i-15, 0);
        }
        for(int i=level2+1;i<6;i++){
            knopf[i].setzeTransparenz(0.5);
        }
        for(int i=level2;i>0;i--){
            knopf[i].setzeTransparenz(0);
        }
    }
    public void level(){
        maus = false;
        for(int i=1; i<knopf.length;i++){
            knopf[i].setzeMittelpunkt(0, 100);
        }
        spielfigur.macheAktiv();
        setzeKamerafokus(spielfigur);
        if(level==1){
            level1();
        }
        if(level==2){
            level2();
        }
        if(level==3){
            level1();
        }
    }
    public void level1(){
        
        spielfigur.setzeMittelpunkt(-2, 0);
        gewinn.setzeGroesse(1, 1);
        gewinn.setzeMittelpunkt(14.5, 11);
        
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
        rechteck[6].setzeMittelpunkt(18.75, 7.725);
        //ebene über hindernis
        rechteck[7].setzeGroesse(5, 0.5);
        rechteck[7].setzeMittelpunkt(16, 9.75);
        
        //hindernisse(0-9)
        //lern hindernis
        rechteck2[0].setzeGroesse(2, 0.5);
        rechteck2[0].setzeMittelpunkt(2, 2.25);
        //bodenhindernis
        rechteck2[1].setzeGroesse(10, 1);
        rechteck2[1].setzeMittelpunkt(20, -7);
        //ducken lernen
        rechteck2[6].setzeGroesse(5, 4);
        rechteck2[6].setzeMittelpunkt(16, 7.5);
        //rahmen(10-20)
        rechteck2[10].setzeGroesse(200,0.1);
        rechteck2[10].setzeMittelpunkt(0, -20);
        
        //info laufen
        info[0].setzeInhalt("Nutze die Pfeiltasten um zu laufen");
        info[0].setzeMittelpunkt(-2, -6);
        //info springen
        info[1].setzeInhalt("Mit dem Pfeil nach oben kannst du springen");
        info[1].setzeMittelpunkt(2, -6);
        //info ducken
        info[2].setzeInhalt("ducken");
        info[2].setzeMittelpunkt(11, 5);
        //info gewinn
        info[3].setzeInhalt("gewinn");
        info[3].setzeMittelpunkt(20, 5);
    }
    
    public void level2(){
        level=2;
        //rahmen(10-20)
        rechteck2[10].setzeGroesse(200,0.1);
        rechteck2[10].setzeMittelpunkt(0, -20);
        
        spielfigur.setzeMittelpunkt(0, 0);
        //boden
        rechteck[0].setzeGroesse(50, 1);
        rechteck[0].setzeMittelpunkt(20, -7);
        //sensor
        rechteck[1].setzeGroesse(1, 1);
        rechteck[1].setzeMittelpunkt(15.5, -7);
        //decke
        rechteck[2].setzeGroesse(20, 10);
        rechteck[2].setzeMittelpunkt(15, 5);
        //fallen
        rechteck[3].setzeGroesse(8, 0.5);
        rechteck[3].setzeMittelpunkt(18, 0.5);
        //hinderniss
        rechteck[4].setzeGroesse(9, 6.5);
        rechteck[4].setzeMittelpunkt(30, -4);
        //2. etage
        rechteck[5].setzeGroesse(1, 0.5);
        rechteck[5].setzeMittelpunkt(0, 10);
         
        rechteck[6].setzeGroesse(1, 0.5);
        rechteck[6].setzeMittelpunkt(-2.5, 12.5);
        
        rechteck[7].setzeGroesse(1, 0.5);
        rechteck[7].setzeMittelpunkt(-5, 15);
         
         
    
        
        
        //hindernisse(0-9)
        //rechteck2[0].setzeGroesse(5, 0.4);
        //rechteck2[0].setzeMittelpunkt(1, -6.25);
        //rahmen(10-20)
        //rechteck2[10].setzeGroesse(70,0.1);
        //rechteck2[10].setzeMittelpunkt(0, -20);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
