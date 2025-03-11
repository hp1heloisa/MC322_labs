import java.util.Scanner;

public class Main {
  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    Ambiente ambiente;
    Robo[] listaRobos = new Robo[50] ;
    System.out.println("Quantos robos você quer?");
    int quantRob = scanner.nextInt();
    
    for (int i=0; i<quantRob; i++){
      System.out.println("Qual o nome do robo " + (i+1) + "?");
      String nome = scanner.nextLine();
      listaRobos[i] = new Robo(nome,i,i);
    }
    // robo1 = new Robo("robo1", 0, 0);
    // robo2 = new Robo("robo2", 0, 0);
    ambiente = new Ambiente(10, 10);
    listaRobos[0].exibirNome();
    listaRobos[0].exibirPosicao();
    listaRobos[0].mover(11, 0);
    listaRobos[0].exibirPosicao();
    System.out.println(ambiente.dentroDosLimites(listaRobos[0].posicaoX(), listaRobos[0].posicaoY()));
  }
}
