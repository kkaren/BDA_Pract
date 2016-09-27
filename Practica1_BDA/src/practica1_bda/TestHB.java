package practica1_bda;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.StringType;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class TestHB {
        private static SessionFactory sessionFactory;
        private static ServiceRegistry serviceRegistry;
	/**
	 * @param args
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
            Session session = getSessionFactory().openSession();
            // Local variable
            int swValue;
            boolean acabar = FALSE;

            do {
                
            
                // Display menu graphics
                System.out.println("============================");
                System.out.println("|   MENU SELECTION DEMO    |");
                System.out.println("============================");
                System.out.println("| Options:                 |");
                System.out.println("|        1. Consultar      |");
                System.out.println("|        2. Afegir         |");
                System.out.println("|        3. Borrar         |");
                System.out.println("|        4. Modificar      |");
                System.out.println("|        5. Sortir         |");
                System.out.println("============================");
                swValue = Keyin.inInt(" Select option: ");

                // Switch construct
                switch (swValue) {
                case 1:
                  System.out.println("Option 1 selected");
                  menu_consultar(session);
                  break;
                case 2:
                  System.out.println("Option 2 selected");
                  menu_afegir(session);
                  break;
                case 3:
                  System.out.println("Option 3 selected");
                  menu_borrar(session);
                  break;
                case 4:
                  System.out.println("Option 4 selected");
                  menu_modificar(session);
                  break;
                case 5:
                  System.out.println("Exit selected");
                  acabar=TRUE;
                  break;
                default:
                  System.out.println("Invalid selection");
                  break; // This break is not really necessary
                }
            }while(!acabar);
            session.close();
            
	}
        
        public static SessionFactory getSessionFactory() {
            Configuration configuration = new Configuration();
            configuration.configure();
            serviceRegistry = (ServiceRegistry) new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
            
        }
        
        public static void createAeroport(Session session, Scanner sc){
            System.out.println("==========CREATE AEROPORT==============");
            session.beginTransaction();
            
            System.out.println("Codi internacional: ");
            String codi = sc.nextLine();
            System.out.println("Nom: ");
            String nom = sc.nextLine();
            System.out.println("Ciutat: ");
            String ciutat = sc.nextLine();
            System.out.println("Cost handling: ");
            double cost = sc.nextDouble();
            
            /*Aeroport a1 = new Aeroport("BCN","Aeroport del prat", "Barcelona", 1500.33);
            Aeroport a2 = new Aeroport("LAX","Aeroport de LA", "Los Angeles", 1500.33);
            */
            Aeroport a1 = new Aeroport(codi, nom, ciutat, cost);
            
            session.save(a1);
            //session.save(a2);
            session.getTransaction().commit();
            
            System.out.println("Successfully created "+ a1.toString());
            //System.out.println("Successfully created "+ a2.toString());
            sc.nextLine();
            
        }
        
        public static void createModelAvio(Session session, Scanner sc){
            System.out.println("==========CREATE MODEL AVIO==============");
            session.beginTransaction();
            
            System.out.println("Nom: ");
            String nom = sc.nextLine();
            System.out.println("Descripcio: ");
            String desc = sc.nextLine();
            System.out.println("Places: ");
            int places = sc.nextInt();
            System.out.println("Pes: ");
            double pes = sc.nextDouble();
            
            /*
            ModelAvio m1 = new ModelAvio("Airbus A320","Model amb altes prestacions, blabla ...",300,200);
            ModelAvio m2 = new ModelAvio("Boeing 747","Model amb molta potencia, blabla ...",420,250);
            */  
            ModelAvio m1 = new ModelAvio(nom, desc, places, pes);
            
            session.save(m1);
            //session.save(m2);
            session.getTransaction().commit();
            
            System.out.println("Successfully created "+ m1.toString());
            //System.out.println("Successfully created "+ m2.toString());
            sc.nextLine();
        }
        
        public static void createAvio(Session session, Scanner sc){
            System.out.println("==========CREATE AVIO ==============");
            session.beginTransaction();
            
            System.out.println("Matricula: ");
            String matricula = sc.nextLine();
            
            System.out.println("---------- Models Avio ----------");
            List<ModelAvio> listado = new ArrayList<ModelAvio>();
            Query q = session.createQuery("from ModelAvio");
            listado = q.list();
            int i = 1;
            
            for (ModelAvio model : listado) {
                System.out.println(i+"."+model.getNom());
                i++;
            }
            System.out.println("Model Avio: ");
            int sel = sc.nextInt();
            
            ModelAvio m = listado.get(sel-1);
                
            Avio a2 = new Avio(matricula, m);
        
            session.save(a2);
            session.getTransaction().commit();
            
            System.out.println("Successfully created "+ a2.toString());
            sc.nextLine();
        }
                
        public static void createPilot(Session session, Scanner sc){
            System.out.println("==========CREATE PILOT ==============");
            session.beginTransaction();
            
            System.out.println("Nom: ");
            String nom = sc.nextLine();
            System.out.println("Cognom: ");
            String cognom = sc.nextLine();
            System.out.println("Hores Vol: ");
            int hores = sc.nextInt();        
            
            System.out.println("---------- Aeroports ----------");
            List<Aeroport> listado = new ArrayList<Aeroport>();
            Query q = session.createQuery("from Aeroport");
            listado = q.list();
            int i = 1;
            
            for (Aeroport aero : listado) {
                System.out.println(i+"."+aero.getCodi_int());
                i++;
            }
            System.out.println("Aeroport Base: ");
            int sel = sc.nextInt();
            
            Aeroport aero = listado.get(sel-1);
            
            System.out.println("-------- Models Avio --------");
            List<ModelAvio> listado2 = new ArrayList<ModelAvio>();
            q = session.createQuery("from ModelAvio");
            listado2 = q.list();
            i = 1;
  
            for (ModelAvio model : listado2) {
                System.out.println(i+"."+model.getNom());
                i++;
            }
            System.out.println("Model Avio: ");
            sel = sc.nextInt();
            
            ModelAvio model = listado2.get(sel-1);
            
            //Pilot p = new Pilot("Pepito", "Martinez", 125, aero);
            Pilot p = new Pilot(nom, cognom, hores, aero);
            p.addModel(model);
            // BUCLE PER AFEGIR MES D'UN MODEL !
            
            session.save(p);
            session.getTransaction().commit();
            
            System.out.println("Successfully created "+ p.toString());
            sc.nextLine();
        }
        
        public static void createRuta(Session session, Scanner sc){
            System.out.println("==========CREATE RUTA ==============");
            session.beginTransaction();
            
            System.out.println("Dia: ");
            String dia = sc.nextLine();
            System.out.println("Hora: (hh:mm:ss) ");
            String hora = sc.nextLine();
            String[] h = hora.split(":");
            Time t = new Time(Integer.parseInt(h[0]), Integer.parseInt(h[1]), Integer.parseInt(h[2]));
          
            System.out.println("---------- Aeroports ----------");          
            List<Aeroport> listado = new ArrayList<Aeroport>();
            Query q = session.createQuery("from Aeroport");
            listado = q.list();
            int i = 1;

            for (Aeroport aero : listado) {
                System.out.println(i+"."+aero.getCodi_int());
                i++;
            }
            System.out.println("Aeroport Origen: ");
            int sel = sc.nextInt();
            Aeroport aero_origen = listado.get(sel-1);
            
            System.out.println("Aeroport Desti: ");
            sel = sc.nextInt();
            Aeroport aero_desti = listado.get(sel-1);
            
            System.out.println("---------- Models Avio ----------");
            List<ModelAvio> listado2 = new ArrayList<ModelAvio>();
            q = session.createQuery("from ModelAvio");
            listado2 = q.list();
            i = 1;
            
            for (ModelAvio model : listado2) {
                System.out.println(i+"."+model.getNom());
                i++;
            }
            System.out.println("Model Avio: ");
            sel = sc.nextInt();
            
            ModelAvio model = listado2.get(sel-1);
            
            Ruta r = new Ruta(dia, t, aero_origen, aero_desti, model);
            session.save(r);
            session.getTransaction().commit();
            
            System.out.println("Successfully created "+ r.toString());
            sc.nextLine();
        }
        
        public static void readAeroport(Session session){
            System.out.println("---------- Aeroports ----------");          
            List<Aeroport> listado = new ArrayList<Aeroport>();
            Query q = session.createQuery("from Aeroport");
            listado = q.list();

            System.out.println("Id | Codi_int |  Nom                    | Ciutat     | Cost_handling ");
            System.out.println("--------------------------------------------------------------------");
            for (Aeroport aero : listado) {
                System.out.println(aero.getId() + " | " + aero.getCodi_int() + "       | " 
                        + aero.getNom() + " | " + aero.getCiutat() + " | " + aero.getCost_handling());
            }
        }
        
        public static void readModelAvio(Session session){
            System.out.println("---------- Models Avio ----------");          
            List<ModelAvio> listado = new ArrayList<ModelAvio>();
            Query q = session.createQuery("from ModelAvio");
            listado = q.list();

            System.out.println("Id | Nom |  Descripcio | Places | Pes ");
            System.out.println("--------------------------------------");
            for (ModelAvio model : listado) {
                System.out.println(model.getId() + " | " + model.getNom() + " | " 
                        + model.getDescripcio() + " | " + model.getPlaces() + " | " 
                        + model.getPes());
            }
        }
        
        public static void readAvio(Session session){
            System.out.println("------------ Avions ------------");          
            List<Avio> listado = new ArrayList<Avio>();
            Query q = session.createQuery("from Avio");
            listado = q.list();

            System.out.println("Id | Matricula |  Model ");
            System.out.println("-------------------------");
            for (Avio avio : listado) {
                System.out.println(avio.getId() + " | " + avio.getMatricula() + " | " 
                        + avio.getModelAvio().getNom());
            }
        }
        
        public static void readPilot(Session session){
            System.out.println("------------ Pilots ------------");          
            List<Pilot> listado = new ArrayList<Pilot>();
            Query q = session.createQuery("from Pilot");
            listado = q.list();

            System.out.println("Id | Nom | Cognom | Hores_vol | Model_avio ");
            System.out.println("-------------------------------------------");
            for (Pilot pilot : listado) {
                System.out.println(pilot.getId() + " | " + pilot.getNom() + " | " 
                        + pilot.getCognom() + " | " + pilot.getHores_vol() + " | " 
                        + pilot.getModelsNames());
            }
        }
             
        public static void readRuta(Session session){
            System.out.println("------------ Rutes ------------");          
            List<Ruta> listado = new ArrayList<Ruta>();
            Query q = session.createQuery("from Ruta");
            listado = q.list();

            System.out.println("Id | Dia | Hora | Origen | Desti | Incidencies | Pilot");
            System.out.println("-------------------------------------------");
            for (Ruta ruta : listado) {
                System.out.println(ruta.getId() + " | " + ruta.getDia()+ " | " 
                        + ruta.getHora() + " | " + ruta.getAeroport_origen().getCodi_int()+ " | " 
                        + ruta.getAeroport_desti().getCodi_int());
            }
        }
        
        
        private static void deleteAeroport(Session session) {
            System.out.println("---------- Aeroports ----------");
            Scanner sc = new Scanner(System.in);
            List<Aeroport> listado = new ArrayList<Aeroport>();
            Query q = session.createQuery("from Aeroport");
            listado = q.list();
            int i = 1;
            
            for (Aeroport aero : listado) {
                System.out.println(i+"."+aero.getCodi_int());
                i++;
            }
            System.out.println("Aeroport Base: ");
            int sel = sc.nextInt();
            
            Aeroport aero = listado.get(sel-1);
            session.beginTransaction();
            session.delete(aero);
            session.getTransaction().commit();
            
            System.out.println("---------- Aeroports ----------");
            listado = new ArrayList<Aeroport>();
            q = session.createQuery("from Aeroport");
            listado = q.list();
            i = 1;
            
            for (Aeroport aero1 : listado) {
                System.out.println(i+"."+aero1.getCodi_int());
                i++;
            }
        }

        private static void deleteModelAvio(Session session) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private static void deleteAvio(Session session) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private static void deletePilot(Session session) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private static void deleteRuta(Session session) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        private static void editAeroport(Session session) {
            System.out.println("---------- Aeroports ----------");
            Scanner sc = new Scanner(System.in);
            List<Aeroport> listado = new ArrayList<Aeroport>();
            Query q = session.createQuery("from Aeroport");
            listado = q.list();
            int i = 1;
            
            for (Aeroport aero : listado) {
                System.out.println(i+"."+aero.getCodi_int());
                i++;
            }
            System.out.println("Aeroport Base: ");
            int sel = sc.nextInt();
            session.beginTransaction();
            Aeroport aero = (Aeroport) session.load(Aeroport.class,listado.get(sel-1).getId());
            sc.nextLine();
            System.out.println("Codi internacional: ");
            String codi = sc.nextLine();
            System.out.println("Nom: ");
            String nom = sc.nextLine();
            System.out.println("Ciutat: ");
            String ciutat = sc.nextLine();
            System.out.println("Cost handling: ");
            double cost = sc.nextDouble();
            aero.setCodi_int(codi);
            aero.setCiutat(ciutat);            
            aero.setNom(nom);
            aero.setCost_handling(cost);
            session.getTransaction().commit();
            System.out.println("TAULA DESPRES DE UPDATES: ");
            listado = new ArrayList<Aeroport>();
            q = session.createQuery("from Aeroport");
            listado = q.list();
            i = 1;           
            for (Aeroport aero1 : listado) {
                System.out.println(i+"."+aero1.getCodi_int());
                i++;
            }
            

        }

        private static void editModelAvio(Session session) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private static void editAvio(Session session) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private static void editPilot(Session session) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private static void editRuta(Session session) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }


        private static void menu_consultar(Session session) {
        // Local variable
            int swValue;

            // Display menu graphics
            System.out.println("============================");
            System.out.println("|   MENU CONSULTAR         |");
            System.out.println("============================");
            System.out.println("| Options:                 |");
            System.out.println("|        1. Aeroports      |");
            System.out.println("|        2. Models d'avio  |");
            System.out.println("|        3. Avions         |");
            System.out.println("|        4. Pilots         |");
            System.out.println("|        5. Rutes          |");
            System.out.println("============================");
            swValue = Keyin.inInt(" Select option: ");

            // Switch construct
            switch (swValue) {
            case 1:
                System.out.println("Option 1 selected");
                readAeroport(session);
              break;
            case 2:
                System.out.println("Option 2 selected");
                readModelAvio(session);
              break;
            case 3:
                System.out.println("Option 3 selected");
                readAvio(session);
              break;
            case 4:
                System.out.println("Option 4 selected");
                readPilot(session);
              break;
            case 5:
                System.out.println("Option 5 selected");
                readRuta(session);
              break;
            default:
              System.out.println("Invalid selection");
              break; // This break is not really necessary
            }
        }

        private static void menu_afegir(Session session) {
        
        // Local variable
            int swValue;
            Scanner sc = new Scanner(System.in);

            // Display menu graphics
            System.out.println("============================");
            System.out.println("|   MENU AFEGIR            |");
            System.out.println("============================");
            System.out.println("| Options:                 |");
            System.out.println("|        1. Aeroports      |");
            System.out.println("|        2. Models d'avio  |");
            System.out.println("|        3. Avions         |");
            System.out.println("|        4. Pilots         |");
            System.out.println("|        5. Rutes          |");
            System.out.println("============================");
            swValue = Keyin.inInt(" Select option: ");

            // Switch construct
            switch (swValue) {
            case 1:
                System.out.println("Option 1 selected");
                createAeroport(session, sc);
              break;
            case 2:
                System.out.println("Option 2 selected");
                createModelAvio(session, sc);
              break;
            case 3:
                System.out.println("Option 3 selected");
                createAvio(session, sc);
              break;
            case 4:
                System.out.println("Option 4 selected");
                createPilot(session, sc);
              break;
            case 5:
                System.out.println("Option 5 selected");
                createRuta(session, sc);
              break;
            default:
              System.out.println("Invalid selection");
              break; // This break is not really necessary
            }
    
        }

        private static void menu_borrar(Session session) {
        // Local variable
            int swValue;
            Scanner sc = null;

            // Display menu graphics
            System.out.println("============================");
            System.out.println("|   MENU BORRAR            |");
            System.out.println("============================");
            System.out.println("| Options:                 |");
            System.out.println("|        1. Aeroports      |");
            System.out.println("|        2. Models d'avio  |");
            System.out.println("|        3. Avions         |");
            System.out.println("|        4. Pilots         |");
            System.out.println("|        5. Rutes          |");
            System.out.println("============================");
            swValue = Keyin.inInt(" Select option: ");

            // Switch construct
            switch (swValue) {
            case 1:
                System.out.println("Option 1 selected");
                deleteAeroport(session);
              break;
            case 2:
                System.out.println("Option 2 selected");
                deleteModelAvio(session);
              break;
            case 3:
                System.out.println("Option 3 selected");
                deleteAvio(session);
              break;
            case 4:
                System.out.println("Option 4 selected");
                deletePilot(session);
              break;
            case 5:
                System.out.println("Option 5 selected");
                deleteRuta(session);
              break;
            default:
              System.out.println("Invalid selection");
              break; // This break is not really necessary
            }
    
        }

        private static void menu_modificar(Session session) {
            int swValue;
            Scanner sc = null;

            // Display menu graphics
            System.out.println("============================");
            System.out.println("|   MENU EDITAR             |");
            System.out.println("============================");
            System.out.println("| Options:                 |");
            System.out.println("|        1. Aeroports      |");
            System.out.println("|        2. Models d'avio  |");
            System.out.println("|        3. Avions         |");
            System.out.println("|        4. Pilots         |");
            System.out.println("|        5. Rutes          |");
            System.out.println("============================");
            swValue = Keyin.inInt(" Select option: ");

            // Switch construct
            switch (swValue) {
            case 1:
                System.out.println("Option 1 selected");
                editAeroport(session);
              break;
            case 2:
                System.out.println("Option 2 selected");
                editModelAvio(session);
              break;
            case 3:
                System.out.println("Option 3 selected");
                editAvio(session);
              break;
            case 4:
                System.out.println("Option 4 selected");
                editPilot(session);
              break;
            case 5:
                System.out.println("Option 5 selected");
                editRuta(session);
              break;
            default:
              System.out.println("Invalid selection");
              break; // This break is not really necessary
            }
        }

}


class Keyin {

  //*******************************
  //   support methods
  //*******************************
  //Method to display the user's prompt string
  public static void printPrompt(String prompt) {
    System.out.print(prompt + " ");
    System.out.flush();
  }

  //Method to make sure no data is available in the
  //input stream
  public static void inputFlush() {
    int dummy;
    int bAvail;

    try {
      while ((System.in.available()) != 0)
        dummy = System.in.read();
    } catch (java.io.IOException e) {
      System.out.println("Input error");
    }
  }

  //********************************
  //  data input methods for
  //string, int, char, and double
  //********************************
  public static String inString(String prompt) {
    inputFlush();
    printPrompt(prompt);
    return inString();
  }

  public static String inString() {
    int aChar;
    String s = "";
    boolean finished = false;

    while (!finished) {
      try {
        aChar = System.in.read();
        if (aChar < 0 || (char) aChar == '\n')
          finished = true;
        else if ((char) aChar != '\r')
          s = s + (char) aChar; // Enter into string
      }

      catch (java.io.IOException e) {
        System.out.println("Input error");
        finished = true;
      }
    }
    return s;
  }

  public static int inInt(String prompt) {
    while (true) {
      inputFlush();
      printPrompt(prompt);
      try {
        return Integer.valueOf(inString().trim()).intValue();
      }

      catch (NumberFormatException e) {
        System.out.println("Invalid input. Not an integer");
      }
    }
  }

  public static char inChar(String prompt) {
    int aChar = 0;

    inputFlush();
    printPrompt(prompt);

    try {
      aChar = System.in.read();
    }

    catch (java.io.IOException e) {
      System.out.println("Input error");
    }
    inputFlush();
    return (char) aChar;
  }

  public static double inDouble(String prompt) {
    while (true) {
      inputFlush();
      printPrompt(prompt);
      try {
        return Double.valueOf(inString().trim()).doubleValue();
      }

      catch (NumberFormatException e) {
        System.out
            .println("Invalid input. Not a floating point number");
      }
    }
  }
}
