*** Settings ***
Library     SeleniumLibrary
Resource    saucedemo2.resource

*** Test Cases ***
Processo de compra da loja Sauce Demo
    Dado que eu estou na página de login
    Quando eu faço login com usuário e senha válidos
    E eu adiciono o produto "Sauce Labs Backpack" ao carrinho
    E acesso o carrinho
    Remover "Sauce Labs Backpack" do carrinho
    Clicar em "Continue Shopping" para voltar à Página Inicial.
    Adicionar o produto "Sauce Labs Fleece Jacket" ao carrinho de compras.
    Abrir o carrinho de compras.
    Clicar em "Checkout" para finalizar a compra.
    Preencher os dados: PRIMEIRO NOME (FIRST NAME), SOBRENOME (LAST NAME) E CEP (POSTAL CODE).
    Clicar no botão "CONTINUE".
    Validar se o valor total cobrado é de "$53.99".
    Clicar no botão "FINISH".
    Validar que a compra foi realizada e a mensagem "THANK YOU FOR YOUR ORDER" está sendo exibida.
