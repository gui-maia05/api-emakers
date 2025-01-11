package com.br.emakers.api_emakers.service;

import com.br.emakers.api_emakers.data.dto.request.EmprestimoRequestDTO;
import com.br.emakers.api_emakers.data.dto.response.EmprestimoResponseDTO;
import com.br.emakers.api_emakers.data.entity.Emprestimo;
import com.br.emakers.api_emakers.data.entity.Livro;
import com.br.emakers.api_emakers.data.entity.Pessoa;
import com.br.emakers.api_emakers.exceptions.general.EntityNotFoundException;
import com.br.emakers.api_emakers.repository.EmprestimoRepository;
import com.br.emakers.api_emakers.repository.LivroRepository;
import com.br.emakers.api_emakers.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LivroRepository livroRepository;

    public List<EmprestimoResponseDTO> getAllEmprestimos() {
        List<Emprestimo> emprestimosAtivos = emprestimoRepository.findByDataDevolucaoIsNull();
        return emprestimosAtivos.stream().map(EmprestimoResponseDTO::new).collect(Collectors.toList());
    }


    public EmprestimoResponseDTO realizarEmprestimo(EmprestimoRequestDTO dto) {
        Pessoa pessoa = pessoaRepository.findById(dto.idPessoa())
                .orElseThrow(() -> new EntityNotFoundException(dto.idPessoa()));

        // Verifica se a pessoa já pegou 2 livros
        long quantidadeLivrosEmprestados = emprestimoRepository.countByPessoaAndDataDevolucaoIsNull(pessoa);
        if (quantidadeLivrosEmprestados >= 2) {
            throw new RuntimeException("A pessoa já possui dois livros emprestados.");
        }

        Livro livro = livroRepository.findById(dto.idLivro())
                .orElseThrow(() -> new EntityNotFoundException(dto.idLivro()));

        if (livro.isDisponivel()) {
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setPessoa(pessoa);
            livro.setDisponivel(false);
            emprestimo.setLivro(livro);
            emprestimo.setDataEmprestimo(new java.util.Date());
            emprestimo.setDataDevolucao(null);  // O livro ainda não foi devolvido

            emprestimoRepository.save(emprestimo);
            livroRepository.save(livro);

            return new EmprestimoResponseDTO(emprestimo);
        } else {
            throw new RuntimeException("O livro com ID " + dto.idLivro() + " não está disponível para empréstimo.");
        }
    }

    public EmprestimoResponseDTO devolverEmprestimo(Long idEmprestimo) {
        Emprestimo emprestimo = emprestimoRepository.findById(idEmprestimo)
                .orElseThrow(() -> new EntityNotFoundException(idEmprestimo));

        if (emprestimo.getDataDevolucao() != null) {
            throw new RuntimeException("Este empréstimo já foi devolvido.");
        }

        // Marca o livro como disponível novamente e define a data de devolução
        emprestimo.setDataDevolucao(new java.util.Date());
        emprestimo.getLivro().setDisponivel(true);

        emprestimoRepository.save(emprestimo);
        livroRepository.save(emprestimo.getLivro());

        return new EmprestimoResponseDTO(emprestimo);
    }
}
