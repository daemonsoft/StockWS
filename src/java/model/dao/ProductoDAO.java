/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.pojos.Producto;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author daemonsoft
 */
public class ProductoDAO {

    public void ingresarProducto(Producto p) {
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            sf = NewHibernateUtil.getSessionFactory();
            session = sf.openSession();
            tx = session.beginTransaction();
            session.save(p);
            tx.commit();
            session.close();

        } catch (Exception e) {
            tx.rollback();
            session.close();
            throw new RuntimeException("No se pudo guardar el producto");
        }
    }

    public Producto consultarProducto(String codigo) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        Producto p = (Producto) sesion.get(Producto.class, codigo);
        sesion.close();
        
        return p;
    }

    public List<Producto> verProductos() {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        Query q = sesion.createQuery("from Producto");
        List<Producto> lista = q.list();
        return lista;
    }

    public String borrarProducto(String codigo) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        Transaction tx = null;
        try {
            tx = sesion.beginTransaction();
            Producto p = (Producto) sesion.get(Producto.class, codigo);
            if (p==null){
                return "El producto con codigo " + codigo + " no existe";
            } 
            sesion.delete(p);
            tx.commit();
            sesion.close();
            return "Producto eliminado correctamente";
        } catch (Exception e) {
            tx.rollback();
            sesion.close();
            return "No se pudo eliminar el producto";
        }
    }
    public String actualizarProducto(Producto p){
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            sf = NewHibernateUtil.getSessionFactory();
            session = sf.openSession();
            tx = session.beginTransaction();
            session.merge(p);
            tx.commit();
            session.close();

        } catch (Exception e) {
            tx.rollback();
            session.close();
            return "No se pudo actualizar el producto";
        }
        return "Producto actualizado correctamente";
    }
}
