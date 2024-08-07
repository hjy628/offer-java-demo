package chap1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @auther: hjy
 * @Date: 2020/5/8 14:43
 * @Description:
 */

public class MyClient {

    private int size = 1024;

    private SocketChannel socketChannel;
    private ByteBuffer byteBuffer;

    public void connectServer()throws IOException{
        socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8888));
        socketChannel.configureBlocking(false);
        byteBuffer = ByteBuffer.allocate(size);
        receive();
    }

    private void receive()throws IOException{
        while (true){
            byteBuffer.clear();
            int count;
            //如果没有数据可读，则read方法一直阻塞，直到读取新的数据
            while ((count = socketChannel.read(byteBuffer))>0){
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()){
                    System.out.print((char)byteBuffer.get());
                }
                send2Server("say hi".getBytes());
                byteBuffer.clear();
            }
        }
    }

    private void send2Server(byte[]bytes)throws IOException{
        byteBuffer.clear();
        byteBuffer.put(bytes);
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
    }

    public static void main(String[] args) throws IOException{
        new MyClient().connectServer();
    }


}
