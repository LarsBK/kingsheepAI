package kingsheep;

import java.awt.Color;

class Player {

    int score;
    final Creature sheep;
    final Creature wolf;
    final Color color;

    Player(Creature sheep, Creature wolf, Color color) {
        this.score = 0;
        this.sheep = sheep;
        this.wolf = wolf;
        this.color = color;
    }
}