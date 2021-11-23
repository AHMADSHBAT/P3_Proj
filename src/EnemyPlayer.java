public class EnemyPlayer implements Player
{
    int HP;
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



    @Override
    public void defeated() {
        this.HP = 0;
    }
}
