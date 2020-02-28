package com.trilogy.bootcampspot.domain.usecase;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;

public abstract class SingleUseCase<T, Params> implements UseCase<DisposableSingleObserver<T>, Params> {

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private final CompositeDisposable disposable;

    public SingleUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.disposable = new CompositeDisposable();
    }

    public abstract Single<T> buildObservable(Params params);

    @Override
    public void execute(DisposableSingleObserver<T> observer, Params params) {
        final Single<T> observable = initObservable(params);
        addDisposable(observable.subscribeWith(observer));
    }

    public void execute(DisposableSingleObserver<T> observer) {
        execute(observer, null);
    }


    public void execute(Params params, Consumer<T> onNext) {
        addDisposable(initObservable(params).subscribe(onNext));
    }

    public void execute(Params params, Consumer<T> onNext, Consumer<? super Throwable> onError) {
        addDisposable(initObservable(params).
                subscribe(onNext, onError));
    }

    public void execute(Consumer<T> onNext) {
        addDisposable(initObservable().subscribe(onNext));
    }

    public void execute(Consumer<T> onNext, Consumer<? super Throwable> onError) {
        addDisposable(initObservable().subscribe(onNext, onError));
    }

    private Single<T> initObservable() {
        return initObservable(null);
    }

    private Single<T> initObservable(Params params) {
        return buildObservable(params)
                .subscribeOn(threadExecutor.getScheduler())
                .observeOn(postExecutionThread.getScheduler());
    }

    private void addDisposable(Disposable observer) {
        disposable.add(observer);
    }

    private void dispose() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void stop() {
        dispose();
    }
}
