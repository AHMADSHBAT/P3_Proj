public class FPS_Player extends Playerabs implements Player
{
    String name;
    int HP;
    int points;
    boolean Win = false;
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

    public int getPoints() {
        return points;
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
        this.points += p;
    }

    public String getName()
    {
        return this.name;
    }

    public int getHP() {
        return HP;
    }

    public void defeated()
    {
        this.HP = 0;
    }

}
