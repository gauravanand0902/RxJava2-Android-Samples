package com.rxjava2.android.samples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class TakeExample extends AppCompatActivity {

    private static final String TAG = MapExample.class.getSimpleName();
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        btn = (Button) findViewById(R.id.btn);
        textView = (TextView) findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSomeWork();
            }
        });
    }

    private void doSomeWork() {
        getObservable()
                .take(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observable<Integer> getObservable() {
        return Observable.just(1, 2, 3, 4, 5);
    }

    private Observer<Integer> getObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                textView.append("onSubscribe : isDisposed :" + d.isDisposed());
                Log.d(TAG, "onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                textView.append("onNext : value : " + value);
                Log.d(TAG, "onNext value : " + value);
            }

            @Override
            public void onError(Throwable e) {
                textView.append("onError : " + e.getMessage());
                Log.d(TAG, "onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                textView.append("onComplete");
                Log.d(TAG, "onComplete");
            }
        };
    }


}