# terminoes

```bash
 javac -d out $(find src -name "*.java") && java -cp out josep42ny.terminoes.Main
```

## Característiques comunes

- **Ús de fitxes de dòmino**: Totes les variants utilitzen fitxes dobles des del doble blanc (0-0) fins al doble sis (
  6-6).
- **Objectiu general**: Desfer-se de les fitxes abans que la resta de jugadors i acumular la menor quantitat de punts a
  la mà en cas de tancament.
- **Nombre de jugadors**: Quatre jugadors en dues parelles, algunes variants permeten que es jugui individualment.
- **Torns per ronda**: Es juga en sentit horari fins que una parella o jugador arribi a la puntuació màxima del joc.

## Característiques individuals

### Dòmino Mexicà

- **Jugadors**: Quatre jugadors individuals, o dues parelles fixes.
- **Objectiu**: Arribar a 200 o 300 punts.
- **Sortida**: Fitxa més alta després de barrejar.
- **Puntuació**: Se sumen tots els punts restants.
- **Tancament**: Guanya la parella o jugador amb menys punts; se sumen tots els punts restants.
- **Pas**: No especificat.

### Dòmino Llatí

- **Jugadors**: Dues parelles.
- **Objectiu**: Arribar a 100 o 200 punts.
- **Sortida**: Comença qui tingui el doble sis.
- **Puntuació**: Se sumen els punts de la parella perdedora.
- **Tancament**: Guanya la parella amb menys punts; se sumen els punts de la perdedora.
- **Pas**: S'afegeixen 25 o 30 punts al que el provoca.

### Dòmino Colombià

- **Jugadors**: Dues parelles.
- **Objectiu**: Arribar a 100 punts.
- **Sortida**: Comença qui tingui el doble sis; després, qui guanyi la ronda.
- **Puntuació**: Se sumen els punts de la parella perdedora.
- **Tancament**: Guanya la parella amb menys punts; se sumen els punts de la perdedora.
- **Pas**: No especificat (es juga amb diners).

### Dòmino Xilè

- **Jugadors**: Quatre jugadors individuals, o dues parelles fixes.
- **Objectiu**: Quan un jugador arribi a 121 punts o més, guanya qui tingui **menys punts**.
- **Sortida**: Comença qui tingui el 6-6; després gira en sentit horari.
- **Puntuació**: Cada jugador o parella acumula els seus punts; qui arribi a 121 perd.
- **Tancament**: Qui tingui més punts suma els punts de tots els altres al seu total.
- **Pas**: No especificat.

### Dòmino Veneçolà

- **Jugadors**: Dues parelles.
- **Objectiu**: Arribar a 75 o 100 punts.
- **Sortida**: Comença qui tingui el 6-6 (la "cochina"); després gira en sentit horari.
- **Puntuació**: Se sumen els punts de la parella perdedora.
- **Tancament**: Guanya la parella amb menys punts; se sumen els punts de la perdedora.
- **Pas**: No especificat.

### Dòmino de Ponce

- **Jugadors**: Dues parelles.
- **Objectiu**: Acumular 20 punts ("palets").
- **Sortida**: Comença qui tingui el 6-6; després gira en sentit horari.
- **Puntuació**:
    - Primer pas = 2 punts.
    - Altres passos = 1 punt (excepte el del company, que no compta).
    - Tancament = 2 punts per a qui tanca, i guanya qui tingui menys punts individualment.
- **Tancament**: Especificat en puntuació.
- **Pas**: Cada pas val punts per al rival, excepte el del company.

---

| Característiques mínimes per superar la part pràctica:                                                                                                                                                                                                                                        | Puntuació        |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------|
| Estil de programació orientat a objectes ( Herència, composició, polimorfisme, excepcions, patterns estudiats al mòdul formatiu, principis SOLID, etc )                                                                                                                                       | Fins a 6,5 punts |
| Utilització de collections de forma eficient.                                                                                                                                                                                                                                                 | Fins a 1,5 punts |
| Implementació de persistència emprant fitxers. L’aplicatiu ha de poder guardar les partides entre diferents jugadors finalitzades i no-finalitzades. En el cas en que la partida no estigui finalitzada l’aplicatiu ha de ser capaç de continuar la partida en el punt en que es va suspendre | Fins a 2 punts   |
| Nota: és necessari superar el 50% de puntuació a cada una de les característiques descrites en aquesta taula.                                                                                                                                                                                 |                  |
