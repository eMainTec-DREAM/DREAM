package dream.note.daily.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.note.daily.dao.MaWoDailyActDetailDAO;
import dream.note.daily.dto.MaWoDailyActDetailDTO;
import dream.note.daily.dto.MaWoDailyCommonDTO;

/**
 * 일일작업 - Main Activities 상세 dao
 * @author  kim21017
 * @version $Id: MaWoDailyActDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoDailyActDetailDAOTarget"
 * @spring.txbn id="maWoDailyActDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoDailyActDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoDailyActDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoDailyActDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param woDayListId
     * @param woDayActId
     * @param compNo
     * @return
     */
    public MaWoDailyActDetailDTO findDetail(String woDayListId, String woDayActId, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT												");
        query.append("        ''						seqNo,				");
        query.append("        ''						isDelCheck,			");
        query.append("        x.wodayact_id				woDayActId,			");
        query.append("        x.eq_name					equipDesc,			");
        query.append("        x.act_contents			actContents,		");
        query.append("        x.action					action,				");
        query.append("        x.emp_id					empId,				");
        query.append("        x.ord_no					ordNo,				");
        query.append("        (SELECT emp_no								");
        query.append("         FROM TAEMP									");
        query.append("        WHERE 1=1										");
        query.append("          AND comp_no = x.comp_no						");
        query.append("          AND emp_id = x.emp_id) empNo,				");
        query.append("        (SELECT emp_name								");
        query.append("         FROM TAEMP									");
        query.append("        WHERE 1=1										");
        query.append("          AND comp_no = x.comp_no						");
        query.append("          AND emp_id = x.emp_id) empDesc				");
        query.append("FROM TAWODAYACT x 									");
        query.append("WHERE 1=1												");
        query.append("  AND	 x.wodaylist_id 	= '"+woDayListId+"'			");
        query.append("  AND	 x.wodayact_id 		= '"+woDayActId+"'			");
        query.append("  AND  x.comp_no			= '"+compNo+"'				");
    
        MaWoDailyActDetailDTO maWoDailyActDetailDTO = 
        		(MaWoDailyActDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaWoDailyActDetailDTO()));
        
        return maWoDailyActDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoDailyActDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoDailyActDetailDTO
     * @param maWoDailyCommonDTO
     * @return
     */
    public int updateDetail(MaWoDailyActDetailDTO maWoDailyActDetailDTO, MaWoDailyCommonDTO maWoDailyCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAWODAYACT SET				");
    	query.append("	emp_id					= ?,	");
    	query.append("	eq_name					= ?,	");
    	query.append("	act_contents			= ?,	");
    	query.append("	action					= ?,	");
    	query.append("	ord_no					= ?		");
    	query.append("WHERE wodayact_id			= ?		");
    	query.append("  AND wodaylist_id		= ?		");
    	query.append("  AND comp_no				= ?		");
    	
    	Object[] objects = new Object[] {
    			maWoDailyActDetailDTO.getEmpId(),
    			maWoDailyActDetailDTO.getEquipDesc(),
    			maWoDailyActDetailDTO.getActContents(),
    			maWoDailyActDetailDTO.getAction(),
    			maWoDailyActDetailDTO.getOrdNo(),
    			maWoDailyActDetailDTO.getWoDayActId(),
    			maWoDailyCommonDTO.getWoDayListId(),
    			maWoDailyCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoDailyActDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoDailyActDetailDTO
     * @param maWoDailyCommonDTO
     * @return
     */
    public int insertDetail(MaWoDailyActDetailDTO maWoDailyActDetailDTO, MaWoDailyCommonDTO maWoDailyCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAWODAYACT				");
    	query.append("	(comp_no,		wodaylist_id,		");
    	query.append("	 wodayact_id,	emp_id,				");
    	query.append("	 eq_name,		act_contents,		");
    	query.append("	 action,		ord_no				");
    	query.append("	)	VALUES							");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?,				?					");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    			maWoDailyCommonDTO.getCompNo(),
    			maWoDailyCommonDTO.getWoDayListId(),
    			maWoDailyActDetailDTO.getWoDayActId(),
    			maWoDailyActDetailDTO.getEmpId(),
    			maWoDailyActDetailDTO.getEquipDesc(),
    			maWoDailyActDetailDTO.getActContents(),
    			maWoDailyActDetailDTO.getAction(),
    			maWoDailyActDetailDTO.getOrdNo()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}