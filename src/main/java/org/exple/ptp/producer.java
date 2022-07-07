package org.exple.ptp;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;


public class producer {
        @Test
        public void producer() throws JMSException {

        //1.创建链接工厂
        ConnectionFactory factory =new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        //2创建链接
        Connection connection=factory.createConnection();

        //3.打开链接
        connection.start();
        //4创建session
        Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5创建目标地址‘
        Queue queue =session.createQueue("test001");
        //6创建生产者
        MessageProducer messageProducer=session.createProducer(queue);
        //7创建消息
       TextMessage textMessage= session.createTextMessage("这是我的第3条消息，这是我的第4条消息");
        //8发送消息
        messageProducer.send(textMessage);
        System.out.println("消息发送成功");
        session.close();
        connection.close();

    }

    @Test
    public void consumer() throws JMSException{
        //1.创建链接工厂
        ConnectionFactory factory =new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        //2创建链接
        Connection connection=factory.createConnection();

        //3.打开链接
        connection.start();
        //4创建session
        Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5指定目标地址
       Queue queue= session.createQueue("test001");
       //6创建消费者
        MessageConsumer consumer=session.createConsumer(queue);
        //7接收数据
        while (true){
            Message message=consumer.receive();
            System.out.println("message======="+message);
            if (message==null){
                break;
            }else if (message instanceof TextMessage){
                TextMessage textMessage= (TextMessage) message;
                System.out.println("textMessage======"+textMessage.getText());
            }
        }

    }












}
