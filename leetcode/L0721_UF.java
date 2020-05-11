import java.util.*;
import java.util.stream.*;

public class L0721_UF {
    public static void main(String args[]) {
        List<List<String>> accounts = new ArrayList<>();
        
        // String[][] arr = {{"John", "johnsmith@mail.com", "john00@mail.com"}, 
        //                 {"John", "johnnybravo@mail.com"}, 
        //                 {"John", "johnsmith@mail.com", "john_newyork@mail.com"}, 
        //                 {"Mary", "mary@mail.com"}};


        String[][] arr = {{"Alex","Alex5@m.co","Alex4@m.co","Alex0@m.co"},
                        {"Ethan","Ethan3@m.co","Ethan3@m.co","Ethan0@m.co"},
                        {"Kevin","Kevin4@m.co","Kevin2@m.co","Kevin2@m.co"},
                        {"Gabe","Gabe0@m.co","Gabe3@m.co","Gabe2@m.co"},
                        {"Gabe","Gabe3@m.co","Gabe4@m.co","Gabe2@m.co"}};

        for(String[] stra : arr) {
            List<String> list = new ArrayList<>();
            for(String s : stra) list.add(s);
            accounts.add(list);
        }

        System.out.println(new L0721_UF().accountsMerge(accounts));

    }

    Map<String, String> parent = new HashMap<>();
    Map<String, TreeSet<String>> merged = new HashMap<>();
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // making everyones their own owner
        for(List<String> account : accounts) {
            for(int i = 1; i < account.size(); i++) {
                parent.put(account.get(i), account.get(i));
            }
        }

        // account merge
        for(List<String> account : accounts) {
            String p = find(account.get(1));
            for(int i = 2; i < account.size(); i++) {
                parent.put(find(account.get(i)), p);
            }
        }

        // create result
        for(List<String> account : accounts) {
            String p = find(account.get(1));
            if(!merged.containsKey(p)) {
                merged.put(p, new TreeSet<>());
                merged.get(p).add(account.get(0)); // added name, as it starts from Capital Letter, it would always be first
            }
            for(int i = 1; i < account.size(); i++) merged.get(p).add(account.get(i));
        }

        List<List<String>> answer = new ArrayList<>(merged.size());
        merged.forEach((k, v) -> {
            answer.add(v.stream().collect(Collectors.toList()));
        });

        return answer;
    }

    public String find(String key) {
        if(!key.equals(parent.get(key))) parent.put(key, find(parent.get(key)));
        return parent.get(key);
    }
}