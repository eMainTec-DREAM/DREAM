package dream.work.let.permit.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.let.permit.dao.WorkLetPermitPointDetailDAO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointListDTO;

/**
 * 안전작업허가서 작업유형 - 점검항목 상세 DAO implements
 * @author syyang
 * @version $Id: WorkLetPermitPointDetailDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
 * @since 1.0
 * 
 * @spring.bean id="workLetPermitPointDetailDAOTarget"
 * @spring.txbn id="workLetPermitPointDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkLetPermitPointDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkLetPermitPointDetailDAO
{
	
    public WorkLetPermitPointDetailDTO findDetail(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointListDTO workLetPermitPointListDTO, User user) throws Exception 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT																	");
        query.append("		 x.woletlistpoint_id						AS woLetListPointId		");
        query.append("		, x.step_num        						AS stepNum				");
        query.append("		, x.check_position  						AS checkPosition		");
        query.append("		, x.check_point     						AS checkPoint			");
        query.append("		, x.check_method  							AS checkMethod			");
        query.append("		, x.fit_basis       						AS fitBasis				");
        query.append("		, x.check_type      						AS checkTypeId			");
        query.append("		, SFACODE_TO_DESC(x.check_type, 'CHECK_TYPE', 'SYS', '', '"+user.getLangId()+"')	AS checkTypeDesc	");
        query.append("		, x.check_min        						AS checkMin				");
        query.append("		, x.check_basis_val  						AS checkBasisVal		");
        query.append("		, x.check_max        						AS checkMax				");
        query.append("		, x.uom              						AS uom					");
        query.append("		, x.wolet_rslt_status						AS woLetRsltStatus		");
        query.append("		, SFACODE_TO_DESC(x.wolet_rslt_status,'WOLET_RSLT_STATUS','SYS','','"+user.getLangId()+"')	AS woLetRsltStatusDesc	");
        query.append("		, x.result_value 							AS resultValue			");
        query.append("		, x.remark          						AS remark				");
        query.append("		, x.woletlist_id    						AS woLetListId			");
        query.append("		, x.woletctg_id     						AS woLetCtgId			");
        query.append("FROM TAWOLETLISTPOINT x													");
        query.append("WHERE 1=1																	");
        query.append(" AND x.comp_no   	     = ?												");
        query.append(" AND x.woletlist_id 	 = ?												");
    	query.append(" AND x.woletlistpoint_id = ?												");
        
        Object[] objects = new Object[] {
        		user.getCompNo()
        		,workLetPermitDetailDTO.getWoLetListId()
        		,workLetPermitPointListDTO.getWoLetListPointId()
    	};
    
        WorkLetPermitPointDetailDTO workLetPermitPointDetailDTO = 
        		(WorkLetPermitPointDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkLetPermitPointDetailDTO()));
        
        return workLetPermitPointDetailDTO;
        
    }
    

    public int insertDetail(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointDetailDTO workLetPermitPointDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAWOLETLISTPOINT							    ");
    	query.append("	(comp_no		,woletlistpoint_id	,woletlist_id		");
    	query.append("	 ,woletctg_id	,step_num			,check_position		");
    	query.append("	 ,check_point	,check_method		,fit_basis			");
    	query.append("	 ,check_type	,check_min			,check_basis_val	");
    	query.append("	 ,check_max		,uom				,wolet_rslt_status	");
    	query.append("	 ,result_value	,remark				,check_date			");
    	query.append("	 ,check_time	,check_by			,cre_time			");
    	query.append("   ,cre_by		,upd_time     		,upd_by				");
    	query.append("	)	VALUES												");
    	query.append("	(?				,?					,?					");
    	query.append("	 ,?				,?					,?					");
    	query.append("	 ,?				,?					,?					");
    	query.append("	 ,?				,?					,?					");
    	query.append("	 ,?				,?					,?					");
    	query.append("	 ,?				,?					,?					");
    	query.append("	 ,?				,?					,?					");
    	query.append("	 ,?				,?					,?					");
    	query.append("	)														");
    	

    	
    	Object[] objects = new Object[] {
    			user.getCompNo()
    			,workLetPermitPointDetailDTO.getWoLetListPointId()
    			,workLetPermitDetailDTO.getWoLetListId()
    			,workLetPermitPointDetailDTO.getWoLetCtgId()
    			,workLetPermitPointDetailDTO.getStepNum()
    			,workLetPermitPointDetailDTO.getCheckPosition()
    			,workLetPermitPointDetailDTO.getCheckPoint()
    			,workLetPermitPointDetailDTO.getCheckMethod()
    			,workLetPermitPointDetailDTO.getFitBasis()
    			,workLetPermitPointDetailDTO.getCheckTypeId()
    			,workLetPermitPointDetailDTO.getCheckMin()
    			,workLetPermitPointDetailDTO.getCheckBasisVal()
    			,workLetPermitPointDetailDTO.getCheckMax()
    			,workLetPermitPointDetailDTO.getUom()
    			,workLetPermitPointDetailDTO.getWoLetRsltStatus()
    			,workLetPermitPointDetailDTO.getResultValue()
    			,workLetPermitPointDetailDTO.getRemark()
    			,workLetPermitPointDetailDTO.getCheckDate()
    			,workLetPermitPointDetailDTO.getCheckTime()
    			,workLetPermitPointDetailDTO.getCheckBy()
    			,workLetPermitPointDetailDTO.getCreTime()
    			,user.getEmpId()
    			,workLetPermitPointDetailDTO.getUpdTime()
    			,user.getEmpId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    
    
    public int updateDetail(WorkLetPermitDetailDTO workLetPermitDetailDTO, WorkLetPermitPointDetailDTO workLetPermitPointDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;
    	
    	query.append("UPDATE TAWOLETLISTPOINT SET 		");
    	query.append("		step_num 			= ?		");
    	query.append("		, check_position 	= ?		");
    	query.append("		, check_point 		= ?		");
    	query.append("		, check_method 		= ?		");
    	query.append("		, fit_basis 		= ?		");
    	query.append("		, check_type 		= ?		");
    	query.append("		, check_min 		= ?		");
    	query.append("		, check_basis_val	= ?		");
    	query.append("		, check_max			= ?		");
    	query.append("		, uom 				= ?		");
    	query.append("		, wolet_rslt_status = ?		");
    	query.append("		, result_value 		= ?		");
    	query.append("		, remark 			= ?		");
    	query.append("		, upd_time 			= ?		");
    	query.append("		, upd_by 			= ?		");
//    	query.append("		, check_date 		= ?		");
//    	query.append("		, check_time 		= ?		");
//    	query.append("		, check_by 			= ?		");
    	query.append("WHERE comp_no 		 = ?		");
        query.append(" AND woletlist_id 	 = ?		");
    	query.append(" AND woletlistpoint_id = ?		");
        
    	Object[] objects = new Object[] {
    			workLetPermitPointDetailDTO.getStepNum()
    			,workLetPermitPointDetailDTO.getCheckPosition()
    			,workLetPermitPointDetailDTO.getCheckPoint()
    			,workLetPermitPointDetailDTO.getCheckMethod()
    			,workLetPermitPointDetailDTO.getFitBasis()
    			,workLetPermitPointDetailDTO.getCheckTypeId()
    			,workLetPermitPointDetailDTO.getCheckMin()
    			,workLetPermitPointDetailDTO.getCheckBasisVal()
    			,workLetPermitPointDetailDTO.getCheckMax()
    			,workLetPermitPointDetailDTO.getUom()
    			,workLetPermitPointDetailDTO.getWoLetRsltStatus()
    			,workLetPermitPointDetailDTO.getResultValue()
    			,workLetPermitPointDetailDTO.getRemark()
    			,workLetPermitPointDetailDTO.getUpdTime()
    			,user.getEmpId()
//    			,workLetPermitPointDetailDTO.getCheckDate()
//    			,workLetPermitPointDetailDTO.getCheckTime()
//    			,workLetPermitPointDetailDTO.getCheckBy()
    			,user.getCompNo()
    			,workLetPermitDetailDTO.getWoLetListId()
    			,workLetPermitPointDetailDTO.getWoLetListPointId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }


	@Override
	public int insertStdPointDetail(String woLetListId, String woLetCtgType, User user) throws Exception
	{
    	QueryBuffer query = new QueryBuffer();
    	
    	int rtnValue  = 0;

    	query.append("INSERT INTO TAWOLETLISTPOINT							    ");
    	query.append("	(comp_no		,woletlist_id		,woletlistpoint_id	");
    	query.append("	 ,woletctg_id	,step_num			,check_position		");
    	query.append("	 ,check_point	,check_method		,fit_basis			");
    	query.append("	 ,check_type	,check_min			,check_basis_val	");
    	query.append("	 ,check_max		,uom				,remark				");
    	query.append("	)														");
    	query.append("SELECT 													");
    	query.append("	 comp_no		,?					,SQAWOLETLISTPOINT_ID.NEXTVAL	");
    	query.append("	 ,woletctg_id	,step_num			,check_position		");
    	query.append("	 ,check_point	,check_method		,fit_basis			");
    	query.append("	 ,check_type	,check_min			,check_basis_val	");
    	query.append("	 ,check_max		,uom				,remark				");
    	query.append("FROM TAWOLETCTGPOINT 										");
    	query.append("WHERE  1=1                                                ");
    	query.append(" AND comp_no = ?                            	            ");
    	query.append(" AND woletctg_id IN (SELECT aa.woletctg_id       	    	");
    	query.append("                     FROM TAWOLETCTG aa         	    	");
    	query.append("                     WHERE aa.comp_no = ?              	");
    	query.append("                     AND aa.woletctg_type = ?) 			");
    	
    	Object[] objects = new Object[] {
    			woLetListId
    			,user.getCompNo()
    			,user.getCompNo()
    			,woLetCtgType
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
	}
}