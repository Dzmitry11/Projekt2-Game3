package se.dzmitry.projekt2Game3;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.dzmitry.projekt2Game3.model.Burglar;
import se.dzmitry.projekt2Game3.model.Resident;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Resident resident;
    Burglar burglar;
    Game game;

    @org.junit.jupiter.api.Test
    @BeforeEach
    void executeAttack1() {
        game = new Game();
        resident = new Resident("Resident", 9,3);
        burglar = new Burglar("Burglar", 6, 3);
    }
    @Test
    void executeAttack2(){
        game.executeAttack (resident, burglar);
        assertEquals(3, burglar.getHealth());
    }

    //game.executeAttack(resident, burglar);

}