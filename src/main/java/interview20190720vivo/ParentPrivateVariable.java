package interview20190720vivo;

/**
 * @author poldi.chen
 * @className ParentPrivateVariable
 * @description TODO
 * @date 2019/7/22 22:33
 **/
public class ParentPrivateVariable {

    private String name = "parent";

    public static void main(String[] args) {
        Sub sub = new Sub();
//        System.out.println(sub.name); // 编译不通过
    }
}

class Sub extends ParentPrivateVariable {

    //

}