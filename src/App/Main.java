package App;

import utils.StringsUtil;

public class Main {
    public static void main (String args[]){
        String equation = " q^p>r =s";
        System.out.println(StringsUtil.propositionList(equation).toString());
    }
}
