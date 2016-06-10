/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.servicio;



import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.dao.ProductoDAO;
import model.pojos.Producto;

/**
 *
 * @author daemonsoft
 */
@WebService(serviceName = "ProductoWebService")
public class ProductoWebService {

    /**
     * Web service operation
     * @param codigo
     * @param nombre
     * @param precio
     * @param stock
     * @param descripcion
     * @return 
     */
    @WebMethod(operationName = "IngresarProducto")
    public String IngresarProducto(@WebParam(name = "codigo") String codigo, @WebParam(name = "nombre") String nombre, @WebParam(name = "precio") double precio, @WebParam(name = "stock") int stock, @WebParam(name = "descripcion") String descripcion) {
        Producto p = new Producto(codigo, nombre, precio, stock, descripcion);
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.ingresarProducto(p);
        return "Producto ingresado correctamente";
    }

    /**
     * Web service operation
     * @param codigo
     * @return 
     */
    @WebMethod(operationName = "BuscarProducto")
    public Producto BuscarProducto(@WebParam(name = "codigo") String codigo) {
        ProductoDAO productoDAO = new ProductoDAO();
        return productoDAO.consultarProducto(codigo);

    }

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "ConsultarProductos")
    public List<Producto> ConsultarProductos() {
        ProductoDAO productoDAO = new ProductoDAO();
        return productoDAO.verProductos();
    }

    /**
     * Web service operation
     * @param codigo
     * @return 
     */
    @WebMethod(operationName = "BorrarProducto")
    public String BorrarProducto(@WebParam(name = "codigo") String codigo) {
         ProductoDAO productoDAO = new ProductoDAO();
        return productoDAO.borrarProducto(codigo);
    }

    /**
     * Web service operation
     * @param codigo
     * @param nombre
     * @param precio
     * @param stock
     * @param descripcion
     * @return 
     */
    @WebMethod(operationName = "ActualizarProducto")
    public String ActualizarProducto(@WebParam(name = "codigo") String codigo, @WebParam(name = "nombre") String nombre, @WebParam(name = "precio") double precio, @WebParam(name = "stock") int stock, @WebParam(name = "descripcion") String descripcion) {
        Producto p = new Producto(codigo, nombre, precio, stock, descripcion);
        ProductoDAO productoDAO = new ProductoDAO();
        String result = productoDAO.actualizarProducto(p);
        return result;
    }
}

