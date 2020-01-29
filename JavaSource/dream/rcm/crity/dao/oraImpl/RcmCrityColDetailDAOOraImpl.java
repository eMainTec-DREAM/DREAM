package dream.rcm.crity.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.rcm.crity.dao.RcmCrityColDetailDAO;
import dream.rcm.crity.dto.RcmCrityColDetailDTO;
import dream.rcm.crity.dto.RcmCrityColListDTO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;

/**
 * Criticality Matrix Col Page - Detail DAO implements
 * @author kim21017
 * @version $Id: RcmCrityColDetailDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmCrityColDetailDAOTarget"
 * @spring.txbn id="rcmCrityColDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmCrityColDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements RcmCrityColDetailDAO
{
	
    public RcmCrityColDetailDTO findDetail(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityColListDTO rcmCrityColListDTO, User user) 
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT											");
        query.append("		x.critylist_id			AS crityListId		");
        query.append("		,x.critycol_id			AS crityColId		");
        query.append("		,x.col_name				AS crityColDesc		");
        query.append("		,x.ord_no				AS ordNo			");
        query.append("		,x.remark				AS remark			");
        query.append("FROM TACRITYCOL x									");
    	query.append("WHERE  1=1										");
    	query.append("AND  critylist_id 	= ?							");
    	query.append("AND  critycol_id 		= ?							");
    	query.append("AND  comp_no    		= ?							");
        
        Object[] objects = new Object[] {
        		rcmCrityCommonDTO.getCrityListId()
        		,rcmCrityColListDTO.getCrityColId()
    			,user.getCompNo()
    	};
    
        RcmCrityColDetailDTO rcmCrityColDetailDTO = 
        		(RcmCrityColDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new RcmCrityColDetailDTO()));
        
        return rcmCrityColDetailDTO;
        
    }

    public int insertDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityColDetailDTO rcmCrityColDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TACRITYCOL(	");
    	query.append("	comp_no					");
    	query.append("	,critylist_id			");
    	query.append("	,critycol_id			");
    	query.append("	,col_name				");
    	query.append("	,ord_no					");
    	query.append("	,remark					");
    	query.append(")VALUES(					");
    	query.append("	?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append(")							");
    	
    	Object[] objects = new Object[] {
    			 user.getCompNo()
    			,rcmCrityCommonDTO.getCrityListId()
    			,rcmCrityColDetailDTO.getCrityColId()
    			,rcmCrityColDetailDTO.getCrityColDesc()
    			,rcmCrityColDetailDTO.getOrdNo()
    			,rcmCrityColDetailDTO.getRemark()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityColDetailDTO rcmCrityColDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TACRITYCOL SET					");
    	query.append("	col_name			= ?				");
    	query.append("	,ord_no				= ?				");
    	query.append("	,remark				= ?				");
    	query.append("WHERE  comp_no		= ?				");
    	query.append("  AND  critylist_id	= ?				");
    	query.append("  AND  critycol_id	= ?				");
    	
    	Object[] objects = new Object[] {
    			rcmCrityColDetailDTO.getCrityColDesc()
    			,rcmCrityColDetailDTO.getOrdNo()
    			,rcmCrityColDetailDTO.getRemark()
    			,user.getCompNo()
    			,rcmCrityCommonDTO.getCrityListId()
    			,rcmCrityColDetailDTO.getCrityColId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    public int updateValue(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityColDetailDTO rcmCrityColDetailDTO, User user, String rowId)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("MERGE INTO TACRITYVALUE a				");
    	query.append("USING ( SELECT 	?	compNo			");
    	query.append("				   ,?	crityListId		");
    	query.append("				   ,?	crityColId		");
    	query.append("				   ,?	crityColDesc	");
    	query.append("				   ,?	ordNo			");
    	query.append("				   ,?	crityRowId		");
    	query.append("				   ,(SELECT a.row_name	");
    	query.append("				    FROM TACRITYROW a	");
    	query.append("				    WHERE a.comp_no = ?	");
    	query.append("				    AND crityrow_id = ?	");
    	query.append("				    ) crityRowDesc		");
    	query.append("			FROM DUAL) b				");
    	query.append("ON ( a.comp_no = b.compNo				");
    	query.append("	AND a.critylist_id = b.crityListId	");
    	query.append("	AND a.critycol_id = b.crityColId	");
    	query.append("	AND a.crityrow_id = b.crityRowId	");
    	query.append("		)								");
    	query.append("WHEN MATCHED THEN						");
    	query.append("UPDATE SET a.col_name = b.crityColDesc");
    	query.append("           ,a.col_ord_no = b.ordNo	");
    	query.append("WHEN NOT MATCHED THEN					");
    	query.append("INSERT	(a.comp_no, a.crityvalue_id, a.critylist_id, a.col_name,a.critycol_id,a.row_name, a.crityrow_id, a.col_ord_no)				");
    	query.append("VALUES	(b.compNo, SQACRITYVALUE_ID.nextval, b.crityListId, b.crityColDesc, b.crityColId, b.crityRowDesc,b.crityRowId, b.ordNo)		");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,rcmCrityCommonDTO.getCrityListId()
    			,rcmCrityColDetailDTO.getCrityColId()
    			,rcmCrityColDetailDTO.getCrityColDesc()
    			,rcmCrityColDetailDTO.getOrdNo()
    			,rowId
    			,user.getCompNo()
    			,rowId
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    public String[] findRowList(RcmCrityCommonDTO rcmCrityCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT x.crityrow_id crityRowId	");
    	query.append("FROM TACRITYROW x					");
    	query.append("WHERE x.comp_no = ?				");
    	query.append("AND	x.critylist_id = ?			");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,rcmCrityCommonDTO.getCrityListId()
    	};
    	
    	return QueryBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString(), objects));
    }
}