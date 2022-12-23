package service;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Pedido;

public class PedidosService {
	String ruta="c:\\Ficheros\\pedidos.txt";

	public void guardarPedido(String producto, Date fechaPedido,double precio) {
		SimpleDateFormat fm=new SimpleDateFormat ("dd-MM-yyy");
		String datos=producto+"|"+fm.format(fechaPedido) +"|"+ precio;
		try (FileOutputStream fos=new FileOutputStream (ruta, true);
				PrintStream out=new PrintStream(fos);){
			out.println(datos);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public Pedido pedidoMasReciente() {
		Pedido pedido=null;
		Date fechaReciente=new Date(0);
		SimpleDateFormat fm = new SimpleDateFormat ("dd-MM-yyyy");
		try(FileReader fr=new FileReader(ruta);
				BufferedReader bf=new BufferedReader(fr)){
			String fila;
			//recorremos el fichero linea a línea, y convertimos cada línea en un Pedido
			//después, comprobamos si ese pedido es posterior al auxiliar
			while((fila=bf.readLine())!=null) {
				//convierto line a array
				String[] linea=fila.split("[|]");
				//creo objeto pedido con datos de la línea
				Pedido p=new Pedido(linea[0],fm.parse(linea[1]),Double.parseDouble(linea[2]));
				//comprobamos si se trata de un pedido más reciente
				if(p.getFechaPedido().after(fechaReciente)) {
					//si es así, actualizo variables
					fechaReciente=p.getFechaPedido();
					pedido=p;
				}
			}			
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		catch(ParseException ex) {
			ex.printStackTrace();
		}
		return pedido; //devuelvo pedido encontrado
	}

	public ArrayList<Pedido> todos(){
		ArrayList<Pedido> resultado=new ArrayList<>();
		SimpleDateFormat fm = new SimpleDateFormat ("dd-MM-yyyy");
		try(FileReader fr=new FileReader(ruta);
				BufferedReader bf=new BufferedReader(fr)){
			String fila;
			//recorremos el fichero linea a línea, y convertimos cada línea en un Pedido
			//después, comprobamos si ese pedido es posterior al auxiliar
			while((fila=bf.readLine())!=null) {
				//convierto line a array
				String[] linea=fila.split("[|]");
				//creo objeto pedido con datos de la línea
				Pedido p=new Pedido(linea[0],fm.parse(linea[1]),Double.parseDouble(linea[2]));
				//añadimos pedido a la lista de resultados
				resultado.add(p);
			}			
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		catch(ParseException ex) {
			ex.printStackTrace();
		}
		return resultado;

	}
}
