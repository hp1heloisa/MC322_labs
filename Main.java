import java.util.Scanner;

public class Main {
  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    Ambiente ambiente;
    Print print;
    Robo[] listaRobos = new Robo[50] ; // 50 só um número de teste grande
    System.out.println("Quantos robos você quer?");
    int num_robos = scanner.nextInt();
    ambiente = new Ambiente(10, 10);
    scanner.nextLine();
    
    for (int i=0; i<num_robos; i++){
      System.out.println("Qual o nome do robo " + (i+1) + "? ");
      String nome = scanner.nextLine();
      System.out.println("Qual a posição x do robo" + (i+1) + "?");
      int pos_x = scanner.nextInt();
      scanner.nextLine();
      System.out.println("Qual a posição y do robo" + (i+1) + "?");
      int pos_y = scanner.nextInt();
      scanner.nextLine();
      listaRobos[i] = new Robo(nome,pos_x,pos_y);
    }

    print = new Print(listaRobos, num_robos, ambiente);
    print.print_tela();

  }
}
