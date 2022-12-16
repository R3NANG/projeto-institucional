package uepb.pw.projetoinstitucional.domain;

import lombok.Data;

@Data
public class EnderecoDTO {

    private Integer id;
    private String rua;
    private String numero;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;

    public EnderecoDTO(Endereco endereco) {
        this.id = endereco.getId();
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.cep = endereco.getCep();
        this.cidade = endereco.getCidade();
        this.estado = endereco.getEstado();
        this.pais = endereco.getPais();
    }
}
