package dream.work.let.categ.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.let.categ.dao.WorkLetCategPointDetailDAO;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;
import dream.work.let.categ.dto.WorkLetCategPointDetailDTO;
import dream.work.let.categ.dto.WorkLetCategPointListDTO;

/**
 * 안전작업유형 점검항목 Detail Page - Detail DAO implements
 * @author euna0207
 * @version $Id: WorkLetCategPointDetailDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workLetCategPointDetailDAOTarget"
 * @spring.txbn id="workLetCategPointDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkLetCategPointDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkLetCategPointDetailDAO
{
	
    public WorkLetCategPointDetailDTO findDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategPointListDTO workLetCategPointListDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT																													");
        query.append("comp_no																								AS compNo			");
        query.append(", woletctgpoint_id                    	 															AS woLetCtgPointId	");
        query.append(", step_num         					 																AS stepNum			");
        query.append(", check_point     																					AS checkPoint		");
        query.append(", check_position  				 																	AS checkPosition	");
        query.append(", fit_basis        			     																	AS fitBasis			");
        query.append(", check_method  					 																	AS checkMethod		");
        query.append(", is_use  					 																		AS isUseId			");
        query.append(", SFACODE_TO_DESC(a.is_use, 'IS_USE', 'SYS', '', ?)           										AS isUseDesc		");
        query.append(", SFACODE_TO_DESC(a.check_type, 'CHECK_TYPE', 'SYS', '', ?) 											AS checkTypeDesc	");
        query.append(", check_type                                                                 				  		 	AS checkTypeId		");
        query.append(", check_min                                                                       					AS checkMin			");
        query.append(", check_basis_val                                                                 					AS checkBasisVal	");
        query.append(", check_max                                                                       					AS checkMax			");
        query.append(", uom                                                                             					AS uom				");
        query.append(", remark                                                                          					AS remark			");
        query.append(", (SELECT description FROM TAWOLETCTG WHERE comp_no = a.comp_no AND woletctg_id = a.woletctg_id)      AS woLetCtgId		");
        query.append("FROM TAWOLETCTGPOINT a																									");
        query.append("WHERE  1=1																												");
    	query.append("AND    woletctgpoint_id = ?																								");
    	query.append("AND    comp_no   		  = ?																								");
        
        Object[] objects = new Object[] {
        		user.getLangId()
        		,user.getLangId()
        		,workLetCategPointListDTO.getWoLetCtgPointId()
    			,user.getCompNo()
    	};
    
        WorkLetCategPointDetailDTO workLetCategPointDetailDTO = 
        		(WorkLetCategPointDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkLetCategPointDetailDTO()));
        
        return workLetCategPointDetailDTO;
        
    }
    

    public int insertDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategPointDetailDTO workLetCategPointDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAWOLETCTGPOINT (		");
    	query.append("comp_no							");
    	query.append(", woletctg_id						"); //최상위 list의 key값
    	query.append(", step_num						");
    	query.append(", check_point						");
    	query.append(", check_position					");
    	query.append(", check_method					");
    	query.append(", fit_basis						");
    	query.append(", check_type						");
    	query.append(", is_use							");
    	query.append(", check_min						");
    	query.append(", check_basis_val					");
    	query.append(", check_max						");
    	query.append(", uom								");
    	query.append(", remark							");
    	query.append(", woletctgpoint_id				");
    	query.append(")									");
    	query.append("VALUES (							");
    	query.append(" ?								");
    	query.append(", ?								");
    	query.append(", ?								");
    	query.append(", ?								");
    	query.append(", ?								");
    	query.append(", ?								");
    	query.append(", ?								");
    	query.append(", ?								");
    	query.append(", ?								");
    	query.append(", ?								");
    	query.append(", ?								");
    	query.append(", ?								");
    	query.append(", ?								");
    	query.append(", ?								");
    	query.append(", ?								");
    	query.append(")									");

    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,workLetCategCommonDTO.getWoLetCtgId()
    			,workLetCategPointDetailDTO.getStepNum()
    			,workLetCategPointDetailDTO.getCheckPoint()
    			,workLetCategPointDetailDTO.getCheckPosition()
    			,workLetCategPointDetailDTO.getCheckMethod()
    			,workLetCategPointDetailDTO.getFitBasis()
    			,workLetCategPointDetailDTO.getCheckTypeId()
    			,workLetCategPointDetailDTO.getIsUseId()
    			,workLetCategPointDetailDTO.getCheckMin()
    			,workLetCategPointDetailDTO.getCheckBasisVal()
    			,workLetCategPointDetailDTO.getCheckMax()
    			,workLetCategPointDetailDTO.getUom()
    			,workLetCategPointDetailDTO.getRemark()
    			,workLetCategPointDetailDTO.getWoLetCtgPointId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    
    
    public int updateDetail(WorkLetCategCommonDTO workLetCategCommonDTO, WorkLetCategPointDetailDTO workLetCategPointDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("UPDATE TAWOLETCTGPOINT SET 	");
    	query.append("step_num 				= ?		");
    	query.append(", check_point 		= ?		");
    	query.append(", check_position 		= ?		");
    	query.append(", fit_basis 			= ?		");
    	query.append(", check_method 		= ?		");
    	query.append(", is_use 				= ?		");
    	query.append(", check_type 			= ?		");
    	query.append(", check_min 			= ?		");
    	query.append(", check_basis_val		= ?		");
    	query.append(", check_max			= ?		");
    	query.append(", uom 				= ?		");
    	query.append(", remark 				= ?		");
    	query.append("WHERE comp_no 		= ?		");
    	query.append("AND woletctgpoint_id 	= ?		");

    	Object[] objects = new Object[] {
    			workLetCategPointDetailDTO.getStepNum()
    			,workLetCategPointDetailDTO.getCheckPoint()
    			,workLetCategPointDetailDTO.getCheckPosition()
    			,workLetCategPointDetailDTO.getFitBasis()
    			,workLetCategPointDetailDTO.getCheckMethod()
    			,workLetCategPointDetailDTO.getIsUseId()
    			,workLetCategPointDetailDTO.getCheckTypeId()
    			,workLetCategPointDetailDTO.getCheckMin()
    			,workLetCategPointDetailDTO.getCheckBasisVal()
    			,workLetCategPointDetailDTO.getCheckMax()
    			,workLetCategPointDetailDTO.getUom()
    			,workLetCategPointDetailDTO.getRemark()
    			,user.getCompNo()
    			,workLetCategPointDetailDTO.getWoLetCtgPointId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
}