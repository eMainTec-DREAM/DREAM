package dream.edu.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.edu.list.dao.EduEmpListDAO;
import dream.edu.list.dto.EduCommonDTO;
import dream.edu.list.dto.EduEmpListDTO;

/**
 * 작업결과 작업자 목록 dao
 * @author  kim21017
 * @version $Id: EduEmpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="eduEmpListDAOTarget"
 * @spring.txbn id="eduEmpListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class EduEmpListDAOSqlImpl extends BaseJdbcDaoSupportSql implements EduEmpListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: EduEmpListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param eduCommonDTO
     * @param eduEmpListDTO
     * @param loginUser
     * @return List
     */
    public List findEmpList(EduCommonDTO eduCommonDTO, EduEmpListDTO eduEmpListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                                                       ");
        query.append("     '' seqNo                                                ");
        query.append("    ,'' isDelCheck                                           ");
        query.append("    ,a.emptrainlist_id               as empTrainListId       ");
        query.append("    ,a.emp_id                        as empId                ");
        query.append("    ,b.emp_no                        as empNo                ");
        query.append("    ,b.emp_name                      as empName              ");
        query.append("    ,a.courselist_id                 as courseListId         ");
        query.append("    ,(select aa.description                                  ");
        query.append("      from tadept aa                                         ");
        query.append("      where b.comp_no = aa.comp_no                           ");
        query.append("             and b.dept_id = aa.dept_id) as deptName         ");
        query.append("    ,a.train_fdate                       as trainFdate       ");
        query.append("    ,a.train_tdate                       as trainTdate       ");
        query.append("    ,a.train_agency                      as trainAgency      ");
        query.append("    ,a.place                      as place                   ");
        query.append("    ,a.teacher                    as teacher                 ");
        query.append("    ,a.remark                            as remark           ");
        query.append("from TAEMPTRAINLIST a, TAEMP B                               ");
        query.append("where a.comp_no = b.comp_no                                  ");
        query.append("    and a.emp_id =b.emp_id                                   ");
        query.append(this.getWhere(eduCommonDTO,eduEmpListDTO,loginUser));
        //query.append("ORDER BY b.dept_id, b.emp_no   							");
        query.getOrderByQuery("a.emptrainlist_id", "b.dept_id, b.emp_no", eduEmpListDTO.getOrderBy(), eduEmpListDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(eduEmpListDTO.getIsLoadMaxCount(), eduEmpListDTO.getFirstRow()));
    }

    /**
     * delete
     * @author kim21017
     * @version $Id: EduEmpListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @param compNo
     * @return
     */
    public int deleteEmpList(String empTrainListId, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("DELETE FROM TAEMPTRAINLIST						                    ");
    	query.append("WHERE  emptrainlist_id 	= '"+empTrainListId+"'		                ");
    	query.append("  AND  comp_no		    = '"+loginUser.getCompNo()+"'			");

    	return this.getJdbcTemplate().update(query.toString());
    }

    private String getWhere(EduCommonDTO eduCommonDTO, EduEmpListDTO eduEmpListDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndQuery("a.courselist_id", eduCommonDTO.getCourseListId());
    	query.getAndQuery("a.comp_no", loginUser.getCompNo());
    	if (!"".equals(eduEmpListDTO.getEmpTrainListId()))
        {
            query.getAndQuery("a.emptrainlist_id", eduEmpListDTO.getEmpTrainListId());
            return query.toString();
        }

    	return query.toString();
    }

	@Override
	public String findTotalCount(EduCommonDTO eduCommonDTO, EduEmpListDTO eduEmpListDTO, User user) 
	{
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                               	");
        query.append("       COUNT(1)						");
        query.append("FROM TAEMPTRAINLIST a, TAEMP B		");
        query.append("WHERE a.comp_no = b.comp_no           ");
        query.append("    AND a.emp_id = b.emp_id           ");
        query.append(this.getWhere(eduCommonDTO,eduEmpListDTO,user));
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QuerySqlBuffer.listToString(resultList);
	}
}