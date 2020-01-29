package intf.dream.bee.menu.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.bee.menu.dao.BeeMenuListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeMenuListDAOTarget"
 * @spring.txbn id="beeMenuListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeMenuListDAOOraImpl extends BaseJdbcDaoSupportOra implements BeeMenuListDAO
{
	public List findMenuList(Map map) throws Exception
    {
		String compNo 	 = String.valueOf(map.get("compNo"));
    	String usrGrpId	 = String.valueOf(map.get("usrGrpId"));
    	
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT menu_id, menu_no, p_menu_id, p_menu_no, key_type   				");
        query.append("		, key_no, ord_no    ,LEVEL menu_lvl									");
        query.append("FROM  (																	");
        query.append("SELECT y.menu_id, y.menu_no, y.p_menu_id, y.p_menu_no, y.key_type			");
        query.append("		, y.key_no, y.ord_no												");
        query.append("FROM TAUSRGRPMENU x INNER JOIN TAMENU y									");
        query.append("ON x.menu_id = y.menu_id													");
        query.append("WHERE 1=1																	");
        query.getStringEqualQuery("x.comp_no", compNo);
        query.getAndQuery("x.usrgrp_id", usrGrpId);
        query.getAndQuery("y.service_type", String.valueOf(map.get("serviceType")));
        query.getAndQuery("y.is_use", "Y");
        query.append("	)																		");
    	query.append("START WITH p_menu_id =0													");
    	query.append("CONNECT BY PRIOR menu_id = p_menu_id										");
    	query.append("ORDER SIBLINGS BY ord_no													");
        
    	return getJdbcTemplate().queryForList(query.toString());
    } 
	
}