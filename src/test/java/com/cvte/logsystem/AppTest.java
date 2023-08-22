package com.cvte.logsystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static com.cvte.logsystem.utils.AppUtils.getAppid;

@SpringBootTest
public class AppTest {

    @Test
    void appidOnlyTest(){
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 10000000; i++) {
            String appid = getAppid();
            if(set.contains(appid)){
                System.out.println("Appid repeated! Test Failed");
                return;
            }
            set.add(appid);
        }
        System.out.println("Appid test passed!");
    }

}
