package gui;

import Interface.*;
import Interface.impl.*;
import entity.*;
import entity.LoaiPhong;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Server {
    private static final int SERVER_PORT = 7900;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(SERVER_PORT)) {
            System.out.println("Server started on port " + SERVER_PORT);

            while (true) {
                Socket client1 = server.accept();

                System.out.println("Client connected");
                System.out.println("Client IP: " + client1.getInetAddress().getHostName());
                System.out.println("Client Port: " + client1.getPort());
                Thread t = new Thread(new PH(client1));
                t.start();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class PH implements Runnable {
    private Socket socket;
    private LoaiPhongDao loaiPhongDao;
    private PhongHatDao phongHatDao;


    public PH(Socket client1) {
        super();
        this.socket = client1;
        this.loaiPhongDao = new LoaiPhongImpl();
        this.phongHatDao = new PhongHatImpl();
    }

    @Override
    public void run() {
        try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {
            while (true) {
//                String request = (String) ois.readObject();
                String request = null;
                try {
                    request = (String) ois.readObject();
                } catch (ClassNotFoundException e) {
                    // Handle the case where the class of the serialized object is not found
                    e.printStackTrace(); // or log the error
                } catch (IOException e) {
                    // Handle IO-related errors
                    e.printStackTrace(); // or log the error
                }
                if ("<local4>".equals(null)) {
                    // Xử lý khi giá trị "<local4>" là null
                } else {
                    int hashCode = "<local4>".hashCode(); // Kiểm tra hashCode khi giá trị không null
                    // Tiếp tục xử lý với hashCode
                }
                switch (request) {
/**
 *
 * PhongHat
 */

//                    addPhongHat
                    case "addPhongHat":
                        PhongHat ph = (PhongHat) ois.readObject();
                        boolean result16 = phongHatDao.addPhongHat(ph);
                        oos.writeBoolean(result16);
                        break;
//                    editPhongHat
                    case "editPhongHat":
                        PhongHat ph2 = (PhongHat) ois.readObject();
                        boolean result13 = phongHatDao.editPhongHat(ph2);
                        oos.writeBoolean(result13);
                        break;

                    case "DeletePhongHat":
                        String maPH = (String) ois.readObject();
                        boolean result14 = phongHatDao.DeletePhongHat(maPH);
                        oos.writeBoolean(result14);
                        break;
                    //
                    case "getSoPhongTrong":
                        int soPhongTrong = phongHatDao.getSoPhongTrong();
                        oos.writeInt(soPhongTrong);
                        break;

                    case "getAllPhongHat":
                        ArrayList<PhongHat> phongHats = phongHatDao.getAllPhongHat();
                        oos.writeObject(phongHats);
                        break;
                    case "getPhongByTinhTrang":
                        String tinhTrang = (String) ois.readObject();
                        ArrayList<PhongHat> phongHatsByTinhTrang = phongHatDao.getPhongByTinhTrang(tinhTrang);
                        oos.writeObject(phongHatsByTinhTrang);
                        System.out.println("Server: " + phongHatsByTinhTrang.toString());
                        break;

                    case "getPhongHatByMaPhong":
                        String maPhong1 = (String) ois.readObject();
                        PhongHat ph11 = phongHatDao.getPhongHatByMaPhong(maPhong1);
                        oos.writeObject(ph11);
                        System.out.println("Server: " + ph11.toString());
                        break;

                    //updateTinhTrangPhong//updateTinhTrangPhong(String maPhong, String tinhTrangMoi)
                    case "updateTinhTrangPhong":
                        String maPhong2 = (String) ois.readObject();
                        String tinhTrangMoi = (String) ois.readObject();
                        boolean results = phongHatDao.updateTinhTrangPhong(maPhong2, tinhTrangMoi);
                        oos.writeBoolean(results);
                        break;


                }

            }

        } catch (IOException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
