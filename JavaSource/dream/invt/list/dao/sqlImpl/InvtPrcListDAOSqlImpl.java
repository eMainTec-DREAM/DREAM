package dream.invt.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.invt.list.dao.InvtPrcListDAO;
import dream.invt.list.dto.InvtCommonDTO;


/**
 * dao
 * @author  kim21017
 * @version $Id: InvtPrcListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="invtPrcListDAOTarget"
 * @spring.txbn id="invtPrcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtPrcListDAOSqlImpl extends BaseJdbcDaoSupportSql implements InvtPrcListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: InvtPrcListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtCommonDTO
     * @return List
     */
    public List findList(InvtCommonDTO invtCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                                                    ");
        query.append("       '' seqNo                                           ");
        query.append("       ,'' isDelCheck                                     ");
        query.append("       ,invtlist_no invtlistNo                            ");
        query.append("       ,x.description                                     ");
        query.append("       ,invt_categ invtCateg                              ");
        query.append("       ,dbo.SFACODE_TO_DESC(invt_categ,'INVT_CATEG','SYS','','"+user.getLangId()+"') invtCategDesc    ");
        query.append("       ,(SELECT full_desc                                 ");
        query.append("         FROM   TAEQCTG                                   ");
        query.append("         WHERE  comp_no = x.comp_no                       ");
        query.append("           AND  eqctg_id = x.eqctg_id) eqCtgDesc          ");
        query.append("       ,(SELECT full_desc                                 ");
        query.append("          FROM  TAEQLOC                                   ");
        query.append("          WHERE comp_no = x.comp_no                       ");
        query.append("            AND eqloc_id = x.eqloc_id) eqLocDesc          ");
        query.append("       ,x.eqloc_id eqlocId                                ");
        query.append("       ,x.eqctg_id eqctgId                                ");
        query.append("       ,x.equip_id equipId                                ");
        query.append("       ,y.description equipDesc                           ");
        query.append("       ,x.dept_id deptId                                  ");
        query.append("       ,(SELECT a.description                             ");
        query.append("         FROM   TADEPT a                                  ");
        query.append("         WHERE  a.dept_id = x.dept_id) deptDesc           ");
        query.append("       ,emp_id empId                                      ");
        query.append("       ,(SELECT a.emp_name                                ");
        query.append("         FROM   TAEMP a                                   ");
        query.append("         WHERE  a.emp_id = x.emp_id) empDesc              ");
        query.append("       ,plan_amt planAmt                                  ");
        query.append("       ,plan_sdate planSdate                              ");
        query.append("       ,plan_edate planEdate                              ");
        query.append("       ,dbo.SFACODE_TO_DESC(invtlist_status,'INVTLIST_STATUS','SYS','','"+user.getLangId()+"') invtStatusDesc ");
        query.append("       ,dbo.SFACODE_TO_DESC(z.invt_proc_ltype,'INVT_PROC_LTYPE','USR','"+user.getCompNo()+"','ko') invtProcLtypeDesc          ");
        query.append("       ,dbo.SFACODE_TO_DESC(z.invt_proc_stype,'INVT_PROC_STYPE','USR','"+user.getCompNo()+"','ko') invtProcStypeDesc      ");
        query.append("       ,invt_proc_ltype invtProcLtype                     ");
        query.append("       ,invt_proc_stype invtProcStype                     ");
        query.append("       ,z.ref_depart refDepart                            ");
        query.append("       ,z.ref_doc refDoc                                  ");
        query.append("       ,z.invtphase_status invtphaseStatus                                  ");
        query.append("       ,dbo.SFACODE_TO_DESC(z.invtphase_status,'INVTPHASE_STATUS','SYS','','ko') invtphaseStatusDesc      ");
        query.append("       ,z.start_date startDate                            ");
        query.append("       ,z.end_date endDate                                ");
        query.append("       ,z.actual_amt actualAmt                            ");
        query.append("       ,z.remark                                          ");
        query.append("       ,z.invtphase_id invtphaseId                        ");
        query.append("       ,x.invtlist_id invtlistId                          ");
        query.append("       ,z.invtprcph_id invtprcphId                        ");
        query.append("       ,z.invt_doc_no invtDocNo                        ");
        query.append("       ,z.ord_no ordNo                                     ");
        query.append("       ,(SELECT COUNT(zb.docdata_id) FROM TAOBJDOC za INNER JOIN TADOCDATA zb ON za.doc_id=zb.doc_id WHERE za.comp_no=z.comp_no AND za.object_type='INVTPHASE' AND za.object_id=z.invtphase_id) docCnt");
        query.append("FROM   TAINVTLIST x LEFT OUTER JOIN TAEQUIPMENT y              ");
        query.append("                  ON x.equip_id = y.equip_id              ");
        query.append("                  AND x.comp_no = y.comp_no              ");
        query.append("           INNER JOIN TAINVTPHASE z                       ");
        query.append("           ON x.invtlist_id = z.invtlist_id               ");
        query.append("           AND x.comp_no = z.comp_no               ");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(invtCommonDTO, user));
        query.getOrderByQuery("x.invtlist_id", "x.invtlist_id", invtCommonDTO.getOrderBy(), invtCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(invtCommonDTO.getIsLoadMaxCount(), invtCommonDTO.getFirstRow()));

    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: InvtPrcListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(InvtCommonDTO invtCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        if (!"".equals(invtCommonDTO.getInvtphaseId()))
        {
            query.getAndNumKeyQuery("z.invtphase_id", invtCommonDTO.getInvtphaseId());
            return query.toString();
        }

        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getLikeQuery("x.description", invtCommonDTO.getDescription());
        query.getCodeLikeQuery("x.invtlist_status", "dbo.SFACODE_TO_DESC(invtlist_status,'INVTLIST_STATUS','SYS','','"+user.getLangId()+"')", invtCommonDTO.getInvtlistStatus(), invtCommonDTO.getInvtlistStatusDesc());
        query.getCodeLikeQuery("x.dept_id", "(SELECT a.description FROM TADEPT a WHERE a.dept_id = x.dept_id)",invtCommonDTO.getDeptId(), invtCommonDTO.getDeptDesc());
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.emp_id)",invtCommonDTO.getEmpId(), invtCommonDTO.getEmpDesc());
        query.getCodeLikeQuery("x.invt_categ", "SFACODE_TO_DESC(invt_categ,'INVT_CATEG','SYS','','"+user.getLangId()+"')",invtCommonDTO.getInvtCateg(), invtCommonDTO.getInvtCategDesc());
        query.getEqLocLevelQuery("x.eqloc_id", invtCommonDTO.getEqlocId(), invtCommonDTO.getEqlocDesc(), user.getCompNo());
        query.getEqCtgLevelQuery("x.eqctg_id", invtCommonDTO.getEqctgId(), invtCommonDTO.getEqctgDesc(), user.getCompNo());
        query.getCodeLikeQuery("x.equip_id", "y.description", invtCommonDTO.getEquipId(), invtCommonDTO.getEquipDesc());
        query.getLikeQuery("(SELECT a.description FROM TAINVTPRCTP a WHERE a.invtprctp_id = x.invtprctp_id)", invtCommonDTO.getInvtprctpDesc());
        query.getAndQuery("x.invtlist_no", invtCommonDTO.getInvtlistNo());
        // 일자
        query.getAndDateQuery("z.end_date", invtCommonDTO.getFilterStartDate(), invtCommonDTO.getFilterEndDate());
        // 사용부서
        query.getDeptLevelQuery("x.usage_dept", invtCommonDTO.getFilterUsageDeptId(), invtCommonDTO.getFilterUsageDeptDesc(), user.getCompNo());
        
        // 공장
        query.getCodeLikeQuery("x.plant", "(SELECT aa.description FROM  TAPLANT aa WHERE aa.comp_no = '"+user.getCompNo()+"' AND aa.plant = x.plant )", 
        		invtCommonDTO.getFilterPlantId(), invtCommonDTO.getFilterPlantDesc());
        
        // 분류
        query.getSysCdQuery("x.invt_type", invtCommonDTO.getInvtTypeId(), invtCommonDTO.getInvtTypeDesc(), "INVT_TYPE", user.getCompNo(), user.getLangId());
        //대분류
        query.getSysCdQuery("z.INVT_PROC_LTYPE", invtCommonDTO.getFilterLTypeId(), invtCommonDTO.getFilterLType(), "INVT_PROC_LTYPE", user.getCompNo(), user.getLangId());
        //소분류
        query.getSysCdQuery("z.INVT_PROC_STYPE", invtCommonDTO.getFilterSTypeId(), invtCommonDTO.getFilterSType(), "INVT_PROC_STYPE", user.getCompNo(), user.getLangId());
        
        
        return query.toString();
    }

    @Override
    public int deleteInvtPhase(String invtphase_id, User loginUser) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE TAINVTPHASE                                            ");
        query.append("WHERE  comp_no  = ?                                           ");
        query.append("  AND  invtphase_id  = ?                                      ");      
        
        Object[] objects = new Object[] {   
                loginUser.getCompNo(),
                invtphase_id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public int deleteInvtList(String invtlist_id, User loginUser) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE TAINVTLIST                                             ");
        query.append("WHERE  comp_no  = ?                                           ");
        query.append("  AND  invtlist_id  = ?                                       ");      
        
        Object[] objects = new Object[] {   
                loginUser.getCompNo(),
                invtlist_id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    public String findTotalCount(
    		InvtCommonDTO invtCommonDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAINVTLIST x LEFT OUTER JOIN TAEQUIPMENT y              ");
        query.append("                  ON x.equip_id = y.equip_id              ");
        query.append("                  AND x.comp_no = y.comp_no              ");
        query.append("           INNER JOIN TAINVTPHASE z                       ");
        query.append("           ON x.invtlist_id = z.invtlist_id               ");
        query.append("           AND x.comp_no = z.comp_no               ");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(invtCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
}