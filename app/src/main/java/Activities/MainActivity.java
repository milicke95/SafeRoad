package Activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.milen.saferoad.R;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import WebAPI.DataProvider;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.safe_road_activity);
        setContentView(R.layout.main_activity);


        timer = new CountDownTimer(3000, 20) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                try {
                    //getData();
                    boolean chk = false; //if server returns data

                    switchLayout(chk);
                    Log.d("Message", "Logging in 5 sec...");
                    timer.start();
                } catch (Exception e) {
                    Log.e("Error", "Error: " + e.toString());
                    //timer.start();
                }
            }
        }.start();

    }

    private void getData()
    {
        Call<JsonObject> call = DataProvider.apiService.getData();
        try {
            JsonObject response = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful())
                {

                }
                else
                    Log.e("API", "Error: response unsuccessful");
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("API", "Error: " + t.getMessage());
                call.cancel();
            }
        });*/
    }

    private void switchLayout(boolean check)
    {
        if(check)
            setContentView(R.layout.safe_road_activity);
        else
            setContentView(R.layout.main_activity);

        /*TextView date = (TextView) findViewById(R.id.textView2);
        TextView time = (TextView) findViewById(R.id.textView3);

        TextView crossroad = (TextView) findViewById(R.id.textView9);
        TextView address = (TextView) findViewById(R.id.textView10);
        TextView city = (TextView) findViewById(R.id.textView11);

        TextView departure = (TextView) findViewById(R.id.textView17);
        TextView speed = (TextView) findViewById(R.id.textView18);
        TextView crossTime = (TextView) findViewById(R.id.textView19);

        Date currentTime = Calendar.getInstance().getTime();

        date.setText(currentTime.toString());
        time.setText(currentTime.toString());*/
    }

    private void vibrate()
    {
        //TODO : Bluetooth communication
    }
}
