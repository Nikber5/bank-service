package bank.app.service.mapper.request;

import bank.app.model.Account;
import bank.app.model.dto.request.AccountRequestDto;
import bank.app.service.mapper.DtoRequestMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountRequestMapper implements DtoRequestMapper<AccountRequestDto, Account> {
    @Override
    public Account fromDto(AccountRequestDto dto) {
        Account account = new Account();
        account.setAccountNumber(dto.getAccount_num());
        account.setAccountType(dto.getAccount_type());
        account.setBalance(dto.getBalance());
        return account;
    }
}
