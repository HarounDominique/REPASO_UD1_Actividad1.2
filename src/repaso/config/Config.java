package repaso.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Scanner;

public class Config {
    public void crearConfigFile(HashMap<String, String> mapa, String rutaFichero) {
        File fichero = new File(rutaFichero);
        if (!fichero.exists()) {
            try {
                fichero.createNewFile();
                try (FileWriter fw = new FileWriter(fichero)) {
                    for (String clave : mapa.keySet()) {
                        fw.write(clave + " = " + mapa.get(clave) + "\n");
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

    public String leerConfig(String rutaFichero, String clave){
        boolean flag = false;
        String valor="";
        try {
            Scanner scan = new Scanner(new File(rutaFichero));
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                if(line.startsWith(clave)){
                    flag = true;
                    valor = line.substring(clave.length()+1);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if(flag){
            return valor;
        }else{
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        Config c = new Config();
        /*
        HashMap<String, String> mapa = new HashMap<>();
        mapa.put("Hola", "Mundo");
        mapa.put("Que", "Tal");
        mapa.put("Aquí", "Andamios");
        mapa.put("Chao", "Pescao");
        System.out.println(mapa.get("Hola"));
        c.crearConfigFile(mapa, "D:\\IdeaProjects\\UD1_Actividad1.2\\src\\repaso\\fichero.txt");
         */

        final String CONFIG_FILE_NAME = "piscina.config";
        File conf = new File(CONFIG_FILE_NAME);
        if (conf.exists()) {
            System.out.println("Existe");
        } else {
            System.out.println("No existe; creando fichero.");
            conf.createNewFile();
            HashMap<String, String> confHashMap = new HashMap<>();
            confHashMap.put("start", "true");
            confHashMap.put("persistencia", "true");
            confHashMap.put("max_franjas", "4");
            try (FileWriter fw = new FileWriter(conf)) {
                for (String key : confHashMap.keySet()) {
                    fw.write(key + "=" + confHashMap.get(key) + "\n");
                }
            }
        }
        System.out.println(c.leerConfig("D:\\IdeaProjects\\UD1_Actividad1.2\\piscina.config", "start"));
    }
}
