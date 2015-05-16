package fr.univ_rouen.hansa.view;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.activity.MainMenuActivity;
import fr.univ_rouen.hansa.gameboard.board.GameBoardFactory;
import fr.univ_rouen.hansa.save.SaveManager;

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
                new SaveManager().save(GameBoardFactory.getGameBoard());
                this.dismiss();
                break;
            }
            case R.id.b_rules: {
                showPdf();
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

    public void showPdf() {
        File pdfFile = getFilePdf();
        Uri path = Uri.fromFile(pdfFile);
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        try {
            context.startActivity(pdfIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, context.getString(R.string.noApplicationPDF), Toast.LENGTH_LONG).show();
        }
    }

    private File getFilePdf() {
        InputStream in = context.getResources().openRawResource(R.raw.rulesfr);
        FileOutputStream out = null;
        byte[] buff = new byte[1024];
        int read = 0;
        try {
            out = new FileOutputStream(context.getString(R.string.rulesFile));
            while ((read = in.read(buff)) > 0) {
                out.write(buff, 0, read);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File(context.getString(R.string.rulesFile));
    }
}
