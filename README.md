<!-- Documentação para criação do arquivo README.md -->
<!-- https://docs.github.com/pt/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax -->
# 📚 Trabalho Semestral ED - Gerenciamento de Docentes

## 🎯 Domínio da Aplicação
### Domínio:
Uma faculdade deve fazer uma chamada pública para contratação temporária de docentes. Apenas funcionários tem acesso ao sistema. É necessário ter um arquivo chamado disciplinas.csv com as disciplinas disponíveis, é importante ter cadastrado o código da disciplina, o nome da disciplina, o dia da semana que será ministrada, o horário inicial que será ministrada, a quantidade de horas diárias e o código
do curso ao qual a disciplina se adequa (`Uma disciplina pertence a apenas um curso`). Os cursos também devem estar disponibilizados em um arquivo chamado `cursos.csv` e, lá devem estar o código do curso, o nome do curso e a área do conhecimento ao qual ele pertence. Cada oferecimento de disciplina tem um
código de processo.<br>
Todos os professores que se inscreverem em algum processo devem estar cadastrado
no sistema  (`Arquivo professor.csv`), com seu CPF, nome, área a qual pretende se inscrever e quantidade de
pontos que faz no momento (`a pontuação é conferida por um funcionário antes de ser inserida`). Por fim,
deve haver um arquivo `inscricoes.csv`, onde deve constar (`apenas dos processos ativos`) o CPF do professor,
o código da disciplina e o código do processo.

> [!WARNING]
> Não se deve fazer inserções de dados diretamente nos arquivos CSV, apenas via sistema.

O sistema deve conter, uma tela para CRUD (`inserção, atualização, remoção e consulta`) de disciplinas,
cursos, professor e inscrições em processos seletivos (`1 Tela de CRUD para cada, que se comunica com os
arquivos`). As operações de atualização, remoção devem envolver listas encadeadas (`Os arquivos não
podem ficar com linhas vazias após operações`). Ao remover uma disciplina, todos os dados de inscrições
daquela disciplina, devem ser excluídos também. A consulta de disciplinas, cursos e professores, devem
vir de uma fila populada a partir dos seus respectivos arquivos.
Deve haver uma tela para consulta de inscritos de uma disciplina. O conteúdo da tela deve ser montado a
partir de uma lista, ordenada pela pontuação (`deve-se aplicar algum algoritmo de ordenação trabalhado
em aula, não sendo permitido o uso de classes internas do Java`), populada com os dados do arquivo de
inscrições. (`Deve se exibir na tela, todos os dados dos professores, não apenas seu CPF`).
Deve haver uma tela para consulta de todos as disciplinas de todos os cursos que têm processos abertos.
Para tanto, deve-se popular uma tabela de espalhamento com uma função hash criada pelo desenvolvedor
do sistema.

---
## 📋 Análise de Requisitos
<svg width="80px" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 200 200"><circle fill="#FF156D" stroke="#FF156D" stroke-width="15" r="15" cx="40" cy="65"><animate attributeName="cy" calcMode="spline" dur="2" values="65;135;65;" keySplines=".5 0 .5 1;.5 0 .5 1" repeatCount="indefinite" begin="-.4"></animate></circle><circle fill="#FF156D" stroke="#FF156D" stroke-width="15" r="15" cx="100" cy="65"><animate attributeName="cy" calcMode="spline" dur="2" values="65;135;65;" keySplines=".5 0 .5 1;.5 0 .5 1" repeatCount="indefinite" begin="-.2"></animate></circle><circle fill="#FF156D" stroke="#FF156D" stroke-width="15" r="15" cx="160" cy="65"><animate attributeName="cy" calcMode="spline" dur="2" values="65;135;65;" keySplines=".5 0 .5 1;.5 0 .5 1" repeatCount="indefinite" begin="0"></animate></circle></svg>

---

## 📌 Casos de Uso
<svg width="80px" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 200 200"><circle fill="#FF156D" stroke="#FF156D" stroke-width="15" r="15" cx="40" cy="65"><animate attributeName="cy" calcMode="spline" dur="2" values="65;135;65;" keySplines=".5 0 .5 1;.5 0 .5 1" repeatCount="indefinite" begin="-.4"></animate></circle><circle fill="#FF156D" stroke="#FF156D" stroke-width="15" r="15" cx="100" cy="65"><animate attributeName="cy" calcMode="spline" dur="2" values="65;135;65;" keySplines=".5 0 .5 1;.5 0 .5 1" repeatCount="indefinite" begin="-.2"></animate></circle><circle fill="#FF156D" stroke="#FF156D" stroke-width="15" r="15" cx="160" cy="65"><animate attributeName="cy" calcMode="spline" dur="2" values="65;135;65;" keySplines=".5 0 .5 1;.5 0 .5 1" repeatCount="indefinite" begin="0"></animate></circle></svg>
---

## 📦 Diagrama de Classes
<svg width="80px" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 200 200"><circle fill="#FF156D" stroke="#FF156D" stroke-width="15" r="15" cx="40" cy="65"><animate attributeName="cy" calcMode="spline" dur="2" values="65;135;65;" keySplines=".5 0 .5 1;.5 0 .5 1" repeatCount="indefinite" begin="-.4"></animate></circle><circle fill="#FF156D" stroke="#FF156D" stroke-width="15" r="15" cx="100" cy="65"><animate attributeName="cy" calcMode="spline" dur="2" values="65;135;65;" keySplines=".5 0 .5 1;.5 0 .5 1" repeatCount="indefinite" begin="-.2"></animate></circle><circle fill="#FF156D" stroke="#FF156D" stroke-width="15" r="15" cx="160" cy="65"><animate attributeName="cy" calcMode="spline" dur="2" values="65;135;65;" keySplines=".5 0 .5 1;.5 0 .5 1" repeatCount="indefinite" begin="0"></animate></circle></svg>
---

## 🧩 Diagrama de Objetos
<svg width="80px" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 200 200"><circle fill="#FF156D" stroke="#FF156D" stroke-width="15" r="15" cx="40" cy="65"><animate attributeName="cy" calcMode="spline" dur="2" values="65;135;65;" keySplines=".5 0 .5 1;.5 0 .5 1" repeatCount="indefinite" begin="-.4"></animate></circle><circle fill="#FF156D" stroke="#FF156D" stroke-width="15" r="15" cx="100" cy="65"><animate attributeName="cy" calcMode="spline" dur="2" values="65;135;65;" keySplines=".5 0 .5 1;.5 0 .5 1" repeatCount="indefinite" begin="-.2"></animate></circle><circle fill="#FF156D" stroke="#FF156D" stroke-width="15" r="15" cx="160" cy="65"><animate attributeName="cy" calcMode="spline" dur="2" values="65;135;65;" keySplines=".5 0 .5 1;.5 0 .5 1" repeatCount="indefinite" begin="0"></animate></circle></svg>
---

## 💻 Tecnologias Utilizadas

- **Java SE-21+**
- **Java Swing** – interface gráfica
- **Arquivos CSV** – persistência de dados local
  

---
## 🗂️ Estrutura do Projeto

A estrutura segue os princípios da Clean Architecture, com separação clara entre camadas de domínio, casos de uso, interface, adaptação e persistência.

<b>`/src`</b><br>
│<br>
├── `/domain` # Regras de negócio e entidades<br>
│ ├── `/entities` # Camada de Modelos<br>
│ └── `/repositories` # Interfaces para persistência de dados<br>
│<br>
├── `/usecases` # Lógica de aplicação<br>
│ └── `/` # Arquivos da aplicação<br>
│<br>
├── `/adapters` # Camada de persistência e mapeamentos<br>
│ ├── `/database` # Implementações das funcionalidades de persistencias<br>
│ └── `/mappers` # Conversões de dados para objetos Java<br>
│<br>
├── `/controllers` # Comunicação entre views e usecases<br>
│<br>
├── `/views` # Interface com o usuário<br>
│ ├── `/` # Arquivos de Interface gráfica<br>
│ └── `/components` # Componentes reutilizáveis<br>
│<br>
└── `Main.java` # Classe principal, ponto de entrada da aplicação<br>

---


## 🧑‍💻 Equipe

- **Desenvolvedores/Alunos:**
  - [Maykon Wlliam](https://github.com/MaykonWilliam)
  - [Lucas Bezerra](https://github.com/LucasBMacedo)
  - [André Oliveira Batista](https://github.com/AndreOliveiraBatista)
  - [Vinicius de Souza](#)
  
- **Disciplina:** Estrutura de Dados - ED
- **Professor:** [Leandro Colevati](https://github.com/lecolevati)
- **Instituição:** FATEC Zona Leste - Análise e Desenvolvimento de Sistemas
