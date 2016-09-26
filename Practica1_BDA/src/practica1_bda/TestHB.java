package practica1_bda;

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
            Scanner sc = new Scanner(System.in);
            /*int sel = 0;
            do{
                System.out.println("VolaUB");
                System.out.println("------------------------------");
                System.out.println("1. Aeroport");
                System.out.println("2. Model Avio");
                System.out.println("3. Avio");
                System.out.println("4. Pilot");
                System.out.println("5. Ruta");
                System.out.println("6. Sortir");
                sel = sc.nextInt();
                
            } while(sel != 6);
            */
            
            
            createAeroport(session, sc);
            createAeroport(session, sc);
            readAeroport(session);
            
            
            createModelAvio(session, sc);
            createModelAvio(session, sc);
            createAvio(session, sc);
            createPilot(session, sc);
            /*createRuta(session, sc);
            */
            
            readModelAvio(session);
            readAvio(session);
            readPilot(session);
            //readRuta(session);
            
            /*
            deleteAeroport();
            deleteModelAvio();
            deleteAvio();
            deletePilot();
            deleteRuta();
            
            updateAeroport();
            updateModelAvio();
            updateAvio();
            updatePilot();
            updateRuta();
            
            */
            
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
            System.out.println("Hora: ");
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
        
        /*
        public static void readRuta(){
            
        }*/
}
