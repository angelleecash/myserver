package info.chenliang.myserver;

import info.chenliang.myclient.MyClientHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class MyServer {
	 private static final Logger logger = Logger.getLogger(
	            MyClientHandler.class.getName());
	
	public String address;
	public int port;
	
	
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void start()
	{
        ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));

        // Set up the event pipeline factory.
        bootstrap.setPipelineFactory(new MyServerPipelineFactory());

        logger.info("myserver listening on "+address+" @ "+port);
        
        // Bind and start to accept incoming connections.
        bootstrap.bind(new InetSocketAddress(port));	
        
        logger.info("myserver started");
	}
	
	public void stop()
	{
		
	}
	
	public static void main(String[] args) {
		MyServer server = new MyServer();
		server.setAddress("localhost");
		server.setPort(8888);
		
		server.start();
	}

}
