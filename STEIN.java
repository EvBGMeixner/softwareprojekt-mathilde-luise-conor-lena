

public class STEIN
{
    FIGUR[] steine;
    int anzahlx;
    int anzahly;
    double mx;
    double my;
    public STEIN()
    {
        steine = new FIGUR[0];
    }
    public void setzeGroesse(int ax, int ay){
        anzahlx=ax;
        anzahly=ay;
        steine=new FIGUR[ax*ay];
        
        int z=0;
        for(int i=0; i<ay;i++){
            for(int j=0; j<ax;j++){
                steine[z] = new FIGUR("extensions2/Stein.jpg");
                steine[z].setzeMittelpunkt(j,-i*0.5);
                steine[z].machePassiv();
                z++;
                
            }
        }
    }
    public void setzeGroesse2(int ax, int ay){
        anzahlx=ax;
        anzahly=ay;
        steine=new FIGUR[ax*ay];
        
        int z=0;
        for(int i=0; i<ay;i++){
            for(int j=0; j<ax;j++){
                steine[z] = new FIGUR("extensions2/Stein.jpg");
                steine[z].setzeMittelpunkt(0.5*j,-i);
                steine[z].machePassiv();
                steine[z].drehenUm(90);
                z++;
                
            }
        }
    }
    public double nennemx(){
        int z=(anzahlx-1)/2;
        int k=(anzahly-1)/2;
        return(steine[z+k*anzahlx].nenneMittelpunktX());
    }
    public double nennemy(){
        int z=(anzahlx-1)/2;
        int k=(anzahly-1)/2;
        return(steine[z+k*anzahlx].nenneMittelpunktY());
    }
    public void setzeMittelpunkt(double x, double y){
        if(steine.length>0){
        double mx=nennemx();
        double my=nennemy();
        
        for(int i=0;i<steine.length;i++){
            steine[i].verschieben(x-mx, y-my);
            
        }
        }
        
    }
    public void setzeMittelpunkt2(double x, double y){
        if(steine.length>0){
        for(int i=0;i<steine.length;i++){
            steine[i].setzeMittelpunkt(x, y);
            
        }
        }
        
    }
    public void drehen(){
        for(int i=0;i<steine.length;i++){
            steine[i].drehenUm(90);
            
        }
    }
    public void macheDynamisch(){
        for(int i=0;i<steine.length;i++){
            steine[i].macheDynamisch();
            
        }
    }
    public void setzeSichtbar(boolean x){
        for(int i=0;i<steine.length;i++){
            steine[i].setzeSichtbar(x);
            
        }
    }
    public void machePassiv(){
        for(int i=0;i<steine.length;i++){
            steine[i].machePassiv();
            
        }
    }
}
