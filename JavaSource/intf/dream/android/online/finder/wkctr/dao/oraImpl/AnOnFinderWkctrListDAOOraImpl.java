package intf.dream.android.online.finder.wkctr.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.android.online.finder.wkctr.dao.AnOnFinderWkctrListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="anOnFinderWkctrListDAOTarget"
 * @spring.txbn id="anOnFinderWkctrListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AnOnFinderWkctrListDAOOraImpl extends BaseJdbcDaoSupportOra implements AnOnFinderWkctrListDAO
{
	public List findWkctrList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String wkCtrDesc = String.valueOf(map.get("wkCtrDesc"));
		String wkCtrNo = String.valueOf(map.get("wkCtrNo"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                               ");
        query.append("         x.wkctr_id id                               ");
        query.append("         ,x.wkctr_no no                              ");
        query.append("         ,x.description                              ");
        query.append("         ,x.p_wkctr_id as pid                        ");
        query.append("         ,LEVEL AS LVL                               ");
        query.append("FROM TAWKCTR x                                       ");
        query.append("WHERE 1=1                                            ");
        query.getAndQuery("x.comp_no",compNo);
        query.getStringEqualQuery("x.wkctr_no",wkCtrNo);
        query.getLikeQuery("x.wkctr_no||x.description", wkCtrDesc);
        query.append("START WITH p_wkctr_id = '0'							");
        query.append("CONNECT BY PRIOR wkctr_id = p_wkctr_id				");
        query.append("ORDER SIBLINGS BY ord_no								");
        
        return getJdbcTemplate().queryForList(query.toString());
    } 
}