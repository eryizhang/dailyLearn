package test.netty.sixunhuan.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther: zhangyahui
 * @Date: 2019/1/8 11:43
 * @Description:
 */
public class S_I_1 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Integer n = (Integer)msg;
        System.out.println("S in 111 get num = " + n);
        n++;
        ctx.fireChannelRead(n);
        //ctx.writeAndFlush(n);
    }
}
