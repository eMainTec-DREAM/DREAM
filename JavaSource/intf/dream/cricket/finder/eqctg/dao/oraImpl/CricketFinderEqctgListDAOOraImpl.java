package intf.dream.cricket.finder.eqctg.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import intf.dream.cricket.finder.eqctg.dao.CricketFinderEqctgListDAO;
/**
 *  dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="cricketFinderEqctgListDAOTarget"
 * @spring.txbn id="cricketFinderEqctgListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class CricketFinderEqctgListDAOOraImpl extends BaseJdbcDaoSupportOra implements CricketFinderEqctgListDAO
{
	public List findEqctgList(Map map) throws Exception
    {
		String compNo = String.valueOf(map.get("compNo"));
		String eqCtgDesc = String.valueOf(map.get("eqCtgDesc"));
		String eqCtgNo = String.valueOf(map.get("eqCtgNo"));
		String plant = String.valueOf(map.get("plant"));
    	
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                ");
        query.append("         x.eqctg_id id                                ");
        query.append("         ,x.eqctg_no no                               ");
        query.append("         ,x.full_desc description                     ");
        query.append("         ,x.p_eqctg_id as pid                         ");
        query.append("         ,x.lvl as lvl                                ");
        query.append("FROM TAEQCTG x                                        ");
        query.append("WHERE 1=1                                             ");
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("x.eqctg_no",eqCtgNo);
        query.getLikeQuery("x.eqctg_no||x.full_desc", eqCtgDesc);
        query.append("ORDER BY x.eqctg_no	");
        return getJdbcTemplate().queryForList(query.toString());
    } 
}