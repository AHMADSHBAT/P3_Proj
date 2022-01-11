public class EnemyPlayer implements Player
{
    int HP;
    int index;
    EnemyPlayer(){}
    EnemyPlayer(int hp){ this.HP = hp; }
    @Override
    public void gotDamage(int d) {
        this.HP -= d;
    }

    @Override
    public void setHP(int hp) {
        this.HP = hp;
    }
    public void setIndex(int i)
    {
        this.index = i;
    }



    @Override
    public void defeated() {
        this.HP = 0;
    }

    @Override
    public int getHP() {
        return this.HP;
    }
}
