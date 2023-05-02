package br.com.estudos.notificationpattern.model.dto.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationRequestDTO implements Serializable {

    private String query;
    private Integer pageSize;
    private Integer pageIndex;
    private String sort;

    public static PaginationRequestDTO of(String query, Integer pageSize, Integer pageIndex, String sort) {
        return PaginationRequestDTO.builder()
                .query(query)
                .pageSize(pageSize)
                .pageIndex(pageIndex)
                .sort(sort)
                .build();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
