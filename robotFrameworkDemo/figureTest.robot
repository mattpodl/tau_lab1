*** Settings ***
Documentation    Test for command program that checks, if it's possible to build a figure with given sides.

Library    Process
Suite Teardown    Terminate All Processes    kill=True

Test Template     Should return correct triangle


*** Test Cases ***
string data             djadsaj       1       2       3   nie rozpoznano
negative data           7      -1      8      ${EMPTY}   nie rozpoznano
one side is 0           8       9       4       0.0   nie rozpoznano
two points              8       9       4.1     0.0.0   nie rozpoznano

can't build triangle    1       1      2     ${EMPTY}      nie rozpoznano
can't build fourside    1       1      1    3     nie rozpoznano

triangle równoboczny             1       1       1       ${EMPTY}   trójkąt równoboczny
1 triangle równoramienny           2.0       1.1       1.1       ${EMPTY}   trójkąt równoramienny
2 triangle równoramienny           1.11111       2.2       1.11111       ${EMPTY}   trójkąt równoramienny
3 triangle równoramienny           2       2       3       ${EMPTY}   trójkąt równoramienny
1 triangle różnoramienny       1.1       2      3       ${EMPTY}    trojkąt różnoramienny
2 triangle różnoramienny       2222222222       5555555555       4444444444       ${EMPTY}    trojkąt różnoramienny
triangle round problem   0.99999999999999999999       1.99999999999999999999          1.00000000000000000001   ${EMPTY}  trojkąt różnoramienny

one arg          1   ${EMPTY}   ${EMPTY}    ${EMPTY}    nie rozpoznano
two args           1       1      ${EMPTY}    ${EMPTY}    nie rozpoznano

*** Keywords ***
Should return correct triangle
    [Arguments]     ${a}    ${b}   ${c}   ${d}  ${expected}
    ${result} =     Run Process    ./figura.py     ${a}   ${b}   ${c} ${d}
    Should Be Equal As Strings    ${result.stdout}    ${expected}