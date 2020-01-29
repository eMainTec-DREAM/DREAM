package dream.ass.base.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.ass.base.dao.AssBaseDetailDAO;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBaseDetailDTO;

/**
 * 설비등급 평가기준 - Detail DAO implements
 * @author kim21017
 * @version $Id: AssBaseDetailDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="assBaseDetailDAOTarget"
 * @spring.txbn id="assBaseDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssBaseDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements AssBaseDetailDAO
{
	
    public AssBaseDetailDTO findDetail(AssBaseCommonDTO assBaseCommonDTO, User user) 
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT																			");
        query.append("		x.assbaselist_id										AS assBaseListId	");
        query.append("		,x.assbaselist_no										AS assBaseListNo	");
        query.append("		,x.description											AS assBaseListDesc	");
        query.append("		,(SELECT full_desc															");
        query.append("		   FROM TAEQCTG																");
        query.append("		  WHERE comp_no = x.comp_no													");
        query.append("		    AND eqctg_id = x.eqctg_id)							AS eqCtgDesc		");
        query.append("		,x.is_use												AS isUseId			");
        query.append("		,SFACODE_TO_DESC(x.is_use,'IS_USE','SYS',''									");
        query.append("							,?) 								AS isUseDesc		");
        query.append("		,x.reg_date												AS regDate			");
        query.append("		,x.remark												AS remark			");
        query.append("FROM TAASSBASELIST x																");
    	query.append("WHERE  1=1																		");
    	query.append("AND  assbaselist_id 	= ?															");
    	query.append("AND  comp_no    		= ?															");
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,assBaseCommonDTO.getAssBaseListId()
    			,user.getCompNo()
    	};
    
        AssBaseDetailDTO assBaseDetailDTO = 
        		(AssBaseDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new AssBaseDetailDTO()));
        
        return assBaseDetailDTO;
        
    }

    public int insertDetail(AssBaseDetailDTO assBaseDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAASSBASELIST(	");
    	query.append("	comp_no						");
    	query.append("	,assbaselist_id				");
    	query.append("	,assbaselist_no				");
    	query.append("	,eqctg_id					");
    	query.append("	,description				");
    	query.append("	,is_use						");
    	query.append("	,reg_date					");
    	query.append("	,remark						");
    	query.append(")VALUES(						");
    	query.append("	?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append(")								");
    	
    	Object[] objects = new Object[] {
    			 user.getCompNo()
    			,assBaseDetailDTO.getAssBaseListId()
    			,assBaseDetailDTO.getAssBaseListNo()
    			,assBaseDetailDTO.getEqCtgId()
    			,assBaseDetailDTO.getAssBaseListDesc()
    			,assBaseDetailDTO.getIsUseId()
    			,assBaseDetailDTO.getRegDate()
    			,assBaseDetailDTO.getRemark()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateDetail(AssBaseDetailDTO assBaseDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAASSBASELIST SET				");
    	query.append("	description			= ?				");
    	query.append("	,eqctg_id			= ?				");
    	query.append("	,is_use				= ?				");
    	query.append("	,remark				= ?				");
    	query.append("	,reg_date			= ?				");
    	query.append("WHERE  comp_no		= ?				");
    	query.append("  AND  assbaselist_id	= ?				");
    	
    	Object[] objects = new Object[] {
    			assBaseDetailDTO.getAssBaseListDesc()
    			,assBaseDetailDTO.getEqCtgId()
    			,assBaseDetailDTO.getIsUseId()
    			,assBaseDetailDTO.getRemark()
    			,assBaseDetailDTO.getRegDate()
    			,user.getCompNo()
    			,assBaseDetailDTO.getAssBaseListId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}