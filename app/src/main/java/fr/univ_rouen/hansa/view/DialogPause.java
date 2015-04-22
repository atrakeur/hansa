package fr.univ_rouen.hansa.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.activity.MainMenuActivity;

public class DialogPause extends Dialog implements View.OnClickListener {

    private Context context;

    public DialogPause(Context context) {
        super(context);
        this.context = context;
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
                this.dismiss();

                Intent intent = new Intent(context, MainMenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                break;
            }
        }

    }
}
