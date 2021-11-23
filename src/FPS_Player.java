public class FPS_Player implements Player
{
    String name;
    int HP;
    int points;
    FPS_Player(){
        name = "untitled";
        HP = 0;
        points = 0;
    }
    FPS_Player(String n, int hp, int p)
    {
        name = n;
        HP = hp;
        points = p;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void gotDamage(int d) {
        this.HP -= d;
    }
    public void gotPoints(int p)
    {
        this.points -= p;
    }

    public void deffeted()
    {
        this.HP = 0;
    }

}
