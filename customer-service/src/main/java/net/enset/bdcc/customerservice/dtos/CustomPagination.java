package net.enset.bdcc.customerservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomPagination<T> {
    private int page;
    private int size;
    private long totalRecords;
    private long totalPages;
    private List<T> data;
}
