package dream.invt.rpt.moninvtamt.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.invt.rpt.moninvtamt.dao.InvtRptMonInvtAmtDetailDAO;
import dream.invt.rpt.moninvtamt.dto.InvtRptMonInvtAmtCommonDTO;
import dream.invt.rpt.moninvtamt.dto.InvtRptMonInvtAmtDetailDTO;
import dream.invt.rpt.moninvtamt.form.InvtRptMonInvtAmtDetailForm;

/**
 * InvtRptMonInvtAmt Page - Detail DAO implements
 * @author cjscjs9
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="invtRptMonInvtAmtDetailDAOTarget"
 * @spring.txbn id="invtRptMonInvtAmtDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtRptMonInvtAmtDetailDAOSqlImpl  extends BaseJdbcDaoSupportSql implements InvtRptMonInvtAmtDetailDAO
{

    /**
     * grid find
     * @author cjscjs9 
     * @version $Id:$
     * @since   1.0
     * 
     * @param invtRptMonInvtAmtDetailDTO
     * @return List
     */
	public List findDetailList(InvtRptMonInvtAmtDetailForm invtRptMonInvtAmtDetailForm, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT         												");
        query.append("        x.tmonth     RECDATE       							");
        query.append("       , ISNULL(SUM(y.totamt),0)    TOTPRICE    				");
        query.append("FROM TADAY x LEFT OUTER JOIN (  SELECT         				");
        query.append("                                y.rec_date  rec_date        	");
        query.append("                                ,y.tot_price totamt         	");
        query.append("                                FROM TAPTPRRECLIST y        	");
        query.append("                                WHERE EXISTS        			");
        query.append("                                (SELECT 1        				");
        query.append("                                FROM tainvtlist a        		");
        query.append("                                WHERE y.comp_no=a.comp_no     ");
        query.append(this.getDeptWhere(invtRptMonInvtAmtDetailForm,loginUser));
        query.append("                              ) y        						");
        query.append("ON x.tday = y.rec_date        								");
        query.append("WHERE 1=1        												");
        query.append(this.getWhere(invtRptMonInvtAmtDetailForm,loginUser));
        query.append("GROUP BY x.tmonth        										");
        query.append("ORDER BY x.tmonth        										");
        
        

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public String getWhere(InvtRptMonInvtAmtDetailForm invtRptMonInvtAmtDetailForm,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        InvtRptMonInvtAmtCommonDTO invtRptMonInvtAmtCommonDTO = invtRptMonInvtAmtDetailForm.getInvtRptMonInvtAmtCommonDTO();
        InvtRptMonInvtAmtDetailDTO invtRptMonInvtAmtDetailDTO = invtRptMonInvtAmtDetailForm.getInvtRptMonInvtAmtDetailDTO();
        
        query.getAndQuery("x.tyyyy", invtRptMonInvtAmtCommonDTO.getFilterYyyy());
        


        return query.toString();
    }
    
    public String getDeptWhere(InvtRptMonInvtAmtDetailForm invtRptMonInvtAmtDetailForm,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        InvtRptMonInvtAmtCommonDTO invtRptMonInvtAmtCommonDTO = invtRptMonInvtAmtDetailForm.getInvtRptMonInvtAmtCommonDTO();
        InvtRptMonInvtAmtDetailDTO invtRptMonInvtAmtDetailDTO = invtRptMonInvtAmtDetailForm.getInvtRptMonInvtAmtDetailDTO();
        
        query.append(" AND a.invtlist_Id = '"+invtRptMonInvtAmtDetailDTO.getInvtListId()+"'			");
        query.append("                                AND y.invtlist_id=a.invtlist_id)");
        query.append("                                AND y.prreclist_status = 'C'  ");
        
        return query.toString();
    }
    
}
