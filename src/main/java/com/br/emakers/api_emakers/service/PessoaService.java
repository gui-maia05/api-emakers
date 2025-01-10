package com.br.emakers.api_emakers.service;

import com.br.emakers.api_emakers.data.dto.request.PessoaRequestDTO;
import com.br.emakers.api_emakers.data.dto.response.PessoaResponseDTO;
import com.br.emakers.api_emakers.data.entity.Pessoa;
import com.br.emakers.api_emakers.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repositoryPessoa;

    public List<PessoaResponseDTO> getAllPessoas() {
        List<Pessoa> pessoas = repositoryPessoa.findAll();

        return pessoas.stream().map(PessoaResponseDTO::new).collect(Collectors.toList());
    }

    public PessoaResponseDTO getPessoaById(Long idPessoa) {
        Pessoa pessoa = getPessoaEntityById(idPessoa);

        return new PessoaResponseDTO(pessoa);
    }

    public PessoaResponseDTO createPessoa(PessoaRequestDTO pessoaRequestDTO) {
        Pessoa pessoa = new Pessoa(pessoaRequestDTO);
        repositoryPessoa.save(pessoa);

        return new PessoaResponseDTO(pessoa);
    }

    public PessoaResponseDTO updatePessoa(Long idPessoa, PessoaRequestDTO pessoaRequestDTO) {
        Pessoa pessoa = getPessoaEntityById(idPessoa);

        pessoa.setNome(pessoaRequestDTO.nome());
        pessoa.setCep(pessoaRequestDTO.cep());
        repositoryPessoa.save(pessoa);

        return new PessoaResponseDTO(pessoa);
    }

    public String deletePessoa(Long idPessoa) {
        Pessoa pessoa = getPessoaEntityById(idPessoa);
        repositoryPessoa.delete(pessoa);
        return "O ID " + idPessoa + " foi removido";
    }

    private Pessoa getPessoaEntityById(Long idPessoa) {
        return repositoryPessoa.findById(idPessoa).orElseThrow(()-> new RuntimeException("Essa Pessoa não foi encontrada"));
    }
}
