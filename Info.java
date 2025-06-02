
public class Info
{
    TEXT text;
    int x;
    int y;
    String inhalt;
    public Info(int _x, int _y, String _inhalt)
    {
        x=_x;
        y=_y;
        inhalt=_inhalt;
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
