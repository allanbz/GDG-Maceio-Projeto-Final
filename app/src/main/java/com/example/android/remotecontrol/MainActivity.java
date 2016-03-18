package com.example.android.remotecontrol;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int volume = 0;
    int channel = 1;
    String power = "OFF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*************************************************************************************/

    //METODO PARA 'LIGAR' E 'DESLIGAR' O CONTROLE

    //Metodo para mudar a TextView de inicio (ON/OFF)
    public void power(View view) {
        if (power == "OFF") {
            TextView powerTextView = (TextView) findViewById(
                    R.id.power_text_view);
            powerTextView.setText("ON");
            powerTextView.setTextColor(Color.GREEN);
            power = "ON";

            return;
        }

        if (power == "ON") {
            TextView powerTextView = (TextView) findViewById(
                    R.id.power_text_view);
            powerTextView.setText("OFF");
            powerTextView.setTextColor(Color.RED);
            power = "OFF";

            return;
        }
    }

    /*************************************************************************************/

    //METODOS PARA MUDANCA DE VOLUME

    //Metodo para aumentar o volume e chamar o metodo que mostra o volume (Textview entre os botoes + e -)
    public void volume_increment(View view) {
        if (power == "OFF") {
            Toast.makeText(this, "You need to turn ON the TV before changing the volume", Toast.LENGTH_SHORT).show();
            return;
        }

        if (volume == 25) {
            Toast.makeText(this, "High Volume can cause hearing loss", Toast.LENGTH_SHORT).show();
            return;
        }

        volume++;
        displayVolume(volume);
    }

    //Metodo para diminuir o volume e chamar o metodo que mostra o volume (Textview entre os botoes + e -)
    public void volume_decrement(View view) {
        if (power == "OFF") {
            Toast.makeText(this, "You need to turn ON the TV before changing the volume", Toast.LENGTH_SHORT).show();
            return;
        }

        if (volume == 0) {
            return;
        }

        volume--;
        displayVolume(volume);
    }

    //Metodo para mudar o valor da TextView de volume;
    private void displayVolume(int volume) {
        TextView volumeTextView = (TextView) findViewById(
                R.id.volume_text_view);
        volumeTextView.setText("" + volume);
    }

    /*************************************************************************************/

    //METODOS PARA MUDANCA DE CANAL

    //Metodo para aumentar o canal e chamar o metodo que mostra o canal (Textview entre os botoes + e -)
    public void channel_increment(View view) {
        if (power == "OFF") {
            Toast.makeText(this, "You need to turn ON the TV before changing the channel", Toast.LENGTH_SHORT).show();
            return;
        }

        if (channel == 50) {
            channel = 1;
            displayChannel(channel);
            return;
        }

        channel++;
        displayChannel(channel);
    }

    //Metodo para diminuir o canal e chamar o metodo que mostra o canal (Textview entre os botoes + e -)
    public void channel_decrement(View view) {
        if (power == "OFF") {
            Toast.makeText(this, "You need to turn ON the TV before changing the channel", Toast.LENGTH_SHORT).show();
            return;
        }

        if (channel == 1) {
            channel = 50;
            displayChannel(channel);
            return;
        }

        channel--;
        displayChannel(channel);
    }

    //Metodo para mudar o valor da TextView de canal;
    private void displayChannel(int channel) {
        TextView channelTextView = (TextView) findViewById(
                R.id.channel_text_view);
        channelTextView.setText("" + channel);
    }

    /*************************************************************************************/

    //METODO PARA ZERAR O VOLUME AO PRESSIONAR BOTÃO MUTE
    public void mute(View view) {
        if (power == "OFF") {
            Toast.makeText(this, "You need to turn ON the TV before using Mute button", Toast.LENGTH_SHORT).show();
            return;
        }

        volume = 0;
        displayVolume(volume);
    }

    /*************************************************************************************/

    //METODO PARA CHAMAR INTENT AO CLICAR EM 'REPORT' E ABRIR EMAIL
    public void report(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Maintenance Request");
        intent.putExtra(Intent.EXTRA_TEXT, "I'm having problems with channel " + channel + ".");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /*************************************************************************************/

}

