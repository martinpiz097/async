package org.netutils.async.main;

import org.netutils.async.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new AsyncTask<>(() ->
                new ServerSocket(4000)).execute()
                .then(result -> {
                    try {
                        Socket sock;
                        Scanner scan = new Scanner(System.in);
                        sock = result.accept();
                        OutputStream out = sock.getOutputStream();
                        while (true) {
                            out.write(scan.nextLine().getBytes());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).get();
        Socket sock = new Socket("localhost", 4000);
        InputStream inputStream = sock.getInputStream();
        int r;
        StringBuilder sbMsg = new StringBuilder();
        while (true)
            while ((r = inputStream.read()) != -1)
                System.out.print((char) r);
        //serverSocket.close();
        //System.out.println("Finish!");
    }
}
