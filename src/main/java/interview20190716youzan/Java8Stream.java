package interview20190716youzan;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author poldi.chen
 * @className Java8Stream
 * @description TODO
 * @date 2019/7/17 22:54
 **/
public class Java8Stream {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("aa", "bb", "");
        List<String> listNew = list.stream().filter(str -> !str.isEmpty()).collect(Collectors.toList());
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(Arrays.toString(listNew.toArray()));
    }

}
