package dream.rcm.crity.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.rcm.crity.dao.RcmCrityValDetailDAO;
import dream.rcm.crity.dto.RcmCrityValDetailDTO;
import dream.rcm.crity.dto.RcmCrityValListDTO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;

/**
 * Criticality Matrix Val Page - Detail DAO implements
 * @author kim21017
 * @version $Id: RcmCrityValDetailDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmCrityValDetailDAOTarget"
 * @spring.txbn id="rcmCrityValDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmCrityValDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements RcmCrityValDetailDAO
{
	
    public RcmCrityValDetailDTO findDetail(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityValListDTO rcmCrityValListDTO, User user) 
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT													");
        query.append("		x.critylist_id					AS crityListId		");
        query.append("		,x.crityvalue_id				AS crityValId		");
        query.append("		,x.critycol_id					AS crityColId		");
        query.append("		,x.crityrow_id					AS crityRowId		");
        query.append("		,x.col_name						AS crityColDesc		");
        query.append("		,x.crity_lvl					AS crityLevel		");
        query.append("		,x.row_name						AS crityRowDesc		");
        query.append("		,x.crityvalue					AS crityValDesc		");
        query.append("		,x.critycolor					AS crityColorId		");
        query.append("		,SFACODE_TO_DESC(x.critycolor,'CRITYCOLOR','USR', '"+user.getCompNo()+"'	");
        query.append("			, ?) 						AS crityColorDesc	");
        query.append("		,x.is_critical					AS isCriticalId		");
        query.append("		,SFACODE_TO_DESC(x.is_critical,'IS_USE','SYS',''	");
        query.append("			, ?) 						AS isCriticalDesc	");
        query.append("		,x.remark						AS remark			");
        query.append("FROM TACRITYVALUE x										");
    	query.append("WHERE  1=1												");
    	query.append("AND  critylist_id 	= ?									");
    	query.append("AND  crityvalue_id 	= ?									");
    	query.append("AND  comp_no    		= ?									");
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,user.getLangId()
        		,rcmCrityCommonDTO.getCrityListId()
        		,rcmCrityValListDTO.getCrityValId()
    			,user.getCompNo()
    	};
    
        RcmCrityValDetailDTO rcmCrityValDetailDTO = 
        		(RcmCrityValDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new RcmCrityValDetailDTO()));
        
        return rcmCrityValDetailDTO;
        
    }
    
    public int updateDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityValDetailDTO rcmCrityValDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TACRITYVALUE SET					");
    	query.append("	is_critical				= ?				");
    	query.append("	,crityvalue				= ?				");
    	query.append("	,critycolor				= ?				");
    	query.append("	,remark					= ?				");
    	query.append("WHERE  comp_no			= ?				");
    	query.append("  AND  critylist_id		= ?				");
    	query.append("  AND  crityvalue_id		= ?				");
    	
    	Object[] objects = new Object[] {
    			rcmCrityValDetailDTO.getIsCriticalId()
    			,rcmCrityValDetailDTO.getCrityValDesc()
    			,rcmCrityValDetailDTO.getCrityColorId()
    			,rcmCrityValDetailDTO.getRemark()
    			,user.getCompNo()
    			,rcmCrityCommonDTO.getCrityListId()
    			,rcmCrityValDetailDTO.getCrityValId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}