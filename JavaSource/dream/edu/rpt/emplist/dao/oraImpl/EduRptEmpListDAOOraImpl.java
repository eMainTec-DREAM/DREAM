package dream.edu.rpt.emplist.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;

import dream.edu.rpt.emplist.dao.EduRptEmpListDAO;
import dream.edu.rpt.emplist.dto.EduRptEmpCommonDTO;

/**
 * 자격증분류 - 목록 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="eduRptEmpListDAOTarget"
 * @spring.txbn id="eduRptEmpListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class EduRptEmpListDAOOraImpl extends BaseJdbcDaoSupportOra implements EduRptEmpListDAO
{
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     *
     * @param eduRptEmpCommonDTO
     * @return List
     */
    public List findList(EduRptEmpCommonDTO eduRptEmpCommonDTO    ,User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                         ");
        query.append("      ''                                  as seqNo             ");
        query.append("    ,c.courselist_no                      as courseListNo      ");
        query.append("    ,c.description                        as description       ");
        query.append("    ,b.emp_no                             as empNo             ");
        query.append("    ,b.emp_name                           as empName           ");
        query.append("    ,(select aa.description                                     ");
        query.append("      from tadept aa                                            ");
        query.append("      where b.comp_no = aa.comp_no                              ");
        query.append("             and b.dept_id = aa.dept_id)   as deptName          ");
        query.append("    ,a.train_fdate                         as trainFdate        ");
        query.append("    ,a.train_tdate                         as trainTdate        ");
        query.append("    ,a.train_agency                        as trainAgency       ");
        query.append("    ,a.place                               as place             ");
        query.append("    ,a.teacher                             as teacher           ");
        query.append("    ,a.remark                              as remark            ");
        query.append("    ,a.emptrainlist_id                     as empTrainListId    ");
        query.append("FROM TAEMPTRAINLIST a INNER JOIN TAEMP B     					  ");
        query.append("ON a.comp_no = b.comp_no AND a.emp_id = b.emp_id                ");
        query.append(" INNER JOIN TACOURSELIST c    								  ");
        query.append(" ON a.comp_no = c.comp_no AND a.courselist_id = c.courselist_id ");
        query.append("where 1=1                                                       ");
        query.append(this.getWhere(eduRptEmpCommonDTO, user));
        //query.append("ORDER BY c.courselist_no                                   ");
        query.getOrderByQuery("c.courselist_no", eduRptEmpCommonDTO.getOrderBy(), eduRptEmpCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(eduRptEmpCommonDTO.getIsLoadMaxCount(), eduRptEmpCommonDTO.getFirstRow()));
    }


    /**
     * Filter 조건
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     *
     * @param eduRptEmpCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(EduRptEmpCommonDTO eduRptEmpCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();


        //상태
        query.getCodeLikeQuery("c.course_type", "SFACODE_TO_DESC(c.course_type, 'EDU_TYPE', 'SYS', c.comp_no,'"+user.getLangId()+"')", 
                                         eduRptEmpCommonDTO.getFilterEduType(), eduRptEmpCommonDTO.getFilterEduTypeDesc());

        query.getLikeQuery("c.description", eduRptEmpCommonDTO.getFilterEduName());
        return query.toString();
    }


	@Override
	public String findTotalCount(EduRptEmpCommonDTO eduRptEmpCommonDTO, User user)
	{
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                           					");
        query.append("       COUNT(1)													");
        query.append("FROM TAEMPTRAINLIST a INNER JOIN TAEMP B     						");
        query.append("ON a.comp_no = b.comp_no AND a.emp_id = b.emp_id              	");
        query.append(" INNER JOIN TACOURSELIST c    									");
        query.append(" ON a.comp_no = c.comp_no AND a.courselist_id = c.courselist_id	");
        query.append("WHERE 1=1                                         				");
        query.append(this.getWhere(eduRptEmpCommonDTO, user));

        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QueryBuffer.listToString(resultList);
	}
}