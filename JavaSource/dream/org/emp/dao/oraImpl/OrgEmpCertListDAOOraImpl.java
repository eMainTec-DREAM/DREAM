package dream.org.emp.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.org.emp.dao.OrgEmpCertListDAO;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.OrgEmpCertListDTO;

/**
 * 구매신청 item 목록 dao
 * @author  kim21017
 * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="orgEmpCertListDAOTarget"
 * @spring.txbn id="orgEmpCertListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class OrgEmpCertListDAOOraImpl extends BaseJdbcDaoSupportOra implements OrgEmpCertListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEmpCommonDTO
     * @param orgEmpCertListDTO
     * @return List
     */
    public List findItemList(MaEmpCommonDTO maEmpCommonDTO,OrgEmpCertListDTO orgEmpCertListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maEmpCommonDTO.getCompNo();
 
        query.append("SELECT 		");
        query.append("       '' seqNo,                                        		");
        query.append("       '' isDelCheck,            		");
        query.append("       x.comp_no compNo,		");
        query.append("       x.empcertlist_id empCertListId,		");
        query.append("       x.emp_id empId,		");
        query.append("       x.certlist_id certListId,		");
        query.append("       y.cert_name certName,		");
        query.append("		 SFACODE_TO_DESC(y.cert_type,'CERT_TYPE','USR','"+user.getCompNo()+"','"+user.getLangId()+"') 			certType,	");
        query.append("       DECODE(x.acq_date,'','',SUBSTR(x.acq_date, 0, 4)||'-'||SUBSTR(x.acq_date, 5, 2)||'-'||SUBSTR(x.acq_date, 7, 2)) as acqDate,		");
        query.append("       DECODE(x.exp_date,'','',SUBSTR(x.exp_date, 0, 4)||'-'||SUBSTR(x.exp_date, 5, 2)||'-'||SUBSTR(x.exp_date, 7, 2)) as expDate,		");
        query.append("		 SFACODE_TO_DESC(x.empcert_status,'EMPCERT_STATUS','SYS','','"+user.getLangId()+"') 			empCertStatus,	");
        query.append("       x.remark remark                               		");
        query.append("FROM TAEMPCERTLIST x, TACERTLIST y		");
        query.append("WHERE x.certlist_id = y.certlist_id		");
        query.append(this.getWhere(maEmpCommonDTO,orgEmpCertListDTO,user));
        
        query.getOrderByQuery("x.certlist_id", maEmpCommonDTO.getOrderBy(), maEmpCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maEmpCommonDTO.getIsLoadMaxCount(), maEmpCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: PartAdjStkTakeListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteItemList(String deleteRow, String deleteRowsExt, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	String empCertListId =deleteRow;
    	
    	query.append("DELETE FROM TAEMPCERTLIST						");
    	query.append("WHERE empcertlist_id 	= '"+empCertListId+"'	");
    	query.append(" AND comp_no 	= '"+user.getCompNo()+"'		");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaEmpCommonDTO maEmpCommonDTO, OrgEmpCertListDTO orgEmpCertListDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.comp_no", user.getCompNo());
    	query.getAndQuery("x.emp_id", maEmpCommonDTO.getEmpId());
    	if (!"".equals(orgEmpCertListDTO.getEmpCertListId()))
        {
            query.getAndQuery("x.empcertlist_id", orgEmpCertListDTO.getEmpCertListId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(MaEmpCommonDTO maEmpCommonDTO, OrgEmpCertListDTO orgEmpCertListDTO, User user)
	{
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                    ");
        query.append("      count(1)            ");
        query.append("FROM TAEMPCERTLIST x, TACERTLIST y		");
        query.append("WHERE x.certlist_id = y.certlist_id		");
        query.append(this.getWhere(maEmpCommonDTO,orgEmpCertListDTO,user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
	}
}
