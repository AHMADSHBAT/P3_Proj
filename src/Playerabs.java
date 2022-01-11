public abstract class Playerabs implements Player
{
    String name;
    int HP;
    int points;
    public void gotDamage(int d){
        this.HP -= d;
    }
    public void setHP(int hp){
        this.HP = hp;
    }
    public void defeated(){
        this.HP = 0;
    }

    public int getHP()
    {
        return HP;
    }
}
