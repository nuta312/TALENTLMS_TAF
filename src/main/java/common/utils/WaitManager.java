package common.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaitManager {

    public static void pauseInSeconds(Integer seconds) {
        try {
            log.info("Waiting for {} seconds", seconds);
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}