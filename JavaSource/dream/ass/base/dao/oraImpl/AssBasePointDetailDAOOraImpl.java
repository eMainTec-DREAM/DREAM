package dream.ass.base.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.ass.base.dao.AssBasePointDetailDAO;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBasePointDetailDTO;
import dream.ass.base.dto.AssBasePointListDTO;

/**
 * 평가항목 - Detail DAO implements
 * @author kim21017
 * @version $Id: AssBasePointDetailDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="assBasePointDetailDAOTarget"
 * @spring.txbn id="assBasePointDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssBasePointDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements AssBasePointDetailDAO
{
	
    public AssBasePointDetailDTO findDetail(AssBaseCommonDTO assBaseCommonDTO, AssBasePointListDTO assBasePointListDTO, User user) 
    {
    	QueryBuffer query = new QueryBuffer();

        query.append("SELECT													");
        query.append("		x.assbasepoint_id				AS assBasePointId	");
        query.append("		,x.assbaselist_id				AS assBaseListId	");
        query.append("		,x.ass_point_value_type			AS reAssBaseId		");
        query.append("		,SFACODE_TO_DESC(x.ass_point_value_type,'ASS_POINT_VALUE_TYPE','SYS',''	");
        query.append("			,?	) 						AS reAssBaseDesc	");
        query.append("		,x.ass_point_type				AS assPointTypeId	");
        query.append("		,SFACODE_TO_DESC(x.ass_point_type,'ASS_POINT_TYPE','SYS',''	");
        query.append("			,?	) 						AS assPointTypeDesc	");
        query.append("		,x.ass_point					AS assPoint			");
        query.append("		,x.ord_no						AS ordNo			");
        query.append("		,x.is_use						AS isUseId			");
        query.append("		,SFACODE_TO_DESC(x.is_use,'IS_USE','SYS',''			");
        query.append("			,?	) 						AS isUseDesc		");
        query.append("		,x.remark						AS remark			");
        query.append("		,x.SQL_SCRIPT					AS reAssScript		");
        query.append("FROM TAASSBASEPOINT x										");
    	query.append("WHERE  1=1												");
    	query.append("AND  x.assbaselist_id		= ?								");
    	query.append("AND  x.assbasepoint_id	= ?								");
    	query.append("AND  x.comp_no    		= ?								");
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,user.getLangId()
        		,user.getLangId()
        		,assBaseCommonDTO.getAssBaseListId()
        		,assBasePointListDTO.getAssBasePointId()
    			,user.getCompNo()
    	};
    
        AssBasePointDetailDTO assBasePointDetailDTO = 
        		(AssBasePointDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new AssBasePointDetailDTO()));
        
        return assBasePointDetailDTO;
        
    }

    public int insertDetail(AssBaseCommonDTO assBaseCommonDTO,AssBasePointDetailDTO assBasePointDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAASSBASEPOINT(	");
    	query.append("	comp_no						");
    	query.append("	,assbasepoint_id			");
    	query.append("	,assbaselist_id				");
    	query.append("	,ass_point_value_type		");
    	query.append("	,ass_point_type				");
    	query.append("	,ass_point					");
    	query.append("	,ord_no						");
    	query.append("	,is_use						");
    	query.append("	,remark						");
    	query.append("	,sql_script					");
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
    	query.append(")								");
    	
    	Object[] objects = new Object[] {
    			 user.getCompNo()
     			,assBasePointDetailDTO.getAssBasePointId()
    			,assBaseCommonDTO.getAssBaseListId()
    			,assBasePointDetailDTO.getReAssBaseId()
    			,assBasePointDetailDTO.getAssPointTypeId()
    			,assBasePointDetailDTO.getAssPoint()
    			,assBasePointDetailDTO.getOrdNo()
    			,assBasePointDetailDTO.getIsUseId()
    			,assBasePointDetailDTO.getRemark()
    			,assBasePointDetailDTO.getReAssScript()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateDetail(AssBaseCommonDTO assBaseCommonDTO,AssBasePointDetailDTO assBasePointDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAASSBASEPOINT SET					");
    	query.append("	ass_point_value_type	= ?				");
    	query.append("	,ass_point_type			= ?				");
    	query.append("	,ass_point				= ?				");
    	query.append("	,ord_no					= ?				");
    	query.append("	,is_use					= ?				");
    	query.append("	,remark					= ?				");
    	query.append("	,sql_script				= ?				");
    	query.append("WHERE  comp_no			= ?				");
    	query.append("  AND  assbaselist_id		= ?				");
    	query.append("  AND  assbasepoint_id	= ?				");
    	
    	Object[] objects = new Object[] {
    			assBasePointDetailDTO.getReAssBaseId()
    			,assBasePointDetailDTO.getAssPointTypeId()
    			,assBasePointDetailDTO.getAssPoint()
    			,assBasePointDetailDTO.getOrdNo()
    			,assBasePointDetailDTO.getIsUseId()
    			,assBasePointDetailDTO.getRemark()
    			,assBasePointDetailDTO.getReAssScript()
    			,user.getCompNo()
    			,assBaseCommonDTO.getAssBaseListId()
    			,assBasePointDetailDTO.getAssBasePointId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}