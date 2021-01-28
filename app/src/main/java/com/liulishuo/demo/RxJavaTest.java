package com.liulishuo.demo;


import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
public class RxJavaTest {
    private String TAG ="RxJavaTest";


    // 1. 创建被观察者 Observable 对象
    Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
        // 2. 在复写的subscribe（）里定义需要发送的事件
        @Override
        public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onNext(4);
            emitter.onComplete();
        }
    });

//   <--方式1：采用Observer 接口 -->

    // 1. 创建观察者 （Observer ）对象
    Observer<Integer> observer = new Observer<Integer>() {
        // 2. 创建对象时通过对应复写对应事件方法 从而 响应对应事件

        // 观察者接收事件前，默认最先调用复写 onSubscribe（）
        @Override
        public void onSubscribe(Disposable d) {
            Log.d("TAG", "开始采用subscribe连接");
        }

        // 当被观察者生产Next事件 & 观察者接收到时，会调用该复写方法 进行响应
        @Override
        public void onNext(Integer value) {
            Log.d("TAG", "对Next事件作出响应" + value);
        }

        // 当被观察者生产Error事件& 观察者接收到时，会调用该复写方法 进行响应
        @Override
        public void onError(Throwable e) {
            Log.d("TAG", "对Error事件作出响应");
        }

        // 当被观察者生产Complete事件& 观察者接收到时，会调用该复写方法 进行响应
        @Override
        public void onComplete() {
            Log.d("TAG", "对Complete事件作出响应");
        }
    };

//<--方式2：采用Subscriber 抽象类 -->
// 说明：Subscriber类 = RxJava 内置的一个实现了 Observer 的抽象类，对 Observer 接口进行了扩展

    // 1. 创建观察者 （Observer ）对象
    Subscriber<Integer> subscriber = new Subscriber<Integer>() {

        // 2. 创建对象时通过对应复写对应事件方法 从而 响应对应事件
        // 观察者接收事件前，默认最先调用复写 onSubscribe（）
        @Override
        public void onSubscribe(Subscription s) {
            Log.d("TAG", "开始采用subscribe连接");
        }

        // 当被观察者生产Next事件 & 观察者接收到时，会调用该复写方法 进行响应
        @Override
        public void onNext(Integer value) {
            Log.d("TAG", "对Next事件作出响应" + value);
        }

        // 当被观察者生产Error事件& 观察者接收到时，会调用该复写方法 进行响应
        @Override
        public void onError(Throwable e) {
            Log.d("TAG", "对Error事件作出响应");
        }

        // 当被观察者生产Complete事件& 观察者接收到时，会调用该复写方法 进行响应
        @Override
        public void onComplete() {
            Log.d("TAG", "对Complete事件作出响应");

        }
    };

//<--特别注意：2种方法的区别，即Subscriber 抽象类与Observer 接口的区别 -->
// 相同点：二者基本使用方式完全一致（实质上，在RxJava的 subscribe 过程中，Observer总是会先被转换成Subscriber再使用）
// 不同点：Subscriber抽象类对 Observer 接口进行了扩展，新增了两个方法：
    // 1. onStart()：在还未响应事件前调用，用于做一些初始化工作
    // 2. unsubscribe()：用于取消订阅。在该方法被调用后，观察者将不再接收 & 响应事件
    // 调用该方法前，先使用 isUnsubscribed() 判断状态，确定被观察者Observable是否还持有观察者Subscriber的引用，如果引用不能及时释放，就会出现内存泄露


    //    通过订阅（Subscribe）连接观察者和被观察者
    public void subscriber(){

        observable.subscribe(observer);
//       subscriber() 做了3件事：
//       调用 Subscriber.onStart() 。这个方法在前面已经介绍过，是一个可选的准备方法。
//       调用 Observable 中的 OnSubscribe.call(Subscriber) 。在这里，事件发送的逻辑开始运行。从这也可以看出，在 RxJava 中，
//       Observable 并不是在创建的时候就立即开始发送事件，而是在它被订阅的时候，即当 subscribe() 方法执行的时候。
//       将传入的 Subscriber 作为 Subscription 返回。这是为了方便 unsubscribe().
    }





    public void setSubscriber() {
        // RxJava的链式操作

        Observable.create(new ObservableOnSubscribe<Integer>() {
            // 1. 创建被观察者 & 生产事件
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        })
//                .subscribeOn(Schedulers.io())
                .flatMap((Function<Integer, ObservableSource<Integer>>) integer -> null)
                .doOnNext(integer -> Log.d("Aoli", "subscribeOn single" + Thread.currentThread()))
                .subscribe(new Observer<Integer>() {
            // 2. 通过通过订阅（subscribe）连接观察者和被观察者
            // 3. 创建观察者 & 定义响应事件的行为
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "开始采用subscribe连接");
            }
            // 默认最先调用复写的 onSubscribe（）

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "对Next事件" + value + "作出响应");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }

        });

    }
}


