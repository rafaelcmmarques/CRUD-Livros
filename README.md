# 📚 Cadastro de Livros (DB4O)

Documentação do Sistema de Gerenciamento e Cadastro de Acervo Literário em Java (Swing + db4o).

---

## 1. Visão Geral do Projeto
O **Cadastro de Livros** é uma aplicação desktop desenvolvida em Java com interface gráfica Swing, projetada para gerenciar o acervo de uma biblioteca de forma simples e direta. O sistema permite a inclusão, consulta, alteração e exclusão de livros em um banco de dados orientado a objetos embutido (**db4o**).

O projeto serve como uma demonstração prática da arquitetura em três camadas (**IHM, BLL, DAL**), aliando tratamento centralizado de mensagens de validação e persistência orientada a objetos sem a necessidade de comandos SQL tradicionais.

---

## 2. Funcionalidades Principais
* **Cadastro de Livros:** Registro completo de títulos, autores, editoras, anos de edição e localizações no acervo.
* **Consulta por Título:** Busca interativa no banco de dados baseada no título informado, preenchendo automaticamente os demais campos.
* **Alteração de Dados:** Atualização dos dados cadastrais do livro diretamente no acervo persistido.
* **Exclusão de Registros:** Remoção definitiva de livros do banco de dados orientado a objetos.
* **Validação em Camada de Negócio:** Tratamento de campos obrigatórios e verificação de tipos numéricos antes de qualquer operação no banco.
* **Limpeza de Formulário:** Botão para redefinir rapidamente a interface gráfica e permitir novas consultas ou cadastros.

---

## 3. Arquitetura e Estrutura de Classes

| Classe | Relação / Camada | Descrição e Responsabilidades |
| :--- | :--- | :--- |
| **`LivroIHM`** | Interface (GUI - Swing) | Janela principal da aplicação desenvolvida em Swing. Captura ações do usuário e consome as validações da camada de negócio. |
| **`LivroBLL`** | Camada de Negócio (BLL) | Contém as regras de negócio do sistema. Valida campos obrigatórios, tipos de dados e gerencia chamadas seguras para a DAL. |
| **`LivroDAL`** | Acesso aos Dados (DAL) | Interage diretamente com o motor do db4o, realizando as operações de persistência, busca, exclusão e alteração de objetos. |
| **`Livro`** | Modelo / Entidade (POJO) | Representa a entidade do acervo com seus atributos (`titulo`, `autor`, `editora`, `anoedicao`, `localizacao`) e métodos acessores. |
| **`Erro`** | Utilitário / Estado | Armazena o estado de erros da aplicação e mensagens customizadas para exibição nas caixas de diálogo da interface. |

---

## 4. Regras de Negócio e Validações

* **Preenchimento Obrigatório:** Os campos **Título**, **Autor**, **Editora**, **Ano de Edição** e **Localização** são obrigatórios na criação e alteração.
* **Validação Numérica do Ano:** O campo **Ano de Edição** é validado para garantir a inserção exclusiva de valores numéricos inteiros.
* **Busca e Ações por Título:** Para as operações de Consulta e Exclusão, o fornecimento do campo **Título** é indispensável para localização no db4o.

> 💡 **Destaque de Arquitetura: Persistência Orientada a Objetos**  
> Diferente dos bancos de dados relacionais tradicionais, o **db4o** armazena a instância exata da classe `Livro`. O ciclo de conexões é protegido com blocos `try...finally` no `LivroBLL`, assegurando o encerramento da conexão e liberando a trava do arquivo (`meubanco.dbo`) mesmo na ocorrência de exceções.

---

## 5. Mapeamento dos Arquivos do Projeto

* `src/classes/Livro.java`: Modelo da classe de dados com *getters* e *setters*.
* `src/classes/Erro.java`: Classe utilitária estática para controle e recuperação de mensagens de exceção.
* `src/classes/LivroDAL.java`: Manipulador de persistência e consultas via motor db4o.
* `src/classes/LivroBLL.java`: Regras de negócio e rotinas de validação pré-persistência.
* `src/classes/LivroIHM.java`: Interface gráfica (JFrame) com ouvintes de evento dos botões.
* `src/classes/LivroIHM.form`: Arquivo de metadados de layout da interface no NetBeans GUI Builder.
* `manifest.mf`: Arquivo de manifesto para geração de empacotamento JAR do projeto.

---

## 6. Instruções de Instalação e Execução

### Pré-requisitos
* **Java Development Kit (JDK 8 ou superior)** instalado.
* IDE **NetBeans** (ou qualquer IDE Java compatível com suporte a formulários Swing).
* Biblioteca **db4o** inclusa nas dependências do projeto.

### Passos de Execução
1. Clone este repositório do GitHub:
   ```bash
   git clone [https://github.com/rafaelcmmarques/cadastro-livros-dboo.git](https://github.com/rafaelcmmarques/cadastro-livros-dboo.git)
