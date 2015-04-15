package fr.univ_rouen.hansa.exceptions;

public class PopupException extends RuntimeException {

    private final PopupType popupType;

    public enum PopupType {
        none,
        movementPawnRtoGB
    }

    public PopupException() {
        this.popupType = PopupType.none;
    }

    public PopupException(PopupType popupType) {
        this.popupType = popupType;
    }

    public PopupException(String message) {
        super(message);

        popupType = PopupType.none;
    }

    public PopupType getPopupType() {
        return popupType;
    }

}
