package info.chenliang.myserver;

import java.util.LinkedList;
import java.util.List;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyServerChannelMonitor extends SimpleChannelUpstreamHandler {
	private static Logger logger = LoggerFactory.getLogger(MyServerChannelMonitor.class);
	
	public List<Channel> channels = new LinkedList<Channel>();
	
	public static MyServerChannelMonitor instance = new MyServerChannelMonitor();
	
	private MyServerChannelMonitor()
	{
		instance = this;
	}

	private void removeChannel(String reason, Channel channel)
	{
		boolean removed = channels.remove(channel);
		if(removed)
		{
			logger.info("channel removed {} {}", reason, removed?"success":"fail");	
		}
		
	}
	
	private void addChannel(String reason, Channel channel)
	{
		channels.add(channel);
		logger.info("channel added {}", reason);
	}
	
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		
		addChannel("channelConnected", ctx.getChannel());
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception {
		removeChannel("channelDisconnected", ctx.getChannel());
	}

	@Override
	public void channelUnbound(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		removeChannel("channelUnbound", ctx.getChannel());
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		removeChannel("channelClosed", ctx.getChannel());
	}

	

}
