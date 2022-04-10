import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static void printLista(LinkedList<Processo> listaProcessos, int totalProcessos) {
		Processo x;
		System.out.println("  ID         Prioridade         ArrivalTime        FinishTime          BurstTime                 TurnAround");
		for(int i = 0; i < totalProcessos; i++) {
			x = listaProcessos.get(i);
			System.out.printf("%3d        	%3d         	   %3d         	      %3d        		 %3d        		 %3d\n", 
					x.getIdProcesso(), x.getPrioridade(), x.getArrivalTime(), 
					x.getFinishTime(), x.getBurstTime(), x.getTurnAround());
		}
	}
	
	public static void scanTempoManual(LinkedList<Processo> listaProcessos, int totalProcessos, int quantum, Scanner sc) {
		int arrivalTime, burstTime, prioridade;
		for(int i = 0; i < totalProcessos; i++) {
			System.out.println("Processo " + (i+1) + ":");
			System.out.print("Entre com arrivalTime: ");
			arrivalTime = sc.nextInt();
			System.out.print("Entre com burstTime: ");
			burstTime = sc.nextInt();
			System.out.print("Entre com a prioridade: ");
			prioridade = sc.nextInt();
			Processo x = new Processo(i+1, arrivalTime, burstTime, burstTime, quantum, prioridade);
			listaProcessos.add(x);
		}
	}
	
	public static void scanTempoAleatorio(LinkedList<Processo> listaProcessos, int totalProcessos, int quantum) {
		int arrivalTime, burstTime, prioridade;
		Random rand = new Random();
		for(int i = 0; i < totalProcessos; i++) {
			arrivalTime = rand.nextInt(101);
			burstTime = rand.nextInt(50);
			burstTime++; // para nao pegar resultado 0!!
			prioridade = rand.nextInt(10);
			prioridade++; // para nao pegar resultado 0!!
			Processo x = new Processo(i+1, arrivalTime, burstTime, burstTime, quantum, prioridade);
			listaProcessos.add(x);
		}
	}
	
	public static boolean repetidoPrioridade(int[] prioridade, int totalPrioridade, int x) {
		for(int i = 0; i < totalPrioridade; i++) 
			if(x == prioridade[i])
				return true;
		return false;
	}
	
	public static int contaPrioridade(LinkedList<Processo> listaProcesso, int totalProcesso) {
		int[] prioridade = new int[totalProcesso];
		int index = 0;
		for(int i = 0; i < totalProcesso; i++) 
			if(!repetidoPrioridade(prioridade, index, listaProcesso.get(i).getPrioridade())) 
				prioridade[index++] = listaProcesso.get(i).getPrioridade();
		return index;
	}

	public static void main(String[] args) {
		LinkedList<Processo> listaProcesso = new LinkedList<>();
		EscalonadorPrioridade escalonador;
		int n, quantum, flag;
		Scanner sc = new Scanner(System.in);
		System.out.print("Entre com o total de processos: ");
		n = sc.nextInt();
		System.out.print("Entre com o quantum (timeslice): ");
		quantum = sc.nextInt();
		System.out.println("Entrada de Dados:");
		System.out.println("1 - Entrada Manual");
		System.out.println("2 - Entrada Aleatoria");
		flag = sc.nextInt();
		switch(flag) {
		case 1:
			scanTempoManual(listaProcesso, n, quantum, sc);
			break;
		case 2:
			scanTempoAleatorio(listaProcesso, n, quantum);
			break;
		default:
			System.out.println("Opcao invalida!!");
		}
		escalonador = new EscalonadorPrioridade(listaProcesso, n, contaPrioridade(listaProcesso, n), quantum);
		printLista(listaProcesso, n);
		escalonador.start();
		while(escalonador.isAlive()) 
			;
		listaProcesso = escalonador.getListaProcesso();
		printLista(listaProcesso, n);
		sc.close();
	}

}
