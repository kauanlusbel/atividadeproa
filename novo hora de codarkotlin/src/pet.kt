import kotlin.system.exitProcess

// Classe representando o Bichinho Virtual
class VirtualPet(
    val nome: String,
    var fome: Int = 0,
    var felicidade: Int = 100,
    var cansaço: Int = 0,
    var idade: Int = 0
) {

    // Método para alimentar o bichinho
    fun alimentar() {
        if (fome > 0) {
            fome -= 10
            println("$nome foi alimentado! Nível de fome agora é $fome.")
        } else {
            println("$nome já está satisfeito!")
        }
    }

    // Método para brincar com o bichinho
    fun brincar() {
        felicidade += 20
        cansaço += 15
        println("$nome se divertiu brincando! Felicidade: $felicidade, Cansaço: $cansaço.")
    }

    // Método para descansar
    fun descansar() {
        if (cansaço > 0) {
            cansaço -= 20
            println("$nome descansou! Nível de cansaço agora é $cansaço.")
        } else {
            println("$nome já está descansado!")
        }
    }

    // Método para verificar o status do bichinho
    fun verificarStatus() {
        println(
            """
            Status de $nome:
            Idade: $idade
            Fome: $fome
            Felicidade: $felicidade
            Cansaço: $cansaço
        """.trimIndent()
        )
    }

    // Método que simula a passagem do tempo
    fun passarTempo() {
        fome += 3
        felicidade -= 30
        cansaço += 10
        idade += 1

        println("O tempo passou... Idade de $nome agora é $idade.")
        verificarRegrasDePerda()
    }

    // Regras de derrota
    private fun verificarRegrasDePerda() {
        when {
            fome >= 100 -> perderJogo("fome")
            felicidade <= 0 -> perderJogo("falta de felicidade")
            cansaço >= 100 -> perderJogo("cansaço")
            idade >= 50 -> ganharJogo()
        }
    }

    private fun perderJogo(razão: String) {
        println("Você perdeu! $nome não resistiu devido a $razão.")
        exitProcess(0)
    }

    private fun ganharJogo() {
        println("Parabéns! Você cuidou bem de $nome e ele atingiu a idade máxima de 50!")
        exitProcess(0)
    }
}

// Função principal com menu de interação
fun main() {
    println("Bem-vindo ao Simulador de Animal de Estimação Virtual!")
    print("Digite o nome do seu bichinho: ")
    val nome = readLine() ?: "Bichinho"
    val pet = VirtualPet(nome)

    while (true) {
        println(
            """
            Menu:
            1. Alimentar o bichinho
            2. Brincar com o bichinho
            3. Descansar o bichinho
            4. Verificar o status
            5. Passar o tempo
            6. Sair
        """.trimIndent()
        )
        print("Escolha uma opção: ")
        when (readLine()?.toIntOrNull()) {
            1 -> pet.alimentar()
            2 -> pet.brincar()
            3 -> pet.descansar()
            4 -> pet.verificarStatus()
            5 -> pet.passarTempo()
            6 -> {
                println("Saindo do jogo. Até mais!")
                exitProcess(0)
            }
            else -> println("Opção inválida, tente novamente.")
        }
    }
}
