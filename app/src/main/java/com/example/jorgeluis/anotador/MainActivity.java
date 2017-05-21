package com.example.jorgeluis.anotador;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.jorgeluis.anotador.Activities.MainActivityView;
import com.example.jorgeluis.anotador.Presenter.MainPresenter;
import com.example.jorgeluis.anotador.Services.MainActivityServices;
import com.example.jorgeluis.anotador.Util.AndroidFileManager;
import com.example.jorgeluis.anotador.Util.BusProvider;
import com.squareup.otto.Bus;




public class MainActivity extends AppCompatActivity {

    private MainPresenter presenter;
    private MainActivityView   view;
    private Bus bus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = new MainActivityView(this,BusProvider.getInstance());
        bus = BusProvider.getInstance();
        presenter = new MainPresenter(view,new MainActivityServices(new AndroidFileManager(this)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.register(presenter);
        bus.post(new onResumeMainActivity());
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.unregister(presenter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        bus.post(new onOptionsItemSelectedClick(item.getItemId()));
        return super.onOptionsItemSelected(item);
    }


    public static class onResumeMainActivity {
    }

    public static class onOptionsItemSelectedClick {
        public int itemId;
        public onOptionsItemSelectedClick(int itemId) {
            this.itemId=itemId;
        }
    }
}
