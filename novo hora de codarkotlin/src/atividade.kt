import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    // Listas para armazenar os dados dos funcionários
    val matriculas = mutableListOf<Int>()
    val nomes = mutableListOf<String>()
    val salarios = mutableListOf<Double>()

    while (true) {
        println(
            """
            MENU DE OPÇÕES
            1. Cadastrar 20 empregados
            2. Pesquisar empregado por matrícula
            3. Apresentar empregados com salário acima de R$1.000,00
            4. Apresentar empregados com salário abaixo de R$1.000,00
            5. Apresentar empregados com salário igual a R$1.000,00
            6. Sair
        """.trimIndent()
        )
        print("Escolha uma opção: ")
        when (scanner.nextInt()) {
            1 -> cadastrarEmpregados(scanner, matriculas, nomes, salarios)
            2 -> pesquisarPorMatricula(scanner, matriculas, nomes, salarios)
            3 -> apresentarPorFaixaSalarial(matriculas, nomes, salarios, 1000.0, Double.MAX_VALUE, "acima de R$1.000,00")
            4 -> apresentarPorFaixaSalarial(matriculas, nomes, salarios, Double.MIN_VALUE, 999.99, "abaixo de R$1.000,00")
            5 -> apresentarPorFaixaSalarial(matriculas, nomes, salarios, 1000.0, 1000.0, "igual a R$1.000,00")
            6 -> {
                println("Saindo do programa...")
                break
            }
            else -> println("Opção inválida. Tente novamente.")
        }
    }
}

fun cadastrarEmpregados(scanner: Scanner, matriculas: MutableList<Int>, nomes: MutableList<String>, salarios: MutableList<Double>) {
    println("Cadastro de empregados:")
    for (i in 1..20) {
        print("Digite a matrícula do funcionário $i: ")
        val matricula = scanner.nextInt()
        matriculas.add(matricula)

        print("Digite o nome do funcionário $i: ")
        scanner.nextLine() // Limpar buffer
        val nome = scanner.nextLine()
        nomes.add(nome)

        print("Digite o salário do funcionário $i: R$")
        val salario = scanner.nextDouble()
        salarios.add(salario)
    }

    // Ordenar os registros por matrícula
    val indicesOrdenados = matriculas.indices.sortedBy { matriculas[it] }
    ordenarRegistros(indicesOrdenados, matriculas, nomes, salarios)

    println("Cadastro concluído e registros ordenados por matrícula!")
}

fun ordenarRegistros(
    indicesOrdenados: List<Int>,
    matriculas: MutableList<Int>,
    nomes: MutableList<String>,
    salarios: MutableList<Double>
) {
    val matriculasOrdenadas = indicesOrdenados.map { matriculas[it] }
    val nomesOrdenados = indicesOrdenados.map { nomes[it] }
    val salariosOrdenados = indicesOrdenados.map { salarios[it] }

    matriculas.clear()
    nomes.clear()
    salarios.clear()

    matriculas.addAll(matriculasOrdenadas)
    nomes.addAll(nomesOrdenados)
    salarios.addAll(salariosOrdenados)
}

fun pesquisarPorMatricula(scanner: Scanner, matriculas: List<Int>, nomes: List<String>, salarios: List<Double>) {
    print("Digite a matrícula do funcionário a ser pesquisado: ")
    val matricula = scanner.nextInt()
    val index = matriculas.indexOf(matricula)

    if (index != -1) {
        println("Funcionário encontrado:")
        println("Matrícula: ${matriculas[index]}, Nome: ${nomes[index]}, Salário: R$%.2f".format(salarios[index]))
    } else {
        println("Funcionário com a matrícula $matricula não encontrado.")
    }
}

fun apresentarPorFaixaSalarial(
    matriculas: List<Int>,
    nomes: List<String>,
    salarios: List<Double>,
    minSalario: Double,
    maxSalario: Double,
    descricao: String
) {
    println("Empregados com salário $descricao:")
    for (i in matriculas.indices) {
        if (salarios[i] in minSalario..maxSalario) {
            println("Matrícula: ${matriculas[i]}, Nome: ${nomes[i]}, Salário: R$%.2f".format(salarios[i]))
        }
    }
}
