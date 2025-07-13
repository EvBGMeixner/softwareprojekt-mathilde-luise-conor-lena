
/**
 * Beschreiben Sie hier die Klasse probe.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class probe extends SPIEL
{
    STEIN[] stein;
    
    public probe()
    {
        stein = new STEIN[4];
        for(int i=0;i<stein.length;i++){
            stein[i]= new STEIN();
            
        }
    }
    public void a(){
        stein[0].setzeGroesse(49, 1);
        stein[0].setzeMittelpunkt(0, 0);
    }
    public void b(){
        for(int i=0;i<stein.length;i++){
            //for(int j=0;j<stein[i].steine.length; j++){
                
                stein[i].steine=null;
            
            //}
            
            
        }
    }
    void c(){
        stein[0].setzeMittelpunkt(0, 3);
    }
}
