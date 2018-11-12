package com.cxxy.receive.consumer;

import com.cxxy.receive.constants.topics;
import com.cxxy.receive.util.DateUtil;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Author:liuhui
 * Description:
 * Date: 5:23 PM 2018/11/9
 */
@Component
public class consumer1 {

//    @JmsListener(destination = topics.COMMON_TEST_TOPIC, subscription = "commonTestTopic", containerFactory = "consumer1JmsContainerFactory")
    @JmsListener(destination = topics.COMMON_TEST_TOPIC)
    public void receiveMessage(String text) {

        System.out.println(DateUtil.getCurrentDateString("yyyy-MM-dd HH:mm:ss") + ",consumer1 收到的消息:" + text);
    }
}
