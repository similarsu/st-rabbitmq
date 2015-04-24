package cn.st.rabbitmq.api.test;

import org.junit.Test;

import cn.st.rabbitmq.api.RabbitAPIUtils;

/**
 * <p>
 * description:api 测试类
 * </p>
 * 
 * @author coolearth
 * @since 2015年4月24日
 * @version v1.0
 */
public class RabbitAPIUtilsTest {
    @Test
    public void vhosts() throws Exception {
        RabbitAPIUtils rabbitAPIUtils = new RabbitAPIUtils();
        rabbitAPIUtils.getVhosts();
    }

    @Test
    public void createVhost() throws Exception {
        RabbitAPIUtils rabbitAPIUtils = new RabbitAPIUtils();
        rabbitAPIUtils.createVhost("test");
    }
}
