package dream.work.service.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.service.dao.WorkServiceDetailDAO;
import dream.work.service.dto.WorkServiceCommonDTO;
import dream.work.service.dto.WorkServiceDetailDTO;

/**
 * 서비스 마스터 - Detail DAO implements
 * @author cjscjs9
 * @version $Id: WorkServiceDetailDAOOraImpl.java,v 1.0 2018/08/02 09:12:40 cjscjs9 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workServiceDetailDAOTarget"
 * @spring.txbn id="workServiceDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkServiceDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkServiceDetailDAO
{
	
    public WorkServiceDetailDTO findWorkServiceDetail(WorkServiceCommonDTO workServiceCommonDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        String compNo = user.getCompNo();
        
        query.append("SELECT         																						");
        query.append("	a.service_id     											  					  AS serviceId      ");
        query.append("	,a.service_no 												  					  AS serviceNo      ");
        query.append("	,a.description 												  					  AS serviceName    ");
        query.append("	,a.service_uom 												  					  AS serviceUomId   ");
        query.append("	,SFACODE_TO_DESC(a.service_uom,'SERVICE_UOM', 'SYS', '',  ?)					  AS serviceUomDesc ");
        query.append("	,a.reg_date      											  					  AS regDate        ");
        query.append("	,a.dept_id 												  					  	  AS deptId      	");
        query.append("	,(SELECT  description FROM tadept x        															");
        query.append("	  WHERE x.comp_no = a.comp_no AND x.dept_id =a.dept_id) 		  				  AS deptDesc       ");
        query.append("	,a.emp_id 												  					  	  AS empId      	");
        query.append("	,(SELECT  emp_name FROM taemp y        																");
        query.append("	  WHERE y.comp_no = a.comp_no AND y.emp_id =a.emp_id) 			  				  AS empDesc        ");
        query.append("	,a.is_use 												  					  	  AS isUseId        ");
        query.append("	,SFACODE_TO_DESC(a.is_use,'IS_USE','SYS','',?) 			  					  	  AS isUseDesc      ");
        query.append("	,a.remark 													  					  AS remark         ");
        query.append("	,a.fit_value 													  				  AS fitValue       ");
        query.append("FROM taservice a        																				");
        query.append("WHERE 1=1      																					    ");
    	query.append("AND    service_id  = ?																				");
    	query.append("AND    comp_no    = ?																					");
    	query.getOrderByQuery("service_no", workServiceCommonDTO.getOrderBy(), workServiceCommonDTO.getDirection());
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,user.getLangId()
        		,workServiceCommonDTO.getServiceId()
    			,user.getCompNo()
    	};
    
        WorkServiceDetailDTO workServiceDetailDTO = 
        		(WorkServiceDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkServiceDetailDTO()));
        
        return workServiceDetailDTO;
        
    }
    

    public int insertWorkServiceDetail(WorkServiceDetailDTO workServiceDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TASERVICE(	");
    	query.append("	comp_no					");
    	query.append("	,service_id				");
    	query.append("	,service_no				");
    	query.append("	,description			");
    	query.append("	,service_uom			");
    	query.append("	,fit_value				");
    	query.append("	,is_use					");
    	query.append("	,reg_date				");
    	query.append("	,dept_id				");
    	query.append("	,emp_id 				");
    	query.append("	,remark					");
    	query.append(")VALUES(					");
    	query.append("	?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append("	,?						");
    	query.append(")							");
    	
    	Object[] objects = new Object[] {
    			 user.getCompNo()
    			,workServiceDetailDTO.getServiceId()
    			,workServiceDetailDTO.getServiceNo()
    			,workServiceDetailDTO.getServiceName()
    			,workServiceDetailDTO.getServiceUomId()
    			,workServiceDetailDTO.getFitValue()
    			,workServiceDetailDTO.getIsUseId()
    			,workServiceDetailDTO.getRegDate()
    			,workServiceDetailDTO.getDeptId()
    			,workServiceDetailDTO.getEmpId()
    			,workServiceDetailDTO.getRemark()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    
    
    public int updateWorkServiceDetail(WorkServiceDetailDTO workServiceDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TASERVICE SET						");
    	query.append("	description				= ?				");
    	query.append("	,service_uom			= ?				");
    	query.append("	,fit_value			    = ?				");
    	query.append("	,is_use			        = ?				");
    	query.append("	,reg_date			    = ?				");
    	query.append("	,dept_id				= ?				");
    	query.append("	,emp_id			        = ?				");
    	query.append("	,remark					= ?				");
    	query.append("WHERE  comp_no			= ?				");
    	query.append("  AND  service_id			= ?				");
    	
    	Object[] objects = new Object[] {
    			workServiceDetailDTO.getServiceName()
    			,workServiceDetailDTO.getServiceUomId()
    			,workServiceDetailDTO.getFitValue()
    			,workServiceDetailDTO.getIsUseId()
    			,workServiceDetailDTO.getRegDate()
    			,workServiceDetailDTO.getDeptId()
    			,workServiceDetailDTO.getEmpId()
    			,workServiceDetailDTO.getRemark()
    			,user.getCompNo()
    			,workServiceDetailDTO.getServiceId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}