package App;

import utils.StringsUtil;

public class Main {
    public static void main (String args[]){
        try{
            String equation = " q^p>r =s";
            System.out.println(StringsUtil.propositionList(equation).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
