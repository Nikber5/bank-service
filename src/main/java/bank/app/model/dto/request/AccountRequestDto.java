package bank.app.model.dto.request;

import java.math.BigInteger;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AccountRequestDto {
    @NotNull
    private Long account_num;
    private String account_type;
    @NotNull
    @Min(value = 0)
    private BigInteger balance;

    public Long getAccount_num() {
        return account_num;
    }

    public void setAccount_num(Long account_num) {
        this.account_num = account_num;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "AccountRequestDto{"
                + " account_num=" + account_num
                + ", account_type='" + account_type + '\''
                + ", balance=" + balance
                + '}';
    }
}
