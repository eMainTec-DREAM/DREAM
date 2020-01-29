package dream.rcm.crity.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.rcm.crity.dao.RcmCrityColDetailDAO;
import dream.rcm.crity.dto.RcmCrityColDetailDTO;
import dream.rcm.crity.dto.RcmCrityColListDTO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;

/**
 * Criticality Matrix Col Page - Detail DAO implements
 * @author kim21017
 * @version $Id: RcmCrityColDetailDAOSqlImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmCrityColDetailDAOTarget"
 * @spring.txbn id="rcmCrityColDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmCrityColDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmCrityColDetailDAO
{
	
    public RcmCrityColDetailDTO findDetail(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityColListDTO rcmCrityColListDTO, User user) 
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

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
    	QuerySqlBuffer query = new QuerySqlBuffer();
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
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
    
    public int updateDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityColDetailDTO rcmCrityColDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
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
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
    public int updateValue(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityColDetailDTO rcmCrityColDetailDTO, User user, String rowId)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int rtnValue  = 0;
    	
    	query.append("DECLARE @t1 TABLE( 																		");
    	query.append("	compNo NVARCHAR(1000),																	");
    	query.append("	crityListId NVARCHAR(1000),																");
    	query.append("	crityColId NVARCHAR(1000),																");
    	query.append("	crityColDesc NVARCHAR(1000),															");
    	query.append("	ordNo NVARCHAR(1000),																	");
    	query.append("	crityRowId NVARCHAR(1000),																");
    	query.append("	crityRowDesc NVARCHAR(1000)																");
    	query.append(") 																						");
    	query.append(" 																							");
    	query.append("INSERT @t1 VALUES(?,?,?,?,?,?,(SELECT row_name FROM TACRITYROW WHERE comp_no=? AND crityrow_id=?) ");
    	query.append(" 																							");
    	query.append("IF EXISTS( 																				");
    	query.append("	SELECT 1 																				");
    	query.append("	FROM TACRITYVALUE A, @t1 b 																");
    	query.append("	WHERE A.comp_no = b.compNo 																");
    	query.append("	AND A.critylist_id = b.crityListId 														");
    	query.append("	AND A.critycol_id = b.crityColId 														");
    	query.append("	AND A.crityrow_id = b.crityRowId 														");
    	query.append(") 																						");
    	query.append(" BEGIN  																					");
    	query.append("  UPDATE TACRITYVALUE SET   																");
    	query.append(" 		col_name = b.crityColDesc, 															");
    	query.append(" 		col_ord_no = b.ordNo   																");
    	query.append("  FROM TACRITYVALUE A JOIN @t1 b 															");
    	query.append("  ON 	 A.comp_no = b.compNo 																");
    	query.append("  AND  A.critylist_id = b.crityListId 													");
    	query.append(" END 																						");
    	query.append("ELSE 																						");
    	query.append(" BEGIN 																					");
    	query.append("  INSERT INTO TACRITYVALUE 																");
    	query.append("  	(comp_no,  crityvalue_id,  critylist_id,  col_name, critycol_id,  					");
    	query.append("   	row_name,crityrow_id,col_ord_no) 													");
    	query.append("SELECT 																					");
    	query.append("		b.compNo, NEXT VALUE FOR SQACRITYVALUE_ID, b.crityListId, b.crityColDesc, b.crityColId,   	");
    	query.append("  	b.crityRowDesc,b.crityRowId, b.ordNo 												");
    	query.append("FROM 	@t1 b 																				");
    	query.append("	END 																					");
    	
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
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
    public String[] findRowList(RcmCrityCommonDTO rcmCrityCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT x.crityrow_id crityRowId	");
    	query.append("FROM TACRITYROW x					");
    	query.append("WHERE x.comp_no = ?				");
    	query.append("AND	x.critylist_id = ?			");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,rcmCrityCommonDTO.getCrityListId()
    	};
    	
    	return QuerySqlBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString(), objects));
    }
}