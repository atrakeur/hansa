package fr.univ_rouen.hansa.gameboard.bonusmarkers;

/**
 * Enum of Bonus pawns states
 */
public enum BonusState {

    //Pawn still in the stack of the game
    unused,
    //Pawn on the Board
    onBoard,
    //Pawn as been picked up this turn ( you can't play a bonus pawn that you've just picked up )
    pickedThisTurn,
    //Pawn in your plate : it has been draw and need to be placed before next player's turn
    inPlate,
    //Pawn has been picked up by a player
    onHand,
    //Pawn has been used by a player
    used
}
