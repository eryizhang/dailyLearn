package test.netty.sixunhuan.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @Auther: zhangyahui
 * @Date: 2019/1/8 11:47
 * @Description:
 */
public class S_O_2 extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        Integer n = (Integer)msg;
        System.out.println("S out222 get num = " + n);
        n++;
        ctx.writeAndFlush(n);
    }
}
