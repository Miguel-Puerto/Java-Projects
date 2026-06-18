public class Tamagotchi {
    private String nome;
    private int fome; // 0 a 100
    private int felicidade; // 0 a 100
    private int energia; // 0 a 100

    public Tamagotchi(String nome) {
        this.nome = nome;
        this.fome = 50;
        this.felicidade = 50;
        this.energia = 50;
    }

    // Métodos de interação
    public void alimentar() {
        if (fome > 0) {
            fome -= 10;
            System.out.println(nome + " foi alimentado! Fome: " + fome);
        } else {
            System.out.println(nome + " não está com fome.");
        }
    }

    public void brincar() {
        if (energia > 10) {
            felicidade += 10;
            energia -= 10;
            System.out.println(nome + " brincou! Felicidade: " + felicidade + ", Energia: " + energia);
        } else {
            System.out.println(nome + " está cansado demais para brincar.");
        }
    }

    public void dormir() {
        energia += 20;
        fome += 10; // Fica com mais fome quando dorme
        System.out.println(nome + " dormiu! Energia: " + energia + ", Fome: " + fome);
    }

    public void mostrarStatus() {
        System.out.println("Status de " + nome);
        System.out.println("Fome: " + fome + "/100");
        System.out.println("Felicidade: " + felicidade + "/100");
        System.out.println("Energia: " + energia + "/100");
    }
}
