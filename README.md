>Manuel González_21-10250

##Para ejecutar:
Primero se debe ejecutar el CMD en la carpeta dónde está descargado el archivo, luego se usa el comando **"make"** para compilar el archivo. Una vez se compile se ejecuta usando el coamndo **./runSudoku.sh "Entrada"**, dónde *"Entrada"* es un string compuesto de números del 0 al 9, de 81 carcacteres, dónde 0 representa el espacio vacío en el sudoku y los números se ordenan por las filas, por ejemplo el sudoku:

1 |2 |3 |4|5|6|7|8|9
|-|-|-|-|-|-|-|-|-|-|
5|3| | | 7| 
6 | | | 1 |9 |5 
| |9 |8| | | | |6|
8| | |  |6| | | | 3
4 | | | 8|  |3| |  |1
7| | |  |2| | |  |6
| | 6| | | |  |2 |8
| | | | 4 |1 |9| |  |5
| | | | | 8| |  |7| 9

>representado como:
 530070000600195000098000060800060003400803001700020006060000280000419005000080079

tendría la siguiente entrada:

"./runSudoku.sh 530070000600195000098000060800060003400803001700020006060000280000419005000080079"

>Orden de las funciones: No se puede hablar del orden de las funciones con exactitud al tener *"hardcoded"* el tamaño del sudoku en 81. La funciòn principal de "ResolverSudoku" si se viese en representación O() serìa similar a O(n * n² * 3n) = O(n⁴) con n siendo el for de 1 a 9, n² el tamaño del sudoku y 3n la función de SePuedeAñadir


##Para el diseño de la funciones:

**ResolverSudoku:** Función inicial, usa la funcion auxiliar "SePuedeAñadir", se diseñò usando como referencia el algoritmo de ejemplo visto en clases de laboratorio de algoritmos, pero realizando cambios pertinentes para que efectivamente pueda comprobar los diferentes casos. Adicionalmente está la lìnea "if(solucion.size != 0) return true" para detenerse al hayar una solución, la cual puede ser cambiada para encontrar todas las posibles soluciones, o la cantidad de soluciones deseadas.

**SePuedeAñadir**: Originalmente compuesto por tres funciones auxiliares, "VerificarColumna", "VerificarFila" y "VerificarCuadrante" (todas con bucles while que iteraban en todas las posiciones excepto en la posición a añadir) busca el valor de la fila y columna dónde se encuentra el elemento actual y se itera para encontrar si está el nùmero buscado en la fila o columna.Se combinaron las dos funciones auxiliares en SePuedeAñadir, por la simpleza del for, al no afectar la legibilidad y que el ahorro de un elemento de la bùsqueda no afectaba realmente el rendimiento demasiado. En su lugar para ver el cuadrante, se hace uso de la función auxiliar "VerificarCuadrante" al ser un poco más complejo, por lo que no se combinó con la funcion final.

**VerificarCuadrante:** Funcion auxiliar: primero se intentó seguir el esquema de las funciones preliminares "VerificarColumna" y "VerificarFila",que constaban de bubles while que verificaban ambos lados para evitar verificar el elemento central (para ahorrar rendimiento), pero debido a la alta complejidad y múltiples repeticiones de las mismas lìneas de código, la implementación final busca el inicio del cuadrante e itera en un cuadrado 3x3 (en este caso salta de una posición a otra de la lista del sudoku)

**cargarDatos:** Funcion para verificar la entrada, se revisa si la entrada tiene el tamaño adecuado y no posee nùmeros repetidos. sigue un esquema similar a previos "cargarDatos" realizados en el laboratorio de Algoritmos II, pero este transforma el String de entrada en caracteres, los cuales son transformados en su valor en dìgitos de int, para introducirse en un arreglo, una vez en el arreglo se verifica si el valor añadido está en una posición problemática reutilizando "SePuedeAñadir". Para el diseño solo se pensó en procesar los datos y verificar a la vez, por lo que retorna null si es que no es una entrada válida y tiene prints para indicar el problema.


**main:** el main de la función hace uso de cargarDatos y ResolverSudoku para producir un arreglo soluciìon que se añade al set, el diseño fue pensado para poder imprimir varias soluciones, solo se tendrìa que realizar un for que itere en los elementos del set de soluciones después de la línea "if(solucion.size != 0)"
