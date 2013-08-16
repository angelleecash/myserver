package info.chenliang.myserver;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyServer {
	private static Logger logger = LoggerFactory.getLogger(MyServer.class);
	
	public String address;
	public int port;
	
	public static MyServer instance;
	
	public ServerBootstrap bootstrap;
	public NioServerSocketChannelFactory channelFactory;
	public MyServerPipelineFactory pipelineFactory;
	
	public MyServer()
	{
		instance = this;
	}

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

	public void start() {
		channelFactory = new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());
		
		bootstrap = new ServerBootstrap(channelFactory);
		
		pipelineFactory = new MyServerPipelineFactory();
		bootstrap.setPipelineFactory(pipelineFactory);

		bootstrap.bind(new InetSocketAddress(port));

		logger.info("myserver started");
	}

	public void stop() {
		logger.info("myserver going to shutdown");
		
		bootstrap.shutdown();
		bootstrap.releaseExternalResources();
		
		logger.info("myserver shutdown");
	}

	public static void main(String[] args) {
		MyServer server = new MyServer();
		server.setPort(8888);

		server.start();
	}

}
