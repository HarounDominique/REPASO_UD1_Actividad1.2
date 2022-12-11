package repaso.persistencia;

import repaso.model.Piscina;

import java.io.*;

public class FilePersistencia {

    public void write(Piscina object, String ruta) {
        File fichero = new File(ruta);
        if (!fichero.exists()) {
            try {
                fichero.createNewFile();
            } catch (IOException e) {
                e.getMessage();
            }
        }
        try (
                FileOutputStream fis = new FileOutputStream(fichero);
                ObjectOutputStream ois = new ObjectOutputStream(fis)
        ) {
            ois.writeObject(object);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public Piscina read(String ruta) {
        Piscina piscinaRecuperada = null;
        File fichero = new File(ruta);
        if (!fichero.exists()) {
            try {
                fichero.createNewFile();
            } catch (IOException e) {
                e.getMessage();
            }
        }
        try (
                FileInputStream fis = new FileInputStream(fichero);
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            piscinaRecuperada = (Piscina)ois.readObject();
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }
        System.out.println(piscinaRecuperada.toString());
        return piscinaRecuperada;
    }

    public static void main(String[] args) {
        Piscina miPiscina = new Piscina(5,5,5,5);
        FilePersistencia fp = new FilePersistencia();
        fp.write(miPiscina, "D:\\IdeaProjects\\UD1_Actividad1.2\\ficheroPiscina.dat");
        fp.read("D:\\IdeaProjects\\UD1_Actividad1.2\\ficheroPiscina.dat");
    }
}

