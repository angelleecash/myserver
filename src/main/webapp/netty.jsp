<%@page import="org.jboss.netty.channel.Channel"%>
<%@page import="info.chenliang.myserver.MyServerChannelMonitor"%>
<%@page import="info.chenliang.myserver.MyServer"%>

<html>

<% 

MyServerChannelMonitor monitor = MyServerChannelMonitor.instance;
for(int i=0; i < monitor.channels.size();i++)
{
	Channel channel = monitor.channels.get(i);
	out.println("<p>"+"channel " + i +"</p>");
}
%>
</html>