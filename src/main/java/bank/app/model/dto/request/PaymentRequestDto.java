package bank.app.model.dto.request;

import java.math.BigInteger;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PaymentRequestDto {
    @NotNull
    private Long source_acc_id;
    @NotNull
    private Long dest_acc_id;
    @NotNull
    @Min(value = 0)
    private BigInteger amount;
    private String reason;

    public Long getSource_acc_id() {
        return source_acc_id;
    }

    public void setSource_acc_id(Long source_acc_id) {
        this.source_acc_id = source_acc_id;
    }

    public Long getDest_acc_id() {
        return dest_acc_id;
    }

    public void setDest_acc_id(Long dest_acc_id) {
        this.dest_acc_id = dest_acc_id;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "PaymentRequestDto{"
                + " source_acc_id=" + source_acc_id
                + ", dest_acc_id=" + dest_acc_id
                + ", amount=" + amount
                + ", reason='" + reason + '\''
                + '}';
    }
}
