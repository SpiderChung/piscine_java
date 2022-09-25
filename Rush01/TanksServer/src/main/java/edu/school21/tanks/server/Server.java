package edu.school21.tanks.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import edu.school21.tanks.objects.BulletLogic;
import edu.school21.tanks.objects.TanksLogic;
import edu.school21.tanks.services.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class Server {
    @Autowired
    private StatService statService;

    public static LinkedList<ClientListener> listeners = new LinkedList<>();
    private LinkedList<BulletLogic> bullets1;
    private LinkedList<BulletLogic> bullets2;
    private TanksLogic first;
    private TanksLogic second;
    private static boolean end = true;

    public Server(){
        bullets1 = new LinkedList<>();
        bullets2 = new LinkedList<>();
        first = new TanksLogic(1, 521, 100);
        second = new TanksLogic(2, 521, 900);

    }

    public void start(int port) {
        Players player1 = new Players(first, bullets1);
        Players player2 = new Players(second, bullets2);
        World output1 = new World();
        World output2 = new World();
        output1.players.add(player1);
        output1.players.add(player2);
        output2.players.add(player2);
        output2.players.add(player1);
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            listeners.add(new ClientListener(socket, first, second, 1, bullets1, statService));
            socket = serverSocket.accept();
            listeners.add(new ClientListener(socket, second, first, 2, bullets2, statService));
            listeners.get(0).start();
            listeners.get(1).start();
            while (end){
                for (ClientListener cl : Server.listeners) {
                    if(cl.getCLientId() == 2){
                        String json2 = JSON.toJSONString(output2, SerializerFeature.DisableCircularReferenceDetect);
                        cl.write(json2);
                    }
                    else{
                        String json1 = JSON.toJSONString(output1, SerializerFeature.DisableCircularReferenceDetect);
                        cl.write(json1);
                    }
                }
                Thread.sleep(50);
            }
            statService.saveStat(first, second);
        } catch (Exception ex) {
            System.err.println("Server close");
        }
        System.out.println("Server is closed!");
    }

    public static void changeEnd(){
        end = false;
    }
}

class World{
    public List<Players> players = new ArrayList<>();
}

class Players{
    private TanksLogic tank;
    private LinkedList<BulletLogic> bullets;

    public Players(TanksLogic tank, LinkedList<BulletLogic> bullets){
        this.tank = tank;
        this.bullets = bullets;
    }

    public TanksLogic getTank() {
        return tank;
    }

    public void setTank(TanksLogic tank) {
        this.tank = tank;
    }

    public LinkedList<BulletLogic> getBullets() {
        return bullets;
    }

    public void setBullets(LinkedList<BulletLogic> bullets) {
        this.bullets = bullets;
    }
}
