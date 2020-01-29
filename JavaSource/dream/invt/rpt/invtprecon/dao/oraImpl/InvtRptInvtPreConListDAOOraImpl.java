package dream.invt.rpt.invtprecon.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.invt.rpt.invtprecon.dao.InvtRptInvtPreConListDAO;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConCommonDTO;

/**
 * InvtRptInvtPreCon Page - List DAO implements
 * @author youngjoo38
 * @version $Id: InvtRptInvtPreConListDAOOraImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="invtRptInvtPreConListDAOTarget"
 * @spring.txbn id="invtRptInvtPreConListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtRptInvtPreConListDAOOraImpl  extends BaseJdbcDaoSupportOra implements InvtRptInvtPreConListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: invtRptInvtPreConListDAO.java,v 1.0 2017/08/22 15:20:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param invtRptInvtPreConCommonDTO
     * @return List
     */
    public List findList(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT        																						");
        query.append("    ''                                              	seqNo         									");
        query.append("    ,deptId                                       	deptId       									");
        query.append("    ,(SELECT aa.description FROM TADEPT aa WHERE aa.dept_id=deptId AND aa.comp_no=compNo)   dept      ");
        query.append("    ,plant                                       		plant       									");
        query.append("    ,(SELECT aa.description FROM TAPLANT aa WHERE aa.comp_no=a.compNo AND aa.plant=a.plant) PLANTDESC	");
        query.append("    ,YEAR                                        		YEAR     										");
        query.append("    ,NVL(SUM(planAmt),0)                           	planAmt       									");
        query.append("    ,NVL(SUM(curResult),0)                          	curResult        								");
        query.append("    ,NVL(SUM(planAmt),0)-NVL(SUM(curResult),0)    	balance       									");
        query.append("FROM (        																						");
        query.append("    SELECT        																					");
        query.append("        a.comp_no                              		compNo     										");
        query.append("        ,a.dept_id                               		deptId       									");
        query.append("        ,SUBSTR(a.plan_sdate,0,4)       				YEAR      										");
        query.append("        ,a.plan_amt                             		planAmt       									");
        query.append("        ,(SELECT SUM(actual_amt) FROM TAINVTPHASE WHERE invtlist_id=a.invtlist_id AND comp_no=a.comp_no AND invtphase_status='C')       curResult     ");
        query.append("        ,a.plant                                    	plant											");
        query.append("        ,a.invt_categ                             	invtCateg										");
        query.append("    FROM TAINVTLIST a     																			");
        query.append("    ) a    																							");
        query.append("WHERE 1=1 																							");
        query.append(this.getWhere(invtRptInvtPreConCommonDTO, user));
        query.append("GROUP BY plant,deptId,compNo,YEAR       																	");
        query.getOrderByQuery("YEAR", invtRptInvtPreConCommonDTO.getOrderBy(), invtRptInvtPreConCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(invtRptInvtPreConCommonDTO.getIsLoadMaxCount(), invtRptInvtPreConCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     *   
     * @param invtRptInvtPreConCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        if(!"".equals(invtRptInvtPreConCommonDTO.getDeptId()) && !"".equals(invtRptInvtPreConCommonDTO.getYear())){
            query.getAndQuery("deptId", invtRptInvtPreConCommonDTO.getDeptId());
            query.getAndQuery("year", invtRptInvtPreConCommonDTO.getYear());
            return query.toString();
        }
        
        //회사
        query.getAndNumKeyQuery("compNo", user.getCompNo());
        
        // 일자
        query.getAndDateQuery("YEAR", invtRptInvtPreConCommonDTO.getFilterStartYear(), invtRptInvtPreConCommonDTO.getFilterEndYear());
        
        // 부서
        query.getDeptLevelQuery("deptId", invtRptInvtPreConCommonDTO.getFilterDeptId(), invtRptInvtPreConCommonDTO.getFilterDeptDesc(), user.getCompNo());
        
        // 공장
        query.getCodeLikeQuery("plant", "(SELECT aa.description FROM  TAPLANT aa WHERE aa.comp_no = '"+user.getCompNo()+"' AND aa.plant = a.plant )", 
        		invtRptInvtPreConCommonDTO.getFilterPlantId(), invtRptInvtPreConCommonDTO.getFilterPlantDesc());
        
        // 투자구분
        query.getCodeLikeQuery("invtCateg","SFACODE_TO_DESC(invtCateg,'INVT_CATEG','SYS','','"+user.getLangId()+"')", invtRptInvtPreConCommonDTO.getFilterInvtCateg(), invtRptInvtPreConCommonDTO.getFilterInvtCategDesc());
        
        
        return query.toString();
    }

    public String findTotalCount(
            InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                            				");
        query.append("       COUNT(*)                   				");
        query.append("FROM   (                          				");

        query.append("SELECT        									");
        query.append("    deptId							deptId		");
        query.append("    ,YEAR                     		YEAR    	");
        query.append("    ,plant                      		plant		");
        query.append("FROM (        									");
        query.append("    SELECT        								");
        query.append("        a.comp_no             		compNo    	");
        query.append("        ,a.dept_id            		deptId		");
        query.append("        ,SUBSTR(a.plan_sdate,0,4)		YEAR      	");
        query.append("        ,a.plant                      plant		");
        query.append("        ,a.invt_categ                 invtCateg	");
        query.append("    FROM TAINVTLIST a     						");
        query.append("    )  a   										");        
        query.append("WHERE 1=1 										");
        query.append(this.getWhere(invtRptInvtPreConCommonDTO, user));
        query.append("GROUP BY plant,deptId,compNo,YEAR       				");        
        query.append(") aa                                             	");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
