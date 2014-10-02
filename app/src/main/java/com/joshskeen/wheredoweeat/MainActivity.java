package com.joshskeen.wheredoweeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;
import rx.Subscriber;


public class MainActivity extends Activity {

    @InjectView(R.id.what_should_i_eat_button)
    Button mWhatShouldIEatButton;
    private String TAG = "MainActivity";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.inject(this);
    }

    private void doStuff(String s) {
        System.out.println("yeeahh doing stuff to " + s);
    }

    private Observable<String> getTitle(String url) {
        return Observable.just("radddd url: " + url);
    }

    private Observable<List<String>> query(String query) {
        return Observable.create((Subscriber<? super List<String>> subscriber) -> {
            subscriber.onNext(Arrays.asList("foo", "bar", "qaz"));
        });
    }

}
