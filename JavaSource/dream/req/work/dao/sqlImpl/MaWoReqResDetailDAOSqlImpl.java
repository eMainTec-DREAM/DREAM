package dream.req.work.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.req.work.dao.MaWoReqResDetailDAO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqResDetailDTO;

/**
 * 작업요청-처리사항 상세 dao
 * @author  kim21017
 * @version $Id: MaWoReqResDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoReqResDetailDAOTarget"
 * @spring.txbn id="maWoReqResDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoReqResDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoReqResDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoReqResDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param woDayListId
     * @param woDayActId
     * @param compNo
     * @return
     */
    public MaWoReqResDetailDTO findDetail(String woReqId, String woReqResId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT												");
        query.append("        x.woreqres_id				woReqResId,			");
        query.append("        x.res_date				resDate,			");
        query.append("        x.wores_status			resStatusId,		");
        query.append("		  dbo.SFACODE_TO_DESC(x.wores_status,'WORES_STATUS','SYS','','"+user.getLangId()+"')			resStatusDesc,	");
        query.append("        x.emp_id					empId,				");
        query.append("        (SELECT emp_name								");
        query.append("         FROM TAEMP									");
        query.append("        WHERE 1=1										");
        query.append("          AND comp_no = x.comp_no						");
        query.append("          AND emp_id = x.emp_id) empDesc,				");
        query.append("        x.dept_id					deptId,				");
        query.append("        (SELECT description							");
        query.append("         FROM TADEPT									");
        query.append("        WHERE 1=1										");
        query.append("          AND comp_no = x.comp_no						");
        query.append("          AND dept_id = x.dept_id) deptDesc,			");
        query.append("        x.response				response			");
        query.append("FROM TAWOREQRES x 									");
        query.append("WHERE 1=1												");
        query.append("  AND	 x.woreq_id 		= '"+woReqId+"'				");
        query.append("  AND	 x.woreqres_id 		= '"+woReqResId+"'			");
        query.append("  AND  x.comp_no			= '"+user.getCompNo()+"'	");
    
        MaWoReqResDetailDTO maWoReqResDetailDTO = 
        		(MaWoReqResDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaWoReqResDetailDTO()));
        
        return maWoReqResDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoReqResDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoReqResDetailDTO
     * @param maWoReqCommonDTO
     * @return
     */
    public int updateDetail(MaWoReqResDetailDTO maWoReqResDetailDTO, MaWoReqCommonDTO maWoReqCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAWOREQRES SET				");
    	query.append("	res_date				= ?,	");
    	query.append("	wores_status			= ?,	");
    	query.append("	emp_id					= ?,	");
    	query.append("	dept_id					= ?,	");
    	query.append("	response				= ?		");
    	query.append("WHERE woreq_id			= ?		");
    	query.append("  AND woreqres_id			= ?		");
    	query.append("  AND comp_no				= ?		");
    	
    	Object[] objects = new Object[] {
    			maWoReqResDetailDTO.getResDate()
    			,maWoReqResDetailDTO.getResStatusId()
    			,maWoReqResDetailDTO.getEmpId()
    			,maWoReqResDetailDTO.getDeptId()
    			,maWoReqResDetailDTO.getResponse()
    			,maWoReqCommonDTO.getWoReqId()
    			,maWoReqResDetailDTO.getWoReqResId()
    			,maWoReqCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoReqResDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoReqResDetailDTO
     * @param maWoReqCommonDTO
     * @return
     */
    public int insertDetail(MaWoReqResDetailDTO maWoReqResDetailDTO, MaWoReqCommonDTO maWoReqCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAWOREQRES(												");
    	query.append("		comp_no				, woreq_id				, woreqres_id		");
    	query.append("	  , res_date			, wores_status			, dept_id			");
    	query.append("	  , emp_id				, response 				, wkor_id			");
    	query.append("	  , woreqres_method		, invtlist_id			, woreq_gen_type	");
    	query.append("	  , res_time       													");
    	query.append(")	VALUES (															");
    	query.append("	 	?					, ?						, ?					");
    	query.append("	  , ?					, ?						, ?					");
    	query.append("	  , ?					, ?						, ?					");
    	query.append("	  , ?             		, ?						, ?    				");
    	query.append("	  , ?                 												");
    	query.append(")																		");
    	
    	Object[] objects = new Object[] {
    			maWoReqCommonDTO.getCompNo(),
    			maWoReqCommonDTO.getWoReqId(),
    			maWoReqResDetailDTO.getWoReqResId(),
    			maWoReqResDetailDTO.getResDate(),
    			maWoReqResDetailDTO.getResStatusId(),
    			maWoReqResDetailDTO.getDeptId(),
    			maWoReqResDetailDTO.getEmpId(),
    			maWoReqResDetailDTO.getResponse(),
    			maWoReqResDetailDTO.getWkorId(),
    			maWoReqResDetailDTO.getWoreqresMethod(),
    			maWoReqResDetailDTO.getInvtlistId(),
    			maWoReqResDetailDTO.getWoReqGenType(),
    			DateUtil.getDateTime()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public int changeHdrStatus(MaWoReqResDetailDTO maWoReqResDetailDTO, MaWoReqCommonDTO maWoReqCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAWOREQ SET				");
    	query.append("	woreq_status			= ?		");
    	query.append("WHERE woreq_id			= ?		");
    	query.append("  AND comp_no				= ?		");
    	
    	Object[] objects = new Object[] {
    			maWoReqResDetailDTO.getResStatusId()
    			,maWoReqCommonDTO.getWoReqId()
    			,maWoReqCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
	@Override
	public int setResStatus(MaWoReqResDetailDTO maWoReqResDetailDTO, User user) {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAWOREQRES SET                								");
    	query.append("    WORES_STATUS =   (SELECT CASE WHEN (							");
    	query.append("                                  	SELECT invtlist_status		");
    	query.append("                                  	FROM TAINVTLIST				");
    	query.append("                                  	WHERE invtlist_id = ?		");
    	query.append("                                  	  AND comp_no = ?) = 'W'	");
    	query.append("                                  THEN 'REV'						");
    	query.append("                                  WHEN (							");
    	query.append("                                  	SELECT invtlist_status		");
    	query.append("                                  	FROM TAINVTLIST				");
    	query.append("                                  	WHERE invtlist_id = ?		");
    	query.append("                                  	  AND comp_no = ?) = 'C'	");
    	query.append("                                  THEN 'COM'						");
    	query.append("                                  ELSE 'WRK'						");
    	query.append("                                  END)								");
    	query.append("WHERE invtlist_id            = ?        							");
    	query.append("  AND comp_no                = ? 									");

    	
    	Object[] objects = new Object[] {
    			maWoReqResDetailDTO.getInvtlistId()
    			,user.getCompNo()
    			,maWoReqResDetailDTO.getInvtlistId()
    			,user.getCompNo()
    			,maWoReqResDetailDTO.getInvtlistId()
    			,user.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    @Override
    public String chkWoStCnt(MaWoReqCommonDTO maWoReqCommonDTO, User user, String method)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append(" SELECT                                    ");
        query.append("        COUNT(1)             AS count      ");
        query.append("   FROM TAWOREQRES x                       ");
        query.append("  INNER JOIN TAWORKORDER y                 ");
        query.append("          ON x.comp_no     = y.comp_no     ");
        query.append("         AND x.wkor_id     = y.wkor_id     ");
        query.append("  WHERE  x.comp_no         = ?             ");
        query.append("    AND  x.woreq_id        = ?             ");
        query.append("    AND  x.woreqres_method = ?             ");
        query.append("    AND  y.wo_status      != 'C'           ");
        
        Object[] objects = new Object[] {
                 user.getCompNo()
               , maWoReqCommonDTO.getWoReqId()
               , method
        };
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), getObject(objects)));
    }
    
    @Override
    public String chkInvtStCnt(MaWoReqCommonDTO maWoReqCommonDTO, User user, String method)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append(" SELECT                                    ");
        query.append("        COUNT(1)             AS count      ");
        query.append("   FROM TAWOREQRES x                       ");
        query.append("  INNER JOIN TAINVTLIST y                  ");
        query.append("          ON x.comp_no     = y.comp_no     ");
        query.append("         AND x.invtlist_id = y.invtlist_id ");
        query.append("  WHERE  x.comp_no         = ?             ");
        query.append("    AND  x.woreq_id        = ?             ");
        query.append("    AND  x.woreqres_method = ?             ");
        query.append("    AND  y.invtlist_status != 'C'          ");
        
        Object[] objects = new Object[] {
                 user.getCompNo()
               , maWoReqCommonDTO.getWoReqId()
               , method
        };
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), getObject(objects)));
    }
    
    @Override
    public String chkWoExistCnt(MaWoReqCommonDTO maWoReqCommonDTO, User user, String method)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append(" SELECT                                    ");
        query.append("        COUNT(1)             AS count      ");
        query.append("   FROM TAWOREQRES x                       ");
        query.append("   INNER JOIN TAWORKORDER y                ");
        query.append("          ON x.comp_no     = y.comp_no     ");
        query.append("         AND x.wkor_id     = y.wkor_id     ");
        query.append("  WHERE  x.comp_no         = ?             ");
        query.append("    AND  x.woreq_id        = ?             ");
        query.append("    AND  x.woreqres_method = ?             ");
        
        Object[] objects = new Object[] {
                 user.getCompNo()
               , maWoReqCommonDTO.getWoReqId()
               , method
        };
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), getObject(objects)));
    }
    
    @Override
    public String chkInvtExistCnt(MaWoReqCommonDTO maWoReqCommonDTO, User user, String method)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append(" SELECT                                    ");
        query.append("        COUNT(1)             AS count      ");
        query.append("   FROM TAWOREQRES x                       ");
        query.append("   INNER JOIN TAINVTLIST y                 ");
        query.append("          ON x.comp_no     = y.comp_no     ");
        query.append("         AND x.invtlist_id = y.invtlist_id ");
        query.append("  WHERE  x.comp_no         = ?             ");
        query.append("    AND  x.woreq_id        = ?             ");
        query.append("    AND  x.woreqres_method = ?             ");
        
        Object[] objects = new Object[] {
                 user.getCompNo()
               , maWoReqCommonDTO.getWoReqId()
               , method
        };
        
        return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), getObject(objects)));
    }
    
    @Override
    public int setWoResStatus(MaWoReqCommonDTO maWoReqCommonDTO, String status, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAWOREQRES         ");
        query.append("   SET wores_status = ?   ");
        query.append(" WHERE comp_no = ?        ");
        query.append("   AND woreq_id = ?       ");
        query.append("   AND woreqres_id = ?    ");
        
        Object[] objects = new Object[] {
                 status
               , user.getCompNo()
               , maWoReqCommonDTO.getWoReqId()
               , maWoReqCommonDTO.getWoReqResId()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}