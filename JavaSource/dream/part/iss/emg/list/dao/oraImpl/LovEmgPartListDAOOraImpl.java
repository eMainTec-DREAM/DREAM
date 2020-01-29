package dream.part.iss.emg.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.iss.emg.list.dao.LovEmgPartListDAO;
import dream.part.iss.emg.list.dto.LovEmgPartListDTO;

/**
 * 긴급출고 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovEmgPartListDAOTarget"
 * @spring.txbn id="lovEmgPartListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovEmgPartListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovEmgPartListDAO
{
    /**
     * 항목 검색
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEmgPartListDTO
     * @param loginUser
     * @return
     */
    public List findEmgPartList(LovEmgPartListDTO lovEmgPartListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT																			");
        query.append("        '' seqNo     																");
        query.append("        ,'' isDelCheck     														");
        query.append("        ,ptemgisslist_id ptemgisslistId     										");
        query.append("        ,(SELECT a.description     												");
        query.append("          FROM   TADEPT a     													");
        query.append("          WHERE  a.dept_id = x.issue_dept) issueDept,     						");
        query.getDate("issue_date", "issueDate,");
//        query.append("        ,issue_date issueDate     ");
        query.append("        y.part_no partNo     														");
        query.append("        ,y.full_desc partDesc     												");
        query.append("        ,x.part_grade partGrade     												");
        query.append("        ,SFACODE_TO_DESC(x.part_grade,'PART_GRADE','SYS','','"+loginUser.getLangId()+"') partGradeDesc  ");
        query.append("        ,x.issue_qty issueQty     												");
        query.append("        ,(select sum(aa.stock_qty)                                                ");
        query.append("          from taptstock aa                                                       ");
        query.append("          where x.comp_no = aa.comp_no                                            ");
        query.append("              and x.part_id = aa.part_id                                          ");
        query.append("              and x.part_grade = aa.part_grade                                    ");
        query.append("              and x.to_wcode_id = aa.wcode_id) as  STOCKQTY                       ");
        query.append("        ,(SELECT emp_name     													");
        query.append("          FROM   TAEMP a     														");
        query.append("          WHERE  a.emp_id = x.rec_by) recByName     								");
        query.append("        ,ptemgiss_status ptemgissStatus     										");
        query.append("        ,SFACODE_TO_DESC(x.ptemgiss_status,'PTEMGISS_STATUS','SYS','','"+loginUser.getLangId()+"') ptemgissStatusDesc     ");
        query.append("        ,ptemg_task_status ptemgTaskStatus     									");
        query.append("        ,SFACODE_TO_DESC(x.ptemg_task_status,'PTEMG_TASK_STATUS','SYS','','"+loginUser.getLangId()+"') ptemgTaskStatusDesc     ");
        query.append("        ,(SELECT a.wo_no     														");
        query.append("          FROM   TAWORKORDER a     												");
        query.append("          WHERE  a.wkor_id = x.wkor_id) woNo     									");
        query.append("        ,(SELECT a.description     												");
        query.append("          FROM   TAWORKORDER a     												");
        query.append("          WHERE  a.wkor_id = x.wkor_id) woDesc     								");
        query.append("        ,x.remark     															");
        query.append("        ,x.req_date reqDate                                                       ");
        query.append("        ,(SELECT description                                                      ");
        query.append("          from tadept                                                             ");
        query.append("          where dept_id=x.req_dept) reqDeptDesc                                   ");
        query.append("        ,(SELECT emp_name                                                         ");
        query.append("          FROM   TAEMP a                                                          ");
        query.append("          WHERE  a.emp_id = x.req_by) reqByName                                   ");
        query.append("        ,(SELECT wname                                                            ");
        query.append("          FROM   TAWAREHOUSE a                                                    ");
        query.append("          WHERE  a.wcode_id = x.wcode_id) fromWname                               ");
        query.append("        ,(SELECT wname                                                            ");
        query.append("          FROM   TAWAREHOUSE a                                                    ");
        query.append("          WHERE  a.wcode_id = x.to_wcode_id) toWname                              ");
        query.append("FROM TAPTEMGISSLIST x, TAPARTS y     												");
        query.append("WHERE x.part_id = y.part_id     													");
        query.append(this.getWhere(lovEmgPartListDTO, loginUser));
        query.getOrderByQuery("x.ptemgisslist_id  desc ", lovEmgPartListDTO.getOrderBy(), lovEmgPartListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovEmgPartListDTO.getIsLoadMaxCount(), lovEmgPartListDTO.getFirstRow()));


    }
	
	private String getWhere(LovEmgPartListDTO lovEmgPartListDTO, User loginUser)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.append("AND ptemgiss_status = 'C'");
        query.append("AND ptemg_task_status = 'W'");
        
        query.getAndDateQuery("issue_date", lovEmgPartListDTO.getIssueDateFrom(), lovEmgPartListDTO.getIssueDateTo());
        query.getCodeLikeQuery("x.ptemgiss_status", "SFACODE_TO_DESC(x.ptemgiss_status,'PTEMGISS_STATUS','SYS','','"+loginUser.getLangId()+"')", lovEmgPartListDTO.getPtemgissStatus(), lovEmgPartListDTO.getPtemgissStatusDesc());
        query.getCodeLikeQuery("x.issue_dept", "(SELECT a.description FROM TADEPT a WHERE a.dpet_id = x.issue_dept) ", lovEmgPartListDTO.getIssueDept(), lovEmgPartListDTO.getIssueDeptDesc());
        query.getCodeLikeQuery("x.ptemg_task_status", "SFACODE_TO_DESC(x.ptemg_task_status,'PTEMG_TASK_STATUS','SYS','','"+loginUser.getLangId()+"')", lovEmgPartListDTO.getPtemgTaskStatus(), lovEmgPartListDTO.getPtemgTaskStatusDesc());
        query.getAndNumKeyQuery("x.ptemgisslist_id", lovEmgPartListDTO.getFilterPtemgisslistId());
        query.getLikeQuery("y.full_desc", lovEmgPartListDTO.getPartDesc());
        query.getCodeLikeQuery("x.rec_by", "(SELECT a.emp_name FROM TAEMP a WHERE a.emp_id = x.rec_by)", lovEmgPartListDTO.getRecBy(), lovEmgPartListDTO.getRecByName());
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+loginUser.getCompNo()+"' AND a.plant = x.plant )", 
                lovEmgPartListDTO.getFilterPlantId(), lovEmgPartListDTO.getFilterPlantDesc());
        
        //창고명
        query.getCodeLikeQuery("x.to_wcode_id", "(SELECT a.wname FROM  TAWAREHOUSE a WHERE a.comp_no = '"+loginUser.getCompNo()+"' AND a.wcode_id = x.to_wcode_id )", 
                lovEmgPartListDTO.getFilterWCodeId(), lovEmgPartListDTO.getFilterWName());

        //현재고
        if(!"".equals(lovEmgPartListDTO.getFilterStockQty()))
        {
        	query.append("and exists(select 1                           ");
            query.append("           from taptstock aa                  ");
            query.append("           where x.comp_no = aa.comp_no       ");
            query.append("           and x.part_id = aa.part_id         ");
            query.append("           and x.part_grade = aa.part_grade   ");
            query.append("           and x.to_wcode_id = aa.wcode_id    ");
            query.append("           and aa.stock_qty > '"+lovEmgPartListDTO.getFilterStockQty()+"' )	");
        }
        
        //비고
        query.getLikeQuery("x.remark", lovEmgPartListDTO.getFilterRemark());
        
        return query.toString();
    }

	@Override
	public String findTotalCount(LovEmgPartListDTO lovEmgPartListDTO, User loginUser) throws Exception {

		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT								");
        query.append("       COUNT(1)                       ");
        query.append("FROM TAPTEMGISSLIST x, TAPARTS y		");
        query.append("WHERE x.part_id = y.part_id     		");
        query.append(this.getWhere(lovEmgPartListDTO, loginUser));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}

}