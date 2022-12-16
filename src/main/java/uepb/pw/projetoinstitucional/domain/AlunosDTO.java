package uepb.pw.projetoinstitucional.domain;

import lombok.Data;

import java.util.List;

@Data
public class AlunosDTO {

    private List<Integer> ids;

    public AlunosDTO(List<Integer> ids) {
        this.ids = ids;
    }
}
