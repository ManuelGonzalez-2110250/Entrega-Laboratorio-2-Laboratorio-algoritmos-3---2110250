fun ResolverSudoku(sudoku: Array<Int>, i: Int, solucion: MutableSet<Array<Int>>): Boolean{
    //si hay solución, retorna true
    if(solucion.size != 0) return true
    //si se ve el sudoku entero, se retorna que se hayó solución
    if(i == 81){
    	solucion.add(sudoku.copyOf())
    	return true
    }
    val elem: Int = sudoku[i]
    if(elem == 0){
        //se prueba del 1 al 9 
    	for(k in 1..9){
    	    //si se puede añadir
    	    if(SePuedeAñadir(sudoku, k, i)){
    	    
    	        //se añade el elemento k en la posicion i
    	        sudoku[i] = k
    	        //se itera en la siguiente posición
    	        ResolverSudoku(sudoku, i + 1, solucion)
    	        //se hace backtracking
    	        sudoku[i] = 0
    	    }
    	}
    }else{
    	return ResolverSudoku(sudoku, i + 1, solucion)
    }
    return false

}
fun SePuedeAñadir(sudoku:Array<Int>, numero: Int, indice: Int): Boolean{
    val fila = indice / 9
    val columna = indice % 9
    
    //se verifica la fila
    for(c in 0 until 9){
        //si se consigue el numero, retorna false
        if (sudoku[(fila * 9) + c] == numero) return false
    }
    //se verifica la columna
    for(f in 0 until 9){
        //si se consigue el numero, retorna false
        if (sudoku[(f * 9) + columna] == numero) return false
    }
    //si se se verifica true para ver el cuadrante, retorna true
    if(VerificarCuadrante(sudoku, numero, indice)) return true
    //de lo contrario returna false
    return false
}

fun VerificarCuadrante(sudoku:Array<Int>, numero: Int, indice: Int):Boolean{
    //posiciones en el arreglo
    val fila = indice / 9
    val columna = indice % 9
    //se haya el inicio del cuadrante
    val inicioF = (fila / 3) * 3
    val inicioC = (columna / 3) * 3
    for (f in 0..2){
        for(c in 0..2){
            //se calcula la posición en base a la iteración
            val pos = (inicioF + f) * 9 + (inicioC + c)
            if(sudoku[pos] == numero) return false
        }
    }
    //si no se consigue en el cuadrante, retorna true
    return true
    
}

fun cargarDatos(sudokuStr: String): Array<Int>?{
    var Datos : Array<Int> = Array<Int>(81) { 0 }
    var i: Int = 0
    var tamanio: Int = sudokuStr.length
    if(tamanio != 81) return null
    for (char in sudokuStr) {
        var num: Int = char.digitToInt()
        if(num == 0){
            Datos[i] = num
            i++
        }else if(SePuedeAñadir(Datos, num, i)){
            Datos[i] = num
            i++
        }else{
            println("$num no es válido en la posición $i")
            return null
        }
    }
    return Datos
}

fun main(args: Array<String>){
    val entrada: String = args[0]
    var sudoku: Array<Int>? = cargarDatos(entrada)
    if(sudoku == null){
    	println("La entrada no es válida")
    }else{
    	var solucion: MutableSet<Array<Int>> = mutableSetOf<Array<Int>>()
    	ResolverSudoku(sudoku, 0, solucion)
    	if(solucion.size != 0){
            val sol = solucion.elementAt(0)
    	    println(sol.joinToString(""))
    	}else{
    	    println("No se encontró la solución al sudoku")
        }
    }
    
}

