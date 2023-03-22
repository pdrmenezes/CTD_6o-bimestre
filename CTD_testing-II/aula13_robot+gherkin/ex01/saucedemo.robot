*** Settings ***
Library             SeleniumLibrary
Resource            saucedemo.resource

Test Setup          Acessar o site da Sauce Demo
Test Teardown       Close Browser


*** Test Cases ***
Processo de compra na loja Sauce Demo
    Acessar o site da Sauce Demo
    Fazer login
    Selecionar produto e colocar no carrinho
    Abrir o carrinho e clicar em checkout
    Preencher os dados de entrega e clicar em Continue
    Validar total da compra e clicar em finish
    Validar mensagem de compra finalizada

    #rodar o arquivo com robot nomedoarquivo.robot
    # pra rodar e salvar os arquivos de logs numa pasta específica: robot -d ./logs nomedoarquivo.robot
