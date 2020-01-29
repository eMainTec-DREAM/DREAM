package dream.mgr.intf.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.intf.dao.MgrIntfListDAO;
import dream.mgr.intf.dto.MgrIntfCommonDTO;

/**
 * Interface Page - List DAO implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="mgrIntfListDAOTarget"
 * @spring.txbn id="mgrIntfListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrIntfListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrIntfListDAO
{
	public List findList(MgrIntfCommonDTO mgrIntfCommonDTO, User user) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SELECT                                                                                        ");
        query.append("      ''                                                                      AS seqNo        ");
        query.append("      ,''                                                                     AS isDelCheck   ");
        query.append("      ,x.intf_id                                                              AS intfId       ");
        query.append("      ,x.intf_no                                                              AS intfNo       ");
        query.append("      ,x.description                                                          AS intfDesc     ");
        query.append("      ,x.intf_type                                                            AS intfType     ");
        query.append("      ,x.last_exe_date                                                        AS lastExeDate  ");
        query.append("      ,x.exe_time                                                             AS exeTime      ");
        query.append("      ,x.manual_last_exe_date                                                 AS manualLastExeDate    ");
        query.append("      ,x.manual_exe_time                                                      AS manualExeTime        ");
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
        query.append(this.getWhere(mgrIntfCommonDTO, user));
        query.getOrderByQuery("x.intf_id","x.intf_no", mgrIntfCommonDTO.getOrderBy(), mgrIntfCommonDTO.getDirection());
        
        
    	return getJdbcTemplate().queryForList(query.toString(mgrIntfCommonDTO.getIsLoadMaxCount(), mgrIntfCommonDTO.getFirstRow()));
    } 

	private String getWhere(MgrIntfCommonDTO mgrIntfCommonDTO, User user)
    {        
		QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.getStringEqualQuery("x.comp_no", user.getCompNo());
        if(!"".equals(mgrIntfCommonDTO.getIntfId())){
            query.getAndQuery("x.intf_id", mgrIntfCommonDTO.getIntfId());
            return query.toString();
        }
        //인터페이스 명
        query.getLikeQuery("x.description", mgrIntfCommonDTO.getFilterIntfDesc());
        //사용여부
        query.getSysCdQuery("x.is_use", mgrIntfCommonDTO.getFilterIsUse(), mgrIntfCommonDTO.getFilterIsUseDesc(), "IS_USE", user.getCompNo(),user.getLangId());

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
    public String findTotalCount(MgrIntfCommonDTO mgrIntfCommonDTO, User user) throws Exception
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM TAINTF x												");
    	query.append("WHERE  1=1												");
    	query.append(this.getWhere(mgrIntfCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}