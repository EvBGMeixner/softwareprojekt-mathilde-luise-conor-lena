
public class s2 extends SPIEL
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
    FIGUR spielfigur2;
    TEXT textt;

    RECHTECK gewinn;
    RECHTECK[] rechteck;
    RECHTECK[] rechteck2;//bei Berührung tot
    KNOPF[] knopf;
    FIGUR[] figur;
    STEIN[] stein;
    
    INFO[] info;

    TEXT[] text; 

    KREIS[] kreis;
    KREIS[] kreis2;
    /**
     * Konstruktor für Objekte der Klasse CopyOfHüpfUndRenne
     */
    public s2()
    {
        maus=true;

        spielfigur = new FIGUR("steht","extensions2/bildsteht.png", 1, 1);
        spielfigur.skaliere(0.1);
        spielfigur.fuegeZustandVonSpritesheetHinzu("laufen", "extensions2/bildlaufen.png", 1, 1);
        spielfigur.fuegeZustandVonSpritesheetHinzu("duckt", "extensions2/bildduckt.png", 1, 1);
        spielfigur2 = new FIGUR("steht","extensions2/bildsteht.png", 1, 1);
        spielfigur2.skaliere(0.1);
        spielfigur2.fuegeZustandVonSpritesheetHinzu("laufen", "extensions2/bildlaufen.png", 1, 1);
        spielfigur2.fuegeZustandVonSpritesheetHinzu("duckt", "extensions2/bildduckt.png", 1, 1);

        gewinn=new RECHTECK(1,1);
        gewinn.setzeMittelpunkt(-100, 0);
        
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
        stein=new STEIN[5];

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
        setzeHintergrundgrafik("extensions2/levelneu");
        start();
        hintergrundbild.skaliere(0.73);
        hintergrundbild.setzeMittelpunkt(0,-0.5);

        bildAktualisierungReagieren(0.02);

        setzeSchwerkraft(15);

        zeigeKoordinatensystem(true);   
    }

    public void klickReagieren(double x, double y) 
    {
        //knopf reagieren
        if (knopf[0].rechteck.beinhaltetPunkt(x, y))
        {
            variante=2;
            rechteck2[11].setzeMittelpunkt(0, 100);
            text[0].setzeMittelpunkt(0, 100);
            level();
            knopf[0].setzeMittelpunkt(0, 100);
            rechteck2[11].setzeTransparenz(1);
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
                }
                if(istTasteGedrueckt(37)==true){
                    spielfigur.verschieben(-0.07, 0);
                }
                if(istTasteGedrueckt(68)==true){
                    spielfigur2.verschieben(0.07, 0);
                }
                if(istTasteGedrueckt(65)==true){
                    spielfigur2.verschieben(-0.07, 0);
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
                if(spielfigur.beruehrt(rechteck[1])){
                    rechteck[3].macheDynamisch();
                    raktiv=true;
                    rechteck[3].setzeSichtbar(true);
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
                case 65: spielfigur2.setzeZustand("laufen");
                    spielfigur2.spiegelnHorizontal(true);
                    break;
                case 68: spielfigur2.setzeZustand("laufen");
                    spielfigur2.spiegelnHorizontal(false);
                    break;
                case 87: spielfigur2.springe(9);
                    break;
                case 83: spielfigur2.skaliere(0.5);
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
                case 65: 
                    if(istTasteGedrueckt(37)==false&&istTasteGedrueckt(39)==false){
                        spielfigur2.setzeZustand("steht");
                        spielfigur2.spiegelnHorizontal(true);
                    }
                    break;
                case 68: 
                    if(istTasteGedrueckt(37)==false&&istTasteGedrueckt(39)==false){
                        spielfigur2.setzeZustand("steht");
                        spielfigur2.spiegelnHorizontal(false);
                    }
                    break;
                case 83: spielfigur2.skaliere(2);
                    klein=false;
                    break;
            }

        }
    }

    //aufräumen
    public void aufraeumen(){
        spielfigur.machePassiv();
        spielfigur.setzeMittelpunkt(-100, 0);
        spielfigur2.machePassiv();
        spielfigur2.setzeMittelpunkt(-100, 0);
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
        maus=true;
        aufraeumen();
        
        hintergrundbild.setzeSichtbar(true);

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

    public void level(){
        alive=true;
        hintergrundbild.setzeSichtbar(false);
        for(int i=0; i<figur.length;i++){
            figur[i].setzeSichtbar(false);
        }
        maus = false;
        for(int i=1; i<knopf.length;i++){
            knopf[i].setzeMittelpunkt(0, 100);
        }
        spielfigur.macheAktiv();
        spielfigur2.macheAktiv();
        //boden
        stein[0].setzeGroesse(11, 1);
        

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
        spielfigur.setzeMittelpunkt(2.5, -8);
        spielfigur2.setzeMittelpunkt(-2.5, -8);
        
    }

    public void level2(){

    }

    public void level3(){
    
    }

    public void level4() {
    
    }

}
