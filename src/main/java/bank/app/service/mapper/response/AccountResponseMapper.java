package bank.app.service.mapper.response;

import bank.app.model.Account;
import bank.app.model.dto.response.AccountResponseDto;
import bank.app.service.mapper.DtoResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountResponseMapper implements DtoResponseMapper<AccountResponseDto, Account> {
    @Override
    public AccountResponseDto toDto(Account object) {
        AccountResponseDto accountResponseDto = new AccountResponseDto();
        accountResponseDto.setAccount_id(object.getId());
        accountResponseDto.setAccount_num(object.getAccountNumber());
        accountResponseDto.setAccount_type(object.getAccountType());
        accountResponseDto.setBalance(object.getBalance());
        return accountResponseDto;
    }
}
