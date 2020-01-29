package dream.rcm.crity.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.rcm.crity.dao.RcmCrityRowDetailDAO;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityRowDetailDTO;
import dream.rcm.crity.dto.RcmCrityRowListDTO;

/**
 * Criticality Matrix Row Page - Detail DAO implements
 * @author kim21017
 * @version $Id: RcmCrityRowDetailDAOSqlImpl.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="rcmCrityRowDetailDAOTarget"
 * @spring.txbn id="rcmCrityRowDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmCrityRowDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmCrityRowDetailDAO
{
	
    public RcmCrityRowDetailDTO findDetail(RcmCrityCommonDTO rcmCrityCommonDTO, RcmCrityRowListDTO rcmCrityRowListDTO, User user) 
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

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
    	QuerySqlBuffer query = new QuerySqlBuffer();
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
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
    
    public int updateDetail(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityRowDetailDTO rcmCrityRowDetailDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
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
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
    public int updateValue(RcmCrityCommonDTO rcmCrityCommonDTO,RcmCrityRowDetailDTO rcmCrityRowDetailDTO, User user, String colId)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	int rtnValue  = 0;
    	
    	query.append("DECLARE @t1 TABLE( 																		");
    	query.append("	compNo NVARCHAR(1000),																	");
    	query.append("	crityListId NVARCHAR(1000),																");
    	query.append("	crityRowId NVARCHAR(1000),																");
    	query.append("	crityRowDesc NVARCHAR(1000),															");
    	query.append("	ordNo NVARCHAR(1000),																	");
    	query.append("	crityColId NVARCHAR(1000),																");
    	query.append("	crityColDesc NVARCHAR(1000),															");
    	query.append("	crityLevel NVARCHAR(1000)																");
    	query.append(") 																						");
    	query.append(" 																							");
    	query.append("INSERT @t1 VALUES(?,?,?,?,?,?,(SELECT col_name FROM TACRITYCOL WHERE comp_no=? AND critycol_id=?),?) ");
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
    	query.append(" 		row_name = b.crityRowDesc, 															");
    	query.append(" 		row_ord_no = b.ordNo   																");
    	query.append("  FROM TACRITYVALUE A JOIN @t1 b 															");
    	query.append("  ON 	 A.comp_no = b.compNo 																");
    	query.append("  AND  A.critylist_id = b.crityListId 													");
    	query.append(" END 																						");
    	query.append("ELSE 																						");
    	query.append(" BEGIN 																					");
    	query.append("  INSERT INTO TACRITYVALUE 																");
    	query.append("  	(comp_no,  crityvalue_id,  critylist_id,  col_name, critycol_id,  					");
    	query.append("   	row_name,crityrow_id,row_ord_no) 													");
    	query.append("SELECT 																					");
    	query.append("		b.compNo, NEXT VALUE FOR SQACRITYVALUE_ID, b.crityListId, b.crityColDesc, b.crityColId,   	");
    	query.append("  	b.crityRowDesc,b.crityRowId, b.ordNo 												");
    	query.append("FROM 	@t1 b 																				");
    	query.append("	END 																					");
    	
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
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
    public String[] findColList(RcmCrityCommonDTO rcmCrityCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT x.critycol_id crityColId	");
    	query.append("FROM TACRITYCOL x					");
    	query.append("WHERE x.comp_no = ?				");
    	query.append("AND	x.critylist_id = ?			");
    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,rcmCrityCommonDTO.getCrityListId()
    	};
    	
    	return QuerySqlBuffer.toStringSingleArray(getJdbcTemplate().queryForList(query.toString(), objects));
    }
}