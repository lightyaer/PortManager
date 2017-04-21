/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portmanager;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 *
 * @author dhana
 */
public class PortManager extends Application {

    @Override
    @SuppressWarnings("empty-statement")
    public void start(Stage primaryStage) throws InterruptedException, ExecutionException {

        Button btn = new Button();
        TextField tf = new TextField();
        TextArea ta = new TextArea();
        
        ta = pscan(ta);
        
        tf.setText("Enter Port Number");
        tf.setPadding(new Insets(5, 5, 5, 5));
        btn.setText("Submit");
        btn.setAlignment(Pos.TOP_RIGHT);

        btn.setOnAction(e -> System.out.println("Port Selected"));

        BorderPane layout = new BorderPane();
        HBox tfandb = new HBox(40);
        tfandb.getChildren().add(tf);
        tfandb.getChildren().add(btn);
        layout.setCenter(ta);
        layout.setBottom(tfandb);

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setResizable(true);
        primaryStage.setTitle("Port Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);

    }

    public static Future<ScanResult> portIsOpen(final ExecutorService es, final String ip, final int port,
            final int timeout) {
        return es.submit(() -> {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(ip, port), timeout);
                socket.close();
                return new ScanResult(port, true);
            } catch (Exception ex) {
                return new ScanResult(port, false);
            }
        });
    }

    public TextArea pscan(TextArea ta) throws InterruptedException, ExecutionException {
            
        TextArea temp = ta;
            
        try {
            ;
            int i = Integer.parseInt(JOptionPane.showInputDialog("Enter the starting range of port scan, MIN 0"));;
            int j = Integer.parseInt(JOptionPane.showInputDialog("Enter the ending range of port scan, MAX 65535"));;

            final ExecutorService es = Executors.newFixedThreadPool(30);
            final String ip = "127.0.0.1";
            final int timeout = 200;
            final List<Future<ScanResult>> futures = new ArrayList<>();

            for (int port = i; port <= j; port++) {
                // for (int port = 1; port <= 80; port++) {
                futures.add(portIsOpen(es, ip, port, timeout));
            }
            es.awaitTermination(200L, TimeUnit.MILLISECONDS);
            int openPorts = 0;
            for (final Future<ScanResult> f : futures) {
                try {
                    if (f.get().isOpen()) {
                        openPorts++;
                        System.out.println(f.get().getPort());
                        String s = Integer.toString(f.get().getPort());
                        temp.appendText(s +"\n");
                    }
                } catch (ExecutionException ex) {
                    Logger.getLogger(PortManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            System.out.println("There are " + openPorts + " open ports on host " + ip + " (probed with a timeout of "
                    + timeout + "ms)");
            
        } catch (InterruptedException ex) {
            Logger.getLogger(PortManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }

    public static class ScanResult {

        private int port;

        private boolean isOpen;

        public ScanResult(int port, boolean isOpen) {
            super();
            this.port = port;
            this.isOpen = isOpen;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public boolean isOpen() {
            return isOpen;
        }

        public void setOpen(boolean isOpen) {
            this.isOpen = isOpen;
        }

    }

}
