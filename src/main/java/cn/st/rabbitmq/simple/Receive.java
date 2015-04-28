package cn.st.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * <p>
 * description:消息接收
 * </p>
 * 
 * @author coolearth
 * @since 2015年4月22日
 * @version v1.0
 */
public class Receive {
    private final static String QUEUE_NAME = "send.first";

    private final static String HOST = "192.168.44.131";
    private final static int PORT = 5672;
    private final static String USERNAME = "receive";
    private final static String PASSWORD = "receive";
    private final static String VHOST = "first-vhost";

    public static void main(String[] argv) throws java.io.IOException,
            java.lang.InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setVirtualHost(VHOST);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
        }
    }
}
