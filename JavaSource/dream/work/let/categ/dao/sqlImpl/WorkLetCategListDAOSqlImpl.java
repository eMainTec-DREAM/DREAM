package dream.work.let.categ.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.work.let.categ.dao.WorkLetCategListDAO;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;

/**
 * 안전작업유형 list Page - List DAO implements
 * @author euna0207
 * @version $Id: WorkLetCategListDAOSqlImpl.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workLetCategListDAOTarget"
 * @spring.txbn id="workLetCategListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkLetCategListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkLetCategListDAO
{
	public List findList(WorkLetCategCommonDTO workLetCategCommonDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
        
    query.append("SELECT 																									");
    query.append(" ''            		 											      			      AS isDelCheck		");
    query.append(",''               													   				  AS seqNo			");
    query.append(", comp_no    																  			  AS compNo		  	");
    query.append(", woletctg_no    															  			  AS woletctgNo  	");
    query.append(", description  														      			  AS description	");
    query.append(", dbo.SFACODE_TO_DESC(a.woletctg_type,'WOLETCTG_TYPE','SYS','','"+user.getLangId()+"') 					  AS woLetCtgType	");
    query.append(", (SELECT description FROM TADEPT WHERE comp_no = a.comp_no AND dept_id = a.dept_id) 	  AS deptId			");
    query.append(", (SELECT emp_name FROM TAEMP WHERE comp_no = a.comp_no AND emp_id = a.emp_id) 		  AS empId			");
    query.append(", reg_date                                                                 			  AS regDate		");
    query.append(", dbo.SFACODE_TO_DESC(a.is_use, 'IS_USE', 'SYS', '', '"+user.getLangId()+"')							  AS isUse			");
    query.append(", remark                                                                 	 			  AS remark			");
    query.append(", woletctg_id                                                               			  AS woLetCtgId 	");
    query.append("FROM TAWOLETCTG a																							");
    query.append("WHERE  1=1																								");
	query.append(this.getWhere(workLetCategCommonDTO, user));
    query.getOrderByQuery("woletctg_id", "a.woletctg_id", workLetCategCommonDTO.getOrderBy(), workLetCategCommonDTO.getDirection());
        
    										//쿼리 STRING화
    	return getJdbcTemplate().queryForList(query.toString(workLetCategCommonDTO.getIsLoadMaxCount(), workLetCategCommonDTO.getFirstRow()));
    } 

	private String getWhere(WorkLetCategCommonDTO workLetCategCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getStringEqualQuery("a.comp_no", user.getCompNo());
        if(!"".equals(workLetCategCommonDTO.getWoLetCtgId())){
        	query.getAndQuery("a.woletctg_id", workLetCategCommonDTO.getWoLetCtgId());
        	return query.toString();
        }
        //제목
        query.getLikeQuery("a.description", workLetCategCommonDTO.getFilterDes());
        //안전작업유형
    	query.getSysCdQuery("a.woletctg_type", workLetCategCommonDTO.getFilterWoLetCtgTypeId(), workLetCategCommonDTO.getFilterWoLetCtgTypeDesc(), "WOLETCTG_TYPE", user.getCompNo(), user.getLangId());

    	return query.toString();
    }

    public int deleteList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE FROM TAWOLETCTG			");
        query.append("WHERE  comp_no 		= ?			");
        query.append("  AND  woletctg_id  	= ?			");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    public String findTotalCount(WorkLetCategCommonDTO workLetCategCommonDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                ");
        query.append("       COUNT(1)                       ");
        query.append("FROM TAWOLETCTG a						");
    	query.append("WHERE  1=1							");
    	query.append(this.getWhere(workLetCategCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}