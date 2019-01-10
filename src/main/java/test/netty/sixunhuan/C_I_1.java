package test.netty.sixunhuan;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther: zhangyahui
 * @Date: 2019/1/8 11:39
 * @Description:
 */
public class C_I_1 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Integer n = (Integer)msg;
        System.out.println("C in 111 get num = " + n);
        n++;
        //ctx.writeAndFlush(n);
    }
}
/*
---------------------
        作者：FishSeeker
        来源：CSDN
        原文：https://blog.csdn.net/FishSeeker/article/details/78447684
        版权声明：本文为博主原创文章，转载请附上博文链接！*/
