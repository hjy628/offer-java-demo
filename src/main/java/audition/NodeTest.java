package audition;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: hjy
 * @Date: 2020/6/6 15:31
 * @Description:
 */

public class NodeTest {

    public static void main(String[] args) {
        List<Node> list = new ArrayList<>(9);
        list.add(new Node(1,0,"AA"));
        list.add(new Node(2,1,"BB"));
        list.add(new Node(3,1,"CC"));
        list.add(new Node(4,3,"DD"));
        list.add(new Node(5,3,"EE"));
        list.add(new Node(6,2,"FF"));
        list.add(new Node(7,2,"GG"));
        list.add(new Node(8,4,"HH"));
        list.add(new Node(9,5,"II"));
        print(list);
    }


    @Data
    static
    class Node{
        int id;
        int parent;
        String name;

        public Node(int id, int parent, String name) {
            this.id = id;
            this.parent = parent;
            this.name = name;
        }
    }


    public static void print(List<Node> list){
        Map<Node,List<Node>> childrenMap = new HashMap<>();
        list.stream().forEach(node -> {
            childrenMap.put(node,new ArrayList<>());
                }
        );
        list.stream().forEach(element->{
            int parentId = element.getParent();
            if (parentId!=0){
                list.stream().forEach(ele->{
                    if (ele.id==parentId){  //当内层循环的ID== 外层循环的parendId时，（说明有children），需要往该内层id里建个children并push对应的数组；
                        childrenMap.get(ele).add(element);
                    }
                });
            }
        });

        System.out.println(childrenMap);

        childrenMap.entrySet().forEach(nodeListEntry -> {
            System.out.println(nodeListEntry.getKey().getName());
            nodeListEntry.getValue().stream().forEach(e->{
                System.out.println("    "+e.name);
            });
        });

    }

}
