package dream.invt.rpt.invtprecon.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.invt.rpt.invtprecon.dao.InvtItemListDAO;
import dream.invt.rpt.invtprecon.dto.InvtItemListDTO;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConCommonDTO;

/**
 * InvtItem Page - List DAO implements
 * @author youngjoo38
 * @version $Id: InvtItemListDAOOraImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="invtItemListDAOTarget"
 * @spring.txbn id="invtItemListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtItemListDAOOraImpl  extends BaseJdbcDaoSupportOra implements InvtItemListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: invtItemListDAO.java,v 1.0 2017/08/22 15:20:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param invtItemListDTO
     * @return List
     */
    public List findList(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO, InvtItemListDTO invtItemListDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("  ''                              seqNo                   ");
        query.append("  ,''                             isDelCheck              ");
        query.append("  , a.invtlist_id                 invtListId              ");
        query.append("  , a.description                 invtItem                ");
        query.append("  , NVL(a.plan_amt,0)             planAmt              	");
        query.append("  , NVL((SELECT SUM(actual_amt) FROM TAINVTPHASE WHERE invtlist_id=a.invtlist_id AND comp_no=a.comp_no AND invtphase_status='C'),0)    curResult              ");
        query.append("  , (NVL(a.plan_amt,0) - NVL((SELECT SUM(actual_amt) FROM TAINVTPHASE WHERE invtlist_id=a.invtlist_id AND comp_no=a.comp_no AND invtphase_status='C'),0))   balance               ");
        query.append("  , plan_sdate             		planSdate       		");
        query.append("  , plan_edate             		planEdate       		");
        query.append("  , end_date               		endDate  				");
        query.append("FROM TAINVTLIST a                     					");
        query.append("WHERE 1=1                 								");

        query.append(this.getWhere(invtRptInvtPreConCommonDTO, user));
        
        query.getOrderByQuery("a.invtlist_id DESC", invtItemListDTO.getOrderBy(), invtItemListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(invtItemListDTO.getIsLoadMaxCount(), invtItemListDTO.getFirstRow()));

    }

    /**
     * Filter Á¶°Ç
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param invtItemListDTO
     * @return
     * @throws Exception
     */
    private String getWhere(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());
        
        query.getAndQuery("SUBSTR(a.plan_sdate,0,4)", invtRptInvtPreConCommonDTO.getYear());
        
        query.getAndQuery("a.dept_id", invtRptInvtPreConCommonDTO.getDeptId());

        return query.toString();
    }

    public String findTotalCount(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO,
            InvtItemListDTO invtItemListDTO, User user) throws Exception 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                            ");
        query.append("       COUNT(1)                                   ");
        query.append("FROM TAINVTLIST a                  ");
        query.append("WHERE 1=1                 ");

        query.append(this.getWhere(invtRptInvtPreConCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
