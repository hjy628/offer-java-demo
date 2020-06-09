package audition;

/**
 * @auther: hjy
 * @Date: 2020/6/6 16:26
 * @Description:
 */

public class Test {

    public static void main(String[] args) throws Exception{
        IA ia = (IA)createObject(IA.class.getName()+"$getName=Abc");
        System.out.println(ia.getName());
        ia = (IA)createObject(IA.class.getName()+"$getName=Bcd");
        System.out.println(ia.getName());
    }

    public static Object createObject(String str)throws Exception{
        String[] names = str.split("\\$");
        String[] beanName = names[1].split("\\=");
        Class clazz = Class.forName(names[0]);

        class Boj implements IA{
            @Override
            public String getName() {
                return beanName[1];
            }
        }


        return new Boj();
    }

}
