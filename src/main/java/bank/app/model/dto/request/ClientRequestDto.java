package bank.app.model.dto.request;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ClientRequestDto {
    @NotNull
    private String first_name;
    @NotNull
    private String last_name;
    @Valid
    @NotNull
    private List<AccountRequestDto> accounts;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public List<AccountRequestDto> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountRequestDto> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "ClientRequestDto{"
                + " first_name='" + first_name + '\''
                + ", last_name='" + last_name + '\''
                + ", accounts=" + accounts
                + '}';
    }
}
