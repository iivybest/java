package edu.hit.adv.rmi.demo.server;

import edu.hit.adv.rmi.demo.api.ClubEntity;
import edu.hit.adv.rmi.demo.api.ClubService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>ClubServiceImpl</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2014年11月4日 - 下午1:17:58
 */
public class ClubServiceImpl extends UnicastRemoteObject implements ClubService {
    private static final long serialVersionUID = -6612446829943247341L;

    // ----club datasource cache
    private Map<String, ClubEntity> cache;

    /**
     * constructor
     *
     * @throws RemoteException
     */
    protected ClubServiceImpl() throws RemoteException {
        super();
        this.init();
    }

    private void init() {
        this.cache = new HashMap<String, ClubEntity>();
        ClubEntity club;
        for (int i = 0; i < 10; i++) {
            club = ClubEntity.newInstance().setId(String.valueOf(i)).setName("Club_" + i);
            this.cache.put(club.getId(), club);
        }
    }
    @Override
    public ClubEntity getClubById(String id) throws RemoteException {
        return this.cache.get(id);
    }

}








