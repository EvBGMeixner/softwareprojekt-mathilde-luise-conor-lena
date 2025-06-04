

public class INFO
{
    
    FIGUR figur;
    TEXT text;
    public INFO(double x, double y, String inhalt)
    {
        figur=new FIGUR("a", "extensions2/Bild", 1, 1);
        figur.setzeMittelpunkt(x, y);
        figur.setzeEbenenposition(-1);
        figur.skaliere(0.2);
        text=new TEXT(x,y,0.5,inhalt);
        text.setzeSichtbar(false);
    }
    public void textAnzeigen(){
        text.setzeSichtbar(true);
    }
    public void textVerbergen(){
        text.setzeSichtbar(false);
    }
    public void setzeMittelpunkt(double x, double y){
        figur.setzeMittelpunkt(x, y);
        text.setzeMittelpunkt(x-0.5, y+2);
    }
    public void setzeInhalt(String inhalt){
        text.setzeInhalt(inhalt);
    }
}
