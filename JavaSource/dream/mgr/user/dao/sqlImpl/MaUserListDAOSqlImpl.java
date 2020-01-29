package dream.mgr.user.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.QuerySqlBuffer;
import dream.mgr.user.dao.MaUserListDAO;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MaUserListDTO;

/**
 * 사용자 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maUserListDAOTarget"
 * @spring.txbn id="maUserListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaUserListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaUserListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserCommonDTO
     * @param user
     * @return List
     */
    public List findUserList(MaUserCommonDTO maUserCommonDTO, User loginUser)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer(); 
        
        query.append("SELECT ''                                     				   seqNo      ");
        query.append("      ,''                                     				   isDelCheck ");
        query.append("      ,x.comp_no                              				   compNo     ");
        query.append("      ,x.user_id                              				   userId     ");
        query.append("      ,x.user_no                              				   userNo     "); 
        query.append("      ,x.user_name                            				   userName   "); 
        query.append("      ,dbo.SFAIDTODESC(y.dept_id, '', 'DEPT', x.comp_no)         deptDesc   "); 
        query.append("      ,x.emp_id                                                  empId      ");
        query.append("      ,y.emp_no                             					   empNo      "); 
        query.append("      ,y.emp_name                             				   empName    "); 
        query.append("      ,y.grade                                				   grade      "); 
        query.append("      ,x.menu_display                                            menuDisplayId   ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.menu_display, 'MENU_DISPLAY', 'SYS', x.comp_no,'"+loginUser.getLangId()+"') menuDisplayDesc ");
        query.append("      ,x.usrgrp_id                                               usrGrpId        ");
        query.append("      ,dbo.SFAIDTODESC(x.usrgrp_id, '', 'USRGRP', x.comp_no)     usrGrpName      ");
        query.append("      ,x.init_menu_id                                            initMenuId      ");
        query.append("      ,(SELECT  (SELECT aa.key_name                                              ");
        query.append("                 FROM TALANG aa                                                  ");
        query.append("                 WHERE aa.key_no = a.key_no                                      ");
        query.getStringEqualQuery("aa.key_type", "MENU");
        query.getStringEqualQuery("aa.lang", loginUser.getLangId());
        query.append("                 )                                                               ");
        query.append("        FROM TAMENU a                                                            ");
        query.append("        WHERE a.menu_id=init_menu_id )                           initMenuDesc    ");
        query.append("      ,x.m_phone                                                 mphone          ");
        query.append("       ,x.e_mail                                                 email           ");
        query.append("      ,x.is_use                                                  isUse           ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no,'"+loginUser.getLangId()+"')             isUseDesc       ");
        query.append("      ,x.is_monitor                                              isMonitor       ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.is_monitor, 'IS_USE', 'SYS', x.comp_no,'"+loginUser.getLangId()+"')             isMonitorDesc       ");
        query.append("      ,x.eqctg_type                                              eqCtgTypeId     ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.eqctg_type, 'EQCTG_TYPE', 'SYS', x.comp_no,'"+loginUser.getLangId()+"')     eqCtgTypeDesc   ");
        query.append("      ,x.shift_type                                              shiftTypeId     ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.shift_type, 'SHIFT_TYPE', 'SYS', x.comp_no,'"+loginUser.getLangId()+"')     shiftTypeDesc   ");
        query.append("      ,x.reg_date                                                regDate         ");
        query.append("      ,x.login_date                                              loginDate       ");
        query.append("      ,x.eqloc_id                                                eqLocId         ");
        query.append("      ,(SELECT aa.full_desc FROM TAEQLOC aa                                      ");
        query.append("        WHERE aa.eqloc_id = x.eqloc_id                                           ");
        query.append("         AND aa.comp_no = x.comp_no                                              ");
        query.append("       )                                                         eqLocDesc       ");
        query.append("      ,x.filter_dept_id                                          filterDeptId    ");
        query.append("      ,dbo.SFAIDTODESC(x.filter_dept_id, '', 'DEPT', x.comp_no)  filterDeptDesc  ");
        query.append("      ,x.filter_wcode_id                                         filterWcodeId   ");
        query.append("      ,(SELECT A.wname                                                           ");
        query.append("        FROM TAWAREHOUSE A                                                       ");
        query.append("        WHERE A.comp_no = x.comp_no                                              ");
        query.append("         AND A.wcode_id = x.filter_wcode_id)                     filterWcodeDesc ");
        query.append("      ,x.filter_wkctr_id                                         filterWkCtrId   ");
        query.append("      ,(SELECT description                                                       ");
        query.append("        FROM TAWKCTR                                                             ");
        query.append("        WHERE comp_no = x.comp_no                                                ");
        query.append("         AND wkctr_id = x.filter_wkctr_id)                       filterWkCtrDesc ");
        query.append("      ,x.filter_emp_id                                           filterEmpId     ");
        query.append("      ,(SELECT a.emp_name                                                        ");
        query.append("        FROM TAEMP a                                                             ");
        query.append("        WHERE a.comp_no = x.comp_no                                              ");
        query.append("         AND a.emp_id = x.filter_emp_id)                         filterEmpDesc   ");
        query.append("      ,x.secur_grade                                              securGradeId    ");
        query.append("      ,(select                                                                    ");
        query.append("       ISNULL((select bb.key_name                                                 ");
        query.append("            from talang bb                                                        ");
        query.append("            where  bb.lang = '"+loginUser.getLangId()+"'                          ");
        query.append("            and aa.key_type = bb.key_type                                         ");
        query.append("            and aa.key_no = bb.key_no), aa.description)                           ");
        query.append("       description                                                                ");
        query.append("      FROM TACDSYSD aa                                                            ");
        query.append("        where aa.list_type = 'SECUR_GRADE'                                        ");
        query.append("                  and aa.cdsysd_no = x.secur_grade                                ");
        query.append("      )                                                           securGradeDesc  ");
        query.append("      ,x.reset_password                                           resetPassword   ");
        query.append("      ,x.otp_key                                                  otpKey          ");
        query.append("      ,x.is_locked                                                isLocked        ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.is_locked, 'IS_USE', 'SYS', x.comp_no,'"+loginUser.getLangId()+"')           isLockedDesc        ");
        query.append("      ,x.filter_usage_dept_id                                         filterUsageDeptId   ");
        query.append("      ,dbo.SFAIDTODESC(x.filter_usage_dept_id, '', 'DEPT', x.comp_no) filterUsageDeptDesc ");
        query.append("      ,x.bee_init_menu_id                                            beeInitMenuId       ");
        query.append("      ,(SELECT  (SELECT aa.key_name                                              ");
        query.append("                 FROM TALANG aa                                                  ");
        query.append("                 WHERE aa.key_no = a.key_no                                      ");
        query.getStringEqualQuery("aa.key_type", "MENU");
        query.getStringEqualQuery("aa.lang", loginUser.getLangId());
        query.append("                 )                                                               ");
        query.append("        FROM TAMENU a                                                            ");
        query.append("        WHERE a.menu_id=x.bee_init_menu_id )                         beeInitMenuDesc     ");
        query.append("      ,x.is_direct                                                   isDirect    ");
        query.append("      ,dbo.SFACODE_TO_DESC(x.is_direct, 'IS_USE', 'SYS', x.comp_no,'"+loginUser.getLangId() + "')     isDirectDesc            ");
        query.append("      ,x.vendor_id                                                   vendorId    ");
        query.append("      ,(SELECT a.description                                                     ");
        query.append("        FROM TAVENDOR a                                                          ");
        query.append("        WHERE a.comp_no = x.comp_no                                              ");
        query.append("         AND a.vendor_id = x.vendor_id)                              vendorDesc  ");
        query.append("      ,x.filter_vendor_id                                            filterVendorId  ");
        query.append("      ,(SELECT a.description                                                     ");
        query.append("        FROM TAVENDOR a                                                          ");
        query.append("        WHERE a.comp_no = x.comp_no                                              ");
        query.append("         AND a.vendor_id = x.filter_vendor_id)                       filterVendorDesc ");
        query.append("FROM   TAUSER x LEFT OUTER JOIN TAEMP y                                  ");
        query.append("ON  x.emp_id  = y.emp_id                                  			   "); 
        query.append("WHERE 1=1                           									   "); 
        query.append(this.getWhere(maUserCommonDTO,loginUser));
        query.getOrderByQuery("x.user_id","user_no", maUserCommonDTO.getOrderBy(), maUserCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(maUserCommonDTO.getIsLoadMaxCount(), maUserCommonDTO.getFirstRow()));
    } 
    public String findTotalCount(MaUserCommonDTO maUserCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;		");
        query.append("SELECT                    							");
        query.append("      count(1)            							");
        query.append("FROM   TAUSER x LEFT OUTER JOIN TAEMP y               ");
        query.append("ON  x.emp_id  = y.emp_id                              "); 
        query.append("WHERE 1=1                           					"); 
        query.append(this.getWhere(maUserCommonDTO,loginUser));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    public int updateUsers(Map map, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAUSER SET             								");
        query.append("       password        = ?    								");
        query.append("       ,change_pwd_date  = CONVERT(VARCHAR, GETDATE(), 112)	");
        query.append("WHERE  1 = 1     												");
        query.append("  AND  user_id        = ?     								");
        query.append("  AND  comp_no        = ?     								");
        
        Object[] objects = new Object[] {
        		CommonUtil.aesEncodeString(String.valueOf(map.get("userNo")))
                ,String.valueOf(map.get("userId"))
                ,String.valueOf(map.get("compNo"))
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * Filter 조건
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maUserCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaUserCommonDTO maUserCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.getAndQuery("x.comp_no", maUserCommonDTO.getFilterCompNo()); 
        if (!"".equals(maUserCommonDTO.getUserId()))
        {
            query.getAndQuery("x.user_id", maUserCommonDTO.getUserId());
            return query.toString();
        }
        
        // 권한 
        if(!"".equals(maUserCommonDTO.getFilterUsrGrpId()))
        {
            query.getAndQuery("x.usrgrp_id", maUserCommonDTO.getFilterUsrGrpId());
        }
        else if(!"".equals(maUserCommonDTO.getFilterUsrGrpName()))
        {
            query.append("AND x.usrgrp_id IN (SELECT a.usrgrp_id                ");
            query.append("                    FROM   TAUSRGRP a                 ");
            query.append("                    WHERE  1=1                        ");
            query.getLikeQuery("a.group_name", maUserCommonDTO.getFilterUsrGrpName());
            query.append("                    )                                 ");
        }

        // 계정명
        query.getLikeQuery("x.user_name",  maUserCommonDTO.getFilterUserName());
        
        // 부서
        query.getDeptLevelQuery("y.dept_id", maUserCommonDTO.getFilterDeptId(), maUserCommonDTO.getFilterDeptDesc(), maUserCommonDTO.getFilterCompNo());
        //관리 설비우형
        query.getSysCdQuery("x.eqctg_type", maUserCommonDTO.getFilterEqCtgTypeId(), maUserCommonDTO.getFilterEqCtgTypeDesc(), "EQCTG_TYPE", maUserCommonDTO.getFilterCompNo(),user.getLangId());
        // 로그인계정
        query.getLikeQuery("x.user_no",  maUserCommonDTO.getFilterUserNo());
        // 사용여부
        query.getSysCdQuery("x.is_use", maUserCommonDTO.getFilterIsUse(), maUserCommonDTO.getFilterIsUseDesc(), "IS_USE", maUserCommonDTO.getFilterCompNo(),user.getLangId());
        // 잠김여부
        query.getSysCdQuery("x.is_locked", maUserCommonDTO.getFilterIsLocked(), maUserCommonDTO.getFilterIsLockedDesc(), "IS_USE", maUserCommonDTO.getFilterCompNo(),user.getLangId());
        //공장코드
        query.getCodeLikeQuery("y.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = y.plant )", 
        		maUserCommonDTO.getFilterPlantId(), maUserCommonDTO.getFilterPlantDesc());
        // 모니터링대상 여부
        query.getSysCdQuery("x.is_monitor", maUserCommonDTO.getFilterIsMonitor(), maUserCommonDTO.getFilterIsMonitor(), "IS_USE", maUserCommonDTO.getFilterCompNo(),user.getLangId());
        
        // 직영여부
        query.getAndQuery("x.is_direct", maUserCommonDTO.getFilterIsDirectDesc());
        //거래처
        query.getCodeLikeQuery("x.vendor_id", "(SELECT a.description FROM  tavendor a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.vendor_id = x.vendor_id )", 
        		maUserCommonDTO.getFilterVendorId(), maUserCommonDTO.getFilterVendorDesc());
        
        return query.toString();
    }
    
    /**
     * 사용자 삭제
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param userId
     * @return
     */
    public int deleteUser(MaUserListDTO maUserListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE TAUSER                            ");
        query.append("WHERE  comp_no  = ?                      ");
        query.append("  AND  user_id  = ?                      ");

        Object[] objects = new Object[] {   
                loginUser.getCompNo()
                ,maUserListDTO.getUserId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}