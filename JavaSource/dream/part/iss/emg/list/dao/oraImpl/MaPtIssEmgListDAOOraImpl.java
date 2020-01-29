package dream.part.iss.emg.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.iss.emg.list.dao.MaPtIssEmgListDAO;
import dream.part.iss.emg.list.dto.MaPtIssEmgCommonDTO;

/**
 * 자재출고확정 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtIssEmgListDAOTarget"
 * @spring.txbn id="maPtIssEmgListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtIssEmgListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtIssEmgListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssEmgCommonDTO
     * @return List
     */
    public List findPtIssEmgList(MaPtIssEmgCommonDTO maPtIssEmgCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maPtIssEmgCommonDTO.getCompNo();
        
        query.append("SELECT																			");
        query.append("        '' seqNo     																");
        query.append("        ,'' isDelCheck     														");
        query.append("        ,ptemgisslist_id ptemgisslistId     										");
        query.append("        ,(SELECT a.description     												");
        query.append("          FROM   TADEPT a     													");
        query.append("          WHERE  a.dept_id = x.issue_dept AND a.comp_no = x.comp_no) issueDept,     						");
        query.getDate("issue_date", "issueDate,");
//        query.append("        ,issue_date issueDate     ");
        query.append("        y.part_no partNo     														");
        query.append("        ,y.full_desc partDesc     												");
        query.append("        ,x.part_grade partGrade     												");
        query.append("        ,SFACODE_TO_DESC(x.part_grade,'PART_GRADE','SYS','','"+user.getLangId()+"') partGradeDesc     		");
        query.append("        ,x.issue_qty issueQty     												");
        query.append("        ,(SELECT emp_name     													");
        query.append("          FROM   TAEMP a     														");
        query.append("          WHERE  a.emp_id = rec_by AND a.comp_no = x.comp_no) recByName     								");
        query.append("        ,ptemgiss_status ptemgissStatus     										");
        query.append("        ,SFACODE_TO_DESC(x.ptemgiss_status,'PTEMGISS_STATUS','SYS','','"+user.getLangId()+"') ptemgissStatusDesc     ");
        query.append("        ,ptemg_task_status ptemgTaskStatus     									");
        query.append("        ,SFACODE_TO_DESC(x.ptemg_task_status,'PTEMG_TASK_STATUS','SYS','','"+user.getLangId()+"') ptemgTaskStatusDesc     ");
        query.append("        ,(SELECT a.wo_no     														");
        query.append("          FROM   TAWORKORDER a     												");
        query.append("          WHERE  a.wkor_id = x.wkor_id AND a.comp_no = x.comp_no) woNo     									");
        query.append("        ,(SELECT a.description     												");
        query.append("          FROM   TAWORKORDER a     												");
        query.append("          WHERE  a.wkor_id = x.wkor_id AND a.comp_no = x.comp_no) woDesc     								");
        query.append("        ,x.remark     															");
        query.append("        ,x.req_qty reqQty     ");
        query.append("        ,(SELECT a.description     ");
        query.append("          FROM   TAEQUIPMENT a     ");
        query.append("          WHERE a.equip_id = x.equip_id AND a.comp_no = x.comp_no) equipDesc     ");
        query.append("        ,(SELECT a.full_desc     ");
        query.append("          FROM   TAEQLOC a     ");
        query.append("          WHERE a.eqloc_id = (select aa.eqloc_id from taequipment aa where aa.equip_id=x.equip_id and aa.comp_no=a.comp_no) AND a.comp_no = x.comp_no) eqLocDesc     ");
        query.append("        ,(SELECT a.full_desc                                                                  ");
        query.append("          FROM   TAEQASMB a                                                                   ");
        query.append("          WHERE a.eqasmb_id = x.eqasmb_id AND a.comp_no = x.comp_no) eqAsmbDesc               ");
        query.append("        ,x.req_date reqDate                                                                   ");
        query.append("        ,(SELECT a.description                                                                ");
        query.append("          FROM   TADEPT a                                                                     ");
        query.append("          WHERE a.dept_id = x.req_dept AND a.comp_no = x.comp_no) reqDeptDesc                 ");
        query.append("        ,(SELECT a.emp_name                                                                   ");
        query.append("          FROM   TAEMP a                                                                      ");
        query.append("          WHERE  a.emp_id = x.req_by AND a.comp_no = x.comp_no) reqByName                     ");
        query.append("        ,(SELECT a.ptemgissreq_no                                                             ");
        query.append("          FROM   TAPTEMGISSREQ a                                                              ");
        query.append("          WHERE  a.ptemgissreq_id = x.ptemgissreq_id AND a.comp_no = x.comp_no) ptEmgIssReqNo ");
        query.append("        ,(SELECT wname                                                                ");
        query.append("          FROM   TAWAREHOUSE a                                                        ");
        query.append("          WHERE  a.wcode_id = x.wcode_id AND a.comp_no = x.comp_no) wname             ");
        query.append("        ,(SELECT wname                                                                ");
        query.append("          FROM   TAWAREHOUSE a                                                        ");
        query.append("          WHERE  a.wcode_id = x.to_wcode_id AND a.comp_no = x.comp_no) toWname        ");
        query.append("        ,(SELECT ctctr_no                                                             ");
        query.append("          FROM   TACTCTR a                                                            ");
        query.append("          WHERE  a.ctctr_id = x.ctctr_id AND a.comp_no = x.comp_no) ctCtrNo           ");
        query.append("        ,(SELECT description                                                          ");
        query.append("          FROM   TACTCTR a                                                            ");
        query.append("          WHERE  a.ctctr_id = x.ctctr_id AND a.comp_no = x.comp_no) ctCtrDesc         ");
        query.append("FROM TAPTEMGISSLIST x, TAPARTS y     												");
        query.append("WHERE x.part_id = y.part_id     													");
        query.append("AND x.comp_no = y.comp_no                                              ");
        query.append(this.getWhere(maPtIssEmgCommonDTO, user));
        query.append("ORDER BY ptemgisslist_id DESC                          							");
        
        return getJdbcTemplate().queryForList(query.toString(maPtIssEmgCommonDTO.getIsLoadMaxCount(), maPtIssEmgCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtIssEmgCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPtIssEmgCommonDTO maPtIssEmgCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
      
        query.getAndQuery("x.ptemgissreq_id", maPtIssEmgCommonDTO.getPtemgissreqId());
        query.getAndQuery("x.comp_no", user.getCompNo());
        if (!"".equals(maPtIssEmgCommonDTO.getPtemgisslistId()))
        {
            query.getAndQuery("x.ptemgisslist_id", maPtIssEmgCommonDTO.getPtemgisslistId());
            return query.toString();
        }
        
        query.getAndDateQuery("issue_date", maPtIssEmgCommonDTO.getIssueDateFrom(), maPtIssEmgCommonDTO.getIssueDateTo());
        query.getCodeLikeQuery("x.ptemgiss_status", "SFACODE_TO_DESC(x.ptemgiss_status,'PTEMGISS_STATUS','SYS','','"+user.getLangId()+"')", maPtIssEmgCommonDTO.getPtemgissStatus(), maPtIssEmgCommonDTO.getPtemgissStatusDesc());
        query.getCodeLikeQuery("x.issue_dept", "(SELECT a.description FROM TADEPT a WHERE a.dpet_id = x.issue_dept) ", maPtIssEmgCommonDTO.getIssueDept(), maPtIssEmgCommonDTO.getIssueDeptDesc());
        query.getCodeLikeQuery("x.ptemg_task_status", "SFACODE_TO_DESC(x.ptemg_task_status,'PTEMG_TASK_STATUS','SYS','','"+user.getLangId()+"')", maPtIssEmgCommonDTO.getPtemgTaskStatus(), maPtIssEmgCommonDTO.getPtemgTaskStatusDesc());
        query.getAndNumKeyQuery("x.ptemgisslist_id", maPtIssEmgCommonDTO.getFilterPtemgisslistId());
        query.getLikeQuery("y.full_desc", maPtIssEmgCommonDTO.getPartDesc());
        query.getCodeLikeQuery("x.rec_by", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.rec_by AND a.comp_no = x.comp_no)", maPtIssEmgCommonDTO.getRecBy(), maPtIssEmgCommonDTO.getRecByName());
        
        //요청부서
        query.getDeptLevelQuery("x.req_dept", maPtIssEmgCommonDTO.getFilterReqDeptId(), maPtIssEmgCommonDTO.getFilterReqDeptDesc(), user.getCompNo());
        
        //요청일자
        query.getAndDateQuery("x.req_date", maPtIssEmgCommonDTO.getFilterReqStartDate(), maPtIssEmgCommonDTO.getFilterReqEndDate());
        
        //요청자
        query.getCodeLikeQuery("x.req_by", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.req_by AND a.comp_no = x.comp_no)", maPtIssEmgCommonDTO.getFilterReqById(), maPtIssEmgCommonDTO.getFilterReqByDesc());
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = x.comp_no AND plant = x.plant )", 
                maPtIssEmgCommonDTO.getFilterPlantId(), maPtIssEmgCommonDTO.getFilterPlantDesc());
        
        //코스트센터
        query.getCodeLikeQuery("x.ctctr_id", "(SELECT '('+a.ctctr_no+')' + a.description FROM  TACTCTR a WHERE a.comp_no = x.comp_no AND a.ctctr_id = x.ctctr_id )", 
                maPtIssEmgCommonDTO.getFilterCtctrId(), maPtIssEmgCommonDTO.getFilterCtctrDesc());
        
        return query.toString();
    }

    public int deletePtIssEmg(String ptemgisslist_id, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAPTEMGISSLIST                 	");
        query.append("WHERE ptemgisslist_id = ?                     ");
        query.append("    and comp_no   = ?                         ");
        
        Object[] objects = new Object[]{
        	ptemgisslist_id
        	,user
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public String findTotalCount(MaPtIssEmgCommonDTO maPtIssEmgCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM TAPTEMGISSLIST x, TAPARTS y                                                  ");
        query.append("WHERE x.part_id = y.part_id                                                       ");
        query.append("AND x.comp_no = y.comp_no                                              ");
        query.append(this.getWhere(maPtIssEmgCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}