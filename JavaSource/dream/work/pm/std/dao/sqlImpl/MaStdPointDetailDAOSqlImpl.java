package dream.work.pm.std.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.work.pm.std.dao.MaStdPointDetailDAO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdPointDetailDTO;
import dream.work.pm.std.dto.MaStdPointListDTO;

/**
 * 표준항목 리스트 - 상세 dao
 * 
 * @author kim21017
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maStdPointDetailDAOTarget"
 * @spring.txbn id="maStdPointDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaStdPointDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaStdPointDetailDAO
{
	 /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0 
     * 
     * @param maStdPointCommonDTO
     * @param loginUser
     * @return
     */
    public MaStdPointDetailDTO findDetail(MaStdPointCommonDTO maStdPointCommonDTO, MaStdPointListDTO maStdPointListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String compNo=  loginUser.getCompNo();
        
        query.append("SELECT																		");
        query.append("		x.stwrk_point_id stWrkPointId,											");
        query.append("		x.step_num stepNum,														");
        query.append("		x.check_point 'CHECKPOINT',												");
        query.append("		x.check_method checkMethod,												");
        query.append("		x.fit_basis fitBasis,													");
        query.append("		dbo.SFACODE_TO_DESC(x.check_type,'CHECK_TYPE','SYS','','"+loginUser.getLangId()+"') checkTypeDesc,		");
        query.append("      x.check_type checkType,                                                 ");
        query.append("      x.fit_basis fitBasis,                                                   ");
        query.append("      x.check_min checkMin,                                                   ");
        query.append("      x.check_max checkMax,                                                   ");
        query.append("      x.uom,                                                                  ");
        query.append("      x.check_basis_val checkBasisVal,                                        ");
        query.append("      x.is_active isActive,                                                   ");
        query.append("		x.remark remark,														");
        query.append("		x.schedule_type scheduleType,											");
        query.append("      dbo.SFACODE_TO_DESC(x.schedule_type, 'SCHEDULE_TYPE', 'SYS', '"+compNo+"','"+loginUser.getLangId()+"') scheduleTypeDesc,    ");
        query.append("      x.cycle,                                                      			");
        query.append("      x.period_type 								periodType,      			");
        query.append("      dbo.SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE', 'SYS', '"+compNo+"','"+loginUser.getLangId()+"') periodTypeDesc,          ");
        query.append("      x.USAGE                                                      			");
        query.append("		, x.work_time								predTime					");
        query.append("		, x.ord_no									ordNo						");
        query.append("FROM   TASTWRKPOINT x															");
        query.append("WHERE  1=1																	");
        
        
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.stwrk_id", maStdPointCommonDTO.getStWrkId());
        query.getAndQuery("x.stwrk_point_id", maStdPointListDTO.getStWrkPointId());
    
        MaStdPointDetailDTO maStdPointDetailDTO = 
        		(MaStdPointDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaStdPointDetailDTO()));
        
        return maStdPointDetailDTO;
    }
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointDetailDTO
     * @return
     */
    public int insertDetail(MaStdPointDetailDTO maStdPointDetailDTO, MaStdPointCommonDTO maStdPointCommonDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TASTWRKPOINT                             ");
    	query.append("    (comp_no,           stwrk_point_id,              ");
    	query.append("     step_num,          check_point,                 ");
    	query.append("     check_method,      fit_basis,                   ");
    	query.append("     check_type,        check_min,                   ");
    	query.append("     check_basis_val,   check_max,                   ");
    	query.append("     uom,               is_active,                   ");
    	query.append("     remark,            stwrk_id,                    ");
    	query.append("     schedule_type,     ord_no,      				   ");
    	query.append("     cycle,             period_type,                 ");
    	query.append("     usage             ,work_time					   ");
    	query.append("    )    VALUES                                      ");
    	query.append(" 		  (?,                ?,                         ");	
    	query.append(" 		   dbo.IS_EMPTY( ?),                ?,          ");	
    	query.append(" 		   ?,                ?,                         ");	
    	query.append(" 		   ?,                dbo.IS_EMPTY( ?),          ");	
    	query.append(" 		   dbo.IS_EMPTY( ?), dbo.IS_EMPTY( ?),          ");	
    	query.append(" 		   ?,                ?,                         ");	
    	query.append(" 		   ?,                ?,                         ");	
    	query.append(" 		   ?,                ?,	                        ");	
    	query.append(" 		  dbo.IS_EMPTY( ?),                ?,           ");	
    	query.append(" 		  dbo.IS_EMPTY( ?)	,?                          ");	
    	query.append(" 		  )                   				            ");	
    	
    	Object[] objects = new Object[] {
    			loginUser.getCompNo(),
    			maStdPointDetailDTO.getStWrkPointId(),
    	        maStdPointDetailDTO.getStepNum(),
    	        maStdPointDetailDTO.getCheckPoint(),
    	        maStdPointDetailDTO.getCheckMethod(),
    	        maStdPointDetailDTO.getFitBasis(),
    	        maStdPointDetailDTO.getCheckType(),
    	        maStdPointDetailDTO.getCheckMin(),
    	        maStdPointDetailDTO.getCheckBasisVal(),
    	        maStdPointDetailDTO.getCheckMax(),
    	        maStdPointDetailDTO.getUom(),
    	        maStdPointDetailDTO.getIsActive(),
    	        maStdPointDetailDTO.getRemark(),
    	        maStdPointCommonDTO.getStWrkId(),
    	        maStdPointDetailDTO.getScheduleType(),
    	        maStdPointDetailDTO.getOrdNo(),
    	        maStdPointDetailDTO.getCycle(),
    	        maStdPointDetailDTO.getPeriodType(),
    	        maStdPointDetailDTO.getUsage()
    	        ,maStdPointDetailDTO.getPredTime()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointDetailDTO
     * @return
     */
    public int updateDetail(MaStdPointDetailDTO maStdPointDetailDTO,MaStdPointCommonDTO maStdPointCommonDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	Object[] objects;

    	query.append("UPDATE TASTWRKPOINT  SET  ");
    	query.append("    step_num        =  dbo.IS_EMPTY( ?),  ");
    	query.append("    check_point     = ?,  ");
    	query.append("    check_method    = ?,  ");
    	query.append("    fit_basis       = ?,  ");
    	query.append("    check_type      = ?,  ");
    	query.append("    check_min       = dbo.IS_EMPTY( ?),  ");
    	query.append("    check_basis_val = dbo.IS_EMPTY( ?),  ");
    	query.append("    check_max       = dbo.IS_EMPTY( ?),  ");
    	query.append("    uom             = ?,  ");
    	query.append("    is_active       = ?,  ");
    	query.append("    remark          = ?,  ");
    	query.append("    schedule_type   = ?,  ");
    	query.append("    cycle           = dbo.IS_EMPTY( ?),  ");
    	query.append("    period_type     = ?,  ");
    	query.append("    usage           = dbo.IS_EMPTY( ?)  ");
    	query.append("	, work_time		  = ? 	");
    	query.append("	, ord_no		  = ? 	");
    	query.append("WHERE stwrk_point_id= ?   ");
    	query.append("  AND comp_no       = ?   ");
    	
    	objects = new Object[] {
    			maStdPointDetailDTO.getStepNum(),
    			maStdPointDetailDTO.getCheckPoint(),
    			maStdPointDetailDTO.getCheckMethod(),
    			maStdPointDetailDTO.getFitBasis(),
    			maStdPointDetailDTO.getCheckType(),
    	        maStdPointDetailDTO.getCheckMin(),
    	        maStdPointDetailDTO.getCheckBasisVal(),
    	        maStdPointDetailDTO.getCheckMax(),
    	        maStdPointDetailDTO.getUom(),
    	        maStdPointDetailDTO.getIsActive(),
    	        maStdPointDetailDTO.getRemark(),
    	        maStdPointDetailDTO.getScheduleType(),
    	        maStdPointDetailDTO.getCycle(),
    	        maStdPointDetailDTO.getPeriodType(),
    	        maStdPointDetailDTO.getUsage(),
    	        maStdPointDetailDTO.getPredTime(),
    	        maStdPointDetailDTO.getOrdNo(),
    	        maStdPointDetailDTO.getStWrkPointId(),
    	        loginUser.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}