package intf.dream.android.online.menu.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import intf.dream.android.online.menu.dao.AnOnMenuListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anOnMenuListDAOTarget"
 * @spring.txbn id="anOnMenuListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnOnMenuListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AnOnMenuListDAO
{
	public List findMenuList(Map map) throws Exception
    {
		String compNo 	 = String.valueOf(map.get("compNo"));
    	String usrGrpId	 = String.valueOf(map.get("usrGrpId"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();


        query.append("SELECT y.menu_id AS menu_id							");
        query.append("		,y.menu_no AS menu_no							");
        query.append("		,y.p_menu_id AS p_menu_id						");
        query.append("		,y.p_menu_no AS p_menu_no						");
        query.append("		,y.key_type AS key_type							");
        query.append("		,y.key_no AS key_no								");
        query.append("		,y.ord_no AS ord_no								");
        query.append("		,z.lvl AS menu_lvl								");
        query.append("FROM TAUSRGRPMENU x INNER JOIN TAMENU y				");
        query.append("ON x.menu_id = y.menu_id								");
        query.append("INNER JOIN (SELECT * FROM dbo.SFAMENU_ALL('0')) z		");
        query.append("ON y.menu_id = z.menu_id								");
        query.append("WHERE 1=1												");
        query.append("AND x.comp_no 		= ?								");
        query.append("AND x.usrgrp_id 		= ?								");
        query.getAndQuery("y.service_type", String.valueOf(map.get("serviceType")));
        query.append("AND y.is_use 			= ?								");
        query.append("AND y.is_system 		= ?								");
        
        Object [] objects = new Object[]{
        		compNo
        		,usrGrpId
        		,"Y"
        		,"N"
        };
        
    	return getJdbcTemplate().queryForList(query.toString(), getObject(objects));
    } 
}