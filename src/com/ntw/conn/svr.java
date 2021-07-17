package com.ntw.conn;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class svr extends JFrame {
    private JPanel mainp;
    private JList list1;
    private Socket socket = null;
    private ServerSocket ss = null;
    private DataInputStream in =null;
    DefaultListModel dlm = new DefaultListModel();
    public svr(){
        setContentPane(mainp);
        setTitle("Wellcome");
        setSize(450,350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        try{
            ss = new ServerSocket(2025);
            dlm.addElement("Server Started");
            dlm.addElement("Waiting For Client......");
            list1.setModel(dlm);
            socket = ss.accept();
            dlm.addElement("Connected ");
            list1.setModel(dlm);
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String line =" ";
            while (!line.equals("Q")){
                try{
                    line = in.readUTF();
                    dlm.addElement(line);
                    list1.setModel(dlm);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            dlm.addElement("Closing....");
            list1.setModel(dlm);
            socket.close();
            in.close();
            dlm.addElement("Disconnected....");
            list1.setModel(dlm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        svr svr = new svr();
    }

}
