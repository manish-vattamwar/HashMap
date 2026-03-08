import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

class HashMap <T> {
    Node<T>[] array;
    HashMap(){
        array = new Node[16];
    }
    public int HashFunction(String key){
        int hash = 0;
        for(char c : key.toCharArray()){
            hash += c;
        }
        hash %= 16;
        return hash;
    }
    public void put(String key,T value){
        int hash = HashFunction(key);
        if(array[hash] == null){
            array[hash] = new Node<>(key,value);
        }
        else{
            Node<T> current = array[hash];
            while(current.next != null){
                if(current.key.equals(key)){
                    current.value = value;
                    break;
                }
                current = current.next;
            }
            if(current.key.equals(key)){
                current.value = value;
            }
            else{
                current.next = new Node<>(key,value);
            }
        }
    }
    public <T> T get(String key){
        int hash =  HashFunction(key);
        if(array[hash] == null){
            return null;
        }
        Node current = array[hash];
        while(current != null){
            if(current.key.equals(key)){
                return (T) current.value;
            }
            current = current.next;
        }
        return null;
    }
    public void remove(String key){
        int hash = HashFunction(key);
        if(array[hash] == null){
            return;
        }
        Node current = array[hash];
        if(current.key.equals(key)){
            array[hash] = current.next;
            return;
        }
        Node prev = current;
        while(current != null){
            if(current.key.equals(key)){
                prev.next = current.next;
                return;
            }
            prev = current;
            current = current.next;
        }
    }
    public boolean contains(String key){
        int hash = HashFunction(key);
        Node current = array[hash];
        while(current != null){
            if(current.key.equals(key)){
                return true;
            }
            current = current.next;
        }
        return false;
    }
    public HashSet<String> keySet(){
        HashSet<String> set = new HashSet<>();
        for(Node temp : array){
            if(temp != null){
                Node current = temp;
                while(current != null){
                    set.add(current.key);
                    current = current.next;
                }
            }
        }
        return set;
    }
    public ArrayList<T> values(){
        ArrayList<T> list = new ArrayList<>();
        for(Node temp : array){
            if(temp != null){
                Node current = temp;
                while(current != null){
                    list.add((T) current.value);
                    current = current.next;
                }
            }
        }
        return list;
    }
    public <T> T getOrDefault(String key,T defaultvalue){
        int hash = HashFunction(key);
        if(contains(key)){
            Node current = array[hash];
            while(current != null){
                if(current.key.equals(key)){
                    return (T) current.value;
                }
                current = current.next;
            }
        }
        return defaultvalue;
    }
}
class Node <T>{
    String key;
    T value;
    Node <T> next;
    Node(String key,T value){
        this.key = key;
        this.value = value;
    }
}
public class Main{
    public static void main(String[] args) {
        HashMap<String> map = new HashMap<>();
        map.put("name", "Manish");
        map.put("mane","charish");
        map.put("ityc","hi");
        map.put("age", "18");
        map.put("city", "Hyderabad");
        map.remove("age");
        System.out.println((String) map.get("age"));
        System.out.println((String) map.get("name"));
        System.out.println(map.keySet());
        System.out.println(map.values());
        String val = map.getOrDefault("mnea","0");
        System.out.println(val);
    }
}