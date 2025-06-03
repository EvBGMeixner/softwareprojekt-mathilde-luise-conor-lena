

public class INFO
{
    String inhalt;
    FIGUR figur;
    TEXT text;
    public INFO(double x, double y, String _inhalt)
    {
        figur=new FIGUR("a", "extensions2/Bild", 1, 1);
        figur.setzeMittelpunkt(x, y);
        inhalt=_inhalt;
    }
    public void textAnzeigen(){
        if(text==null){
            text=new TEXT(0,5,1,inhalt);
        }
        else{
            
        }
    }
    
}
