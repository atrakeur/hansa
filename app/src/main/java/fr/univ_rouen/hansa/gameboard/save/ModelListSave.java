package fr.univ_rouen.hansa.gameboard.save;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.List;

import fr.univ_rouen.hansa.R;
import fr.univ_rouen.hansa.activity.GameActivity;
import fr.univ_rouen.hansa.activity.LoadActivity;
import fr.univ_rouen.hansa.gameboard.bonusmarkers.IBonusMarker;
import fr.univ_rouen.hansa.gameboard.cities.ICity;
import fr.univ_rouen.hansa.gameboard.cities.IKontor;
import fr.univ_rouen.hansa.gameboard.player.IHTPlayer;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IEscritoire;
import fr.univ_rouen.hansa.gameboard.player.escritoire.IPawnList;
import fr.univ_rouen.hansa.gameboard.player.pawns.Pawn;
import fr.univ_rouen.hansa.gameboard.routes.IRoute;
import fr.univ_rouen.hansa.gameboard.routes.IVillage;


public class ModelListSave extends BaseAdapter {
    private LayoutInflater myInflater;
    private Intent game;
    private Context context;
    private List<Save> data;

    public ModelListSave(Context context, List<Save> mydata) {
        data = mydata;
        this.context = context;
        this.myInflater = LayoutInflater.from(context);
        this.game = new Intent(context, GameActivity.class);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {

        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        final int position = pos;
        ViewHolder holder;
        if (convertView == null) {
            convertView = myInflater.inflate(R.layout.list_save, null);
            holder = new ViewHolder();
            holder.item = (TextView) convertView.findViewById(R.id.item);
            holder.delete = (ImageView) convertView.findViewById(R.id.delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (getItem(position) != null) {
            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
            int indice = position + 1;

            holder.item.setText(" La sauvegarde " + indice + ": le " + dateFormat.format(data.get(position).getDate()));

            holder.item.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    String s = serialiseGamesave(data.get(position).getBoard());
                    bundle.putString("board", s);
                    game.putExtras(bundle);

                    Toast toast = Toast.makeText(context, R.string.load_toast, Toast.LENGTH_SHORT);
                    toast.show();
                    context.startActivity(game);

                }
            });

            holder.delete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    deleteSave(position);
                }
            });


        }
        return convertView;
    }


    private void deleteSave(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.delete_save);

        builder.setMessage(R.string.question_delete_save);
        builder.setPositiveButton(R.string.alert_confirm, new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                SaveGame saveGame = null;
                try {
                    saveGame = SaveGame.getSaveGame(context.getApplicationContext());
                    Toast toast = Toast.makeText(context, R.string.delete_toast, Toast.LENGTH_SHORT);
                    toast.show();
                    saveGame.remove(position, context);

                    Intent reloadIntent = new Intent(context, LoadActivity.class);
                    context.startActivity(reloadIntent);
                } catch (IOException e) {
                    Toast.makeText(context, R.string.error_load_save, Toast.LENGTH_SHORT).show();
                }


            }
        });
        builder.setNegativeButton(R.string.alert_cancel, null);
        builder.show();
    }

    private String serialiseGamesave(GameBoardSave boardSave) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(IRoute.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(IHTPlayer.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(IVillage.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(Pawn.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(IKontor.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(IEscritoire.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(IBonusMarker.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(IPawnList.class,
                        new GameBoardSaveSerializer())
                .registerTypeAdapter(ICity.class,
                        new GameBoardSaveSerializer()).excludeFieldsWithoutExposeAnnotation().create();

        Type typeSave = new TypeToken<GameBoardSave>() {
        }.getType();
        return gson.toJson(boardSave, typeSave);
    }


    public static class ViewHolder {
        public TextView item;
        public ImageView delete;
    }
}
