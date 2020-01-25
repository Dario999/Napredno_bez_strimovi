//package Lab7;

import javax.swing.plaf.multi.MultiSeparatorUI;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.TreeSet;

class NoSuchRoomException extends Exception{

    public NoSuchRoomException(String roomName){
        super("No such room " + roomName);
    }

}

class NoSuchUserException extends Exception{

    public NoSuchUserException(String name){
        super("No such user " + name);
    }

}

class ChatRoom{

    public String name;
    private TreeSet<String> users;

    public ChatRoom(String name){
        this.name = name;
        this.users = new TreeSet<>();
    }

    public void addUser(String userName){
        users.add(userName);
    }

    public void removeUser(String userName){
        users.remove(userName);
    }

    @Override
    public String toString() {
        if (users.size() == 0)
            return name + "\nEMPTY";
        StringBuilder sb = new StringBuilder();
        sb.append(name + "\n");
        for (String temp : users)
            sb.append(temp + "\n");
        return sb.toString();
    }

    public boolean hasUser(String username){
        return users.contains(username);
    }

    public int numUsers(){
        return users.size();
    }

}

class ChatSystem{

    private TreeMap<String,ChatRoom> mapa;

    public ChatSystem(){
        mapa = new TreeMap<String,ChatRoom>();
    }

    public void addRoom(String roomName){
        mapa.put(roomName,new ChatRoom(roomName));
    }

    public void removeRoom(String roomName){
        mapa.remove(roomName);
    }

    public ChatRoom getRoom(String roomName) throws NoSuchRoomException {
        if (!mapa.containsKey(roomName))
            throw new NoSuchRoomException(roomName);
        return mapa.get(roomName);
    }

    public void register(String userName) throws NoSuchRoomException, NoSuchUserException {
        /*String roomKey = mapa.firstKey();
        for (String temp : mapa.keySet()){
            if (mapa.get(temp).numUsers() < mapa.get(roomKey).numUsers()){
                roomKey = temp;
            }
        }
        mapa.get(roomKey).addUser(userName);*/
        if (!mapa.isEmpty()) {
            String min = mapa.values().stream()
                    .min(Comparator.comparing(ChatRoom::numUsers).thenComparing(room -> room.name)).get().name;
            registerAndJoin(userName, min);
        }
    }

    public void registerAndJoin(String userName,String roomName) throws NoSuchRoomException, NoSuchUserException {
        joinRoom(userName,roomName);
    }

    public void joinRoom(String userName,String roomName) throws NoSuchRoomException, NoSuchUserException {
        if (mapa.containsKey(roomName)){
            if(mapa.get(roomName).hasUser(userName))
                throw new NoSuchUserException(userName);
            else
                mapa.get(roomName).addUser(userName);
        }else {
            throw new NoSuchRoomException(roomName);
        }
    }

    public void leaveRoom(String username,String roomName) throws NoSuchRoomException, NoSuchUserException {
        if (mapa.containsKey(roomName)){
            if (mapa.get(roomName).hasUser(username))
                mapa.get(roomName).removeUser(username);
            else
                throw new NoSuchUserException(username);
        }else {
            throw new NoSuchRoomException(roomName);
        }
    }

    public void followFriend(String username,String friend_username){
        /*for (String temp : mapa.keySet()){
            if (mapa.get(temp).hasUser(friend_username))
                mapa.get(temp).addUser(username);*/
        mapa.values().stream().filter(room -> room.hasUser(friend_username)).forEach(room -> room.addUser(username));
    }

}

public class ChatSystemTest {

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchRoomException {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) {
            ChatRoom cr = new ChatRoom(jin.next());
            int n = jin.nextInt();
            for ( int i = 0 ; i < n ; ++i ) {
                k = jin.nextInt();
                if ( k == 0 ) cr.addUser(jin.next());
                if ( k == 1 ) cr.removeUser(jin.next());
                if ( k == 2 ) System.out.println(cr.hasUser(jin.next()));
            }
            System.out.println("");
            System.out.println(cr.toString());
            n = jin.nextInt();
            if ( n == 0 ) return;
            ChatRoom cr2 = new ChatRoom(jin.next());
            for ( int i = 0 ; i < n ; ++i ) {
                k = jin.nextInt();
                if ( k == 0 ) cr2.addUser(jin.next());
                if ( k == 1 ) cr2.removeUser(jin.next());
                if ( k == 2 ) cr2.hasUser(jin.next());
            }
            System.out.println(cr2.toString());
        }
        if ( k == 1 ) {
            ChatSystem cs = new ChatSystem();
            Method mts[] = cs.getClass().getMethods();
            while ( true ) {
                String cmd = jin.next();
                if ( cmd.equals("stop") ) break;
                if ( cmd.equals("print") ) {
                    System.out.println(cs.getRoom(jin.next())+"\n");continue;
                }
                for ( Method m : mts ) {
                    if ( m.getName().equals(cmd) ) {
                        String params[] = new String[m.getParameterTypes().length];
                        for ( int i = 0 ; i < params.length ; ++i ) params[i] = jin.next();
                        m.invoke(cs,params);
                    }
                }
            }
        }
    }

}
