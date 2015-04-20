package fr.univ_rouen.hansa.actions;

public enum Actions {
    liberSophia,      //Move a pawn on the gameboard
    increasePower,    //Increase power from a city
    movePawnRtoGB,    //Place un pion sur le gameboard
    keepKontor,       //Prend un contoir de ville a partir d'une route
    bursa,            //Move a pawn from Stock to reserve
    movePawnRtoS,     //Move Supply (accessible) to Stock (unaccessible)
    endRound,         //End the turn
    playBonus,        //Play a bonus jeton
    keepRoute,        //Take a route from a route
    replaceMovedPawn, //Replace a pawn that has been moved from another player
    validateMovedPawn //Validate that all pawn has been replaced
}
