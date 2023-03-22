*** Settings ***
Library     Collections
Library     RequestsLibrary


*** Variables ***
${API_BASE}         https://pokeapi.co/api/v2/
${ENDPOINT_NAME}    pokemon/
${POKEMON_NAME}     magikarp
${STATUS_CODE}      200


*** Keywords ***
Consulta de Pokemons
    Verificar pokemon    ${POKEMON_NAME}
    Conferir Status Code    ${STATUS_CODE}

Verificar pokemon
    [Arguments]    ${POKEMON_NAME}

    Create Session    pokeApi    ${API_BASE}${ENDPOINT_NAME}${POKEMON_NAME}
    ${RESPOSTA}    GET On Session    pokeApi    ${API_BASE}${ENDPOINT_NAME}${POKEMON_NAME}
    Set Test Variable    ${RESPOSTA}
    Dictionary Should Contain Item    ${RESPOSTA.json()}    name    ${POKEMON_NAME}

Conferir Status Code
    [Arguments]    ${STATUS_CODE}
    Should Be Equal As Strings    ${RESPOSTA.status_code}    ${STATUS_CODE}
