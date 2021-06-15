package bank.app.service.mapper;

public interface DtoResponseMapper<D, C> {
    D toDto(C object);
}
