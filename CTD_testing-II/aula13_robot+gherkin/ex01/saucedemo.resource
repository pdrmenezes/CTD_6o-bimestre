*** Settings ***
Library     SeleniumLibrary


*** Variables ***
${SITE}                 https://saucedemo.com
${NAVEGADOR}            chrome
#${NAVEGADOR}    Headless Chrome pro navegador trabalhar por trás dos panos e não abrir uma janela nova
${CAMPO_USUARIO}        css=#user-name
${CAMPO_SENHA}          css=#password
${USUARIO}              standard_user
${SENHA}                secret_sauce
${BOTAO_LOGIN}          css=#login-button
${PRODUTO}              css=#item_4_title_link > div
${ADICIONAR}            css=#add-to-cart-sauce-labs-backpack
${CARRINHO}             css=#shopping_cart_container > a
${CHECKOUT}             css=#checkout
${CAMPO_NOME}           css=#first-name
${NOME}                 Tiago
${CAMPO_SOBRENOME}      css=#last-name
${SOBRENOME}            Gomes
${CAMPO_CEP}            css=#postal-code
${CEP}                  000000
${BOTAO_CONTINUE}       css=#continue
${TOTAL}                css=#checkout_summary_container > div > div.summary_info > div.summary_total_label
${VALOR_TOTAL}          32.39
${BOTAO_FINISH}         css=#finish
${CONFIRMACAO}          css=#checkout_complete_container > h2
${MENSAGEM}             THANK YOU FOR YOUR ODER


*** Keywords ***
Acessar o site da Sauce Demo
    Open Browser    ${SITE}    ${NAVEGADOR}
    Maximize Browser Window
    Set Selenium Speed    0.75

Fazer login
    Input Text    ${CAMPO_USUARIO}    ${USUARIO}
    Input Text    ${CAMPO_SENHA}    ${SENHA}
    Capture Page Screenshot
    #Wait Until Element Is Visible    ${BOTAO_LOGIN}
    #Click Element    ${BOTAO_LOGIN}
    Wait and Click    ${BOTAO_LOGIN}

Selecionar produto e colocar no carrinho
    Wait and Click    ${PRODUTO}
    Wait and Click    ${ADICIONAR}

Abrir o carrinho e clicar em checkout
    Wait and Click    ${CARRINHO}
    Wait and Click    ${CHECKOUT}

Preencher os dados de entrega e clicar em Continue
    Input Text    ${CAMPO_NOME}    ${NOME}
    Input Text    ${CAMPO_SOBRENOME}    ${SOBRENOME}
    Input Text    ${CAMPO_CEP}    ${CEP}
    Wait and Click    ${BOTAO_CONTINUE}

Validar total da compra e clicar em finish
    Element Should Contain    ${TOTAL}    ${VALOR_TOTAL}
    Click Element    ${BOTAO_FINISH}

Validar mensagem de compra finalizada
    Element Should Contain    ${CONFIRMACAO}    ${MENSAGEM}

Wait and Click
    [Arguments]    ${ELEMENTO}
    Wait Until Element Is Visible    ${ELEMENTO}
    Wait Until Element Is Enabled    ${ELEMENTO}
    Scroll Element Into View    ${ELEMENTO}
    Click Element    ${ELEMENTO}

Wait and Input Text
    [Arguments]    ${ELEMENTO}    ${TEXTO}
    Wait Until Element Is Visible    ${ELEMENTO}
    Wait Until Element Is Enabled    ${ELEMENTO}
    Scroll Element Into View    ${ELEMENTO}
    Input Text    ${ELEMENTO}    ${TEXTO}
