package com.choucair.formacion.queries;

public class Pedidos {
	
	   public static String qryConsultarPedido(String pedido) {              
	        String sql="";
	    	sql = "SELECT des_pedido_mov.num_pedido,des_pedido_mov.cod_empresa, des_pedido_mov.cod_concepto, des_pedido_mov.cod_tercero," +
	    			"des_pedido_mov.num_ordencompra, des_pedido_mov.fec_registro, des_pedido_mov.est_pedido," + 
	    			"des_pedido_mov.num_pedido_market, des_pedido_mov.val_flete, des_dpedido_mov.cod_referencia," + 
	    			"des_dpedido_mov.tip_talla, des_dpedido_mov.cod_color, des_dpedido_mov.cod_proveedoref," + 
	    			"SUM (des_dpedido_mov.can_pedida) AS can_pedida, des_dpedido_mov.val_unidad, des_dpedido_mov.val_descuento, " + 
	    			"des_dpedido_mov.cod_plu, des_dpedido_mov.nom_dirdespacho, des_dpedido_mov.cod_bodega " + 
	    			"FROM des_pedido_mov INNER JOIN " + 
	    			"des_dpedido_mov on des_pedido_mov.num_pedido = des_dpedido_mov.num_pedido and des_pedido_mov.hor_pedido NOTNULL " + 
	    			"WHERE des_pedido_mov.num_pedido = '%s' GROUP BY des_pedido_mov.num_pedido,des_pedido_mov.cod_empresa, des_pedido_mov.cod_concepto, des_pedido_mov.cod_tercero," +
	    			"des_pedido_mov.num_ordencompra, des_pedido_mov.fec_registro, des_pedido_mov.est_pedido," + 
	    			"des_pedido_mov.num_pedido_market, des_pedido_mov.val_flete, des_dpedido_mov.cod_referencia," + 
	    			"des_dpedido_mov.tip_talla, des_dpedido_mov.cod_color, des_dpedido_mov.cod_proveedoref," + 
	    			"des_dpedido_mov.can_pedida, des_dpedido_mov.val_unidad, des_dpedido_mov.val_descuento, des_dpedido_mov.cod_plu," + 
	    			"des_dpedido_mov.nom_dirdespacho, des_dpedido_mov.cod_bodega" ;
			return String.format(sql, pedido);

	    } 

}
