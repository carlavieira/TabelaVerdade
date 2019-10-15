package App;

import test.Sentence;
import utils.StringsUtil;

public class Main {
    public static void main (String args[]){
        try{
            String equation = "q+p*r.s=r";

            Sentence sentence = new Sentence("q∧p");

            System.out.println(StringsUtil.propositionList(equation).toString());
            System.out.println(StringsUtil.conectivesList(equation).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
