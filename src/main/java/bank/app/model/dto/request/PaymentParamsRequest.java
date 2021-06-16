package bank.app.model.dto.request;

public class PaymentParamsRequest {
    private Long source_acc_id;
    private Long dest_acc_id;

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
}
