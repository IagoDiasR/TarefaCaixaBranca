# Teste de Caixa Branca

## Descrição da Atividade

Este exercício prático, parte da avaliação de UX/UI do 4º semestre de ADS, visa avaliar a capacidade de testar um código Java. O desafio consiste em verificar se todas as funcionalidades estão implementadas corretamente e identificar possíveis falhas, considerando as limitações de informações sobre o ambiente de desenvolvimento.


# **Etapa 1: Erros identificados**

# Relatório de Análise Estática do Código
Durante a análise estática do código, identifiquei alguns pontos críticos e oportunidades de melhoria que podem impactar tanto a funcionalidade quanto a segurança da aplicação. Abaixo, destaco os principais problemas encontrados e suas respectivas sugestões de correção:



# 1. Driver MySQL incorreto:
Foi detectado que a classe de driver MySQL está sendo importada de forma incorreta. Atualmente, o código utiliza "com.mysql.Driver.Manager", o que não é válido. Para corrigir isso:

Se estiver utilizando versões anteriores ao MySQL Connector/J 8.0, a importação correta seria "com.mysql.jdbc.Driver".
Para versões 8.0 ou superiores, use "com.mysql.cj.jdbc.Driver".

# 2. Uso do método newInstance():
O método newInstance() presente na linha 12 foi depreciado a partir do Java 9. Se a aplicação estiver rodando em uma versão mais recente, é recomendado remover esse método, pois a chamada Class.forName() já é suficiente para carregar o driver.

# 3. Ausência de porta na URL JDBC:
A URL de conexão com o banco de dados não especifica a porta, o que pode causar problemas se o banco estiver configurado para usar uma porta diferente da padrão. Recomenda-se incluir a porta explicitamente, como em:
jdbc:mysql://127.0.0.1:3306/test.

# 4. Parâmetros adicionais na URL:
É importante incluir parâmetros como useSSL e serverTimezone na URL de conexão para garantir uma conexão segura e evitar problemas com fusos horários. Um exemplo de URL corrigida seria:
jdbc:mysql://127.0.0.1:3306/test?user=lopes&password=123&useSSL=false&serverTimezone=UTC.

# 5. Tratamento inadequado de exceções:
Os blocos catch estão vazios, o que dificulta a identificação de falhas. É fundamental adicionar logs ou mensagens de erro para facilitar a depuração. Além disso, utilizar a classe genérica Exception pode ocultar erros específicos. O ideal é capturar exceções mais específicas, como SQLException.

# 6. Verificação da conexão:
A função conectarBD não verifica se a conexão foi bem-sucedida antes de retorná-la. Isso pode causar falhas inesperadas em outros métodos que dependem dessa conexão. Recomenda-se validar a conexão e lançar uma exceção ou retornar null em caso de falha.

# 7. Uso inadequado de variável global:
A variável nome, declarada como public, pode ser acessada e modificada diretamente por outras classes, o que compromete a segurança dos dados. Uma boa prática seria torná-la private e fornecer métodos get e set para controlar o acesso.

# 8. Vulnerabilidade a SQL Injection:
A forma como a query SQL é construída concatena diretamente os parâmetros login e senha, tornando o código vulnerável a SQL Injection. A solução recomendada é usar PreparedStatement, que insere os parâmetros de forma segura.

# 9. Fechamento de recursos:
Recursos como Connection, Statement e ResultSet não estão sendo fechados corretamente, o que pode causar vazamento de memória. Utilizar a estrutura try-with-resources é a melhor abordagem para garantir que esses recursos sejam liberados adequadamente.

# 10. Retorno inadequado na função verificarUsuario:
Atualmente, a função retorna false em casos de erro ou quando o usuário não é encontrado. Isso pode dificultar a distinção entre falha na execução e ausência de dados. O ideal é tratar as exceções separadamente e retornar false apenas quando o usuário realmente não for encontrado.

# Melhorias adicionais:

Renomeação da classe User: A classe User poderia ter um nome mais descritivo, como UserRepository ou UserService, para refletir melhor sua responsabilidade.
Indentação e formatação: Melhorar a indentação e o alinhamento do código facilita a leitura e a manutenção futura.

Essas correções e melhorias não apenas resolverão problemas técnicos, mas também aumentarão a segurança e a clareza do código, proporcionando uma base mais robusta e fácil de manter para a aplicação.

# **Etapa 2: Preenchimento do Formulário :**

O formulário foi preenchido de acordo com os requisitos da Etapa 2. A planilha 'PLANO DE TESTE' apresenta o modelo e os critérios utilizados para o preenchimento.