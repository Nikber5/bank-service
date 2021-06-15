package bank.app.service.mapper;

public interface DtoRequestMapper<D, C> {
    C fromDto(D dto);
}
