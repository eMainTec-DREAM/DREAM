package dream.ass.base.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.ass.base.dao.AssBasePointValDetailDAO;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBasePointListDTO;
import dream.ass.base.dto.AssBasePointValDetailDTO;
import dream.ass.base.dto.AssBasePointValListDTO;

/**
 * ���׸� - Detail DAO implements
 * @author kim21017
 * @version $Id: AssBasePointValDetailDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="assBasePointValDetailDAOTarget"
 * @spring.txbn id="assBasePointValDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssBasePointValDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements AssBasePointValDetailDAO
{
	
    public AssBasePointValDetailDTO findDetail(AssBaseCommonDTO assBaseCommonDTO,AssBasePointListDTO assBasePointListDTO, AssBasePointValListDTO assBasePointValListDTO, User user) 
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT														");
        query.append("		x.assbasepval_id				AS assBasePointValId	");
        query.append("		,x.assbasepoint_id				AS assBasePointId		");
        query.append("		,x.assbaselist_id				AS assBaseListId		");
        query.append("		,x.ass_eval						AS assEval				");
        query.append("		,x.eval_value					AS evalValue			");
        query.append("		,x.ord_no						AS ordNo				");
        query.append("		,x.is_use						AS isUseId				");
        query.append("		,SFACODE_TO_DESC(x.is_use,'IS_USE','SYS','', ? ) AS isUseDesc			");
        query.append("		,x.remark						AS remark				");
        query.append("		,x.ASS_EVAL_FROM				AS ASSEVALFROM			");
        query.append("		,x.ASS_EVAL_TO					AS ASSEVALTO			");
        query.append("FROM TAASSBASEPVAL x											");
    	query.append("WHERE  1=1													");
    	query.append("AND  x.assbaselist_id		= ?									");
    	query.append("AND  x.assbasepoint_id	= ?									");
    	query.append("AND  x.assbasepval_id		= ?									");
    	query.append("AND  x.comp_no    		= ?									");
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,assBaseCommonDTO.getAssBaseListId()
        		,assBasePointListDTO.getAssBasePointId()
        		,assBasePointValListDTO.getAssBasePointValId()
    			,user.getCompNo()
    	};
    
        AssBasePointValDetailDTO assBasePointValDetailDTO = 
        		(AssBasePointValDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new AssBasePointValDetailDTO()));
        
        return assBasePointValDetailDTO;
        
    }

    public int insertDetail(AssBaseCommonDTO assBaseCommonDTO,AssBasePointListDTO assBasePointListDTO,AssBasePointValListDTO assBasePointValListDTO,AssBasePointValDetailDTO assBasePointValDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAASSBASEPVAL(	");
    	query.append("	comp_no						");
    	query.append("	,assbasepval_id				");
    	query.append("	,assbasepoint_id			");
    	query.append("	,assbaselist_id				");
    	query.append("	,ass_eval					");
    	query.append("	,eval_value					");
    	query.append("	,ord_no						");
    	query.append("	,is_use						");
    	query.append("	,remark						");
    	query.append("	,ASS_EVAL_FROM				");
    	query.append("	,ASS_EVAL_TO				");
    	query.append(")VALUES(						");
    	query.append("	?							");
    	query.append("	,?							");
    	query.append("	,?							");
    	query.append("	,?							");
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
     			,assBasePointValDetailDTO.getAssBasePointValId()
     			,assBasePointListDTO.getAssBasePointId()
    			,assBaseCommonDTO.getAssBaseListId()
    			,assBasePointValDetailDTO.getAssEval()
    			,assBasePointValDetailDTO.getEvalValue()
    			,assBasePointValDetailDTO.getOrdNo()
    			,assBasePointValDetailDTO.getIsUseId()
    			,assBasePointValDetailDTO.getRemark()
    			,assBasePointValDetailDTO.getAssEvalFrom()
    			,assBasePointValDetailDTO.getAssEvalTo()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateDetail(AssBaseCommonDTO assBaseCommonDTO,AssBasePointListDTO assBasePointListDTO,AssBasePointValListDTO assBasePointValListDTO,AssBasePointValDetailDTO assBasePointValDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAASSBASEPVAL SET					");
    	query.append("	ass_eval				= ?				");
    	query.append("	,eval_value				= ?				");
    	query.append("	,ord_no					= ?				");
    	query.append("	,is_use					= ?				");
    	query.append("	,remark					= ?				");
    	query.append("	,ASS_EVAL_FROM			= ?				");
    	query.append("	,ASS_EVAL_TO			= ?				");
    	query.append("WHERE  comp_no			= ?				");
    	query.append("  AND  assbaselist_id		= ?				");
    	query.append("  AND  assbasepoint_id	= ?				");
    	query.append("  AND  assbasepval_id		= ?				");
    	
    	Object[] objects = new Object[] {
    			assBasePointValDetailDTO.getAssEval()
    			,assBasePointValDetailDTO.getEvalValue()
    			,assBasePointValDetailDTO.getOrdNo()
    			,assBasePointValDetailDTO.getIsUseId()
    			,assBasePointValDetailDTO.getRemark()
    			,assBasePointValDetailDTO.getAssEvalFrom()
    			,assBasePointValDetailDTO.getAssEvalTo()
    			,user.getCompNo()
    			,assBaseCommonDTO.getAssBaseListId()
    			,assBasePointListDTO.getAssBasePointId()
    			,assBasePointValListDTO.getAssBasePointValId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}