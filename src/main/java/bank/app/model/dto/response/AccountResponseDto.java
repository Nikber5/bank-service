package bank.app.model.dto.response;

import java.math.BigInteger;

public class AccountResponseDto {
    private Long account_id;
    private Long account_num;
    private String account_type;
    private BigInteger balance;

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

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
        return "AccountResponseDto{"
                + " account_id=" + account_id
                + ", account_num=" + account_num
                + ", account_type='" + account_type + '\''
                + ", balance=" + balance
                + '}';
    }
}
