import java.math.BigDecimal;

public class AmountUtils {

    public static BigDecimal yuan2fen(BigDecimal yuan){

        BigDecimal amount = new BigDecimal(yuan.toPlainString());
        //乘以100
        amount = amount.multiply(BigDecimal.valueOf(100));

        return amount;
    }

    public static BigDecimal fen2yuan(BigDecimal yuan){

        BigDecimal amount = new BigDecimal(yuan.toPlainString());

        amount = amount.divide(BigDecimal.valueOf(100));

        return amount;
    }

    public static void main(String[] args) {
        BigDecimal amount = new BigDecimal(0.5 );
        String aa = "0";

        BigDecimal bigDecimal = new BigDecimal(aa.toString()).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);

        //System.out.println(yuan2fen(new BigDecimal(aa)));
        System.out.println(bigDecimal.toPlainString());
    }

}
