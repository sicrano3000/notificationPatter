package br.com.estudos.notificationpattern.model.mapper;

import br.com.estudos.notificationpattern.model.dto.pagination.PaginationResponseDTO;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel="spring")
public interface PaginationMapper<T> {

    default PaginationResponseDTO<T> toDTO(Page<T> page) {
        if (page == null) {
            return null;
        }

        PaginationResponseDTO<T> dto = new PaginationResponseDTO<T>();
        dto.setContent(page.getContent());
        dto.setEmpty(page.isEmpty());
        dto.setFirst(page.isFirst());
        dto.setLast(page.isLast());
        dto.setPageIndex(page.getNumber());
        dto.setContentSize(page.getNumberOfElements());
        dto.setPageSize(page.getSize());
        dto.setTotalElements(page.getTotalElements());
        dto.setTotalPages(page.getTotalPages());

        return dto;
    }

}