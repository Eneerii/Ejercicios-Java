package service;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.CiudadesModel;

public class CiudadesService {
	ArrayList<CiudadesModel> ciudades=new ArrayList<>();
	String ruta="c:\\ficheros\\ciudades.txt";
	
	public void guardarCiudad(String nombre, int habitantes, String pais) {
		String datos=nombre+"|"+habitantes +"|"+pais;
		try(FileOutputStream fos=new FileOutputStream(ruta, true);
			PrintStream out=new PrintStream(fos);){
			out.println(datos);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public CiudadesModel ciudadMasPoblada() {
		CiudadesModel ciudad=null;
		int habitantesMax=0;
		try(FileReader fr=new FileReader(ruta);
				BufferedReader bf=new BufferedReader(fr)){
				String fila;
				//recorremos el fichero linea a línea, y convertimos cada línea en un Pedido
				//después, comprobamos si ese pedido es posterior al auxiliar
				while((fila=bf.readLine())!=null) {
					//convierto line a array
					String[] linea=fila.split("[|]");
					//creo objeto pedido con datos de la línea
					CiudadesModel c=new CiudadesModel(linea[0],Integer.parseInt(linea[1]),linea[2]);
					//comprobamos si se trata de una ciudad más poblada
					if(c.getHabitantes()>habitantesMax) {
						//si es así, actualizo variables
						habitantesMax=c.getHabitantes();
						ciudad=c;
					}
				}			
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
			
			return ciudad; //devuelvo pedido encontrado
	}
	
	public ArrayList<CiudadesModel>  ciudadesPorPais(String pais) {
		ArrayList<CiudadesModel> resultado=new ArrayList<>();
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
				CiudadesModel c=new CiudadesModel(linea[0],Integer.parseInt(linea[1]),linea[2]);
				if (c.getPais().equals(pais)) {
					
				}
				//añadimos pedido a la lista de resultados
				resultado.add(c);
			}			
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		return resultado;
	}
}
