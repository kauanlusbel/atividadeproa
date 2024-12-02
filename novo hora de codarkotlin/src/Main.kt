import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    // Listas para armazenar dados dos alunos
    val nomes = mutableListOf<String>()
    val notas1 = mutableListOf<Double>()
    val notas2 = mutableListOf<Double>()
    val notas3 = mutableListOf<Double>()
    val notas4 = mutableListOf<Double>()

    while (true) {
        println(
            """
            MENU DE OPÇÕES
            1. Cadastrar alunos
            2. Pesquisar aluno pelo nome
            3. Apresentar todos os registros
            4. Sair
        """.trimIndent()
        )
        print("Escolha uma opção: ")
        when (scanner.nextInt()) {
            1 -> cadastrarAlunos(scanner, nomes, notas1, notas2, notas3, notas4)
            2 -> pesquisarAluno(scanner, nomes, notas1, notas2, notas3, notas4)
            3 -> apresentarTodos(nomes, notas1, notas2, notas3, notas4)
            4 -> {
                println("Saindo do programa...")
                break
            }
            else -> println("Opção inválida. Tente novamente.")
        }
    }
}

fun cadastrarAlunos(
    scanner: Scanner,
    nomes: MutableList<String>,
    notas1: MutableList<Double>,
    notas2: MutableList<Double>,
    notas3: MutableList<Double>,
    notas4: MutableList<Double>
) {
    println("Cadastro de alunos:")
    for (i in 1..20) {
        print("Digite o nome do aluno $i: ")
        scanner.nextLine() // Limpar buffer
        val nome = scanner.nextLine()
        nomes.add(nome)

        print("Digite a 1ª nota do aluno $nome: ")
        notas1.add(scanner.nextDouble())
        print("Digite a 2ª nota do aluno $nome: ")
        notas2.add(scanner.nextDouble())
        print("Digite a 3ª nota do aluno $nome: ")
        notas3.add(scanner.nextDouble())
        print("Digite a 4ª nota do aluno $nome: ")
        notas4.add(scanner.nextDouble())
    }

    // Ordenação dos registros por nome
    val indicesOrdenados = nomes.indices.sortedBy { nomes[it] }
    ordenarRegistros(indicesOrdenados, nomes, notas1, notas2, notas3, notas4)

    println("Cadastro concluído e ordenado por nome!")
}

fun ordenarRegistros(
    indicesOrdenados: List<Int>,
    nomes: MutableList<String>,
    notas1: MutableList<Double>,
    notas2: MutableList<Double>,
    notas3: MutableList<Double>,
    notas4: MutableList<Double>
) {
    val nomesOrdenados = indicesOrdenados.map { nomes[it] }
    val notas1Ordenadas = indicesOrdenados.map { notas1[it] }
    val notas2Ordenadas = indicesOrdenados.map { notas2[it] }
    val notas3Ordenadas = indicesOrdenados.map { notas3[it] }
    val notas4Ordenadas = indicesOrdenados.map { notas4[it] }

    nomes.clear()
    notas1.clear()
    notas2.clear()
    notas3.clear()
    notas4.clear()

    nomes.addAll(nomesOrdenados)
    notas1.addAll(notas1Ordenadas)
    notas2.addAll(notas2Ordenadas)
    notas3.addAll(notas3Ordenadas)
    notas4.addAll(notas4Ordenadas)
}

fun pesquisarAluno(
    scanner: Scanner,
    nomes: List<String>,
    notas1: List<Double>,
    notas2: List<Double>,
    notas3: List<Double>,
    notas4: List<Double>
) {
    print("Digite o nome do aluno a ser pesquisado: ")
    scanner.nextLine() // Limpar buffer
    val nome = scanner.nextLine()

    val index = nomes.indexOf(nome)
    if (index != -1) {
        val media = calcularMedia(notas1[index], notas2[index], notas3[index], notas4[index])
        val status = if (media >= 5) "Aprovado" else "Reprovado"
        println("Aluno: $nome")
        println("Média: %.2f".format(media))
        println("Status: $status")
    } else {
        println("Aluno não encontrado.")
    }
}

fun apresentarTodos(
    nomes: List<String>,
    notas1: List<Double>,
    notas2: List<Double>,
    notas3: List<Double>,
    notas4: List<Double>
) {
    println("Lista de todos os alunos:")
    for (i in nomes.indices) {
        val media = calcularMedia(notas1[i], notas2[i], notas3[i], notas4[i])
        val status = if (media >= 5) "Aprovado" else "Reprovado"
        println("Aluno: ${nomes[i]}, Média: %.2f, Status: $status".format(media))
    }
}

fun calcularMedia(n1: Double, n2: Double, n3: Double, n4: Double): Double {
    return (n1 + n2 + n3 + n4) / 4
}
