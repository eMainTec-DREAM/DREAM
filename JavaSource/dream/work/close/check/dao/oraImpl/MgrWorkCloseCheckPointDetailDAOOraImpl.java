package dream.work.close.check.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.close.check.dao.MgrWorkCloseCheckPointDetailDAO;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckPointDetailDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckPointListDTO;

/**
 * 표준항목 리스트 - 상세 dao
 * 
 * @author kim21017
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="mgrWorkCloseCheckPointDetailDAOTarget"
 * @spring.txbn id="mgrWorkCloseCheckPointDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrWorkCloseCheckPointDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrWorkCloseCheckPointDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0 
     * 
     * @param mgrWorkCloseCheckCommonDTO
     * @param loginUser
     * @return
     */
    public MgrWorkCloseCheckPointDetailDTO findDetail(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, MgrWorkCloseCheckPointListDTO mgrWorkCloseCheckPointListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        String compNo=  loginUser.getCompNo();
        
        query.append("SELECT																		");
        query.append("		x.stwrk_point_id stWrkPointId,											");
        query.append("		x.step_num stepNum,														");
        query.append("		x.check_point CHECKPOINT,												");
        query.append("		x.check_method checkMethod,												");
        query.append("		x.fit_basis fitBasis,													");
        query.append("		SFACODE_TO_DESC(x.check_type,'CHECK_TYPE','SYS','','"+loginUser.getLangId()+"') checkTypeDesc,		");
        query.append("      x.check_type checkType,                                                 ");
        query.append("      x.fit_basis fitBasis,                                                   ");
        query.append("      x.check_min checkMin,                                                   ");
        query.append("      x.check_max checkMax,                                                   ");
        query.append("      x.uom,                                                                  ");
        query.append("      x.check_basis_val checkBasisVal,                                        ");
        query.append("      x.is_active isActive,                                                   ");
        query.append("		x.remark remark,														");
        query.append("		x.schedule_type scheduleType,											");
        query.append("      SFACODE_TO_DESC(x.schedule_type, 'SCHEDULE_TYPE', 'SYS', '"+compNo+"','"+loginUser.getLangId()+"') scheduleTypeDesc,    ");
        query.append("      x.cycle,                                                      			");
        query.append("      x.period_type 								periodType,      			");
        query.append("      SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE', 'SYS', '"+compNo+"','"+loginUser.getLangId()+"') periodTypeDesc,          ");
        query.append("      x.USAGE                                                      			");
        query.append("		, x.work_time								predTime					");
        query.append("		, x.ord_no									ordNo						");
        query.append("FROM   TASTWRKPOINT x															");
        query.append("WHERE  1=1																	");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.stwrk_id", mgrWorkCloseCheckCommonDTO.getStwrkId());
        query.getAndQuery("x.stwrk_point_id", mgrWorkCloseCheckPointListDTO.getStWrkPointId());
    
        MgrWorkCloseCheckPointDetailDTO mgrWorkCloseCheckPointDetailDTO = 
        		(MgrWorkCloseCheckPointDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MgrWorkCloseCheckPointDetailDTO()));
        
        return mgrWorkCloseCheckPointDetailDTO;
    }
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrWorkCloseCheckPointDetailDTO
     * @return
     */
    public int insertDetail(MgrWorkCloseCheckPointDetailDTO mgrWorkCloseCheckPointDetailDTO, MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

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
    	query.append("     usage			 ,work_time					   ");
    	query.append("    )    VALUES                                      ");
    	query.append("    (?,                ?,                            ");
    	query.append("     ?,                ?,                            ");
    	query.append("     ?,                ?,                            ");
    	query.append("     ?,                ?,                            ");
    	query.append("     ?,                ?,                            ");
    	query.append("     ?,                ?,                            ");
    	query.append("     ?,                ?,                            ");
    	query.append("     ?,                ?,                            ");
    	query.append("     ?,                ?,                            ");
    	query.append("     ?        		,?			   				   ");
    	query.append("    )                                                ");
    	
    	Object[] objects = new Object[] {
    			loginUser.getCompNo(),
    			mgrWorkCloseCheckPointDetailDTO.getStWrkPointId(),
    	        mgrWorkCloseCheckPointDetailDTO.getStepNum(),
    	        mgrWorkCloseCheckPointDetailDTO.getCheckPoint(),
    	        mgrWorkCloseCheckPointDetailDTO.getCheckMethod(),
    	        mgrWorkCloseCheckPointDetailDTO.getFitBasis(),
    	        mgrWorkCloseCheckPointDetailDTO.getCheckType(),
    	        mgrWorkCloseCheckPointDetailDTO.getCheckMin(),
    	        mgrWorkCloseCheckPointDetailDTO.getCheckBasisVal(),
    	        mgrWorkCloseCheckPointDetailDTO.getCheckMax(),
    	        mgrWorkCloseCheckPointDetailDTO.getUom(),
    	        mgrWorkCloseCheckPointDetailDTO.getIsActive(),
    	        mgrWorkCloseCheckPointDetailDTO.getRemark(),
    	        mgrWorkCloseCheckCommonDTO.getStwrkId(),
    	        mgrWorkCloseCheckPointDetailDTO.getScheduleType(),
    	        mgrWorkCloseCheckPointDetailDTO.getOrdNo(),
    	        mgrWorkCloseCheckPointDetailDTO.getCycle(),
    	        mgrWorkCloseCheckPointDetailDTO.getPeriodType(),
    	        mgrWorkCloseCheckPointDetailDTO.getUsage()
    	        ,mgrWorkCloseCheckPointDetailDTO.getPredTime()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrWorkCloseCheckPointDetailDTO
     * @return
     */
    public int updateDetail(MgrWorkCloseCheckPointDetailDTO mgrWorkCloseCheckPointDetailDTO,MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	Object[] objects;

    	query.append("UPDATE TASTWRKPOINT  SET  ");
    	query.append("    step_num        = ?,  ");
    	query.append("    check_point     = ?,  ");
    	query.append("    check_method    = ?,  ");
    	query.append("    fit_basis       = ?,  ");
    	query.append("    check_type      = ?,  ");
    	query.append("    check_min       = ?,  ");
    	query.append("    check_basis_val = ?,  ");
    	query.append("    check_max       = ?,  ");
    	query.append("    uom             = ?,  ");
    	query.append("    is_active       = ?,  ");
    	query.append("    remark          = ?,  ");
    	query.append("    schedule_type   = ?,  ");
    	query.append("    cycle           = ?,  ");
    	query.append("    period_type     = ?,  ");
    	query.append("    usage           = ?   ");
    	query.append("    ,work_time      = ?   ");
    	query.append("    ,ord_no	      = ?   ");
    	query.append("WHERE stwrk_point_id= ?   ");
    	query.append("  AND comp_no       = ?   ");
    	
    	objects = new Object[] {
    			mgrWorkCloseCheckPointDetailDTO.getStepNum(),
    			mgrWorkCloseCheckPointDetailDTO.getCheckPoint(),
    			mgrWorkCloseCheckPointDetailDTO.getCheckMethod(),
    			mgrWorkCloseCheckPointDetailDTO.getFitBasis(),
    			mgrWorkCloseCheckPointDetailDTO.getCheckType(),
    	        mgrWorkCloseCheckPointDetailDTO.getCheckMin(),
    	        mgrWorkCloseCheckPointDetailDTO.getCheckBasisVal(),
    	        mgrWorkCloseCheckPointDetailDTO.getCheckMax(),
    	        mgrWorkCloseCheckPointDetailDTO.getUom(),
    	        mgrWorkCloseCheckPointDetailDTO.getIsActive(),
    	        mgrWorkCloseCheckPointDetailDTO.getRemark(),
    	        mgrWorkCloseCheckPointDetailDTO.getScheduleType(),
    	        mgrWorkCloseCheckPointDetailDTO.getCycle(),
    	        mgrWorkCloseCheckPointDetailDTO.getPeriodType(),
    	        mgrWorkCloseCheckPointDetailDTO.getUsage(),
    	        mgrWorkCloseCheckPointDetailDTO.getPredTime(),
    	        mgrWorkCloseCheckPointDetailDTO.getOrdNo(),
    	        mgrWorkCloseCheckPointDetailDTO.getStWrkPointId(),
    	        loginUser.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}