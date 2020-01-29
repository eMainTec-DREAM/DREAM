package dream.invt.rpt.invtprecon.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.invt.rpt.invtprecon.dao.InvtRptInvtPreConDetailDAO;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConCommonDTO;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConDetailDTO;

/**
 * InvtRptInvtPreCon Page - Detail DAO implements
 * @author youngjoo38
 * @version $Id: InvtRptInvtPreConDetailDAOOraImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="invtRptInvtPreConDetailDAOTarget"
 * @spring.txbn id="invtRptInvtPreConDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtRptInvtPreConDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements InvtRptInvtPreConDetailDAO
{

    public InvtRptInvtPreConDetailDTO findDetail(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT        ");
        query.append("    deptId                                       deptId       ");
        query.append("    ,(SELECT aa.description FROM TADEPT aa WHERE aa.dept_id=deptId AND aa.comp_no=compNo)   deptDesc      ");
        query.append("    ,YEAR                                        YEAR     ");
        query.append("    ,NVL(SUM(planAmt),0)                           planAmt       ");
        query.append("    ,NVL(SUM(curResult),0)                          curResult        ");
        query.append("    ,NVL(SUM(planAmt),0)-NVL(SUM(curResult),0)    balance       ");
        query.append("FROM (        ");
        query.append("    SELECT        ");
        query.append("        a.comp_no                              compNo     ");
        query.append("        ,a.dept_id                               deptId       ");
        query.append("        ,SUBSTR(a.plan_sdate,0,4)       YEAR      ");
        query.append("        ,a.plan_amt                             planAmt       ");
        query.append("        ,(SELECT SUM(actual_amt) FROM TAINVTPHASE WHERE invtlist_id=a.invtlist_id AND comp_no=a.comp_no AND invtphase_status='C')       curResult     ");
        query.append("    FROM TAINVTLIST a     ");
        query.append("    ) a    ");
        query.append("WHERE compNo = ?   ");
        query.append("AND deptId       = ?   ");
        query.append("AND year          = ?   ");
        query.append("GROUP BY deptId,compNo,YEAR       ");
        
        Object[] objects = new Object[] {
                user.getCompNo()
               ,invtRptInvtPreConCommonDTO.getDeptId()
               ,invtRptInvtPreConCommonDTO.getYear()
        };
    
        InvtRptInvtPreConDetailDTO invtRptInvtPreConDetailDTO = 
                (InvtRptInvtPreConDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new InvtRptInvtPreConDetailDTO()));
        
        return invtRptInvtPreConDetailDTO;
        
    }
}
