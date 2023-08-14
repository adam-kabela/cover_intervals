# cover_intervals

## Summary

Given a family F of intervals on the real line, the algorithm finds a subfamily S of F such that

(1) the union of S = the union of F, and

(2) S is a smallest possible subfamily which satisfies property (1)

The original descrtiption of the task was more detailed, a shortened version of of this description can be found below in Czech. 


## Algorithm description and runtime analysis

TODO


## Zestručněné zadání úkolu

Úkolem je implementovat jednoduchou konzolovou aplikaci například v jazyce java. 


Vstup: dvojice celých kladných čísel, kdy každá dvojice reprezentuje počáteční a koncový bod úsečky v jednorozměrném prostoru (všechny úsečky leží na stejné přímce).

Výstup: podmnožina úseček ze vstupní množiny, která svým sjednocením pokrývá co největší část přímky, a zároveň počet vrácených úseček je nejmenší možný, při zachování maximálního pokrytí přímky.


Podoba vstupu: <start_usecky> <konec_usecky>\n,  za poslední úsečkou je pak prázdný řádek.

Podoba výstupu: ve stejném formátu jako byl vstup s tím, že na pořadí úseček ve výstupu nezáleží.


Konkrétní příklad vstupu:

1 4	

5 6	

10 13

2 8

Očekávaný výstup:

1 4

10 13

2 8

Vynechat ve výstupu úsečku 1 4 by bylo špatně, protože vynecháním by nebyla pokryta souřadnice „1“ a nebyla by splněna podmínka maximálního pokrytí přímky.

Zahrnout do výstupu úsečku 5 6 by bylo špatně, protože pro maximální pokrytí je nadbytečná a nebyla by splněna podmínka minimálního počtu úseček.
