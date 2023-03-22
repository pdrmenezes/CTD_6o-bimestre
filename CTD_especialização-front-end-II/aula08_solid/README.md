# ctd-esp-front2-aula8-solid-base

Aplicando os 2 últimos padrões SOLID. 

O primeiro desafio consiste em aplicar o princípio de segregação de interfaces para separar os métodos da interface TrackingSoftware utilizados no arquivo features/tracking/tracking.context.tsx, de modo que as classes filhas (por exemplo: Google) não precisem aplicar obrigatoriamente o método initialize.

O segundo desafio consiste em aplicar o princípio de inversão de dependências no arquivo features/languages/language.context.tsx, de modo que o arquivo não precise saber a implementação específica de onde as traduções provêm (os data files). 

## Exercício
Vamos pensar: o que será preciso para aplicar o princípio de segregação de interfaces na interface atual de TrackingSoftware é extrair o método de initialize em uma nova interface, que pode ter o nome de Initializable. Assim, tanto as classes do facebook como de amplitude podem implementar essa interface mantendo a lógica de inicialização. Já o Google pode evitar que seja preciso declarar esse método. 
