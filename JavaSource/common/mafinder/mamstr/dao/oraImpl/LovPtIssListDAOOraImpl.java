package common.mafinder.mamstr.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.mafinder.mamstr.dao.LovPtIssListDAO;
import common.mafinder.mamstr.dto.LovPtIssListDTO;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;

/**
 * 출고부품 팝업
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="lovPtIssListDAOTarget"
 * @spring.txbn id="lovPtIssListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovPtIssListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovPtIssListDAO
{
	@Override
	public List findPtIssAcList(LovPtIssListDTO lovPtIssListDTO, User loginUser, Map<String, String> conditionMap) {
		
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT                                                                                                              ");
		query.append("    ''                                                                                      AS seqNo                ");
		query.append("    ,a.ptisslist_id                                                                         AS ptisslistId          ");
		query.append("    ,(SELECT description FROM TADEPT                                                                                ");
		query.append("      WHERE comp_no = a.comp_no                                                                                     ");
		query.append("      AND dept_id = a.issue_dept)                                                           AS issueDeptDesc        ");
		query.append("    ,(SELECT emp_name FROM TAEMP                                                                                    ");
		query.append("      WHERE comp_no = a.comp_no                                                                                     ");
		query.append("      AND emp_id = a.issue_by)                                                              AS issueByDesc          ");
		query.append("    ,a.issue_date                                                                           AS issueDate            ");
		query.append("    ,b.part_no                                                                              AS partNo               ");
		query.append("    ,b.description                                                                          AS partName             ");
		query.append("    ,SFACODE_TO_DESC(a.part_grade,'PART_GRADE','SYS','','"+loginUser.getLangId()+"')        AS partGradeDesc        ");
		query.append("    ,a.issue_qty                                                                            AS issueQty             ");
		query.append("    ,(SELECT wname FROM TAWAREHOUSE                                                                                 ");
		query.append("      WHERE comp_no = a.comp_no                                                                                     ");
		query.append("      AND wcode_id = a.wcode_id)                                                            AS issueWname           ");
		query.append("    ,SFACODE_TO_DESC(a.ptiss_type,'PTISS_TYPE','SYS','','"+loginUser.getLangId()+"')        AS ptIssTypeDesc        ");
		query.append("    ,SFACODE_TO_DESC(a.ptiss_status,'PTISS_STATUS','SYS','','"+loginUser.getLangId()+"')    AS ptIssStatusDesc      ");
		query.append("    ,(SELECT description FROM TADEPT                                                                                ");
		query.append("      WHERE comp_no = a.comp_no                                                                                     ");
		query.append("      AND dept_id = a.rec_dept)                                                             AS recDeptDesc          ");
		query.append("    ,(SELECT emp_name FROM TAEMP                                                                                    ");
		query.append("      WHERE comp_no = a.comp_no                                                                                     ");
		query.append("      AND emp_id = a.rec_by)                                                                AS recByDesc            ");
		query.append("    ,a.remark                                                                               AS remark               ");
		query.append("FROM TAPTISSLIST a INNER JOIN TAPARTS b                                                                             ");
		query.append("ON a.comp_no = b.comp_no                                                                                            ");
		query.append("AND a.part_id = b.part_id                                                                                           ");
		query.append("WHERE 1=1                                                                                                           ");
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        query.getAndQuery("a.ptiss_status", conditionMap);
        query.getAndQuery("a.plant", conditionMap);
        query.getAndQuery("a.rec_dept", conditionMap);
        query.getAndQuery("a.rec_by", conditionMap);
        query.append(this.getWhere(lovPtIssListDTO,loginUser));
        query.getOrderByQuery("a.issue_date desc", lovPtIssListDTO.getOrderBy(), lovPtIssListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovPtIssListDTO.getIsLoadMaxCount(), lovPtIssListDTO.getFirstRow()));
	}
	
	@Override
	public String findPtIssListTotalCount(LovPtIssListDTO lovPtIssListDTO, User user, Map<String, String> conditionMap)
	{
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT				                    ");
        query.append("       COUNT(1)                           ");
        query.append("FROM TAPTISSLIST a INNER JOIN TAPARTS b   ");
        query.append("ON a.comp_no = b.comp_no                  ");
        query.append("AND a.part_id = b.part_id                 ");
        query.append("WHERE 1=1                                 ");
        query.getAndQuery("a.comp_no", user.getCompNo());
        query.getAndQuery("a.ptiss_status", conditionMap);
        query.getAndQuery("a.plant", conditionMap);
        query.getAndQuery("a.rec_dept", conditionMap);
        query.getAndQuery("a.rec_by", conditionMap);
        query.append(this.getWhere(lovPtIssListDTO,user));

        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QueryBuffer.listToString(resultList);
	}
	
    private String getWhere(LovPtIssListDTO lovPtIssListDTO, User loginUser)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndDateQuery("a.issue_date", lovPtIssListDTO.getFilterIssueDateFrom(), lovPtIssListDTO.getFilterIssueDateTo());
        query.getCodeLikeQuery("a.issue_dept", "(SELECT description FROM TADEPT WHERE comp_no = a.comp_no AND dept_id = a.issue_dept)", lovPtIssListDTO.getFilterIssueDept(), lovPtIssListDTO.getFilterIssueDeptDesc());
        query.getAndNumKeyQuery("a.ptisslist_id", lovPtIssListDTO.getFilterPtIssListId());
        query.getLikeQuery("b.full_desc", lovPtIssListDTO.getFilterPtNameSize());
        query.getCodeLikeQuery("a.rec_by", "(SELECT emp_name FROM TAEMP WHERE comp_no = a.comp_no AND emp_id = a.rec_by)", lovPtIssListDTO.getFilterRecBy(), lovPtIssListDTO.getFilterRecByDesc());
        
        //공장코드
        query.getCodeLikeQuery("a.plant", "(SELECT description FROM TAPLANT WHERE comp_no = a.comp_no AND plant = a.plant )", 
                lovPtIssListDTO.getFilterPlant(), lovPtIssListDTO.getFilterPlantDesc());
        
        //창고명
        query.getCodeLikeQuery("a.wcode_id", "(SELECT wname FROM TAWAREHOUSE WHERE comp_no = a.comp_no AND wcode_id = a.wcode_id )", 
                lovPtIssListDTO.getFilterWcodeId(), lovPtIssListDTO.getFilterWname());
        
        return query.toString();
    }
}