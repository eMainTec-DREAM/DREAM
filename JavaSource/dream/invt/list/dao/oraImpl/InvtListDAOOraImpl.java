package dream.invt.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.invt.list.dao.InvtListDAO;
import dream.invt.list.dto.InvtCommonDTO;


/**
 * dao
 * @author  kim21017
 * @version $Id: InvtListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="invtListDAOTarget"
 * @spring.txbn id="invtListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtListDAOOraImpl extends BaseJdbcDaoSupportOra implements InvtListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: InvtListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtCommonDTO
     * @return List
     */
    public List findList(InvtCommonDTO invtCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT													");
        query.append("       '' seqNo											");
        query.append("       ,'' isDelCheck										");
        query.append("       ,invtlist_no invtlistNo							");
        query.append("       ,x.description										");
        query.append("       ,invt_categ invtCateg								");
        query.append("       ,SFACODE_TO_DESC(invt_categ,'INVT_CATEG','SYS','','"+user.getLangId()+"') invtCategDesc	");
        query.append("       ,invt_Type invtTypeId								");
        query.append("       ,SFACODE_TO_DESC(invt_Type,'INVT_TYPE','SYS','','"+user.getLangId()+"') invtTypeDesc	");
        query.append("       ,(SELECT full_desc                                 ");
        query.append("         FROM   TAEQCTG                                   ");
        query.append("         WHERE  comp_no = x.comp_no                       ");
        query.append("           AND  eqctg_id = x.eqctg_id) eqCtgDesc			");
        query.append("       ,(SELECT full_desc                                 ");
        query.append("          FROM  TAEQLOC                                   ");
        query.append("          WHERE comp_no = x.comp_no                       ");
        query.append("            AND eqloc_id = x.eqloc_id) eqLocDesc			");
        query.append("       ,x.eqloc_id eqlocId								");
        query.append("       ,x.eqctg_id eqctgId								");
        query.append("       ,x.equip_id equipId								");
        query.append("       ,y.description equipDesc							");
        query.append("       ,x.plant plant								     	");
        query.append("       ,(SELECT a.description								");
        query.append("         FROM   TAPLANT a									");
        query.append("         WHERE  a.comp_no = x.comp_no         		    ");
        query.append("           AND  a.plant = x.plant) plantDesc		    	");
        query.append("       ,x.dept_id deptId									");
        query.append("       ,(SELECT SUBSTR(SYS_CONNECT_BY_PATH(description, '-'), 2) PATH     ");
        query.append("        FROM TADEPT A                                                    ");
        query.append("        WHERE A.dept_id = x.dept_id                                       ");
        query.append("        START WITH A.p_dept_id = 0                                        ");
        query.append("        CONNECT BY PRIOR a.dept_id = a.p_dept_id ) deptDesc ");

//        query.append("       ,(SELECT a.description								");
//        query.append("         FROM   TADEPT a									");
//        query.append("         WHERE  a.dept_id = x.dept_id) deptDesc			");
        query.append(",       (SELECT SFACODE_TO_DESC(invt_proc_ltype,'INVT_PROC_LTYPE','USR','"+user.getCompNo()+"','"+user.getLangId()+"')         ");
        query.append("         FROM   (SELECT invt_proc_ltype,                  ");
        query.append("                        invtlist_id,                      ");
        query.append("                        comp_no                           ");
        query.append("                 FROM   TAINVTPHASE A                     ");
        query.append("                 WHERE  1=1                               ");
        query.append("                   AND  invtphase_status IN ('C','P')     ");
        query.append("                 ORDER BY ord_no DESC                     ");
        query.append("                 )                                        ");
        query.append("         WHERE ROWNUM = 1                                 ");
        query.append("           AND invtlist_id = x.invtlist_id                ");
        query.append("           AND comp_no = x.comp_no                        ");
        query.append("       ) curPhase                                         ");
        query.append("       ,emp_id empId										");
        query.append("       ,(SELECT a.emp_name								");
        query.append("         FROM   TAEMP a									");
        query.append("         WHERE  a.emp_id = x.emp_id) empDesc				");
        query.append("       ,plan_amt planAmt									");
        query.append("       ,cont_amt contAmt                                  ");
        query.append("       ,(SELECT a.description                             ");
        query.append("         FROM   TAVENDOR a                                ");
        query.append("         WHERE  a.vendor_id = x.vendor_id) vendorDesc     ");
        query.append("       ,plan_sdate planSdate								");
        query.append("       ,plan_edate planEdate								");
        query.append("       ,SFACODE_TO_DESC(invtlist_status,'INVTLIST_STATUS','SYS','','"+user.getLangId()+"') invtStatusDesc	");
        query.append("       ,invtlist_id invtlistId							");
        query.append("       ,end_date endDate									");
        query.append("       ,x.remark            								");
        query.append("       ,(SELECT sum(actual_amt)											");
        query.append("         FROM tainvtphase a												");
        query.append("         WHERE  1=1														");
        query.append("         AND a.invtlist_id = x.invtlist_id 								");
        query.append("         AND a.comp_no = x.comp_no										");
        query.getStringEqualQuery("a.invtphase_status", "C");
        query.append("         GROUP BY a.comp_no , a.invtlist_id) actualAmt				");
        query.append("       ,(SELECT round(sum((CASE WHEN a.invtphase_status='C' THEN 1 ELSE 0 END))/sum(1)*100,2)||'%'		");
        query.append("         FROM tainvtphase a												");
        query.append("         WHERE  1=1														");
        query.append("         AND a.invtlist_id = x.invtlist_id 								");
        query.append("         AND a.comp_no = x.comp_no										");
        query.append("         GROUP BY a.comp_no , a.invtlist_id) actualRate					");
        query.append("        ,(SELECT b.emp_name                                               ");
        query.append("          FROM   TAWOREQRES A INNER JOIN TAEMP b ON A.emp_id = b.emp_id   ");
        query.append("          WHERE  A.invtlist_id = x.invtlist_id                            ");
        query.append("            AND  A.comp_no = x.comp_no                                    ");
        query.append("            AND  ROWNUM = 1                                               ");
        query.append("         )  reqUser                                                       ");
        query.append("       ,NVL(TRUNC(SYSDATE - TO_DATE(plan_edate, 'YYYYMMDD') ), 0) trafficLight        ");
        query.append("       ,SFACODE_TO_DESC(x.invt_kind,'INVT_KIND','SYS','','"+user.getLangId()+"') INVTKINDDESC	");
        query.append("       ,invtlist_status invtlistStatus        ");
        query.append("FROM   TAINVTLIST x LEFT OUTER JOIN TAEQUIPMENT y 						");
        query.append("                  ON x.equip_id = y.equip_id				");
        query.append("WHERE  1=1               									");
        query.append(" and x.comp_no = '"+user.getCompNo()+"'	");
        query.append(this.getWhere(invtCommonDTO, user));
        query.getOrderByQuery("x.invtlist_id DESC", invtCommonDTO.getOrderBy(), invtCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(invtCommonDTO.getIsLoadMaxCount(), invtCommonDTO.getFirstRow()));

    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: InvtListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(InvtCommonDTO invtCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        if (!"".equals(invtCommonDTO.getInvtlistId()))
        {
        	query.getAndNumKeyQuery("x.invtlist_id", invtCommonDTO.getInvtlistId());
            return query.toString();
        }

        query.getLikeQuery("x.description", invtCommonDTO.getDescription());
        query.getLikeQuery("x.invtlist_no", invtCommonDTO.getInvtlistNo());
        //분류
    	query.getSysCdQuery("x.invt_type", invtCommonDTO.getInvtTypeId(), invtCommonDTO.getInvtTypeDesc(), "INVT_TYPE", user.getCompNo(),user.getLangId());
      
        query.getCodeLikeQuery("x.invtlist_status", "SFACODE_TO_DESC(invtlist_status,'INVTLIST_STATUS','SYS','','"+user.getLangId()+"')", invtCommonDTO.getInvtlistStatus(), invtCommonDTO.getInvtlistStatusDesc());
        query.getDeptLevelQuery("x.dept_id", invtCommonDTO.getDeptId(), invtCommonDTO.getDeptDesc(), user.getCompNo());
        
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.emp_id)",invtCommonDTO.getEmpId(), invtCommonDTO.getEmpDesc());
        query.getCodeLikeQuery("x.invt_categ", "SFACODE_TO_DESC(invt_categ,'INVT_CATEG','SYS','','"+user.getLangId()+"')",invtCommonDTO.getInvtCateg(), invtCommonDTO.getInvtCategDesc());
        query.getEqLocLevelQuery("x.eqloc_id", invtCommonDTO.getEqlocId(), invtCommonDTO.getEqlocDesc(), user.getCompNo());
        query.getEqCtgLevelQuery("x.eqctg_id", invtCommonDTO.getEqctgId(), invtCommonDTO.getEqctgDesc(), user.getCompNo());
        query.getCodeLikeQuery("x.equip_id", "y.description", invtCommonDTO.getEquipId(), invtCommonDTO.getEquipDesc());
        query.getLikeQuery("(SELECT a.description FROM TAINVTPRCTP a WHERE a.invtprctp_id = x.invtprctp_id)", invtCommonDTO.getInvtprctpDesc());
        query.getAndDateQuery("x.plan_sdate", invtCommonDTO.getFilterStartDate(), invtCommonDTO.getFilterEndDate());
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		invtCommonDTO.getFilterPlantId(), invtCommonDTO.getFilterPlantDesc());
        //투자종류
    	query.getSysCdQuery("x.invt_kind", invtCommonDTO.getFilterInvtKindId(), invtCommonDTO.getFilterInvtKindDesc(), "INVT_KIND", user.getCompNo(), user.getLangId());
      
    	//투자완료일자
    	query.getAndDateQuery("x.end_date", invtCommonDTO.getFilterInvtComFromDate(), invtCommonDTO.getFilterInvtComToDate());

    	//사용부서
        query.getDeptLevelQuery("x.usage_dept", invtCommonDTO.getFilterUsageDeptId(), invtCommonDTO.getFilterUsageDeptDesc(), user.getCompNo());
        
        return query.toString();
    }

	@Override
	public int deletePhase(String invtlist_id, User loginUser) {
		QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAINVTPHASE                                            ");
        query.append("WHERE  comp_no  = ?                                       	");
        query.append("  AND  invtlist_id  = ?                                       ");      
        
        Object[] objects = new Object[] {   
                loginUser.getCompNo(),
                invtlist_id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
	}

	@Override
	public int deleteInvtList(String invtlist_id, User loginUser) {
		QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAINVTLIST                                             ");
        query.append("WHERE  comp_no  = ?                                       	");
        query.append("  AND  invtlist_id  = ?                                       ");      
        
        Object[] objects = new Object[] {   
                loginUser.getCompNo(),
                invtlist_id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
	}

	@Override
	public String findTotalCount(InvtCommonDTO invtCommonDTO, User user) throws Exception {
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAINVTLIST x LEFT OUTER JOIN TAEQUIPMENT y 						");
        query.append("                  ON x.equip_id = y.equip_id				");
        query.append("WHERE  1=1               									");
        query.append(" and x.comp_no = '"+user.getCompNo()+"'	");
        query.append(this.getWhere(invtCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
  
	public String getData(User user)
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT 										");
		query.append("    CASE WHEN MIN(x.exceltab_id) IS NOT NULL 	");
		query.append("             THEN MIN(x.exceltab_id)|| ',' || min(x.description) || ',' || min(x.table_name) 	");
		query.append("             ELSE '0' 						");
		query.append("             END           					");
		query.append("FROM TAEXCELTAB x								");
		query.append("WHERE x.exceltab_no = ?						");
		
        Object[] objects = new Object[] {
        		"INVESTLIST"
        };

		return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),objects));
	}
}