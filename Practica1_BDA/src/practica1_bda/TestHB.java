package practica1_bda;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


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
                System.out.println("|     VOLA UB - Aerolinia  |");
                System.out.println("============================");
                System.out.println("| Options:                 |");
                System.out.println("|      1. Consultar        |");
                System.out.println("|      2. Afegir           |");
                System.out.println("|      3. Borrar           |");
                System.out.println("|      4. Modificar        |");
                System.out.println("|      5. Programar Ruta   |");
                System.out.println("|      6. Sortir           |");
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
                  System.out.println("Option 5 selected");
                  programarRuta(session);
                  break;
                case 6:
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
            
            
            List<ModelAvio> listado = new ArrayList<ModelAvio>();
            listado = showModelsAvio(session);
            if(!listado.isEmpty()){
                System.out.println("Model Avio: ");
                int sel = sc.nextInt();

                ModelAvio m = listado.get(sel-1);

                Avio a2 = new Avio(matricula, m);

                session.save(a2);
                session.getTransaction().commit();

                System.out.println("Successfully created "+ a2.toString());
                sc.nextLine();
            } else {
                System.out.println("No hi ha models disponibles");
            }
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
            
            List<Aeroport> listado = new ArrayList<Aeroport>();
            listado = showAeroports(session);
            if(!listado.isEmpty()){
                System.out.println("Aeroport Base: ");
                int sel = sc.nextInt();

                Aeroport aero = listado.get(sel-1);

                List<ModelAvio> listado2 = new ArrayList<ModelAvio>();
                listado2 = showModelsAvio(session);
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
            } else {
                System.out.println("No hi ha aeroports disponibles");
            }
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
          
                    
            List<Aeroport> listado = new ArrayList<Aeroport>();
            listado = showAeroports(session);
            if(!listado.isEmpty()){
                System.out.println("Aeroport Origen: ");
                int sel = sc.nextInt();
                Aeroport aero_origen = listado.get(sel-1);

                System.out.println("Aeroport Desti: ");
                sel = sc.nextInt();
                Aeroport aero_desti = listado.get(sel-1);

                List<ModelAvio> listado2 = new ArrayList<ModelAvio>();
                listado2 = showModelsAvio(session);
                System.out.println("Model Avio: ");
                sel = sc.nextInt();

                ModelAvio model = listado2.get(sel-1);

                Ruta r = new Ruta(dia, t, aero_origen, aero_desti, model);
                session.save(r);
                session.getTransaction().commit();

                System.out.println("Successfully created "+ r.toString());
                sc.nextLine();
            } else {
                System.out.println("No hi ha aeroports disponibles");
            }
            
        }
        
        public static void readAeroport(Session session){
            System.out.println("---------- Aeroports ----------");          
            List<Aeroport> listado = new ArrayList<Aeroport>();
            Query q = session.createQuery("from Aeroport");
            listado = q.list();

            System.out.println("Id | Codi_int | Nom       | Ciutat    | Cost_handling ");
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

            System.out.println("Id | Nom    |  Descripcio | Places | Pes ");
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

            System.out.println("Id | Matricula | Model ");
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

            System.out.println(" Id | Dia     | Hora     | Origen | Desti | Model Avio ");
            System.out.println("-------------------------------------------");
            for (Ruta ruta : listado) {
                System.out.println(ruta.getId() + " | " + ruta.getDia()+ " | " 
                        + ruta.getHora() + " | " + ruta.getAeroport_origen().getCodi_int()+ " | " 
                        + ruta.getAeroport_desti().getCodi_int() +  " | " + ruta.getModel_avio().getNom()
                        + " | ");
                if(ruta.getPilot() != null){
                    System.out.println("| Data    | Avio  | Pilot       | Incidencies ");
                    System.out.println(ruta.getData() + " | " + ruta.getAvio().getMatricula() + " | "
                        + ruta.getPilot().getNom() + ruta.getPilot().getCognom() + " | " + ruta.getIncidencies());
                }
            }
        }
        
        
        private static void deleteAeroport(Session session) {
            Scanner sc = new Scanner(System.in);
            List<Aeroport> listado = new ArrayList<Aeroport>();
            listado = showAeroports(session);
            if(!listado.isEmpty()){
                System.out.println("Aeroport: ");
                int sel = sc.nextInt();

                session.beginTransaction();
                Aeroport aero = listado.get(sel-1);

                session.delete(aero);
                session.getTransaction().commit();
            } else {
                System.out.println("No hi ha aeroports disponibles");
            }
        }

        private static void deleteModelAvio(Session session) {
            Scanner sc = new Scanner(System.in);
            List<ModelAvio> listado = new ArrayList<ModelAvio>();
            listado = showModelsAvio(session);
            if(!listado.isEmpty()){
                System.out.println("Model Avio: ");
                int sel = sc.nextInt();

                session.beginTransaction();
                ModelAvio model = listado.get(sel-1);

                List<Pilot> listado3 = new ArrayList<Pilot>();
                Query q3 = session.createQuery("from Pilot");
                listado3 = q3.list();

                for(Pilot pilot : listado3){
                    pilot.deleteModel(model);
                }

                session.delete(model);
                session.getTransaction().commit();
            } else {
                System.out.println("No hi ha models disponibles");
            }
        }

        private static void deleteAvio(Session session) {
            Scanner sc = new Scanner(System.in);
            List<Avio> listado = new ArrayList<Avio>();
            listado = showAvions(session);
            if(!listado.isEmpty()){
                System.out.println("Avio: ");
                int sel = sc.nextInt();

                session.beginTransaction();
                Avio avio = listado.get(sel-1);
                session.delete(avio);
                session.getTransaction().commit();
            } else {
                System.out.println("No hi ha avions disponibles");
            }
        }

        private static void deletePilot(Session session) {
            Scanner sc = new Scanner(System.in);
            List<Pilot> listado = new ArrayList<Pilot>();
            listado = showPilots(session);
            if(!listado.isEmpty()){
                System.out.println("Pilot: ");
                int sel = sc.nextInt();

                session.beginTransaction();
                Pilot pilot = listado.get(sel-1);
                session.delete(pilot);
                session.getTransaction().commit(); 
            } else {
                System.out.println("No hi ha pilots disponibles");
            }
        }

        private static void deleteRuta(Session session) {
            Scanner sc = new Scanner(System.in);
            List<Ruta> listado = new ArrayList<Ruta>();
            listado = showRutes(session);
            if(!listado.isEmpty()){
                System.out.println("Ruta: ");
                int sel = sc.nextInt();

                session.beginTransaction();
                Ruta ruta = listado.get(sel-1);
                session.delete(ruta);
                session.getTransaction().commit();
            } else {
                System.out.println("No hi ha rutes disponibles");
            }
        }
        
        private static void editAeroport(Session session) {
            Scanner sc = new Scanner(System.in);
            List<Aeroport> listado = new ArrayList<Aeroport>();
            listado = showAeroports(session);
            if(!listado.isEmpty()){
                System.out.println("Aeroport: ");
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
                
            } else {
                System.out.println("No hi ha aeroports disponibles");
            }
        }

        private static void editModelAvio(Session session) {
            Scanner sc = new Scanner(System.in);
            List<ModelAvio> listado = new ArrayList<ModelAvio>();
            listado = showModelsAvio(session);
            if(!listado.isEmpty()){
                System.out.println("Model Avio: ");
                int sel = sc.nextInt();

                session.beginTransaction();
                ModelAvio model = (ModelAvio) session.load(ModelAvio.class,listado.get(sel-1).getId());
                sc.nextLine();
                System.out.println("Nom: ");
                String nom = sc.nextLine();
                System.out.println("Descripcio: ");
                String desc = sc.nextLine();
                System.out.println("Places: ");
                int places = sc.nextInt();
                System.out.println("Pes: ");
                double pes = sc.nextDouble();

                model.setNom(nom);
                model.setDescripcio(desc);            
                model.setPlaces(places);
                model.setPes(pes);
                session.getTransaction().commit();
                
            } else {
                System.out.println("No hi ha models disponibles");
            }
        }

        private static void editAvio(Session session) {
            Scanner sc = new Scanner(System.in);
            List<Avio> listado = new ArrayList<Avio>();
            listado = showAvions(session);
            if(!listado.isEmpty()){
                System.out.println("Avio: ");
                int sel = sc.nextInt();

                session.beginTransaction();
                Avio avio = (Avio) session.load(Avio.class,listado.get(sel-1).getId());
                sc.nextLine();

                System.out.println("Matricula: ");
                String matricula = sc.nextLine();

                List<ModelAvio> listado2 = new ArrayList<ModelAvio>();
                listado2 = showModelsAvio(session);
                if(!listado2.isEmpty()){
                    System.out.println("Model Avio: ");
                    sel = sc.nextInt();

                    ModelAvio m = listado2.get(sel-1);

                    avio.setMatricula(matricula);
                    avio.setModelAvio(m);
                    //session.update(avio);
                    
                } else {
                    System.out.println("No hi ha models disponibles");
                }
                session.getTransaction().commit();
            } else {
                System.out.println("No hi ha avions disponibles");
            }
        }

        private static void editPilot(Session session) {
            Scanner sc = new Scanner(System.in);
            List<Pilot> listado = new ArrayList<Pilot>();
            listado = showPilots(session);
            if(!listado.isEmpty()){
                System.out.println("Pilot: ");
                int sel = sc.nextInt();

                session.beginTransaction();
                Pilot pilot = (Pilot) session.load(Pilot.class,listado.get(sel-1).getId());
                sc.nextLine();
                System.out.println("Nom: ");
                String nom = sc.nextLine();
                System.out.println("Cognom: ");
                String cognom = sc.nextLine();
                System.out.println("Hores Vol: ");
                int hores = sc.nextInt();        

                List<Aeroport> listado2 = new ArrayList<Aeroport>();
                listado2 = showAeroports(session);
                if(!listado2.isEmpty()){
                    System.out.println("Aeroport Base: ");
                    sel = sc.nextInt();

                    Aeroport aero = listado2.get(sel-1);

                    List<ModelAvio> listado3 = new ArrayList<ModelAvio>();
                    listado3 = showModelsAvio(session);
                    System.out.println("Model Avio: ");
                    sel = sc.nextInt();

                    ModelAvio model = listado3.get(sel-1);

                    pilot.setNom(nom);
                    pilot.setCognom(cognom);
                    pilot.setHores_vol(hores);
                    pilot.setAeroport(aero);
                    pilot.addModel(model);

                } else {
                    System.out.println("No hi ha aeroports disponibles");
                }
                session.getTransaction().commit();
            } else {
                System.out.println("No hi ha pilots disponibles");
            }
        }

        private static void editRuta(Session session) {
            Scanner sc = new Scanner(System.in);
            List<Ruta> listado = new ArrayList<Ruta>();
            listado = showRutes(session);
            if(!listado.isEmpty()){
                System.out.println("Ruta: ");
                int sel = sc.nextInt();

                session.beginTransaction();
                Ruta ruta = (Ruta) session.load(Ruta.class,listado.get(sel-1).getId());
                sc.nextLine();

                System.out.println("Dia: ");
                String dia = sc.nextLine();
                System.out.println("Hora: (hh:mm:ss) ");
                String hora = sc.nextLine();
                String[] h = hora.split(":");
                Time t = new Time(Integer.parseInt(h[0]), Integer.parseInt(h[1]), Integer.parseInt(h[2]));

                List<Aeroport> listado2 = new ArrayList<Aeroport>();
                listado2 = showAeroports(session);
                if(!listado2.isEmpty()){
                    System.out.println("Aeroport Origen: ");
                    sel = sc.nextInt();
                    Aeroport aero_origen = listado2.get(sel-1);

                    System.out.println("Aeroport Desti: ");
                    sel = sc.nextInt();
                    Aeroport aero_desti = listado2.get(sel-1);

                    List<ModelAvio> listado3 = new ArrayList<ModelAvio>();
                    listado3 = showModelsAvio(session);
                    System.out.println("Model Avio: ");
                    sel = sc.nextInt();

                    ModelAvio model = listado3.get(sel-1);

                    ruta.setDia(dia);
                    ruta.setHora(t);
                    ruta.setAeroport_origen(aero_origen);
                    ruta.setAeroport_desti(aero_desti);
                    ruta.setModel_avio(model);
                } else {
                    System.out.println("No hi ha aeroports disponibles");
                }
                session.getTransaction().commit();
        
            } else {
                System.out.println("No hi ha rutes disponibles");
            }
        }

        private static void programarRuta(Session session){
            
        }

        private static List<Aeroport> showAeroports(Session session){
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
            
            return listado;
        }
        private static List<ModelAvio> showModelsAvio(Session session){
            System.out.println("---------- Models Avio ----------");
            List<ModelAvio> listado = new ArrayList<ModelAvio>();
            Query q = session.createQuery("from ModelAvio");
            listado = q.list();
            int i = 1;
            
            for (ModelAvio model : listado) {
                System.out.println(i+"."+model.getNom());
                i++;
            }
        
            return listado;
        }
        
        private static List<Avio> showAvions(Session session){
            System.out.println("------------ Avions ------------");          
            List<Avio> listado = new ArrayList<Avio>();
            Query q = session.createQuery("from Avio");
            listado = q.list();
            int i = 1;
            
            for (Avio avio : listado) {
                System.out.println(i+"."+avio.getMatricula());
                i++;
            }
            
            return listado;

        }
        
        private static List<Pilot> showPilots(Session session){
            System.out.println("----------- Pilots -----------");
            List<Pilot> listado = new ArrayList<Pilot>();
            Query q = session.createQuery("from Pilot");
            listado = q.list();
            int i = 1;
            
            for (Pilot pilot : listado) {
                System.out.println(i+"."+pilot.getNom()+pilot.getCognom());
                i++;
            }
            
            return listado;
        }
        
        private static List<Ruta> showRutes(Session session){
            System.out.println("----------- Rutes -----------");
            List<Ruta> listado = new ArrayList<Ruta>();
            Query q = session.createQuery("from Ruta");
            listado = q.list();
            int i = 1;
            
            for (Ruta ruta : listado) {
                System.out.println(i+"."+ruta);
                i++;
            }
            return listado;
        }
        
        private static void menu_consultar(Session session) {
        // Local variable
            int swValue;

            // Display menu graphics
            System.out.println("============================");
            System.out.println("|     MENU CONSULTAR       |");
            System.out.println("============================");
            System.out.println("| Options:                 |");
            System.out.println("|      1. Aeroports        |");
            System.out.println("|      2. Models d'avio    |");
            System.out.println("|      3. Avions           |");
            System.out.println("|      4. Pilots           |");
            System.out.println("|      5. Rutes            |");
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
            System.out.println("|     MENU AFEGIR          |");
            System.out.println("============================");
            System.out.println("| Options:                 |");
            System.out.println("|      1. Aeroports        |");
            System.out.println("|      2. Models d'avio    |");
            System.out.println("|      3. Avions           |");
            System.out.println("|      4. Pilots           |");
            System.out.println("|      5. Rutes            |");
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
            System.out.println("|     MENU BORRAR          |");
            System.out.println("============================");
            System.out.println("| Options:                 |");
            System.out.println("|      1. Aeroports        |");
            System.out.println("|      2. Models d'avio    |");
            System.out.println("|      3. Avions           |");
            System.out.println("|      4. Pilots           |");
            System.out.println("|      5. Rutes            |");
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
            System.out.println("|     MENU EDITAR          |");
            System.out.println("============================");
            System.out.println("| Options:                 |");
            System.out.println("|      1. Aeroports        |");
            System.out.println("|      2. Models d'avio    |");
            System.out.println("|      3. Avions           |");
            System.out.println("|      4. Pilots           |");
            System.out.println("|      5. Rutes            |");
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
