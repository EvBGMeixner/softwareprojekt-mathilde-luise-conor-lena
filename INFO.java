
public class INFO
{
    TEXT text;
    RECHTECK rechteck;
    int x;
    int y;
    String inhalt;
    public INFO(int _x, int _y, String _inhalt)
    {
        x=_x;
        y=_y;
        inhalt=_inhalt;
        rechteck=new RECHTECK(1,1);
    }
    public void textAnzeigen(){
        if(text==null){
            text=new TEXT(x,y,1,inhalt);
        }
        else{
            text.setzeMittelpunkt(x, y);
            text.setzeInhalt(inhalt);
        }
    }
}
