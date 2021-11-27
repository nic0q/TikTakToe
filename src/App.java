/* Estructura del programa:
    Se divide en 2 funciones |machine vs machine human vs machine| principales (Jugar 1 vs maquina, Jugar 2 jugadores)
    La maquina juega con la "X" y se representa por un 1 en la matriz
    Los Humanos se representan como 3 o 2 en la matriz y jugan con X o 0
*/

import java.util.*;

public class App {
  public static void main(String[] args) throws Exception {
    Scanner scan = new Scanner(System.in);
    int c = -1; // Verificador de ganador, Si es 0 gano maquina, si es 0 gano humano
    int conth = 0; // Contador de jugadas de humano para ver si es empate
    int[][] matrix = new int[3][3]; // Matriz del juego
    int[] lista_disp = new int[9]; // Lista de positions disponibles
    int x;

    generar_vacia_bi(matrix);
    generar_vacia_un(lista_disp);

    System.out.print("(1) Single Player (P1 vs CPU) \n(2) 2 Players (P1 vs P2)\n");
    x = scan.nextInt();

    tablero_muestra(matrix);
    if (x == 1) {
      machinevshuman(matrix, lista_disp, conth, c);
    } else {
      humanvshuman(matrix, lista_disp, conth, c);
    }

  }

  public static void machinevshuman(int matrix[][], int lista_disp[], int conth, int c) {
    while (true) {
      // Jugada Humano
      jugar_humano1(matrix, lista_disp, conth++);
      // Imprime tablero

      // Verifica si gana o hay empate
      if (empate(conth)) {
        System.out.println("DRAW");
        break;
      }
      if (ganar(matrix, c)) {
        System.out.println("°FINI°");
        tablero(matrix);
        break;
      }
      System.out.println("--------------");
      // Jugada Maquina
      jugar_maquina(matrix, lista_disp);
      // Imprime tablero
      tablero(matrix);

      // Verifica si gana o hay empate
      if (empate(conth)) {
        System.out.println("°DRAW°");
        break;
      }
      if (ganar(matrix, c) || empate(conth)) {
        System.out.println("°FINI°");
        break;
      }
      System.out.println("--------------");
      conth++;
    }
  }

  public static void humanvshuman(int matrix[][], int lista_disp[], int conth, int c) {
    while (true) {
      // Jugada Humano
      jugar_humano1(matrix, lista_disp, conth++);
      // Imprime tablero
      tablero(matrix);
      // Verifica si gana o hay empate
      if (ganar(matrix, c)) {
        System.out.println("°FINI°");
        break;
      }
      System.out.println("--------------");
      // Jugada Maquina
      jugar_humano2(matrix, lista_disp, conth++);
      // Imprime tablero
      tablero(matrix);
      // Verifica si gana o hay empate

      if (ganar(matrix, c)) {
        System.out.println("°FINI°");
        break;
      }
      System.out.println("--------------");
      conth++;
    }
  }

  public static boolean ganar(int matrix[][], int c) {
    if (ganar_columna(matrix, c) || ganar_fila(matrix, c) || ganar_diago(matrix, c)) {
      return true;
    }
    return false;

  }

  public static void ganador(int c) { // Funcion que no se usa por que todavia no se como implementar quien gana
    if (c == 1) {
      System.out.println("Gano Machine");
    }
    if (c == 0) {
      System.out.println("Gano Humano ");
    }
  }

  public static boolean empate(int conth) {
    if (conth > 7) {
      return true;
    }
    return false;
  }

  public static void jugar_humano1(int list[][], int lista_disp[], int conth) { // The human 1 plays with "O"
    Scanner scan = new Scanner(System.in);
    System.out.print("P1: ");
    int x = scan.nextInt();

    if (x == 0) {
      list[0][0] = 2;
      lista_disp[0] = 0;
    }
    if (x == 1) {
      list[0][1] = 2;
      lista_disp[1] = 0;
    }
    if (x == 2) {
      list[0][2] = 2;
      lista_disp[2] = 0;
    }
    if (x == 3) {
      list[1][0] = 2;
      lista_disp[3] = 0;
    }
    if (x == 4) {
      list[1][1] = 2;
      lista_disp[4] = 0;
    }
    if (x == 5) {
      list[1][2] = 2;
      lista_disp[5] = 0;
    }
    if (x == 6) {
      list[2][0] = 2;
      lista_disp[6] = 0;
    }
    if (x == 7) {
      list[2][1] = 2;
      lista_disp[7] = 0;
    }
    if (x == 8) {
      list[2][2] = 2;
      lista_disp[8] = 0;
    }
  }

  public static void jugar_humano2(int list[][], int lista_disp[], int conth) { // The human 2 plays with "X"
    Scanner scan = new Scanner(System.in);
    System.out.print("P2: ");
    int x = scan.nextInt();

    if (x == 0) {
      list[0][0] = 3;
      lista_disp[0] = 0;
    }
    if (x == 1) {
      list[0][1] = 3;
      lista_disp[1] = 0;
    }
    if (x == 2) {
      list[0][2] = 3;
      lista_disp[2] = 0;
    }
    if (x == 3) {
      list[1][0] = 3;
      lista_disp[3] = 0;
    }
    if (x == 4) {
      list[1][1] = 3;
      lista_disp[4] = 0;
    }
    if (x == 5) {
      list[1][2] = 3;
      lista_disp[5] = 0;
    }
    if (x == 6) {
      list[2][0] = 3;
      lista_disp[6] = 0;
    }
    if (x == 7) {
      list[2][1] = 3;
      lista_disp[7] = 0;
    }
    if (x == 8) {
      list[2][2] = 3;
      lista_disp[8] = 0;
    }
  }

  public static void jugar_maquina(int list[][], int lista_disp[]) { // The human plays with "X"
    int x;
    x = getRandom(0, 9);
    // Si el aleatorio genera un numero que ya esta ocupado debe generar otro
    if (lista_disp[x] == 0) {

      while (lista_disp[x] == 0) {
        x = getRandom(0, 9);
      }
    }

    if (x == 0) {
      list[0][0] = 1;
      lista_disp[0] = 0;
    }
    if (x == 1) {
      list[0][1] = 1;
      lista_disp[1] = 0;
    }
    if (x == 2) {
      list[0][2] = 1;
      lista_disp[2] = 0;
    }
    if (x == 3) {
      list[1][0] = 1;
      lista_disp[3] = 0;
    }
    if (x == 4) {
      list[1][1] = 1;
      lista_disp[4] = 0;
    }
    if (x == 5) {
      list[1][2] = 1;
      lista_disp[5] = 0;
    }
    if (x == 6) {
      list[2][0] = 1;
      lista_disp[6] = 0;
    }
    if (x == 7) {
      list[2][1] = 1;
      lista_disp[7] = 0;
    }
    if (x == 8) {
      list[2][2] = 1;
      lista_disp[8] = 0;
    }

  }

  public static boolean ganar_fila(int list[][], int c) {
    int sum = 0;
    int sum2 = 0;
    for (int i = 0; i < 3; i++) {
      sum = 0;
      sum2 = 0;
      for (int j = 0; j < 3; j++) {
        if (list[i][j] == 1) { // Si hay un 2 en tal posición significa que el humano "0" esta ahi
          sum += 1;
        }
        if (list[i][j] == 2) { // Si hay un 1 en tal posición significa que la maquina "X" esta ahi
          sum2 += 1;
        }

      }
      if (sum == 3 || sum2 == 3) { // Si cualquier de esas sumas da 3 quiere decir que alguien gano
        if (sum == 3) {
          c = 1;
        } else {
          c = 0;
        }
        return true;
      }
    }
    return false;
  }

  public static boolean ganar_columna(int list[][], int c) {
    int sum = 0;
    int sum2 = 0;
    for (int i = 0; i < 3; i++) {
      sum = 0;
      sum2 = 0;
      for (int j = 0; j < 3; j++) {
        if (list[j][i] == 2) { // Si hay un 2 en tal posición significa que el humano "0" esta ahi
          sum += 1;
        }
        if (list[j][i] == 1) { // Si hay un 1 en tal posición significa que la maquina "X" esta ahi
          sum2 += 1;
        }
      }
      if (sum == 3 || sum2 == 3) { // Si cualquier de esas sumas da 3 quiere decir que alguien gano
        if (sum == 3) {
          c = 0;
        } else {
          c = 1;
        }
        return true;
      }
    }
    return false;
  }

  public static boolean ganar_diago(int list[][], int c) {

    if ((list[0][0] == 3 && list[1][1] == 3 && list[2][2] == 3)
        || (list[0][0] == 1 && list[1][1] == 1 && list[2][2] == 1)
        || (list[0][0] == 2 && list[1][1] == 2 && list[2][2] == 2)) {
      return true; // Si hay en esos valores en
    }
    if ((list[0][2] == 3 && list[1][1] == 3 && list[2][0] == 3)
        || (list[0][2] == 1 && list[1][1] == 1 && list[2][0] == 1)
        || (list[0][2] == 2 && list[1][1] == 2 && list[2][0] == 2)) {
      return true;
    }
    return false;

  }

  public static int getRandom(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min) + min;
  }

  public static void generar_vacia_bi(int list[][]) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        list[i][j] = -1;
      }
    }
  }

  public static void generar_vacia_un(int list[]) {
    for (int i = 0; i < 9; i++) {
      list[i] = 1;
    }
  }

  public static void mostrar_matriz(int list[][]) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        System.out.println(list[i][j]);
      }
    }
  }

  public static void tablero(int list[][]) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (list[i][j] == -1) {
          System.out.print("| |");
        }
        if (list[i][j] == 1 || list[i][j] == 3) {
          System.out.print("|X|");
        }
        if (list[i][j] == 2) {
          System.out.print("|O|");
        }
      }
      System.out.println("\n");
    }
  }

  public static void tablero_muestra(int list[][]) {
    for (int i = 0; i < 9; i++) {
      System.out.print("|" + i + "|");
      if (i == 2 || i == 5) {
        System.out.println("\n");
      }
    }
    System.out.println("\n");
  }
}