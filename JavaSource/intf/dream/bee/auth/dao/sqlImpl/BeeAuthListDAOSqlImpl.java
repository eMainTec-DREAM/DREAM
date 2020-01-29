package intf.dream.bee.auth.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import intf.dream.bee.auth.dao.BeeAuthListDAO;
/**
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeAuthListDAOTarget"
 * @spring.txbn id="beeAuthListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeAuthListDAOSqlImpl extends BaseJdbcDaoSupportSql implements BeeAuthListDAO
{
	public List findAuthPageList(Map map) throws Exception
    {
		
		String compNo = String.valueOf(map.get("compNo"));
    	String userId = String.valueOf(map.get("userId"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT 										");
        query.append("		x.page_id AS pageId						");
        query.append("		,x.description AS pageDesc				");
        query.append("		,x.file_name AS fileName				");
        query.append("		,x.key_type AS keyType					");
        query.append("		,x.key_no AS keyNo						");
        query.append("		,CASE WHEN x.is_use='Y' THEN			");
        query.append("					CASE WHEN y.usrgrp_id is not null THEN 'Y' ELSE 'N' END		");
        query.append("			ELSE 'N' END AS isUse				");
        query.append("FROM TAPAGE x LEFT OUTER JOIN 				");
        query.append("		(SELECT * 								");
        query.append("			FROM TAUGPGAU						");
        query.append("			WHERE usrgrp_id = (					");
        query.append("				SELECT a.usrgrp_id				");
        query.append("				FROM TAUSER a					");
        query.append("				WHERE a.comp_no = '"+compNo+"'	");
        query.append("				AND a.user_id = '"+userId+"'	");
        query.append("				)								");
        query.append("			AND comp_no = '"+compNo+"'			");
        query.append("		) y										");
        query.append("ON x.page_id = y.page_id						");
        query.append("WHERE 1=1										");
        query.getStringEqualQuery("x.service_type", String.valueOf(map.get("serviceType")));
    	
    	return getJdbcTemplate().queryForList(query.toString());
    	
    } 
	public List findAuthPgBtnList(Map map) throws Exception
	{
		
		String compNo = String.valueOf(map.get("compNo"));
		String userId = String.valueOf(map.get("userId"));
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT 										");
		query.append("		x.pgbtn_id AS pgBtnId					");
		query.append("		,x.page_id AS pageId					");
		query.append("		,x.file_name AS fileName				");
		query.append("		,x.ord_no AS ordNo						");
		query.append("		,(SELECT a.button_no					");
		query.append("			FROM TABUTTON a						");
		query.append("			WHERE a.button_id = x.button_id) AS buttonNo ");
		query.append("		,x.key_type AS keyType					");
		query.append("		,x.key_no AS keyNo						");
        query.append("		,CASE WHEN x.is_use='Y' THEN			");
        query.append("					CASE WHEN y.usrgrp_id is not null THEN 'Y' ELSE 'N' END		");
        query.append("			ELSE 'N' END AS isUse				");
		query.append("FROM TAPGBTN x LEFT OUTER JOIN 				");
        query.append("		(SELECT * 								");
        query.append("			FROM TAUGPGBTNAU					");
        query.append("			WHERE usrgrp_id = (					");
        query.append("				SELECT a.usrgrp_id				");
        query.append("				FROM TAUSER a					");
        query.append("				WHERE a.comp_no = '"+compNo+"'	");
        query.append("				AND a.user_id = '"+userId+"'	");
        query.append("				)								");
        query.append("			AND comp_no = '"+compNo+"'			");
        query.append("		) y										");
		query.append("ON x.pgbtn_id = y.pgbtn_id					");
		query.append("WHERE 1=1										");
		query.append("AND x.page_id IN (							");
		query.append("			SELECT page_id						");
		query.append("			FROM TAPAGE							");
		query.append("			WHERE 1=1							");
        query.getStringEqualQuery("service_type", String.valueOf(map.get("serviceType")));
		query.append("			)									");
		
		return getJdbcTemplate().queryForList(query.toString());
		
	} 
	public List findAuthPgFieldList(Map map) throws Exception
	{
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT 									");
		query.append("		pgfield_id AS pgFieldId				");
		query.append("		,page_id AS pageId					");
		query.append("		,field_id as fieldId				");
		query.append("		,description as fieldDesc			");
		query.append("		,hidden_yn as hiddenYn				");
		query.append("		,display_yn as displayYn			");
		query.append("		,check_yn as checkYn				");
		query.append("		,readonly_yn as readonlyYn			");
		query.append("		,ord_no as ordNo					");
		query.append("		,file_name as fileName				");
		query.append("		,key_type as keyType				");
		query.append("		,key_no as keyNo					");
		query.append("FROM TAPGFIELD							");
		query.append("WHERE 1=1									");
		query.append("AND page_id IN (							");
		query.append("			SELECT page_id					");
		query.append("			FROM TAPAGE						");
		query.append("			WHERE 1=1						");
        query.getStringEqualQuery("service_type", String.valueOf(map.get("serviceType")));
		query.append("			)								");
		
		return getJdbcTemplate().queryForList(query.toString());
		
	} 
	public List findAuthPgGridColList(Map map) throws Exception
	{
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT 																");
		query.append("		pggridcol_id AS pgGridColId										");
		query.append("		,column_id AS columnId											");
		query.append("		,ord_no as ordNo												");
		query.append("		,hidden as hidden												");
		query.append("		,system_col as systemCol										");
		query.append("		,file_name as fileName											");
		query.append("		,grid_obj_id as gridObjId										");
		query.append("FROM TAPGGRIDCOL														");
		query.append("WHERE 1=1																");
		query.append("AND pggrid_id IN (SELECT pggrid_id									");
		query.append("					FROM TAPGGRID										");
		query.append("					WHERE page_id IN (SELECT page_id					");
		query.append("										FROM TAPAGE						");
		query.append("										WHERE 1=1						");
        query.getStringEqualQuery("service_type", String.valueOf(map.get("serviceType")));
		query.append("										)								");
		query.append("					)													");
		
		return getJdbcTemplate().queryForList(query.toString());
		
	} 
	public List findAuthPgPageList(Map map) throws Exception
	{
		
		String compNo = String.valueOf(map.get("compNo"));
		String userId = String.valueOf(map.get("userId"));
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT 										");
		query.append("		x.pgpage_id AS pgPageId					");
		query.append("		,x.page_id AS pageId					");
		query.append("		,x.file_name AS fileName				");
		query.append("		,x.ord_no AS ordNo						");
		query.append("		,x.pgpage_name AS pgPageName			");
		query.append("		,x.key_type AS keyType					");
		query.append("		,x.key_no AS keyNo						");
        query.append("		,CASE WHEN x.is_use='Y' THEN			");
        query.append("					CASE WHEN y.usrgrp_id is not null THEN 'Y' ELSE 'N' END		");
        query.append("			ELSE 'N' END AS isUse				");
		query.append("FROM TAPGPAGE x LEFT OUTER JOIN 				");
        query.append("		(SELECT * 								");
        query.append("			FROM TAUGPGPGAU						");
        query.append("			WHERE usrgrp_id = (					");
        query.append("				SELECT a.usrgrp_id				");
        query.append("				FROM TAUSER a					");
        query.append("				WHERE a.comp_no = '"+compNo+"'	");
        query.append("				AND a.user_id = '"+userId+"'	");
        query.append("				)								");
        query.append("			AND comp_no = '"+compNo+"'			");
        query.append("		) y										");
		query.append("ON x.pgpage_id = y.pgpage_id					");
		query.append("WHERE 1=1										");
		query.append("AND x.page_id IN (							");
		query.append("			SELECT page_id						");
		query.append("			FROM TAPAGE							");
		query.append("			WHERE 1=1							");
        query.getStringEqualQuery("service_type", String.valueOf(map.get("serviceType")));
		query.append("			)									");
		
		return getJdbcTemplate().queryForList(query.toString());
		
	} 
	public List findAuthPgLinkedList(Map map) throws Exception
	{
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT 											");
		query.append("		pglinkedfunc_id AS pgLinkedFuncId			");
		query.append("		,page_id AS pageId							");
		query.append("		,ord_no as ordNo							");
		query.append("		,key_type as keyType						");
		query.append("		,key_no as keyNo							");
		query.append("		,file_name as fileName						");
		query.append("		,is_use as isUse							");
		query.append("		,(SELECT linkedfunc_no FROM TALINKEDFUNC a WHERE a.linkedfunc_id = x.linkedfunc_id) as linkedFuncNo ");
		query.append("FROM TAPGLINKEDFUNC x								");
		query.append("WHERE 1=1											");
		query.append("AND is_use='Y'									");
		query.append("AND page_id IN (SELECT page_id					");
		query.append("					FROM TAPAGE						");
		query.append("					WHERE 1=1						");
        query.getStringEqualQuery("service_type", String.valueOf(map.get("serviceType")));
		query.append("			)										");
		
		return getJdbcTemplate().queryForList(query.toString());
		
	} 
	
}