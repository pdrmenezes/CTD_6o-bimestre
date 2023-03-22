*** Settings ***
Library     SeleniumLibrary


*** Variables ***
${SITE}                 https://www.saucedemo.com
${NAVEGADOR}            chrome
${CAMPO_USUARIO}        id=user-name
${CAMPO_SENHA}          id=password
${BOTAO_LOGIN}          id=login-button
${BOTAO_ADD}            id=add-to-cart-sauce-labs-backpack
${BOTAO_CART}           xpath=//*[@id="shopping_cart_container"]/a
${BOTAO_REMOVE}         id=remove-sauce-labs-backpack
${Botao_Continue}       id=continue-shopping
${ADD_SAUCE_LABS}       id=add-to-cart-sauce-labs-fleece-jacket
${CHECKOUT}             id=checkout
${FIRST_NAME}           id=first-name
${LAST_NAME}            id=last-name
${POSTAL_CODE}          id=postal-code
${CONTINUE}             id=continue
${FINISH}               id=finish


*** Keywords ***
Dado que eu estou na página de login
    Open Browser    ${SITE}    ${NAVEGADOR}

Quando eu faço login com usuário e senha válidos
    Input Text    ${CAMPO_USUARIO}    standard_user
    Input Text    ${CAMPO_SENHA}    secret_sauce
    Click Button    ${BOTAO_LOGIN}

E eu adiciono o produto "Sauce Labs Backpack" ao carrinho
    Click Button    ${BOTAO_ADD}

E acesso o carrinho
    Click Element    ${BOTAO_CART}
    Page Should Contain    text=Sauce Labs Backpack

Remover "Sauce Labs Backpack" do carrinho
    Click Button    ${BOTAO_REMOVE}

Clicar em "Continue Shopping" para voltar à Página Inicial.
    Click Button    ${Botao_Continue}

Adicionar o produto "Sauce Labs Fleece Jacket" ao carrinho de compras.
    Click Button    ${ADD_SAUCE_LABS}

Abrir o carrinho de compras.
    Click Element    ${BOTAO_CART}
    Page Should Contain    text=Sauce Labs Fleece Jacket

Clicar em "Checkout" para finalizar a compra.
    Click Button    ${CHECKOUT}

Preencher os dados: PRIMEIRO NOME (FIRST NAME), SOBRENOME (LAST NAME) E CEP (POSTAL CODE).
    Input Text    ${FIRST_NAME}    Paulo
    Input Text    ${LAST_NAME}    Rossi
    Input Text    ${POSTAL_CODE}    12345

Clicar no botão "CONTINUE".
    Click Button    ${CONTINUE}

Validar se o valor total cobrado é de "$53.99".
    Page Should Contain    text=$53.99

Clicar no botão "FINISH".
    Click Button    ${FINISH}

Validar que a compra foi realizada e a mensagem "THANK YOU FOR YOUR ORDER" está sendo exibida.
    Page Should Contain    text=THANK YOU FOR YOUR ORDER
