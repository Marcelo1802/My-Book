# MY Books

Tecnologias presentes no projeto:

    Linguagem de Programação: Kotlin:
        linguagem recomendada para desenvolvimento nativo android

    Arquitetura: MVVM (Model-View-ViewModel)
        Utilizei a arquitetura MVVM (Model-View-ViewModel) para segmentar as responsabilidades da aplicação, tornando-a mais compreensível e testável.

        Model: Responsável pela lógica de negócios e manipulação de dados, incluindo fontes de dados locais e remotas.
        View: Responsável pela interface do usuário e da apresentação dos dados.
        ViewModel: Funciona como um intermediário entre Model e View, gerenciando os dados a serem exibidos pela View e lidando com a lógica da interface do usuário.
        Gerenciamento de Ciclo de Vida: Utiliza LiveData e ViewModel para gerenciar o ciclo de vida das atividades e fragments.

    Injeção de Dependência: Koin
        utilizei Koin, pois facilita a criaçao e Injeção  de dependências em várias partes do aplicativo, como ViewModels, Repositories, e outras classes, sem a necessidade de escrever muito código boilerplate. Isso torna a aplicação mais modular e facilita a manutenção e o teste.
        também define como as dependências de uma aplicação devem ser instanciadas e gerenciadas.


    Persistência de Dados:
        SQLite:
            utilizei SQLite pois e um banco de dados relacional leve e incorporado, amplamente utilizado em aplicativos Android para armazenar dados localmente.
            também permite o app armazenar dados que precisam ser persistidos entre sessões do aplicativo e permite fazer operações de
            criação, leitura, atualização e exclusão (CRUD) em tabelas de banco de dados usando SQL.

    API:
        Retrofit:
            Utilizei Retrofit pela facilidade em lidar com requisições HTTP e pela abstração da criação de processos paralelos para chamadas de API.
            Além disso, é a biblioteca recomendada para o desenvolvimento de aplicações android