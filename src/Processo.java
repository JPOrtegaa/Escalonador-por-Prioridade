
public class Processo extends Thread{
	private int id;
	private int arrivalTime;
	private int burstTime;
	private int finishTime;
	private int turnAround;
	private int tempoAtual;
	private int tempoRestante;
	private int quantum;
	private int prioridade;
	
	Processo(int id, int arrivalTime, int burstTime, int tempoRestante, int quantum, int prioridade){
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.tempoRestante = tempoRestante;
		this.finishTime = 0;
		this.turnAround = 0;
		this.tempoAtual = 0;
		this.quantum = quantum;
		this.prioridade = prioridade;
	}
	
	public void run() {
		tempoRestante -= quantum;
		if(tempoRestante <= 0) {
			finishTime = (tempoRestante + quantum) + tempoAtual;
			turnAround = finishTime - arrivalTime;
			tempoRestante = 0;
		}
	}
	
	public int getIdProcesso() {
		return this.id;
	}
	
	public int getBurstTime() {
		return this.burstTime;
	}
	
	public int getArrivalTime() {
		return this.arrivalTime;
	}
	
	public int getFinishTime() {
		return this.finishTime;
	}
	
	public int getTurnAround() {
		return this.turnAround;
	}
	
	public int getTempoRestante() {
		return this.tempoRestante;
	}
	
	public int getPrioridade() {
		return this.prioridade;
	}
	
	public void setTurnAround(int turnAround) {
		this.turnAround = turnAround;
	}
	
	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}
	
	public void setTempoAtual(int tempoAtual) {
		this.tempoAtual = tempoAtual;
	}

}
