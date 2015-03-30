package fr.univ_rouen.hansa.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import fr.univ_rouen.hansa.R;

public class DialogPause extends Dialog implements View.OnClickListener {


    public DialogPause(Context context) {
        super(context);
        setContentView(R.layout.dialog_pause);
        setTitle(R.string.pause_title);
        findViewById(R.id.b_continue).setOnClickListener(this);
        findViewById(R.id.b_save).setOnClickListener(this);
        findViewById(R.id.b_rules).setOnClickListener(this);
        findViewById(R.id.b_quit).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_continue: {
                this.dismiss();
                break;
            }
            case R.id.b_save: {
                //TODO close this dialog & open save dialog
                break;
            }
            case R.id.b_rules: {
                //TODO close this dialog & Display rules
                break;
            }
            case R.id.b_quit: {
                //TODO intent to MainMenu
                break;
            }
        }

    }
}
