package dream.mgr.user.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.mgr.user.dao.MaUserDetailDAO;
import dream.mgr.user.dto.MaUserDetailDTO;

/**
 * 사용자 - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maUserDetailDAOTarget"
 * @spring.txbn id="maUserDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaUserDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaUserDetailDAO
{
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserDetailDTO
     * @return
     */
    public int insertDetail(MaUserDetailDTO maUserDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAUSER(					                    							");
    	query.append("    comp_no    		, user_id   			, user_no       		, user_name     ");
    	query.append("	, password   		, usrgrp_id 			, init_menu_id  		, emp_id        ");
    	query.append("	, m_phone    		, e_mail    			, is_use        		, reg_date      ");
    	query.append("	, is_monitor 		, shift_type 			, eqctg_type   			, menu_display	");
    	query.append("	, eqloc_id   		, filter_dept_id		, filter_wcode_id						");
    	query.append("	, filter_wkctr_id	, filter_emp_id			, secur_grade							");
    	query.append("	, reset_password	, otp_key               , is_locked								");
    	query.append("	, filter_usage_dept_id				   		, bee_init_menu_id						");
    	query.append("  , is_direct   		, vendor_id        		, filter_vendor_id      , alarm_view_yn ");
    	query.append(")VALUES(							                        							");
    	query.append("	  ?			        , ?		                , ?	                    , ?             ");
    	query.append("	, ?			        , ?		                , ?	                    , ?             ");
    	query.append("	, ?			        , ?		                , ?                     , TO_CHAR(SYSDATE, 'YYYYMMDD') ");
    	query.append("	, ?			        , ?			            , ?		                , ?				");
    	query.append("	, ?			        , ?			            , ?										");
    	query.append("	, ?			        , ?			            , ?										");
    	query.append("	, ?			        , ?                		, ?										");
    	query.append("	, ?									   		, ?			       						");
    	query.append("	, ?			        , ?			            , ?						, ?				");
    	query.append(")													        							");
    	
    	
    	Object[] objects = new Object[] {
    			maUserDetailDTO.getCompNo()
    			, maUserDetailDTO.getUserId()
    			, maUserDetailDTO.getUserNo()
    			, maUserDetailDTO.getUserName()
    			// 초기 password 세팅 
    			, CommonUtil.aesEncodeString(maUserDetailDTO.getPassword())
    			, maUserDetailDTO.getUsrGrpId()
    			, maUserDetailDTO.getInitMenuId()
    			, maUserDetailDTO.getEmpId()
    			, maUserDetailDTO.getMphone()
    			, maUserDetailDTO.getEmail()
    			, maUserDetailDTO.getIsUse()
    			, maUserDetailDTO.getIsMonitor()
    			, maUserDetailDTO.getShiftTypeId()
    			, maUserDetailDTO.getEqCtgTypeId()
    			, maUserDetailDTO.getMenuDisplayId()
    			, maUserDetailDTO.getEqLocId()
    			, maUserDetailDTO.getFilterDeptId()
    			, maUserDetailDTO.getFilterWcodeId()
    			, maUserDetailDTO.getFilterWkCtrId()
    			, maUserDetailDTO.getFilterEmpId()
    			, maUserDetailDTO.getSecurGradeId()
    			, maUserDetailDTO.getResetPassword()
    			, maUserDetailDTO.getOtpKey()
    			, maUserDetailDTO.getIsLocked()
    			, maUserDetailDTO.getFilterUsageDeptId()
    			, maUserDetailDTO.getBeeInitMenuId()
    			, maUserDetailDTO.getIsDirect()
    			, maUserDetailDTO.getVendorId()
    			, maUserDetailDTO.getFilterVendorId()
    			, maUserDetailDTO.getAlarmViewYn()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserDetailDTO
     * @return
     */
    public int updateDetail(MaUserDetailDTO maUserDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAUSER SET	            	");
    	query.append("	     user_no        	= ?  	");
    	query.append("	   , emp_id         	= ?  	");
    	query.append("	   , user_name	    	= ?  	");
    	query.append("	   , m_phone	    	= ?  	");
    	query.append("	   , usrgrp_id      	= ?  	");
    	query.append("	   , e_mail	        	= ?  	");
    	query.append("	   , init_menu_id		= ?  	");
    	query.append("	   , is_monitor			= ?  	");
    	query.append("	   , shift_type			= ?  	");
    	query.append("	   , eqctg_type			= ?  	");
    	query.append("	   , is_use        		= ?   	");
    	query.append("	   , menu_display   	= ?   	");
    	query.append("	   , eqloc_id   		= ?   	");
    	query.append("	   , filter_dept_id		= ?   	");
    	query.append("	   , filter_wcode_id	= ?  	");
    	query.append("	   , filter_wkctr_id	= ?   	");
    	query.append("	   , filter_emp_id   	= ?   	");
    	query.append("	   , secur_grade	   	= ?   	");
    	query.append("	   , otp_key    	   	= NVL(otp_key,?) ");
    	query.append("     , is_locked          = ? 	");
    	query.append("     , login_fail_cnt     = CASE WHEN 'N' = ? THEN 0 ELSE login_fail_cnt END ");
    	query.append("	   , filter_usage_dept_id	= ?	");
    	query.append("	   , bee_init_menu_id	= ?  	");
    	query.append("	   , is_direct			= ? 	");
    	query.append("	   , vendor_id			= ? 	");
    	query.append("	   , filter_vendor_id	= ? 	");
    	query.append("	   , alarm_view_yn	    = ? 	");
    	query.append("WHERE  comp_no        	= ?	    ");
    	query.append("  AND  user_id        	= ?     ");
    	
    	Object[] objects = new Object[] {
    			maUserDetailDTO.getUserNo()
    			,maUserDetailDTO.getEmpId()
    			,maUserDetailDTO.getUserName()
    			,maUserDetailDTO.getMphone()
    			,maUserDetailDTO.getUsrGrpId()
    			,maUserDetailDTO.getEmail()
    			,maUserDetailDTO.getInitMenuId()
    			,maUserDetailDTO.getIsMonitor()
    			,maUserDetailDTO.getShiftTypeId()
    			,maUserDetailDTO.getEqCtgTypeId()
    			,maUserDetailDTO.getIsUse()
    			,maUserDetailDTO.getMenuDisplayId()
    			,maUserDetailDTO.getEqLocId()
    			,maUserDetailDTO.getFilterDeptId()
    			,maUserDetailDTO.getFilterWcodeId()
    			,maUserDetailDTO.getFilterWkCtrId()
    			,maUserDetailDTO.getFilterEmpId()
    			,maUserDetailDTO.getSecurGradeId()
    			,maUserDetailDTO.getOtpKey()
    			,maUserDetailDTO.getIsLocked()
    			,maUserDetailDTO.getIsLocked()
    			,maUserDetailDTO.getFilterUsageDeptId()
    			,maUserDetailDTO.getBeeInitMenuId()
    			,maUserDetailDTO.getIsDirect()
    			,maUserDetailDTO.getVendorId()
    			,maUserDetailDTO.getFilterVendorId()
    			,maUserDetailDTO.getAlarmViewYn()
    			,maUserDetailDTO.getCompNo()
    			,maUserDetailDTO.getUserId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * valid userId
     * @author 
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maUserDetailDTO
     * @return
     */
    public String validUserNo(MaUserDetailDTO maUserDetailDTO, User loginUser)
    {
        
    	QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)                        ");
        query.append("FROM   TAUSER x                        ");
        query.append("WHERE  1=1                             ");
        query.getAndQuery("x.comp_no", maUserDetailDTO.getCompNo());
        query.getAndQuery("UPPER(x.user_no)", maUserDetailDTO.getUserNo().toUpperCase());
        query.getInequalityQuery("x.user_id", "!=", maUserDetailDTO.getUserId());
     
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    
    }
    @Override
    public void updateRpw(MaUserDetailDTO maUserDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE TAUSER SET                 ");
        query.append("       reset_password     = ?     ");
        query.append("       ,password          = ?     ");
        query.append("WHERE  comp_no            = ?     ");
        query.append("  AND  user_id            = ?     ");
        
        Object[] objects = new Object[] {
                maUserDetailDTO.getResetPassword()
                ,CommonUtil.aesEncodeString(maUserDetailDTO.getResetPassword())
                ,maUserDetailDTO.getCompNo()
                ,maUserDetailDTO.getUserId()
        };
        
        getJdbcTemplate().update(query.toString(), objects);
    }
}