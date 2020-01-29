package dream.mgr.mail.dao.oraImpl;

import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.mgr.mail.dao.MaMailUsrDetailDAO;
import dream.mgr.mail.dto.MaMailCommonDTO;
import dream.mgr.mail.dto.MaMailUsrDetailDTO;
import dream.mgr.mail.dto.MaMailUsrListDTO;

/**
 * 메일수신자설정 - 수신자 상세 dao
 * @author  kim21017
 * @version $Id: MaMailUsrDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maMailUsrDetailDAOTarget"
 * @spring.txbn id="maMailUsrDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaMailUsrDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaMailUsrDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaMailUsrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailUsrListDTO
     * @param maMailCommonDTO
     * @return
     */
    public MaMailUsrDetailDTO findDetail(MaMailUsrListDTO maMailUsrListDTO, MaMailCommonDTO maMailCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT											");
        query.append("       x.mail_usr_id			AS mailUsrId,		");
        query.append("       y.emp_id				AS empId,			");
        query.append("       y.emp_no				AS empNo,			");
        query.append("       y.emp_name				AS empDesc,			");
        query.append("       x.e_mail				AS email,			");
        query.append("       x.phone				AS phone			");
        query.append("FROM TAMAILUSR x, TAEMP y							");
        query.append("WHERE x.comp_no = y.comp_no						");
        query.append("  AND x.emp_id = y.emp_id							");
        query.getAndQuery("x.mail_usr_id",maMailUsrListDTO.getMailUsrId());
        query.getAndQuery("x.mail_list_id",maMailCommonDTO.getMailListId());
        query.getAndQuery("x.comp_no",maMailCommonDTO.getCompNo());
    
        MaMailUsrDetailDTO maMailUsrDetailDTO1 = 
        		(MaMailUsrDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaMailUsrDetailDTO()));
        
        return maMailUsrDetailDTO1;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaMailUsrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailUsrDetailDTO
     * @param maMailCommonDTO
     * @return
     */
    public int updateDetail(MaMailUsrDetailDTO maMailUsrDetailDTO, MaMailCommonDTO maMailCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAMAILUSR SET			");
    	query.append("	emp_id				= ?,	");
    	query.append("	e_mail				= ?,	");
    	query.append("	phone				= ?		");
    	query.append("WHERE mail_usr_id 	= ?		");
    	query.append("  AND mail_list_id	= ? 	");
    	query.append("  AND comp_no			= ? 	");
    	
    	Object[] objects = new Object[] {
    			maMailUsrDetailDTO.getEmpId(),
    			maMailUsrDetailDTO.getEmail(),
    			maMailUsrDetailDTO.getPhone(),
    			maMailUsrDetailDTO.getMailUsrId(),
    			maMailCommonDTO.getMailListId(),
    			maMailCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaMailUsrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailUsrDetailDTO
     * @param maMailCommonDTO
     * @return
     */
    public int insertDetail(MaMailUsrDetailDTO maMailUsrDetailDTO, MaMailCommonDTO maMailCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAMAILUSR (								");
    	query.append("	comp_no,		mail_list_id,		mail_usr_id,	");
    	query.append("	emp_id,			e_mail,				phone			");
    	query.append("	)	VALUES				(							");
    	query.append("	?,				?,					?,				");
    	query.append("	?,				?,					?				");
    	query.append("	)													");
    	
    	Object[] objects = new Object[] {
    			maMailCommonDTO.getCompNo(),
    			maMailCommonDTO.getMailListId(),
    			maMailUsrDetailDTO.getMailUsrId(),
    			maMailUsrDetailDTO.getEmpId(),
    			maMailUsrDetailDTO.getEmail(),
    			maMailUsrDetailDTO.getPhone()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}