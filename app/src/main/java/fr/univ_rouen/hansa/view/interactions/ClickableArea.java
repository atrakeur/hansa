package fr.univ_rouen.hansa.view.interactions;

import android.app.AlertDialog;
import android.content.DialogInterface;

import fr.univ_rouen.hansa.actions.MovementManager;
import fr.univ_rouen.hansa.actions.movement.IMovement;
import fr.univ_rouen.hansa.activity.GameActivity;
import fr.univ_rouen.hansa.exceptions.FinishedRoundException;
import fr.univ_rouen.hansa.exceptions.GameException;
import fr.univ_rouen.hansa.gameboard.TurnManager;

public abstract class ClickableArea implements IClickableArea {

    private final Type type;
    private final GameActivity gameActivity;

    //TODO add id to implementation of Bonus Place
    public ClickableArea(Type type) {
        this.type = type;
        this.gameActivity = GameActivity.getInstance();
    }

    public Type getType() {
        return type;
    }

    protected void doMove(IMovement movement) {
        try {
            MovementManager.getInstance().doMove(movement);
        } catch(FinishedRoundException e) {
            AlertDialog alertDialog = new AlertDialog.Builder(gameActivity).create();
            alertDialog.setTitle("Tour fini");
            alertDialog.setMessage("Vous avez joué toutes vos actions, passer au tour suivant ?");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Oui",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            TurnManager.getInstance().nextPlayer(true);
                            dialog.dismiss();
                            gameActivity.onResume();
                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Non",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            gameActivity.onResume();
                        }
                    });
            alertDialog.show();
        } catch (GameException e) {
            e.printStackTrace();
        }
    }

}
