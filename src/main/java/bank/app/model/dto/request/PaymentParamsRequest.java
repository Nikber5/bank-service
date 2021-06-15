package bank.app.model.dto.request;

public class PaymentParamsRequest {
    private Long payer_id;
    private Long recipient_id;
    private Long source_acc_id;
    private Long dest_acc_id;

    public Long getPayer_id() {
        return payer_id;
    }

    public void setPayer_id(Long payer_id) {
        this.payer_id = payer_id;
    }

    public Long getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(Long recipient_id) {
        this.recipient_id = recipient_id;
    }

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
