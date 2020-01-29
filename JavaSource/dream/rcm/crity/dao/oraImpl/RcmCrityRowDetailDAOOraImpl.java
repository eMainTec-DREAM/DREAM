package dream.rcm.crity.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.rcm.crity.dao.RcmCrityRowDetailDAO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityRowDetailDTO;
import dream.rcm.crity.dto.RcmCrityRowListDTO;

/**
 * Criticality Matrix Row Page - Detail DAO implements
 * @author kim21017
 * @version $Id: RcmCrityRowDetailDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmCrityRowDetailDAOTarget"
 * @spring.txbn id="rcmCrityRowDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmCrityRowDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements RcmCrityRowDetailDAO
{
	
    public RcmCrityRowDetailDTO findDetail(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityRowListDTO rcmCrityRowListDTO, User user) 
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT											");
        query.append("		x.critylist_id			AS crityListId		");
        query.append("		,x.crityrow_id			AS crityRowId		");
        query.append("		,x.row_name				AS crityRowDesc		");
        query.append("		,x.ord_no				AS ordNo			");
        query.append("		,x.remark				AS remark			");
        query.append("		,x.crity_lvl			AS crityLevel		");
        query.append("FROM TACRITYROW x									");
    	query.append("WHERE  1=1										");
    	query.append("AND  critylist_id 	= ?							");
    	query.append("AND  crityrow_id 		= ?							");
    	query.append("AND  comp_no    		= ?							");
        
        Object[] objects = new Object[] {
        		rcmCrityCommonDTO.getCrityListId()
        		,rcmCrityRowListDTO.getCrityRowId()
    			,user.getCompNo()
    	};
    
        RcmCrityRowDetailDTO rcmCrityRowDetailDTO = 
        		(RcmCrityRowDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new RcmCrityRowDetailDTO()));
        
        return rcmCrityRowDetailDTO;
        
    }

    public int insertDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityRowDetailDTO rcmCrityRowDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TACRITYROW(	");
    	query.append("	comp_no					");
    	query.append("	,critylist_id			");
    	query.append("	,crityrow_id			");
    	query.append("	,row_name				");
    	query.append("	,ord_no					");
    	query.append("	,remark					");
    	query.append("	,crity_lvl				");
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
    			,rcmCrityCommonDTO.getCrityListId()
    			,rcmCrityRowDetailDTO.getCrityRowId()
    			,rcmCrityRowDetailDTO.getCrityRowDesc()
    			,rcmCrityRowDetailDTO.getOrdNo()
    			,rcmCrityRowDetailDTO.getRemark()
    			,rcmCrityRowDetailDTO.getCrityLevel()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public int updateDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityRowDetailDTO rcmCrityRowDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TACRITYROW SET					");
    	query.append("	row_name			= ?				");
    	query.append("	,ord_no				= ?				");
    	query.append("	,remark				= ?				");
    	query.append("	,crity_lvl			= ?				");
    	query.append("WHERE  comp_no		= ?				");
    	query.append("  AND  critylist_id	= ?				");
    	query.append("  AND  crityrow_id	= ?				");
    	
    	Object[] objects = new Object[] {
    			rcmCrityRowDetailDTO.getCrityRowDesc()
    			,rcmCrityRowDetailDTO.getOrdNo()
    			,rcmCrityRowDetailDTO.getRemark()
    			,rcmCrityRowDetailDTO.getCrityLevel()
    			,user.getCompNo()
    			,rcmCrityCommonDTO.getCrityListId()
    			,rcmCrityRowDetailDTO.getCrityRowId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    public int updateValue(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityRowDetailDTO rcmCrityRowDetailDTO, User user, String colId)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("MERGE INTO TACRITYVALUE a				");
    	query.append("USING ( SELECT 	?	compNo			");
    	query.append("				   ,?	crityListId		");
    	query.append("				   ,?	crityRowId		");
    	query.append("				   ,?	crityRowDesc	");
    	query.append("				   ,?	ordNo			");
    	query.append("				   ,?	crityColId		");
    	query.append("				   ,(SELECT a.col_name	");
    	query.append("				    FROM TACRITYCOL a	");
    	query.append("				    WHERE a.comp_no = ?	");
    	query.append("				    AND critycol_id = ?	");
    	query.append("				    ) crityColDesc		");
    	query.append("				   ,?	crityLevel		");
    	query.append("			FROM DUAL) b				");
    	query.append("ON ( a.comp_no = b.compNo				");
    	query.append("	AND a.critylist_id = b.crityListId	");
    	query.append("	AND a.critycol_id = b.crityColId	");
    	query.append("	AND a.crityrow_id = b.crityRowId	");
    	query.append("		)								");
    	query.append("WHEN MATCHED THEN						");
    	query.append("UPDATE SET a.row_name = b.crityRowDesc");
    	query.append("			,a.crity_lvl = b.crityLevel	");
    	query.append("			,a.row_ord_no = b.ordNo		");
    	query.append("WHEN NOT MATCHED THEN					");
    	query.append("INSERT	(a.comp_no, a.crityvalue_id, a.critylist_id, a.row_name,a.crityrow_id, a.crity_lvl,a.col_name, a.critycol_id, a.row_ord_no)					");
    	query.append("VALUES	(b.compNo, SQACRITYVALUE_ID.nextval, b.crityListId, b.crityRowDesc, b.crityRowId,b.crityLevel, b.crityColDesc, b.crityColId, b.ordNo)		");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,rcmCrityCommonDTO.getCrityListId()
    			,rcmCrityRowDetailDTO.getCrityRowId()
    			,rcmCrityRowDetailDTO.getCrityRowDesc()
    			,rcmCrityRowDetailDTO.getOrdNo()
    			,colId
    			,user.getCompNo()
    			,colId
    			,rcmCrityRowDetailDTO.getCrityLevel()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    public String[] findColList(RcmCrityCommonDTO rcmCrityCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT x.critycol_id crityColId	");
    	query.append("FROM TACRITYCOL x					");
    	query.append("WHERE x.comp_no = ?				");
    	query.append("AND	x.critylist_id = ?			");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,rcmCrityCommonDTO.getCrityListId()
    	};
    	
    	return QueryBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString(), objects));
    }
}