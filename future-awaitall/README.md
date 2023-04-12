# 使用 Java CompletableFuture 實現非同步執行緒 await all

`mySleep()` 會根據參數 i 讓執行緒等待 i 秒：

```java
  public static void mySleep(long i) {
    try {
      System.out.println("Wait " + i + " secs");
      TimeUnit.SECONDS.sleep(i);
      System.out.println("finished " + i);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
```

`completableFutureMySleep()` 是 `mySleep()` 改寫成非同步的方法，`Executors.newSingleThreadExecutor()` 會使得每次呼叫此方法都會產生一個新的執行緒跑程式碼，`exec.shutdown();` 則會在程式碼跑完時結束執行緒：

```java
  public static CompletableFuture<Void> completableFutureMySleep(long i) {
    ExecutorService exec = Executors.newSingleThreadExecutor();

    return CompletableFuture.runAsync(() -> {
      mySleep(i);
      exec.shutdown();
    }, exec);
  }
```

`main()` 會比較同步和非同步兩種差異，可以看到第一段落每執行一次 `mySleep()` 都需要等待上一個程式碼跑完才會開始執行，而 `CompletableFuture.allOf().join()` 則可以同時執行三個執行緒，並且只會等待最久的執行緒完成：

```java
  public static void main(String[] args) {
    SpringApplication.run(FutureAwaitallApplication.class, args);

    System.out.println("Without await all process");
    long start = System.currentTimeMillis();
    mySleep(1);
    mySleep(2);
    mySleep(3);
    System.out.println("exec time: " + (System.currentTimeMillis() - start) + " ms");

    System.out.println("Await all process");
    start = System.currentTimeMillis();
    CompletableFuture.allOf(completableFutureMySleep(1),
        completableFutureMySleep(2),
        completableFutureMySleep(3)).join();

    System.out.println("exec time: " + (System.currentTimeMillis() - start) + " ms");

  }
```

結果：

```
Without await all process
Wait 1 secs
finished 1
Wait 2 secs
finished 2
Wait 3 secs
finished 3
exec time: 6001 ms
Await all process
Wait 1 secs
Wait 2 secs
Wait 3 secs
finished 1
finished 2
finished 3
exec time: 3012 ms
```
