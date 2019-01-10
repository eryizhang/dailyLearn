package test.netty.sixunhuan;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;


/**
 * @Auther: zhangyahui
 * @Date: 2019/1/8 11:35
 * @Description:
 */
public class Client {

    static String host = "127.0.0.1";
    static int port = 10010;
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //这里一定要加入这两个类，是用来给object编解码的，如果没有就无法传送对象
                        //并且，实体类要实现Serializable接口，否则也无法传输
                        ch.pipeline().addLast(new ObjectEncoder());
                        ch.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE,
                                ClassResolvers.weakCachingConcurrentResolver(null))); // 最大长度
                        ch.pipeline().addLast(new C_I_1());
                    }
                });
        try {
            Integer n = new Integer(1);
            ChannelFuture f =b.connect(host,port).sync();
            f.channel().writeAndFlush(n);
            System.out.println(n);
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }
/*---------------------
    作者：FishSeeker
    来源：CSDN
    原文：https://blog.csdn.net/FishSeeker/article/details/78447684
    版权声明：本文为博主原创文章，转载请附上博文链接！*/
}
