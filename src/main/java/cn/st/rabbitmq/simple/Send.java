package cn.st.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * <p>
 * description:消息发送
 * </p>
 * 
 * @author coolearth
 * @since 2015年4月22日
 * @version v1.0
 */
public class Send {
    private final static String QUEUE_NAME = "send.first";

    private final static String HOST = "192.168.44.131";
    private final static int PORT = 5672;
    private final static String USERNAME = "send";
    private final static String PASSWORD = "send";
    private final static String VHOST = "first-vhost";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        factory.setVirtualHost(VHOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }

}
