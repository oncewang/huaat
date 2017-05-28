package net.huaat.common.util;
import java.io.InputStream;
import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.BlobMessage;
/**  
 * @Description: TODO
 * @author zhiqiang yang 
 * @date 2012-7-30 下午04:45:08
 * @version V1.0  
 */
public class JmsUtil {
	/**
	 * 是否采用事务 
	 */
	private static boolean transacted = false;
	
	public static Connection getConnection(String user, String password,String url) throws JMSException {
		if (url==null) {
			url = ActiveMQConnection.DEFAULT_BROKER_URL;
		}
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
		return connectionFactory.createConnection();
	}
	/**
	 * 单一消息发送对象，采用Session.AUTO_ACKNOWLEDGE模式，保证消息发送的可靠性
	 * @param targetObj
	 * @param queueName
	 * @param connection
	 * @throws JMSException
	 */
	public static void sendObjectMessage(Serializable targetObj,String queueName,Connection connection) throws JMSException { 
		
		Session session = connection.createSession(transacted,Session.AUTO_ACKNOWLEDGE);
		//Session session = connection.createSession(transacted,Session.DUPS_OK_ACKNOWLEDGE);
		
		MessageProducer producer = session.createProducer(session.createQueue(queueName));
		ObjectMessage objectMessage = session.createObjectMessage();
		objectMessage.setObject(targetObj);
		producer.send(objectMessage);
		if(transacted){
			session.commit();
		}
	}
	/**
	 *  Send BlobMessage, which allows massive BLOBs (Binary Large OBjects) to be sent around in some out-of-band transport mechanism<br>
	 *  tcp://127.0.0.1:61616?jms.blobTransferPolicy.defaultUploadUrl=http://127.0.0.1:8161/fileserver/
	 */
	public static void sendBlobMessage(InputStream in,String queueName,Connection connection){
		try {
			//http://activemq.apache.org/blob-messages.html
			ActiveMQSession session = (ActiveMQSession) connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			Destination destination=session.createQueue(queueName);
			
			BlobMessage blobMessage = session.createBlobMessage(in); 
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			producer.send(blobMessage);
		} catch (Exception e) {
			throw new RuntimeException("Send blob message error:"+e.getMessage());
		}
		
	}
	/**
	 * 发送字符串
	 * @param connection
	 * @param queueName
	 * @param msgStr
	 * @throws JMSException
	 */
	public static void sendTextMessage(Connection connection,String queueName,String msgStr) throws JMSException {
		
		Session session = connection.createSession(transacted,Session.AUTO_ACKNOWLEDGE);
		
		Destination destination = session.createQueue(queueName);
		
		MessageProducer producer = session.createProducer(destination);
		 
		Message msg = session.createTextMessage(msgStr);
		
		producer.send(msg);
		
		if(transacted){
			session.commit();
		}
	}
	
	public static void receiveObjectMessage(Connection connection,String queueName) {
	}
	
	public static void closeAll(Connection connection) {
		if(connection!=null){
			try {
				connection.close();
			} catch (JMSException e) { 
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws JMSException { 
		/*Car car = new Car();
		car.setName("toyota"); 
		Connection connection = getConnection(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
		sendObjectMessage(car, "test",connection );
		closeAll(connection);*/
	}
}

