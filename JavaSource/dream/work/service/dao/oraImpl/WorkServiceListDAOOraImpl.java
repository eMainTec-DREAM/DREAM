package dream.work.service.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.service.dao.WorkServiceListDAO;
import dream.work.service.dto.WorkServiceCommonDTO;

/**
 * 서비스 마스터 - List DAO implements
 * @author cjscjs9
 * @version $Id: WorkServiceListDAOOraImpl.java,v 1.0 2018/08/02 09:12:40 cjscjs9 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workServiceListDAOTarget"
 * @spring.txbn id="workServiceListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkServiceListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkServiceListDAO
{
	public List findWorkServiceList(WorkServiceCommonDTO workServiceCommonDTO, User user) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT         																						");
        query.append("	''															  					  AS isDelCheck     ");
        query.append("	,''															  					  AS seqNo          ");
        query.append("	,a.service_id     											  					  AS serviceId      ");
        query.append("	,a.service_no 												  					  AS serviceNo      ");
        query.append("	,a.description 												  					  AS serviceName    ");
        query.append("	,SFACODE_TO_DESC(a.service_uom,'SERVICE_UOM', 'SYS', '',  '"+user.getLangId()+"') AS serviceUom     ");
        query.append("	,a.reg_date      											  					  AS regDate        ");
        query.append("	,(SELECT  description FROM tadept x        															");
        query.append("	  WHERE x.comp_no = a.comp_no AND x.dept_id =a.dept_id) 		  				  AS deptDesc       ");
        query.append("	,(SELECT  emp_name FROM taemp y        																");
        query.append("	  WHERE y.comp_no = a.comp_no AND y.emp_id =a.emp_id) 			  				  AS empDesc        ");
        query.append("	,SFACODE_TO_DESC(a.is_use,'IS_USE','SYS','','ko') 			  					  AS isUse          ");
        query.append("	,a.remark 													  					  AS remark         ");
        query.append("FROM taservice a        																				");
        query.append("WHERE 1=1      																					    ");
        query.append(this.getWhere(workServiceCommonDTO, user));
        query.getOrderByQuery("service_no", workServiceCommonDTO.getOrderBy(), workServiceCommonDTO.getDirection());

        
        
        return getJdbcTemplate().queryForList(query.toString(workServiceCommonDTO.getIsLoadMaxCount(), workServiceCommonDTO.getFirstRow()));
        } 

	private String getWhere(WorkServiceCommonDTO workServiceCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getStringEqualQuery("a.comp_no", user.getCompNo());
        if(!"".equals(workServiceCommonDTO.getServiceId())){
        	query.getAndQuery("a.service_id", workServiceCommonDTO.getServiceId());
        	return query.toString();
        }
        // 등록일자
        query.getAndDateQuery("a.reg_date", workServiceCommonDTO.getFilterFromRegDate(), workServiceCommonDTO.getFilterToRegDate());
        // 작성자 
        query.getCodeLikeQuery("a.emp_id", "(SELECT  emp_name FROM taemp y WHERE y.comp_no = a.comp_no AND y.emp_id =a.emp_id)", 
        		workServiceCommonDTO.getFilterEmpId(), workServiceCommonDTO.getFilterEmpDesc());
        // 서비스명 
        query.getLikeQuery("a.description", workServiceCommonDTO.getFilterDescription());
        // 서비스 #
        query.getAndQuery("a.service_no", workServiceCommonDTO.getFilterServiceNo());
    	return query.toString();
    }

    public int deleteWorkServiceList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TASERVICE			");
        query.append("WHERE  comp_no 		= ?		");
        query.append("  AND  service_id  	= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(WorkServiceCommonDTO workServiceCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM TASERVICE a											");
    	query.append("WHERE  1=1												");
    	query.append(this.getWhere(workServiceCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList); 
    }
}