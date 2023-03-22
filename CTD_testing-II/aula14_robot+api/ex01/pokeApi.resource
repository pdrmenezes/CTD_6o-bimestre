*** Settings ***
Library     Collections
Library     RequestsLibrary


*** Variables ***
${API_BASE}         https://pokeapi.co/api/v2/
${ENDPOINT_COR}     pokemon-color/
${ENDPOINT_NAME}    pokemon/
${POKEMON_COR}      red
${POKEMON_NAME}     magikarp


*** Keywords ***
Consulta de Pokemons
    Verificar cor    red
    Conferir Status Code    200

Verificar cor
    [Arguments]    ${COR}

    Create Session    pokeApi    ${API_BASE}${ENDPOINT_COR}${POKEMON_COR}
    ${RESPOSTA}    GET On Session    pokeApi    ${API_BASE}${ENDPOINT_COR}${POKEMON_COR}
    Set Test Variable    ${RESPOSTA}
    Dictionary Should Contain Item    ${RESPOSTA.json()}    name    ${COR}

Conferir Status Code
    [Arguments]    ${STATUS_CODE}
    Should Be Equal As Strings    ${RESPOSTA.status_code}    ${STATUS_CODE}
