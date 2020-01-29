package dream.rcm.crity.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.rcm.crity.dao.RcmCrityDetailDAO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityDetailDTO;

/**
 * Criticality Matrix Page - Detail DAO implements
 * @author kim21017
 * @version $Id: RcmCrityDetailDAOSqlImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmCrityDetailDAOTarget"
 * @spring.txbn id="rcmCrityDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmCrityDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmCrityDetailDAO
{
	
    public RcmCrityDetailDTO findDetail(RcmCrityCommonDTO rcmCrityCommonDTO, User user) 
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT																			");
        query.append("		x.critylist_id											AS crityListId		");
        query.append("		,x.critylist_no											AS crityListNo		");
        query.append("		,x.description											AS crityListDesc	");
        query.append("		,x.is_use												AS isUseId			");
        query.append("		,dbo.SFACODE_TO_DESC(x.is_use,'IS_USE','SYS',''									");
        query.append("							, ? ) 								AS isUseDesc		");
        query.append("		,x.reg_date												AS regDate			");
        query.append("		,x.remark												AS remark			");
        query.append("FROM TACRITYLIST x																");
    	query.append("WHERE  1=1																		");
    	query.append("AND  critylist_id 	= ?															");
    	query.append("AND  comp_no    		= ?															");
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,rcmCrityCommonDTO.getCrityListId()
    			,user.getCompNo()
    	};
    
        RcmCrityDetailDTO rcmCrityDetailDTO = 
        		(RcmCrityDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new RcmCrityDetailDTO()));
        
        return rcmCrityDetailDTO;
        
    }

    public int insertDetail(RcmCrityDetailDTO rcmCrityDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TACRITYLIST(	");
    	query.append("	comp_no					");
    	query.append("	,critylist_id			");
    	query.append("	,critylist_no			");
    	query.append("	,description			");
    	query.append("	,is_use					");
    	query.append("	,remark					");
    	query.append("	,reg_date				");
    	query.append(")VALUES(					");
    	query.append("	?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append(")							");
    	
    	Object[] objects = new Object[] {
    			 user.getCompNo()
    			,rcmCrityDetailDTO.getCrityListId()
    			,rcmCrityDetailDTO.getCrityListNo()
    			,rcmCrityDetailDTO.getCrityListDesc()
    			,rcmCrityDetailDTO.getIsUseId()
    			,rcmCrityDetailDTO.getRemark()
    			,rcmCrityDetailDTO.getRegDate()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
    
    public int updateDetail(RcmCrityDetailDTO rcmCrityDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TACRITYLIST SET				");
    	query.append("	description			= ?				");
    	query.append("	,is_use				= ?				");
    	query.append("	,reg_date			= ?				");
    	query.append("	,remark				= ?				");
    	query.append("WHERE  comp_no		= ?				");
    	query.append("  AND  critylist_id	= ?				");
    	
    	Object[] objects = new Object[] {
    			rcmCrityDetailDTO.getCrityListDesc()
    			,rcmCrityDetailDTO.getIsUseId()
    			,rcmCrityDetailDTO.getRegDate()
    			,rcmCrityDetailDTO.getRemark()
    			,user.getCompNo()
    			,rcmCrityDetailDTO.getCrityListId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
}