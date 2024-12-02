import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    // Listas para armazenar nomes e alturas
    val nomes = mutableListOf<String>()
    val alturas = mutableListOf<Double>()

    while (true) {
        println(
            """
            MENU DE OPÇÕES
            1. Cadastrar 15 pessoas
            2. Apresentar pessoas com altura menor ou igual a 1.5m
            3. Apresentar pessoas com altura maior que 1.5m
            4. Apresentar pessoas com altura maior que 1.5m e menor que 2.0m
            5. Apresentar média de todas as alturas
            6. Sair
        """.trimIndent()
        )
        print("Escolha uma opção: ")
        when (scanner.nextInt()) {
            1 -> cadastrarPessoas(scanner, nomes, alturas)
            2 -> apresentarAlturasMenoresOuIguais(1.5, nomes, alturas)
            3 -> apresentarAlturasMaioresQue(1.5, nomes, alturas)
            4 -> apresentarAlturasEntre(1.5, 2.0, nomes, alturas)
            5 -> apresentarMediaAlturas(alturas)
            6 -> {
                println("Saindo do programa...")
                break
            }
            else -> println("Opção inválida. Por favor, tente novamente.")
        }
    }
}

fun cadastrarPessoas(scanner: Scanner, nomes: MutableList<String>, alturas: MutableList<Double>) {
    println("Cadastro de pessoas:")
    for (i in 1..15) {
        print("Digite o nome da pessoa $i: ")
        scanner.nextLine() // Limpar buffer
        val nome = scanner.nextLine()
        nomes.add(nome)

        print("Digite a altura da pessoa $nome (em metros): ")
        val altura = scanner.nextDouble()
        alturas.add(altura)
    }
    println("Cadastro concluído com sucesso!")
}

fun apresentarAlturasMenoresOuIguais(
    limite: Double,
    nomes: List<String>,
    alturas: List<Double>
) {
    println("Pessoas com altura menor ou igual a $limite m:")
    for (i in nomes.indices) {
        if (alturas[i] <= limite) {
            println("Nome: ${nomes[i]}, Altura: ${alturas[i]} m")
        }
    }
}

fun apresentarAlturasMaioresQue(
    limite: Double,
    nomes: List<String>,
    alturas: List<Double>
) {
    println("Pessoas com altura maior que $limite m:")
    for (i in nomes.indices) {
        if (alturas[i] > limite) {
            println("Nome: ${nomes[i]}, Altura: ${alturas[i]} m")
        }
    }
}

fun apresentarAlturasEntre(
    min: Double,
    max: Double,
    nomes: List<String>,
    alturas: List<Double>
) {
    println("Pessoas com altura entre $min m e $max m:")
    for (i in nomes.indices) {
        if (alturas[i] > min && alturas[i] < max) {
            println("Nome: ${nomes[i]}, Altura: ${alturas[i]} m")
        }
    }
}

fun apresentarMediaAlturas(alturas: List<Double>) {
    if (alturas.isNotEmpty()) {
        val media = alturas.average()
        println("A média das alturas é: %.2f m".format(media))
    } else {
        println("Nenhuma altura cadastrada.")
    }
}
