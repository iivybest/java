package edu.hit.adv.swing;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Test extends JFrame implements ActionListener, HyperlinkListener {
    private static final long serialVersionUID = -6322158783270080268L;

    private JLabel labUrl = new JLabel("URL 地址:");
    private JTextField txtUrl = new JTextField(20);
    private JEditorPane ep = new JEditorPane();
    private JPanel panel = new JPanel();

    public Test() {
        txtUrl.addActionListener(this);
        ep.addHyperlinkListener(this);

        panel.setLayout(new BorderLayout());
        panel.add(labUrl, BorderLayout.WEST);
        panel.add(txtUrl, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.NORTH);
        this.add(new JScrollPane(ep), BorderLayout.CENTER);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        URL url;
        try {
            url = new URL(txtUrl.getText());
//			System.getProperties().setProperty("http.proxyHost", "172.28.88.2");
//			System.getProperties().setProperty("http.proxyPort", "80");

            ep.setPage(url);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
    @Override
    public void hyperlinkUpdate(HyperlinkEvent e) {
        URL url = e.getURL();
        txtUrl.setText(url.toString());
        try {
            ep.setPage(url);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        // ...这里就可以写到达这个swing系统中其他panel页面的代码
    }

    public static void main(String[] args) {
        Test frame = new Test();
        frame.setTitle("简单的浏览器");
        frame.setSize(640, 480);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}