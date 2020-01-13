package edu.hit.adv.rmi.demo.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * <p>ClubService</p>
 * <p>Description : </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2014年11月4日 - 下午1:16:07
 */
public interface ClubService extends Remote {

    public ClubEntity getClubById(String id) throws RemoteException;

}
