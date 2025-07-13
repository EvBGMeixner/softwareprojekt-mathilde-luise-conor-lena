
public class HüpfUndRenne extends SPIEL
{

    int level;
    int level2; //freigeschaltetes level
    int anzahlLevel;
    int variante;
    boolean maus;
    boolean raktiv;
    boolean alive;
    boolean klein;
    boolean knopftot;
    boolean beruehrt;
    FIGUR spielfigur;
    TEXT textt;

    RECHTECK gewinn;
    RECHTECK[] rechteck;
    RECHTECK[] rechteck2;//bei Berührung tot
    KNOPF[] knopf;
    FIGUR[] figur;

    INFO[] info;

    TEXT[] text; 

    KREIS[] kreis;
    KREIS[] kreis2;
    
    STEIN[] stein;
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
        
        hintergrundbild = new FIGUR ( "start" , "extensions2/levelneu", 1 , 1);
        hintergrundbild.setzeEbenenposition( -2 );
        hintergrundbild.fuegeZustandVonSpritesheetHinzu("level", "Unbenannt.png", 1, 1);
        hintergrundbild.skaliere(0.73);
        hintergrundbild.setzeMittelpunkt(0,0);
        
        gewinn=new RECHTECK(1,1);
        gewinn.setzeMittelpunkt(-100, 0);
        //spielfigur=new RECHTECK(0.6,0.8);

        //spielfigur.setzeFarbe("grün");
        gewinn.setzeFarbe("grün");

 
        rechteck=new RECHTECK[20];
        rechteck2=new RECHTECK[20];

        kreis = new KREIS[20];

        kreis = new KREIS[13];

        kreis2 = new KREIS[14];

        kreis2 = new KREIS[14];


        knopf= new KNOPF[10];
        info=new INFO[10];
        text = new TEXT[10];
        figur=new FIGUR[4];
        stein=new STEIN[12];
        
        for(int i=0;i<text.length;i++){
            text[i]= new TEXT(0, 100, 1, "a");
        }
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
        rechteck2[11].setzeTransparenz(1);
        for(int i=0;i<kreis.length;i++){
            kreis[i]= new KREIS(1);
            kreis[i].setzeMittelpunkt(0, 100);
            kreis[i].setzeTransparenz(0.5);
        }
        for(int i=0;i<kreis2.length;i++){
            kreis2[i]= new KREIS(1);
            kreis2[i].setzeMittelpunkt(0, 100);
            kreis2[i].setzeTransparenz(0.5);
            kreis2[i].setzeFarbe("Rot");
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
        for(int i=0;i<figur.length;i++){
            figur[i]= new FIGUR("schloss","extensions2/schloss.png",1,1);
            figur[i].skaliere(0.1);
            figur[i].setzeMittelpunkt(2.9, -3.7*i+4.15);
        }
        for(int i=0;i<stein.length;i++){
            stein[i]= new STEIN();
            
        }
        level=0;
        level2=4;//eig 1 aber besser zum level ausprobieren
        variante=1;
        anzahlLevel=4;
        raktiv=false;
        
        start();
        

        bildAktualisierungReagieren(0.02);

        setzeSchwerkraft(15);

        //zeigeKoordinatensystem(true);   
    }

    public void klickReagieren(double x, double y) 
    {
        //knopf reagieren
        if (knopf[0].rechteck.beinhaltetPunkt(x, y))
        {
            variante=2;
            //aufraeumen();
            knopf[0].setzeMittelpunkt(0, 100);
            levell();
            rechteck2[11].setzeMittelpunkt(0, 100);
            rechteck2[11].setzeTransparenz(1);
            text[0].setzeMittelpunkt(0, 100);
        }
        for(int i=1;i<anzahlLevel+1;i++){
            if(knopf[i].rechteck.beinhaltetPunkt(x, y)&&i<=level2){
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
            if(alive){
                if(istTasteGedrueckt(39)==true){
                    spielfigur.verschieben(0.07, 0);
                    //if(spielfigur.nenneGeschwindigkeitX()>0){
                        hintergrundbild.verschieben(0.03, 0);
                    //}
                    
                    
                }
                if(istTasteGedrueckt(37)==true){
                    spielfigur.verschieben(-0.07, 0);
                    //if(spielfigur.nenneGeschwindigkeitX()>0){
                        hintergrundbild.verschieben(-0.03, 0);
                    //}
                }
            }
            //sterben
            for(int i=0;i<rechteck2.length;i++){
                if(spielfigur.beruehrt(rechteck2[i])&&i!=11){
                    sterben();
                }
            }
            for(int i=0;i<kreis2.length;i++){
                if(spielfigur.beruehrt(kreis2[i])){
                    sterben();
                }
            }
            //gewinnen
            if(spielfigur.beruehrt(gewinn)){
                gewinnen();
            }

            //2.level
            if(level==2){
                if(spielfigur.beruehrt(stein[1].steine[0])){
                    stein[3].macheDynamisch();
                    raktiv=true;
                    stein[3].setzeSichtbar(true);
                }
                if(stein[1].steine[0].beruehrt(stein[3].steine[0])){
                
                    stein[3].machePassiv();
                    raktiv=false;
                }
                //zerquetschen
                if(raktiv &&(spielfigur.stehtAuf(stein[3].steine[0])||
                    spielfigur.stehtAuf(stein[3].steine[1])||
                    spielfigur.stehtAuf(stein[3].steine[2])||
                    spielfigur.stehtAuf(stein[3].steine[3])||
                    spielfigur.stehtAuf(stein[3].steine[4]))){
                    sterben();
                    stein[3].machePassiv();
                    raktiv=false;
                }
            }
            if(level==3){
                if(spielfigur.beruehrt(rechteck[8])){
                    rechteck[9].animiereGerade(7, -1.5, 20, false);
                    rechteck[10].animiereGerade(7, -1.5, 20, false);
                }
                if(spielfigur.beruehrt(rechteck[12])){
                    spielfigur.setzeSichtbar(false);
                    spielfigur.machePassiv();
                    spielfigur.animiereGerade(2, 20, spielfigur.nenneMy(), false);
                    rechteck[13].setzeSichtbar(true);

                }
                if(spielfigur.istSichtbar()==false&&spielfigur.nenneMx()>19){
                    spielfigur.setzeSichtbar(true);
                    spielfigur.macheAktiv();
                }
                if(spielfigur.beruehrt(rechteck[16])&&beruehrt==false){
                    rechteck[16].animiereGerade(3, 23, 33.5, false);
                    beruehrt=true;
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
            if(rechteck2[11].nenneTransparenz()<=0.7&&knopftot==false){
                knopftot=true;
                knopf[0].text.setzeInhalt("Neustart");
                knopf[0].setzeMittelpunkt(spielfigur.nenneMx(), spielfigur.nenneMy()-5);
                knopf[0].rechteck.setzeTransparenz(0.5);
            }

        }
    }

    public void tasteReagieren(int taste){
        //springen
        if(alive==true){
            switch(taste){
                case 37: spielfigur.setzeZustand("laufen");
                    spielfigur.spiegelnHorizontal(true);
                    break;
                case 39: spielfigur.setzeZustand("laufen");
                    spielfigur.spiegelnHorizontal(false);
                    break;
                case 38: spielfigur.springe(9);
                    break;
                case 40: spielfigur.skaliere(0.5);
                    klein=true;
                    break;

            }
        }

    }

    public void tasteLosgelassenReagieren(int taste){
        //aufrichten
        if(alive==true){
            switch(taste){
                case 37: 
                    if(istTasteGedrueckt(37)==false&&istTasteGedrueckt(39)==false){
                        spielfigur.setzeZustand("steht");
                        spielfigur.spiegelnHorizontal(true);
                    }
                    break;
                case 39: 
                    if(istTasteGedrueckt(37)==false&&istTasteGedrueckt(39)==false){
                        spielfigur.setzeZustand("steht");
                        spielfigur.spiegelnHorizontal(false);
                    }
                    break;
                case 40: spielfigur.skaliere(2);
                    klein=false;
                    break;
            }

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
        for(int i=0;i<kreis.length;i++){
            kreis[i].setzeMittelpunkt(0, 100);
        }
        for(int i=0;i<kreis2.length;i++){
            kreis2[i].setzeMittelpunkt(0, 100);
        }
        for(int i=0;i<stein.length;i++){
            //for(int j=0;j<stein[i].steine.length; j++){
                
                stein[i].setzeMittelpunkt(0, 100);
            
            //}
          //  for(int j=0;j<stein[i].steine.length;j++){
            //    stein[i].setzeMittelpunkt2(0, 100);
                
            //}
            //stein[i].steine=null;
            
        }
        gewinn.setzeMittelpunkt(0, 100);
    }
    //sterben-> noch verschönern
    public void sterben(){
        if(alive){
            knopftot=false;

            alive=false;
            maus=true;

            rechteck2[11].setzeMittelpunkt(spielfigur.nenneMx(), spielfigur.nenneMy());
            rechteck2[11].setzeGroesse(40, 30);
            rechteck2[11].setzeTransparenz(1);
            rechteck2[11].animiereTransparenz(0.5, 0.69);
            spielfigur.machePassiv();

            text[0].setzeSchriftHoehe(4);
            text[0].setzeInhalt("GAME OVER");

            text[0].setzeMittelpunkt(spielfigur.nenneMx(), spielfigur.nenneMy()+5);
            text[0].setzeFarbe("schwarz");

            //aufraeumen();
            //knopf[0].setzeInhalt("Neustart");
            //knopf[0].setzeMittelpunkt(spielfigur.nenneMx(), spielfigur.nenneMy()-5);
            //knopf[0].rechteck.setzeTransparenz(0.5);
            //setzeKamerafokus(knopf[0].rechteck);
            if(klein==true){
                spielfigur.skaliere(2);
                klein=false;
            }
            spielfigur.spiegelnHorizontal(false);   
        }
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
        alive=false;
        maus=true;
        aufraeumen();
        setzeKamerafokus(knopf[2].rechteck);
        
        hintergrundbild.setzeMittelpunkt(0,0);
        hintergrundbild.setzeZustand("start");

        //neu
        for(int i=1; i<anzahlLevel+1; i++){
            knopf[i].setzeAlles(i, 0, -3.7*i+7.85);
            knopf[i].setzeSichtbar(false);
        }
        for(int i=figur.length-1;i>=level2;i--){
            figur[i].setzeSichtbar(true);
        }
        for(int i=0;i<level2;i++){
            figur[i].setzeSichtbar(false);
        }

    }
    
    public void levell(){
        alive = true;
        maus = false;
        spielfigur.macheAktiv();
        if(level==1){
            spielfigur.setzeMittelpunkt(-2, 0);
            
        }
        if(level==2){
            spielfigur.setzeMittelpunkt(0, 0);
            stein[3].setzeMittelpunkt(18, 0);
            stein[3].setzeSichtbar(false);
        }
        if(level==3){
            level3();
        }
        if(level==4){
            level4();
        }
    }
    
    public void level(){
        alive=true;
        for(int i=0; i<figur.length;i++){
            figur[i].setzeSichtbar(false);
        }
        maus = false;
        for(int i=1; i<knopf.length;i++){
            knopf[i].setzeMittelpunkt(0, 100);
        }
        spielfigur.macheAktiv();
        setzeKamerafokus(spielfigur);
        hintergrundbild.setzeZustand("level");
        if(level==1){
            level1();
        }
        if(level==2){
            level2();
        }
        if(level==3){
            level3();
        }
        if(level==4){
            level4();
        }
    }

    public void level1(){

        spielfigur.setzeMittelpunkt(-2, 0);
        gewinn.setzeGroesse(1, 1);
        gewinn.setzeMittelpunkt(14.5, 11);

        //boden
        stein[0].setzeGroesse(49, 1);
        stein[0].setzeMittelpunkt(20, -7);
        //rechteck[0].setzeGroesse(50, 1);
        //rechteck[0].setzeMittelpunkt(20, -7);
        //1.stufe
        stein[1].setzeGroesse(2, 1);
        stein[1].setzeMittelpunkt(5, -4.5);
        //2.stufe
        stein[2].setzeGroesse(2, 1);
        stein[2].setzeMittelpunkt(8, -2);
        //3.stufe
        stein[3].setzeGroesse(2, 1);
        stein[3].setzeMittelpunkt(5, 0.5);
        //4.stufe
        stein[4].setzeGroesse(2, 1);
        stein[4].setzeMittelpunkt(8, 3);
        //2.Ebene
        stein[5].setzeGroesse(20, 1);
        stein[5].setzeMittelpunkt(20, 4.1);
        //wand an hindernis
        //rechteck[6].setzeGroesse(0.5, 4.55);
        //rechteck[6].setzeMittelpunkt(18.75, 7.725);
        stein[6].setzeGroesse2(1, 4);
        stein[6].setzeMittelpunkt(18.75, 8);
        
        //ebene über hindernis
        stein[7].setzeGroesse(5, 1);
        stein[7].setzeMittelpunkt(16, 9.25);

        //hindernisse(0-9)
        //lern hindernis
        rechteck2[0].setzeGroesse(2, 0.5);
        rechteck2[0].setzeMittelpunkt(2, 2.25);
        //bodenhindernis
        rechteck2[1].setzeGroesse(10, 1);
        rechteck2[1].setzeMittelpunkt(20, -7);
        //ducken lernen
        rechteck2[6].setzeGroesse(5, 3.5);
        rechteck2[6].setzeMittelpunkt(16, 7.25);
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
        info[2].setzeInhalt("Der Pfeil nach unten macht dich kleiner");
        info[2].setzeMittelpunkt(11, 5);
        //info gewinn
        info[3].setzeInhalt("An Wänden kannst du höher springen, wenn du häufiger springst");
        info[3].setzeMittelpunkt(20, 5);
    }
    
    
    public void level2(){
        
        //rahmen
        rechteck2[0].setzeGroesse(200,0.1);
        rechteck2[0].setzeMittelpunkt(0, -20);

        spielfigur.setzeMittelpunkt(0, 0); //eig 0 0
        spielfigur.setzeMittelpunkt(0, 0);

        //boden
        //rechteck[0].setzeGroesse(50, 1);
        //rechteck[0].setzeMittelpunkt(20, -7);
        stein[0].setzeGroesse(50, 1);
        stein[0].setzeMittelpunkt(20, -7);
        //sensor
        stein[1].setzeGroesse(1, 1);
        stein[1].setzeMittelpunkt(15.5, -7);
        stein[1].steine[0].setzeSichtbar(false);
        //decke
        stein[2].setzeGroesse(20, 20);
        stein[2].setzeMittelpunkt(14.5, 5.5);
        //fallen
        stein[3].setzeGroesse(8, 1);
        stein[3].setzeMittelpunkt(18, 0);
        stein[3].setzeSichtbar(false);
        //hinderniss
        stein[4].setzeGroesse(9, 11);
        stein[4].setzeMittelpunkt(30, -4);
        //2. etage
        stein[5].setzeGroesse(1, 1);
        stein[5].setzeMittelpunkt(0, 10);

        stein[6].setzeGroesse(1, 1);
        stein[6].setzeMittelpunkt(-2.5, 12.5);

        stein[7].setzeGroesse(1, 1);
        stein[7].setzeMittelpunkt(-5, 15);

        stein[8].setzeGroesse(1, 1);
        stein[8].setzeMittelpunkt(0,17);

        stein[9].setzeGroesse(20, 1);
        stein[9].setzeMittelpunkt(15,17);

        kreis[10].setzeRadius(2.5);
        kreis[10].setzeMittelpunkt(15,20);

        kreis[11].setzeRadius(2.5);
        kreis[11].setzeMittelpunkt(20,20);

        stein[10].setzeGroesse(7, 4);
        stein[10].setzeMittelpunkt(25,23);

        kreis[12].setzeRadius(0.3);
        kreis[12].setzeMittelpunkt(32.5,25);

        stein[11].setzeGroesse(9, 1);
        stein[11].setzeMittelpunkt(41,27);

        gewinn.setzeMittelpunkt(45, 27.5);

        //hindernisse(0-9)
        //rechteck2[1].setzeGroesse(0.1, 9.6);
        //rechteck2[1].setzeMittelpunkt(25.05,5.2 );

    }
    public void level3(){
        //spielfigur.setzeMittelpunkt(0, -5); //anfang, anderes um auszuprobieren
        spielfigur.setzeMittelpunkt(32, 37);
        beruehrt=false;
        //rahmen
        rechteck2[0].setzeGroesse(200,0.1);
        rechteck2[0].setzeMittelpunkt(0, -20);

        //boden
        rechteck[0].setzeGroesse(26, 1);
        rechteck[0].setzeMittelpunkt(8, -7);
        //ducken
        rechteck[1].setzeGroesse(10,1);
        rechteck[1].setzeMittelpunkt(15, -4.5);

        //aufzug
        rechteck[3].setzeGroesse(5,1);
        rechteck[3].setzeMittelpunkt(27, 10);
        rechteck[3].animiereGerade(6, 27, -7, true);
        rechteck[3].macheKinematisch();
        rechteck[4].setzeGroesse(5,1);
        rechteck[4].setzeMittelpunkt(27, 10);
        rechteck[4].animiereGerade(6, 27, -7, true);
        //ebene2
        rechteck[5].setzeGroesse(10, 1);
        rechteck[5].setzeMittelpunkt(15, 9);
        //wand
        rechteck[6].setzeGroesse(1, 10);
        rechteck[6].setzeMittelpunkt(10.5, 14.5);
        //ebene3
        rechteck[7].setzeGroesse(15, 1);
        rechteck[7].setzeMittelpunkt(-11, 10);
        //knopf
        rechteck[8].setzeGroesse(1, 1);
        rechteck[8].setzeMittelpunkt(-18, 11);
        rechteck[8].setzeFarbe("gelb");
        //aufzug2
        rechteck[9].setzeGroesse(4, 1);
        rechteck[9].setzeMittelpunkt(-1.5, 5);
        rechteck[9].macheKinematisch();
        rechteck[9].setzeSichtbar(false);
        rechteck[10].setzeGroesse(4, 1);
        rechteck[10].setzeMittelpunkt(-1.5, 5);
        //ebene tür
        rechteck[11].setzeGroesse(5, 1);
        rechteck[11].setzeMittelpunkt(-7.5, 20);
        rechteck[12].setzeGroesse(1, 1);
        rechteck[12].setzeMittelpunkt(-7.5, 21);
        rechteck[12].setzeFarbe("gelb");
        //ebene4
        rechteck[13].setzeGroesse(20, 1);
        rechteck[13].setzeMittelpunkt(29, 20);
        rechteck[13].setzeSichtbar(false);
        //hochklettern
        rechteck[14].setzeGroesse(1, 10);
        rechteck[14].setzeMittelpunkt(35, 28);
        rechteck[15].setzeGroesse(1, 10);
        rechteck[15].setzeMittelpunkt(38.5, 28);
        //bewegt nach links
        rechteck[16].setzeGroesse(5, 1);
        rechteck[16].setzeMittelpunkt(33, 33.5);
        

        //rote
        rechteck2[1].setzeGroesse(1, 5);
        rechteck2[1].setzeMittelpunkt(-4.5, -4);
        rechteck2[1].animiereGerade(8.7, 30, -4, false);
        //springen geduckt
        rechteck2[2].setzeGroesse(0.3, 0.1);
        rechteck2[2].setzeMittelpunkt(11, -6.5);
        rechteck2[3].setzeGroesse(0.3, 0.1);
        rechteck2[3].setzeMittelpunkt(13, -6.5);
        rechteck2[4].setzeGroesse(0.3, 0.1);
        rechteck2[4].setzeMittelpunkt(15, -6.5);
        rechteck2[5].setzeGroesse(0.3, 0.1);
        rechteck2[5].setzeMittelpunkt(17, -6.5);
        rechteck2[6].setzeGroesse(0.3, 0.1);
        rechteck2[6].setzeMittelpunkt(19, -6.5);
        rechteck2[7].setzeGroesse(10, 0.1);
        rechteck2[7].setzeMittelpunkt(15, -3.85);
        //hocklettern in der mitte
        rechteck2[8].setzeGroesse(0.1, 10);
        rechteck2[8].setzeMittelpunkt(34.45, 28);
        rechteck2[9].setzeGroesse(0.1, 10);
        rechteck2[9].setzeMittelpunkt(39.05, 28);

        //kreise
        kreis[0].setzeMittelpunkt(2, -5.5);
        kreis[0].setzeRadius(0.4);

        kreis[1].setzeMittelpunkt(7, -5.5);
        kreis[1].setzeRadius(0.4);

        kreis2[0].setzeRadius(0.3);
        kreis2[0].setzeMittelpunkt(36.3, 24.5);
        kreis2[1].setzeRadius(0.3);
        kreis2[1].setzeMittelpunkt(36.3, 30.5);
        kreis2[2].setzeRadius(0.3);
        kreis2[2].setzeMittelpunkt(37.2, 27.5);
    }
    public void level4() {
        spielfigur.setzeMittelpunkt(0, -5);
        
        //rahmen
        rechteck2[0].setzeGroesse(200,0.1);
        rechteck2[0].setzeMittelpunkt(0, -20);

        //boden
        
        rechteck[0].setzeGroesse(26, 1);
        rechteck[0].setzeMittelpunkt(8, -7);
        //ducken
        rechteck2[3].setzeGroesse(10,1);
        rechteck2[3].setzeMittelpunkt(15, -4.5);
        
        //aufzug
        rechteck[3].setzeGroesse(5,1);
        rechteck[3].setzeMittelpunkt(32, 20);
        rechteck[3].animiereGerade(3, 32, -7, true);
        rechteck[3].macheKinematisch();
        rechteck[3].setzeGroesse(5,1);
        rechteck[3].setzeMittelpunkt(32, 12);
        rechteck[3].animiereGerade(3, 32, -20, true);
        
        //rote
        rechteck2[1].setzeGroesse(1, 5);
        rechteck2[1].setzeMittelpunkt(-4.5, -4);
        rechteck2[1].animiereGerade(11, 40, -4, false);
        
        //killerdecke
        rechteck2[6].setzeGroesse(5,1);
        rechteck2[6].setzeMittelpunkt(20, 7);
        rechteck2[6].animiereGerade(9, 20, -7, true);
        
        //Stepstone
        rechteck[5].setzeGroesse(3, 1);
        rechteck[5].setzeMittelpunkt(27, -7);
        
        //killerdeckesteht
        rechteck2[7].setzeGroesse(5,1);
        rechteck2[7].setzeMittelpunkt(32, 10);
        
        //plattform
        rechteck[7].setzeGroesse(5,1);
        rechteck[7].setzeMittelpunkt(25,5);
        
        //wand
        rechteck[6].setzeGroesse(1, 10);
        rechteck[6].setzeMittelpunkt(27, 5);
        
        //killerdecke2
        rechteck2[8].setzeGroesse(5,1);
        rechteck2[8].setzeMittelpunkt(30, 15);
        
        //rote
        rechteck2[9].setzeGroesse(1, 10);
        rechteck2[9].setzeMittelpunkt(50, 10);
        rechteck2[9].animiereGerade(15, 0, 10, true);
        
        //plattform2
        rechteck[8].setzeGroesse(4,1);
        rechteck[8].setzeMittelpunkt(15,5);
        
        //boden
        rechteck[10].setzeGroesse(30, 1);
        rechteck[10].setzeMittelpunkt(0, 5);
        
        
        //fireball1
        kreis2[11].setzeRadius(0.4);
        kreis2[11].setzeMittelpunkt(0, 5);
        kreis2[11].animiereGerade(60, 20, 5, false);
        
         //fireball2
       kreis2[12].setzeRadius(0.4);
        kreis2[12].setzeMittelpunkt(0, 7);
        kreis2[12].animiereGerade(50, 20, 7, false);
        
        
         //fireball3
        kreis2[13].setzeRadius(0.4);
        kreis2[13].setzeMittelpunkt(0, 6);
        kreis2[13].animiereGerade(30, 20, 6, false);
        
        
        
    }
    
    
}
