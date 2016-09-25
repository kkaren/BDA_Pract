package practica1_bda;

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
            createAeroport(session, sc);
            createModelAvio(session, sc);
            createAvio(session, sc);
            createPilot(session, sc);
            session.close();
            /*	// TODO Auto-generated method stub
		Session session = null;
	        Transaction tx = null;
	        Articulo art = new Articulo("Lavadora AEG", 23.4);
	        
	        try {
	            session = ConnectorHB.getSession();
	            tx = session.beginTransaction();
	            session.save(art);
	            //El objecto art esta enlazado
	            
	            art.setDescripcion("Seat Leon"); //Esto se modificara en la BD no los datos iniciales.
	            tx.commit();
	            
	            List<Catalogo> listado = new ArrayList<Catalogo>();
	            Query q = session.createQuery("from Catalogo");
	            listado = q.list();
	            
	            for (Catalogo catalogo : listado) {
	            	System.out.println(catalogo.getDescripcion());
	            	for(Articulo articulo: catalogo.getArticulos())
	                System.out.println(articulo.getDescripcion());
	            }
	            System.out.println("Proceso finalizado...");
	            //US DE QUERY SQL PER TROBAR OBJECTES
	           List <Articulo> articulos = session.createSQLQuery("SELECT * FROM ARTICULO").addEntity(Articulo.class).list();
	            for(Articulo articulo: articulos)
	                System.out.println(articulo.getDescripcion());
	            
	  
	            Query q1= session.createSQLQuery("SELECT descripcion from ARTICULO WHERE ID=1").addScalar("descripcion",StringType.INSTANCE);
	          System.out.println("HE OBTINGUT LA DESCRIPCIO: "+q1.list().get(0));
	            
	        } catch (HibernateException e) {
	            if(tx!=null && tx.isActive()) tx.rollback();
	            e.printStackTrace();
	        } finally {
	            if(session!=null) session.close();
	        }
*/
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
            
            //Pilot p = new Pilot("Pepito", "Martinez", 125, aero);
            Pilot p = new Pilot(nom, cognom, hores, aero);
            session.save(p);
            session.getTransaction().commit();
            
            System.out.println("Successfully created "+ p.toString());
            sc.nextLine();
        }
        
        /*public static void createRuta(){
            
        }
        */
}
