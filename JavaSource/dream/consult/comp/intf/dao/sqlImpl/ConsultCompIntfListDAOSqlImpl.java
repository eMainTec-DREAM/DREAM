package dream.consult.comp.intf.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.consult.comp.intf.dao.ConsultCompIntfListDAO;
import dream.consult.comp.intf.dto.ConsultCompIntfCommonDTO;

/**
 * Interface Page - List DAO implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="consultCompIntfListDAOTarget"
 * @spring.txbn id="consultCompIntfListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompIntfListDAOSqlImpl extends BaseJdbcDaoSupportSql implements ConsultCompIntfListDAO
{
	public List findList(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT                                                                                        ");
        query.append("      ''                                                                      AS seqNo        ");
        query.append("      ,''                                                                     AS isDelCheck   ");
        query.append("      ,x.intf_id                                                              AS intfId       ");
        query.append("      ,x.intf_no                                                              AS intfNo       ");
        query.append("      ,x.comp_no                                                              AS compNo       ");
        query.append("      ,x.description                                                          AS intfDesc     ");
        query.append("      ,x.intf_type                                                            AS intfType     ");
        query.append("		,																					");
        query.getDate("x.last_exe_date", "lastExeDate");
        query.append("		,																					");
        query.getTime("x.exe_time", "lastExeTime");
        query.append("		,																					");
        query.getDate("x.manual_last_exe_date", "lastManExeDate");
        query.append("		,																					");
        query.getTime("x.manual_exe_time", "lastManExeTime");
        query.append("      ,x.remark                                                               AS remark       ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.is_use,'IS_USE','SYS','','"+user.getLangId()+"') AS isUse        ");
        query.append("      ,x.success_date                                                         AS successDate  ");
        query.append("      ,x.acc_url                                                              AS accUrl       ");
        query.append("      ,x.acc_name                                                             AS accName      ");
        query.append("      ,x.acc_port                                                             AS accPort      ");
        query.append("      ,x.acc_user                                                             AS accUser      ");
        query.append("      ,x.acc_password                                                         AS accPassword  ");
        query.append("      ,x.acc_site                                                             AS accSite      ");
        query.append("FROM TAINTF x                                                                                 ");
        query.append("WHERE  1=1                                                                                    ");
        query.append(this.getWhere(consultCompIntfCommonDTO, user));
        query.getOrderByQuery("x.intf_id","x.intf_no", consultCompIntfCommonDTO.getOrderBy(), consultCompIntfCommonDTO.getDirection());
        
    	return getJdbcTemplate().queryForList(query.toString(consultCompIntfCommonDTO.getIsLoadMaxCount(), consultCompIntfCommonDTO.getFirstRow()));
    	
    } 

	private String getWhere(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, User user)
    {        
		QuerySqlBuffer query = new QuerySqlBuffer();

		//회사명
        query.getCodeLikeQuery("x.comp_no", "(SELECT a.description FROM  TACOMP a WHERE a.is_use = 'Y' AND a.comp_no = x.comp_no )", 
        		consultCompIntfCommonDTO.getFilterCompNo(), consultCompIntfCommonDTO.getFilterCompDesc());

        if(!"".equals(consultCompIntfCommonDTO.getIntfId())){
            query.getAndQuery("x.intf_id", consultCompIntfCommonDTO.getIntfId());
            return query.toString();
        }
        //인터페이스 명
        query.getLikeQuery("x.description", consultCompIntfCommonDTO.getFilterIntfDesc());
        //사용여부
        query.getSysCdQuery("x.is_use", consultCompIntfCommonDTO.getFilterIsUse(), consultCompIntfCommonDTO.getFilterIsUseDesc(), "IS_USE", consultCompIntfCommonDTO.getFilterCompNo(),user.getLangId());

    	return query.toString();
    }

    public int deleteList(String id, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
    	query.append("DELETE FROM TAINTF            ");
        query.append("WHERE  comp_no        = ?     ");
        query.append("  AND  intf_id        = ?     ");
        
        Object[] objects = new Object[] {   
        		user.getCompNo()
                ,id
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(ConsultCompIntfCommonDTO consultCompIntfCommonDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                        ");
        query.append("       COUNT(1)               ");
        query.append("FROM TAINTF x					");
    	query.append("WHERE  1=1					");
    	query.append(this.getWhere(consultCompIntfCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}