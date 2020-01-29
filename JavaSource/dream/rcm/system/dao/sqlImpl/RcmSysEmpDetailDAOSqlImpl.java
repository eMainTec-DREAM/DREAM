package dream.rcm.system.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.rcm.system.dao.RcmSysEmpDetailDAO;
import dream.rcm.system.dto.RcmSysEmpDetailDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * 분석자 상세 dao
 * @author  kim21017
 * @version $Id: RcmSysEmpDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmSysEmpDetailDAOTarget"
 * @spring.txbn id="rcmSysEmpDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmSysEmpDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmSysEmpDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmSysEmpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmEquipId
     * @param compNo
     * @return
     */
    public RcmSysEmpDetailDTO findDetail(String rcmListId, String rcmEmpId, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("SELECT                                           ");
        query.append("       x.emp_id                       empId       ");
        query.append("       ,x.rcmemp_id                   rcmEmpId    ");
        query.append("       ,y.emp_no                      empNo       ");
        query.append("       ,y.emp_name                    empName     ");
        query.append("       ,y.dept_id                     deptId      ");
        query.append("       ,(SELECT description                       ");
        query.append("           FROM TADEPT                            ");
        query.append("          WHERE comp_no = x.comp_no               ");
        query.append("            AND dept_id = y.dept_id)  deptDesc    ");
        query.append("       ,x.remark                                  ");
        query.append("       ,x.rcmlist_id                  rcmListId   ");
        query.append("FROM   TARCMEMP x INNER JOIN TAEMP y              ");
        query.append("  ON   x.comp_no      = y.comp_no                 ");
        query.append(" AND   x.emp_id       = y.emp_id                  ");
        query.append("WHERE  x.rcmemp_id    = ?                         ");
        query.append("  AND  x.rcmlist_id   = ?                         ");
        query.append("  AND  x.comp_no      = ?                         ");
        
        Object[] objects = new Object[] {
        		rcmEmpId
    			,rcmListId
    			,compNo
    	};
    
        RcmSysEmpDetailDTO rcmSysEmpDetailDTO = 
        		(RcmSysEmpDetailDTO)getJdbcTemplate().query(query.toString(), objects,new MwareExtractor(new RcmSysEmpDetailDTO()));
        
        return rcmSysEmpDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmSysEmpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEmpDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int updateDetail(RcmSysEmpDetailDTO rcmSysEmpDetailDTO, RcmSysCommonDTO rcmSysCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TARCMEMP SET		");
    	query.append("	emp_id				= ?	");
    	query.append("	,emp_no				= ?	");
    	query.append("	,remark				= ?	");
    	query.append("WHERE rcmemp_id		= ?	");
    	query.append("  AND comp_no			= ?	");
    	
    	Object[] objects = new Object[] {
    			rcmSysEmpDetailDTO.getEmpId()
    			,rcmSysEmpDetailDTO.getEmpNo()
    			,rcmSysEmpDetailDTO.getRemark()
    			,rcmSysEmpDetailDTO.getRcmEmpId()
    			,rcmSysCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmSysEmpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmSysEmpDetailDTO
     * @param maPmMstrCommonDTO
     * @return
     */
    public int insertDetail(RcmSysEmpDetailDTO rcmSysEmpDetailDTO, RcmSysCommonDTO rcmSysCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TARCMEMP							");
    	query.append("	(comp_no,		rcmlist_id,		rcmemp_id,	");
    	query.append("	 emp_id,		emp_no,			remark		");
    	query.append("	)VALUES										");
    	query.append("	(?,				?,			?,				");
    	query.append("	 ?,				?,			?				");
    	query.append("	)											");
    	
    	Object[] objects = new Object[] {
    	        rcmSysCommonDTO.getCompNo()
    			,rcmSysCommonDTO.getRcmListId()
    			,rcmSysEmpDetailDTO.getRcmEmpId()
    			,rcmSysEmpDetailDTO.getEmpId()
    			,rcmSysEmpDetailDTO.getEmpNo()
    			,rcmSysEmpDetailDTO.getRemark()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}