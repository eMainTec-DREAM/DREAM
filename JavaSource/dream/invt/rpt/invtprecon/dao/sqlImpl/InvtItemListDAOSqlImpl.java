package dream.invt.rpt.invtprecon.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.invt.rpt.invtprecon.dao.InvtItemListDAO;
import dream.invt.rpt.invtprecon.dto.InvtItemListDTO;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConCommonDTO;

/**
 * InvtItem Page - List DAO implements
 * @author youngjoo38
 * @version $Id: InvtItemListDAOSqlImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="invtItemListDAOTarget"
 * @spring.txbn id="invtItemListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtItemListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements InvtItemListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: invtItemListDAO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param invtItemListDTO
     * @return List
     */
    public List findList(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO, InvtItemListDTO invtItemListDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("  ''                              seqNo                   ");
        query.append("  ,''                             isDelCheck              ");
        query.append("  , a.invtlist_id                 invtListId              ");
        query.append("  , a.description                 invtItem                ");
        query.append("  , ISNULL(a.plan_amt,0)          planAmt              	");
        query.append("  , ISNULL((SELECT SUM(actual_amt) FROM TAINVTPHASE WHERE invtlist_id=a.invtlist_id AND comp_no=a.comp_no AND invtphase_status='C'),0)    curResult              ");
        query.append("  , (ISNULL(a.plan_amt,0) - ISNULL((SELECT SUM(actual_amt) FROM TAINVTPHASE WHERE invtlist_id=a.invtlist_id AND comp_no=a.comp_no AND invtphase_status='C'),0))   balance               ");
        query.append("  , plan_sdate             		planSdate       		");
        query.append("  , plan_edate             		planEdate       		");
        query.append("  , end_date               		endDate  				");
        query.append("FROM TAINVTLIST a                     					");
        query.append("WHERE 1=1                 								");

        query.append(this.getWhere(invtRptInvtPreConCommonDTO, user));
        
        query.getOrderByQuery("a.invtlist_id", "a.invtlist_id DESC", invtItemListDTO.getOrderBy(), invtItemListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(invtRptInvtPreConCommonDTO.getIsLoadMaxCount(), invtRptInvtPreConCommonDTO.getFirstRow()));

    }

    private String getWhere(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.getAndQuery("a.comp_no", user.getCompNo());
        
        query.getAndQuery("SUBSTRING(a.plan_sdate,1,4)", invtRptInvtPreConCommonDTO.getYear());
        
        query.getAndQuery("a.dept_id", invtRptInvtPreConCommonDTO.getDeptId());

        return query.toString();
    }
    
    public String findTotalCount(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO, 
            InvtItemListDTO invtItemListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                            ");
        query.append("       COUNT(1)                                   ");
        query.append("FROM TAINVTLIST a                  ");
        query.append("WHERE 1=1                 ");

        query.append(this.getWhere(invtRptInvtPreConCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
