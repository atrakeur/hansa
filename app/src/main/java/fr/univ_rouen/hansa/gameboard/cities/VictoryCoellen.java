package fr.univ_rouen.hansa.gameboard.cities;

import fr.univ_rouen.hansa.gameboard.Privillegium;
import fr.univ_rouen.hansa.gameboard.player.pawns.Merchant;

public class VictoryCoellen {

    private static final int WHITE = 7;
    private static final int ORANGE = 8;
    private static final int PINK = 9;
    private static final int BLACK = 11;

    private final Privillegium privillegiumRequis;
    private Merchant merchant;
    private final int value;

    public VictoryCoellen(Privillegium privillegiumRequis) {
        this.privillegiumRequis = privillegiumRequis;
        switch (privillegiumRequis) {
            case White:
                this.value = WHITE;
                break;
            case Orange:
                this.value = ORANGE;
                break;
            case Pink:
                this.value = PINK;
                break;
            default:
                this.value = BLACK;
                break;
        }
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }
}
