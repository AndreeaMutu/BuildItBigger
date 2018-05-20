package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

@RunWith(AndroidJUnit4.class)
public class JokeAsyncTaskTest {
    private CountDownLatch countDownLatch = new CountDownLatch(1);


    @Test
    public void testJokeAsyncTaskJokeText() {

        new JokeEndpointAsyncTask().execute(new IEndpointCallback() {
            @Override
            public void onResultReady(String result) {
                Assert.assertThat(result, Matchers.not(Matchers.isEmptyOrNullString()));
                Assert.assertThat(result, Matchers.equalTo("What did the router tell the doctor? \n- It hurts when IP!"));
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            Log.e(JokeAsyncTaskTest.class.getSimpleName(), "testJokeAsyncTaskJokeText error: ", e);
        }
    }
}
