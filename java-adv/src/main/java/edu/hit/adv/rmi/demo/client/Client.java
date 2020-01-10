package edu.hit.adv.rmi.demo.client;

import edu.hit.adv.rmi.demo.api.ClubService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * <p>Client</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2014年11月4日 - 下午6:24:45
 */
public class Client {
    public static void main(String[] args) {
        try {
            ClubService service = (ClubService) Naming.lookup("rmi://127.0.0.1:6600/service");

            System.out.println(service.getClubById("1").getName());
            System.out.println(service.getClubById("2").getName());
            System.out.println(service.getClubById("3").getName());
            System.out.println(service.getClubById("4").getName());
            System.out.println(service.getClubById("19") == null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
