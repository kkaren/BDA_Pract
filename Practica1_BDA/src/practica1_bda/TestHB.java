package practica1_bda;

import java.util.ArrayList;
import java.util.List;


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
            
            Aeroport a1 = new Aeroport("BCN123","Aeroport del prat", "Barcelona", 1500.33);
            System.out.println("==========CREATE AEROPORT==============");
            Session session = getSessionFactory().openSession();
            session.beginTransaction();
            session.save(a1);
            session.getTransaction().commit();
            session.close();
            System.out.println("Successfully created "+ a1.toString());
            System.out.println("Aeroport ID" + a1.getId());
            
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
}
