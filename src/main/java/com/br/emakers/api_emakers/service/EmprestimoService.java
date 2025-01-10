package com.br.emakers.api_emakers.service;
import com.br.emakers.api_emakers.data.dto.request.EmprestimoRequestDTO;
import com.br.emakers.api_emakers.data.dto.response.EmprestimoResponseDTO;
import com.br.emakers.api_emakers.data.entity.Emprestimo;
import com.br.emakers.api_emakers.data.entity.Livro;
import com.br.emakers.api_emakers.data.entity.Pessoa;
import com.br.emakers.api_emakers.repository.EmprestimoRepository;
import com.br.emakers.api_emakers.repository.LivroRepository;
import com.br.emakers.api_emakers.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Date;
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

    public List<EmprestimoResponseDTO> fazerEmprestimos(EmprestimoRequestDTO emprestimoRequestDTO) {

        Pessoa pessoa = pessoaRepository.findById(emprestimoRequestDTO.idPessoa())
                .orElseThrow(() -> new RuntimeException("O ID da pessoa não está cadastrado!"));

        Livro livro = livroRepository.findById(emprestimoRequestDTO.idLivro())
                .orElseThrow(() -> new RuntimeException("O ID do livro não está cadastrado!"));

        if (!livro.isDisponivel()) {
            throw new RuntimeException("O livro não está disponível para empréstimo!");
        }
        // Realizar o empréstimo
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setPessoa(pessoa);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(null);

        // Atualizar disponibilidade do livro
        livro.setDisponivel(false);
        livroRepository.save(livro);

        // Salvar o empréstimo
        emprestimoRepository.save(emprestimo);
        return emprestimoRepository.findAll().stream()
                .map(e -> new EmprestimoResponseDTO(
                        e.getIdEmprestimo(),
                        e.getPessoa(),
                        e.getLivro(),
                        e.getDataEmprestimo(),
                        e.getDataDevolucao()
                ))
                .collect(Collectors.toList());
    }

    public EmprestimoResponseDTO devolverLivro(Long idEmprestimo) {
        Emprestimo emprestimo = emprestimoRepository.findById(idEmprestimo)
                .orElseThrow(() -> new RuntimeException("O ID do empréstimo não está cadastrado!"));
        if (emprestimo.getDataDevolucao() != null) {
            throw new RuntimeException("O livro já foi devolvido!");
        }

        // Realizar a devolução
        emprestimo.setDataDevolucao(LocalDate.now());

        // Atualizar disponibilidade do livro
        Livro livro = emprestimo.getLivro();
        livro.setDisponivel(true);
        livroRepository.save(livro);

        // Salvar alterações no empréstimo
        emprestimoRepository.save(emprestimo);
        return new EmprestimoResponseDTO(
                emprestimo.getIdEmprestimo(),
                emprestimo.getPessoa(),
                emprestimo.getLivro(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao()
        );
    }
}