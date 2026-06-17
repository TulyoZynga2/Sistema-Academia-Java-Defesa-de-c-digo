# 🏋️ Sistema de Gestão de Academia

Projeto acadêmico desenvolvido em **Java** aplicando os conceitos de
Programação Orientada a Objetos: herança, classes abstratas, interfaces,
polimorfismo, tratamento de exceções, persistência por serialização, log
em arquivo e arquitetura **MVC**.

O domínio é uma academia: cadastro de **planos, alunos, instrutores,
exercícios, treinos e pagamentos**, com relacionamento entre eles via
chaves estrangeiras.

---

## ▶️ Como executar

Requisito: **JDK 17 ou superior** (testado no JDK 21).

Pela linha de comando, na raiz do projeto:

```bash
# Compilar (gera os .class na pasta out/)
javac -d out $(find src -name "*.java")

# Executar
java -cp out com.academia.Main
```

Ou simplesmente abra a pasta no **VSCode** ou **IntelliJ** e execute a
classe `Main` (`src/com/academia/Main.java`).

Na primeira execução, o programa cria automaticamente as pastas:
- `dados/` → arquivos `.dat` com os objetos serializados (a base de dados);
- `logs/` → arquivo `sistema.log` com o histórico de operações.

> 💡 Esses dados persistem entre execuções. Pode fechar e abrir o programa
> que nada se perde — exatamente o cenário "a luz da barraquinha acaba e
> volta" descrito no enunciado.

---

## 🗂️ Estrutura (arquitetura MVC)

```
src/com/academia
├── Main.java                  # Ponto de entrada (monta o MVC)
├── model/                     # MODEL: entidades e regras do domínio
│   ├── Pessoa (abstrata) → Aluno, Instrutor
│   ├── Plano (abstrata) → PlanoMensal, PlanoTrimestral
│   ├── Exercicio (abstrata) → Musculacao, Cardio
│   ├── Treino, Pagamento, Endereco
│   └── enums/ (StatusPagamento, FormaPagamento, NivelIntensidade)
├── interfaces/                # Contratos (Pagavel, Identificavel)
├── repository/                # Persistência (Repositorio<T> + 6 repos)
├── controller/                # CONTROLLER: regras de negócio e validações
├── view/                      # VIEW: menus de console
└── util/                      # LogService, InputUtil, exceções customizadas
```

---

## 👥 Divisão de tarefas

O projeto foi dividido em **3 módulos** (padrão para 3 integrantes).
Como nosso grupo tem **2 pessoas**, sugerimos dividir o Módulo 3 entre as duas.

### Módulo 1 — Pessoas (Alunos & Instrutores)
`Pessoa`, `Endereco`, `Aluno`, `Instrutor`, `AlunoRepository`,
`InstrutorRepository`, `AlunoController`, `InstrutorController`,
`AlunoView`, `InstrutorView`.

### Módulo 2 — Treino (Planos, Exercícios & Treinos)
`Plano`, `PlanoMensal`, `PlanoTrimestral`, `Exercicio`, `Musculacao`,
`Cardio`, `Treino`, `PlanoRepository`, `ExercicioRepository`,
`TreinoRepository`, `PlanoController`, `ExercicioController`,
`TreinoController`, `PlanoView`, `ExercicioView`, `TreinoView`.

### Módulo 3 — Financeiro & Infraestrutura
`Pagamento`, `Pagavel`, `Identificavel`, enums, `Repositorio<T>`,
`PagamentoRepository`, `PagamentoController`, `PagamentoView`,
`MenuPrincipalView`, `LogService`, `InputUtil`, exceções, `Main`.

> **Sugestão para 2 integrantes:** Integrante A = Módulo 1 + metade do
> Módulo 3 (financeiro: Pagamento/Pagavel/repos/controller/view).
> Integrante B = Módulo 2 + metade do Módulo 3 (infraestrutura:
> Repositorio, LogService, InputUtil, exceções, Main, MenuPrincipalView).
> Lembrem de **fazer commits dos dois ao longo do mês** — a defesa é individual.

---

## ✅ Onde cada requisito está atendido

| Requisito | Onde encontrar |
|---|---|
| **Herança** | `Pessoa`→`Aluno`/`Instrutor`; `Plano`→`PlanoMensal`/`PlanoTrimestral`; `Exercicio`→`Musculacao`/`Cardio` |
| **Classe abstrata + método abstrato** | `Pessoa.getTipo()`, `Plano.calcularValorFinal()`, `Exercicio.calcularCaloriasEstimadas()` |
| **Interface** | `Pagavel` (implementada por `Aluno`) e `Identificavel` (todas as entidades) |
| **Polimorfismo de sobrescrita** | `calcularValorFinal()` difere em Mensal/Trimestral; `getTipo()`/`getResumo()` em Aluno/Instrutor; `calcularCaloriasEstimadas()` em Musculacao/Cardio |
| **Polimorfismo de sobrecarga** | `Aluno.calcularMensalidade()`, `(int meses)` e `(int meses, double desconto)` |
| **Encapsulamento** | Atributos privados + getters/setters; construtor `protected` em `Pessoa`/`Plano`/`Exercicio` |
| **Coleções** | `List<T>` nos repositórios; `List<Integer>` de exercícios em `Treino` |
| **Agregação/Composição/Associação** | Composição: `Pessoa` tem `Endereco`. Agregação: `Treino` agrega Aluno/Instrutor/Exercícios. Associação: `Pagamento`→`Aluno`, `Aluno`→`Plano` |
| **CRUDs (mín. 3)** | 6 CRUDs: Plano, Aluno, Instrutor, Exercício, Treino, Pagamento |
| **Relacionamento entre CRUDs (FK)** | `Aluno.planoId`, `Pagamento.alunoId`, `Treino.alunoId`/`instrutorId`/`exercicioIds` (validados nos controllers) |
| **Tratamento de exceções** | `DadosInvalidosException`, `EntidadeNaoEncontradaException`, `PersistenciaException`, com `try/catch` nas views |
| **Persistência de dados** | `Repositorio<T>` grava em arquivo a cada operação (pasta `dados/`) |
| **Serialização** | `ObjectOutputStream`/`ObjectInputStream`; entidades implementam `Serializable` |
| **Log em txt** | `LogService` → `logs/sistema.log` |
| **MVC** | Pacotes `model`, `view`, `controller` separados |
| **Estruturas de laço e controle** | `do/while`, `for`, `switch`, `if/else` nas views e controllers |
| **Clean Code** | Nomes descritivos, métodos curtos, generics para evitar repetição, comentários explicativos |

---

## 📌 Observações para a defesa

- **Por que guardar o id do plano (FK) e não o objeto Plano dentro de Aluno?**
  Para manter o `model` independente dos repositórios e evitar dados
  duplicados/desatualizados na serialização. A "junção" é feita nos
  controllers/views, como uma chave estrangeira de banco de dados.
- **Por que `Repositorio<T>` é genérico?** Para reaproveitar todo o código
  de CRUD e serialização em um único lugar (princípio DRY / Clean Code).
- **Onde está o polimorfismo na prática?** Ao listar exercícios, o mesmo
  laço chama `calcularCaloriasEstimadas()` e cada objeto responde conforme
  sua classe (Musculação vs Cardio).
