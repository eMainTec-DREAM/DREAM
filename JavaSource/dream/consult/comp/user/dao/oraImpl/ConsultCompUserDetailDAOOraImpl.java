package dream.consult.comp.user.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.consult.comp.user.dao.ConsultCompUserDetailDAO;
import dream.consult.comp.user.dto.ConsultCompUserCommonDTO;
import dream.consult.comp.user.dto.ConsultCompUserDetailDTO;

/**
 * CompUser Page - Detail DAO implements
 * @author youngjoo38
 * @version $Id: ConsultCompUserDetailDAOOraImpl.java,v 1.0 2017/08/10 09:12:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="consultCompUserDetailDAOTarget"
 * @spring.txbn id="consultCompUserDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompUserDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultCompUserDetailDAO
{

    public ConsultCompUserDetailDTO findCompUserDetail(ConsultCompUserCommonDTO consultCompUserCommonDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                           ");
        query.append("      x.user_id                               AS userId          ");
        query.append("    , x.comp_no                               AS compNo          ");
        query.append("    , (SELECT a.description                                      ");
        query.append("       FROM TACOMP a                                             ");
        query.append("       WHERE a.comp_no = x.comp_no)           AS compDesc        ");
        query.append("    , x.user_no                               AS userNo          ");
        query.append("    , x.user_name                             AS userName        ");
        query.append("    , x.emp_id                                AS empId           ");
        query.append("    , y.emp_name                              AS empName         ");
        query.append("    , x.usrgrp_id                             AS usrGrpId        ");
        query.append("    , (SELECT a.GROUP_NAME                                       ");
        query.append("       FROM TAUSRGRP a                                           ");
        query.append("       WHERE a.comp_no = x.comp_no                               ");
        query.append("           AND a.usrgrp_id = x.usrgrp_id)     AS usrGrpDesc      ");
        query.append("    , x.m_phone                               AS phone           ");
        query.append("    , x.init_menu_id                          AS initMenuId      ");
        query.append("    , (SELECT a.description                                      ");
        query.append("       FROM TAMENU a                                             ");
        query.append("       WHERE a.menu_id = x.init_menu_id )     AS initMenuDesc    ");
        query.append("    , y.e_mail                                AS eMail           ");
        query.append("    , x.shift_type                            AS shiftTypeId     ");
        query.append("    , SFACODE_TO_DESC(x.shift_type, 'SHIFT_TYPE', 'SYS', x.comp_no,?)		AS shiftTypeDesc	");
        query.append("    , x.is_use                                AS isUseId         ");
        query.append("    , SFACODE_TO_DESC( x.is_use, 'IS_USE','SYS',x.comp_no, ?)             AS isUseDesc        ");
        query.append("    , x.eqloc_id                              AS eqLocId         ");
        query.append("    , (SELECT a.full_desc                                        ");
        query.append("       FROM TAEQLOC a                                            ");
        query.append("       WHERE a.eqloc_id = x.eqloc_id)         AS eqLocDesc       ");
        query.append("    , x.alarm_view_yn                         AS alarmViewYnId   ");
        query.append("    , SFACODE_TO_DESC( x.alarm_view_yn , 'IS_USE','SYS',x.comp_no, ?)     AS alarmViewYn      ");
        query.append("    , y.wkctr_id                              AS wkctrId         ");
        query.append("    , (SELECT a.description                                      ");
        query.append("       FROM TAWKCTR a                                            ");
        query.append("       WHERE y.comp_no = a.comp_no                               ");
        query.append("           AND y.wkctr_id = a.wkctr_id)       AS wkctrDesc       ");
        query.append("    , x.eqctg_type                            AS eqCtgTypeId     ");
        query.append("    , SFACODE_TO_DESC(x.eqctg_type, 'EQCTG_TYPE', 'SYS', x.comp_no, ?)    AS eqCtgTypeDesc    ");
        query.append("    , x.FILTER_DEPT_ID                        AS deptId          ");
        query.append("    , (SELECT a.description                                      ");
        query.append("       FROM TADEPT a                                             ");
        query.append("       WHERE x.filter_dept_id = a.dept_id )   AS deptDesc        ");
        query.append("    , x.menu_display                          AS menuDisplayId   ");
        query.append("    , SFACODE_TO_DESC( x.menu_display, 'MENU_DISPLAY','SYS',x.comp_no, ?) AS menuDisplayDesc  ");
        query.append("FROM TAUSER x JOIN TAEMP y                                       ");
        query.append("ON  x.comp_no = y.comp_no                                        ");
        query.append("AND x.emp_id = y.emp_id                                          ");
        query.append("WHERE  1=1                                                       ");
        query.append("AND    x.comp_no    = ?                                          ");
        query.append("AND    x.user_id    = ?                                          ");
        
        Object[] objects = new Object[] {
                user.getLangId()
                ,user.getLangId()
                ,user.getLangId()
                ,user.getLangId()
                ,user.getLangId()
                ,consultCompUserCommonDTO.getCompNo()
                ,consultCompUserCommonDTO.getUserId()
        };
    
        ConsultCompUserDetailDTO consultCompUserDetailDTO = 
                (ConsultCompUserDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new ConsultCompUserDetailDTO()));
        
        return consultCompUserDetailDTO;
        
    }
    

    public int insertCompUserDetail(ConsultCompUserDetailDTO consultCompUserDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;

        query.append("INSERT INTO TAUSER(       ");
        query.append("  comp_no                 ");
        query.append("  ,user_id                ");
        query.append("  ,user_no                ");
        query.append("  ,password               ");
        query.append("  ,user_name              ");
        query.append("  ,usrgrp_id              ");
        query.append("  ,init_menu_id           ");
        query.append("  ,emp_id                 ");
        query.append("  ,m_phone                ");
        query.append("  ,e_mail                 ");
        query.append("  ,is_use                 ");
        query.append("  ,reg_date               ");
        query.append("  ,shift_type             ");
        query.append("  ,eqloc_id               ");
        query.append("  ,alarm_view_yn          ");
        query.append("  ,eqctg_type             ");
        query.append("  ,filter_dept_id         ");
        query.append("  ,menu_display           ");
        query.append("  ,change_pwd_date        ");
        query.append(")VALUES(                  ");
        query.append("  ?                       ");
        query.append("  ,?                      ");
        query.append("  ,?                      ");
        query.append("  ,?                      ");
        query.append("  ,?                      ");
        query.append("  ,?                      ");
        query.append("  ,?                      ");
        query.append("  ,?                      ");
        query.append("  ,?                      ");
        query.append("  ,?                      ");
        query.append("  ,?                      ");
        query.append("  ,?                      ");
        query.append("  ,?                      ");
        query.append("  ,?                      ");
        query.append("  ,?                      ");
        query.append("  ,?                      ");
        query.append("  ,?                      ");
        query.append("  ,?                      ");
        query.append("  ,?                      ");
        query.append(")                         ");
        
        Object[] objects = new Object[] {
                consultCompUserDetailDTO.getCompNo()
                ,consultCompUserDetailDTO.getUserId()
                ,consultCompUserDetailDTO.getUserNo()
                // 초기 password 세팅 
                ,CommonUtil.aesEncodeString(consultCompUserDetailDTO.getPassWord())
                ,consultCompUserDetailDTO.getUserName()
                ,consultCompUserDetailDTO.getUsrGrpId()
                ,consultCompUserDetailDTO.getInitMenuId()
                ,consultCompUserDetailDTO.getEmpId()
                ,consultCompUserDetailDTO.getPhone()
                ,consultCompUserDetailDTO.getEmail()
                ,consultCompUserDetailDTO.getIsUseId()
                ,consultCompUserDetailDTO.getRegDate()
                ,consultCompUserDetailDTO.getShiftTypeDesc()
                ,consultCompUserDetailDTO.getEqLocId()
                ,consultCompUserDetailDTO.getAlarmViewYnId()
                ,consultCompUserDetailDTO.getEqCtgTypeId()
                ,consultCompUserDetailDTO.getDeptId()
                ,consultCompUserDetailDTO.getMenuDisplayId()
                ,consultCompUserDetailDTO.getRegDate()

        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    
    
    public int updateCompUserDetail(ConsultCompUserDetailDTO consultCompUserDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        int rtnValue  = 0;

        query.append("UPDATE TAUSER SET                     ");
        query.append("  user_name           = ?             ");
        query.append("  ,usrgrp_id          = ?             ");
        query.append("  ,user_no            = ?             ");
        query.append("  ,init_menu_id       = ?             ");
        query.append("  ,emp_id             = ?             ");
        query.append("  ,m_phone            = ?             ");
        query.append("  ,e_mail             = ?             ");
        query.append("  ,is_use             = ?             ");
        query.append("  ,reg_date           = ?             ");
        query.append("  ,shift_type         = ?             ");
        query.append("  ,eqloc_id           = ?             ");
        query.append("  ,alarm_view_yn      = ?             ");
        query.append("  ,eqctg_type         = ?             ");
        query.append("  ,filter_dept_id     = ?             ");
        query.append("  ,menu_display       = ?             ");
        query.append("WHERE  comp_no        = ?             ");
        query.append("  AND  user_id        = ?             ");
        
        Object[] objects = new Object[] {
                consultCompUserDetailDTO.getUserName()
                ,consultCompUserDetailDTO.getUsrGrpId()
                ,consultCompUserDetailDTO.getUserNo()
                ,consultCompUserDetailDTO.getInitMenuId()
                ,consultCompUserDetailDTO.getEmpId()
                ,consultCompUserDetailDTO.getPhone()
                ,consultCompUserDetailDTO.getEmail()
                ,consultCompUserDetailDTO.getIsUseId()
                ,consultCompUserDetailDTO.getRegDate()
                ,consultCompUserDetailDTO.getShiftTypeId()
                ,consultCompUserDetailDTO.getEqLocId()
                ,consultCompUserDetailDTO.getAlarmViewYnId()
                ,consultCompUserDetailDTO.getEqCtgTypeId()
                ,consultCompUserDetailDTO.getDeptId()
                ,consultCompUserDetailDTO.getMenuDisplayId()
                ,consultCompUserDetailDTO.getCompNo()
                ,consultCompUserDetailDTO.getUserId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    public String valUserNo(ConsultCompUserCommonDTO consultCompUserCommonDTO, ConsultCompUserDetailDTO consultCompUserDetailDTO) 
    {
    	QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*)                         							  ");
        query.append("FROM   TAUSER x                        							  ");
        query.append("WHERE  x.comp_no  = '" + consultCompUserDetailDTO.getCompNo() + "'  ");
        query.append("  AND  UPPER(x.user_no) = '" + consultCompUserDetailDTO.getUserNo().toUpperCase() + "'   ");
        query.getInequalityQuery("x.user_id", "!=", consultCompUserDetailDTO.getUserId());
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
}
