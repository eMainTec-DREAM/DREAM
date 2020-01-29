package dream.mgr.mail.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.mail.dao.MaMailUsrListDAO;
import dream.mgr.mail.dto.MaMailCommonDTO;
import dream.mgr.mail.dto.MaMailUsrListDTO;

/**
 * 메일수신자설정 - 수신자 목록 dao
 * @author  kim21017
 * @version $Id: MaMailUsrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maMailUsrListDAOTarget"
 * @spring.txbn id="maMailUsrListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaMailUsrListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaMailUsrListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaMailUsrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maMailCommonDTO
     * @param maMailUsrListDTO
     * @return List
     */
    public List findUsrList(MaMailCommonDTO maMailCommonDTO, MaMailUsrListDTO maMailUsrListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT											");
        query.append("       '' seqNo,									");
        query.append("       '' isDelCheck,								");
        query.append("       x.mail_list_id			AS mailListId,		");
        query.append("       x.mail_usr_id			AS mailUsrId,		");
        query.append("       y.emp_no				AS empNo,			");
        query.append("       y.emp_name				AS empName,			");
        query.append("       x.e_mail				AS email,			");
        query.append("       x.phone				AS phone			");
        query.append("FROM TAMAILUSR x, TAEMP y							");
        query.append("WHERE x.comp_no = y.comp_no						");
        query.append("  AND x.emp_id = y.emp_id							");
        query.append(this.getWhere(maMailCommonDTO,maMailUsrListDTO, user));
        query.getOrderByQuery("y.emp_no", maMailUsrListDTO.getOrderBy(), maMailUsrListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maMailUsrListDTO.getIsLoadMaxCount(), maMailUsrListDTO.getFirstRow()));

    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaMailUsrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteUsrList(String deleteRow, String deleteRowsExt)
    {
    	QueryBuffer query = new QueryBuffer();

    	String mailListId=deleteRow;
    	String mailUsrId=deleteRowsExt;
    	
    	query.append("DELETE FROM TAMAILUSR						");
    	query.append("WHERE mail_list_id 	= '"+mailListId+"'	");
    	query.append("  AND mail_usr_id 	= '"+mailUsrId+"'	");
    	
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaMailCommonDTO maMailCommonDTO, MaMailUsrListDTO maMailUsrListDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.comp_no", user.getCompNo());
    	query.getAndQuery("x.mail_list_id", maMailCommonDTO.getMailListId());
    	if (!"".equals(maMailUsrListDTO.getMailUsrId()))
        {
            query.getAndQuery("x.mail_usr_id", maMailUsrListDTO.getMailUsrId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(MaMailCommonDTO maMailCommonDTO, MaMailUsrListDTO maMailUsrListDTO, User user)
			throws Exception {

		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT						");
        query.append("       COUNT(1)               ");
        query.append("FROM TAMAILUSR x, TAEMP y		");
        query.append("WHERE x.comp_no = y.comp_no	");
        query.append("  AND x.emp_id = y.emp_id		");
        query.append(this.getWhere(maMailCommonDTO,maMailUsrListDTO, user));
	
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);

	}
}