package dream.ass.base.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.ass.base.dao.AssBasePointValListDAO;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBasePointListDTO;
import dream.ass.base.dto.AssBasePointValListDTO;

/**
 * 평가기준 - List DAO implements
 * @author kim21017
 * @version $Id: AssBasePointValListDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="assBasePointValListDAOTarget"
 * @spring.txbn id="assBasePointValListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssBasePointValListDAOOraImpl extends BaseJdbcDaoSupportOra implements AssBasePointValListDAO
{
	public List findList(AssBaseCommonDTO assBaseCommonDTO, AssBasePointListDTO assBasePointListDTO, AssBasePointValListDTO assBasePointValListDTO, User user) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT														");
        query.append("		''								AS seqNo				");
        query.append("		,''								AS isDelCheck			");
        query.append("		,x.assbasepval_id				AS assBasePointValId	");
        query.append("		,x.assbasepoint_id				AS assBasePointId		");
        query.append("		,x.assbaselist_id				AS assBaseListId		");
        query.append("		,x.ass_eval						AS assEval				");
        query.append("		,x.eval_value					AS evalValue			");
        query.append("		,x.ord_no						AS ordNo				");
        query.append("		,x.is_use						AS isUseId				");
        query.append("		,SFACODE_TO_DESC(x.is_use,'IS_USE','SYS',''				");
        query.append("			,'"+user.getLangId()+"') 	AS isUseDesc			");
        query.append("		,x.remark						AS remark				");
        query.append("FROM TAASSBASEPVAL x											");
    	query.append("WHERE  1=1													");
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getAndNumKeyQuery("x.assbaselist_id", assBaseCommonDTO.getAssBaseListId());
    	query.getAndNumKeyQuery("x.assbasepoint_id", assBasePointListDTO.getAssBasePointId());
        
    	if (!"".equals(assBasePointValListDTO.getAssBasePointValId()))
        {
            query.getAndQuery("x.assbasepval_id", assBasePointValListDTO.getAssBasePointValId());
        }
        query.getOrderByQuery("x.ord_no", assBaseCommonDTO.getOrderBy(), assBaseCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assBaseCommonDTO.getIsLoadMaxCount(), assBaseCommonDTO.getFirstRow()));
    } 
    public int deleteList(String id, User user) throws Exception
    {
    	QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAASSBASEPVAL				");
        query.append("WHERE  comp_no 				= ?		");
        query.append("  AND  assbasepval_id  		= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
	public String findTotalCount(AssBaseCommonDTO assBaseCommonDTO, AssBasePointListDTO assBasePointListDTO, AssBasePointValListDTO assBasePointValListDTO, User user)
			throws Exception {
		
		QueryBuffer query = new QueryBuffer();
        query.append("SELECT													");
        query.append("		COUNT(1)											");
        query.append("FROM TAASSBASEPVAL x										");
    	query.append("WHERE  1=1												");
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getAndNumKeyQuery("x.assbaselist_id", assBaseCommonDTO.getAssBaseListId());
    	query.getAndNumKeyQuery("x.assbasepoint_id", assBasePointListDTO.getAssBasePointId());
    	
    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}