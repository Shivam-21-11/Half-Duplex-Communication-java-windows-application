package com.ntw.conn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class clit extends JFrame {

    private JButton connectButton;
    private JButton sendButton;
    private JList list1;
    private JTextField textField1;
    private JPanel mainp;
    private Socket socket = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    DefaultListModel dml = new DefaultListModel();
    String line = " ";
    public clit() {
        setContentPane(mainp);
        setTitle("Wellcome");
        setSize(450,350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    socket = new Socket("127.0.0.1",2025);
                    dml.addElement("Connected");
                    list1.setModel(dml);
                    dis = new DataInputStream(System.in);
                    dos = new DataOutputStream(socket.getOutputStream());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                line = textField1.getText();
                if(!line.equals("Q")){
                    try {
//                        line = dis.readLine();
                        dos.writeUTF(line);
                        dos.flush();
                        dml.addElement(line);
                        list1.setModel(dml);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }else{
                    try{
                        dos.writeUTF(line);
                        dos.flush();
                        dml.addElement("Closing....");
                        list1.setModel(dml);
                        socket.close();
                        dos.close();
                        dis.close();
                        dml.addElement("Disconnected.....");
                        list1.setModel(dml);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
    }
    public static void main(String args[]){
        clit ct = new clit();
    }
}
