

public class Level1 
{
    RECHTECK[] rechtecke;
    KREIS[] kreise;
    public Level1()
    {
        rechtecke=new RECHTECK[10];
        kreise = new KREIS[10];
        
        rechtecke[0]= new RECHTECK(20, 1);
        rechtecke[0].setzeMittelpunkt(0, -7);
        rechtecke[1]= new RECHTECK(5, 0.5);
        rechtecke[1].setzeMittelpunkt(2, -5);
        rechtecke[2]= new RECHTECK(5, 0.5);
        rechtecke[2].setzeMittelpunkt(4, -3);
    }
}
