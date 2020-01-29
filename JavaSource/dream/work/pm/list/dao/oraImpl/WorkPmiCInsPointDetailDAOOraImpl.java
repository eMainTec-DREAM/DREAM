package dream.work.pm.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.pm.list.dao.WorkPmiCInsPointDetailDAO;
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiCInsPointDetailDTO;

/**
 * WorkPmiCInsPoint Page - Detail DAO implements
 * @author youngjoo38
 * @version $Id: WorkPmiCInsPointDetailDAOOraImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmiCInsPointDetailDAOTarget"
 * @spring.txbn id="workPmiCInsPointDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmiCInsPointDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkPmiCInsPointDetailDAO
{

    public WorkPmiCInsPointDetailDTO findDetail(WorkPmiCInsCommonDTO workPmiCInsCommonDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                            ");
        query.append("      ''                                              seqNo                       ");
        query.append("      , ''                                            isDelCheck                  ");
        query.append("      , y.step_num                                    stepNum                     ");
        query.append("      , CASE WHEN y.eqasmb_id IS NULL                                             ");
        query.append("           THEN y.eqasmb_desc                                                     ");
        query.append("           ELSE (SELECT NVL(a.full_desc, a.description)                        	");
        query.append("                   FROM TAEQASMB a                                                ");
        query.append("                  WHERE a.comp_no = x.comp_no                                     ");
        query.append("                    AND a.eqasmb_id = y.eqasmb_id)                                ");
        query.append("           END                                        eqAsmbDesc                  ");
        query.append("      , y.check_point                                 CHECKPOINT                  ");
        query.append("      , y.check_method                                checkMethod                 ");
        query.append("      , y.fit_basis                                   fitBasis                    ");
        query.append("      , SFACODE_TO_DESC(y.check_type,'CHECK_TYPE','SYS','', ?)   checkTypeDesc          ");
        query.append("      , x.pm_point_rslt_status                                   pmPointRsltStatusId                    ");
        query.append("      , SFACODE_TO_DESC(x.pm_point_rslt_status,'PM_POINT_RSLT_STATUS','SYS','', ?)   pmPointRsltStatus          ");
        query.append("      , y.check_min                                   checkMin                    ");
        query.append("      , y.check_basis_val                             checkBasisVal               ");
        query.append("      , y.check_max                                   checkMax                    ");
        query.append("      , y.uom                                         uom                         ");
        query.append("      , y.is_active                                   isActive                    ");
        query.append("      , x.pmequip_id                                  pmEquipId                   ");
        query.append("      , y.REMARK                                      REMARK                      ");
        query.append("      , x.REMARK                                      insDetail                   ");
        query.append("      , x.pm_point_id                                 pmPointId                   ");
        query.append("      , x.result_value                                resultValue                 ");
        query.append("      , x.result_value2                               resultValue2                ");
        query.append("      , x.result_value3                               resultValue3                ");
        query.append("      , x.pminsdpoint_id                              pmInsDPointId               ");
        query.append("      , x.pminsdlist_id                               pmInsDListId                ");
        query.append("      , y.check_min||' / '||y.check_basis_val||' / '||y.check_max||' . ('||y.uom||')' checkValUom ");
        query.append("  , y.stwrk_point_id          stwrkPointId    ");
        query.append("  , (SELECT a.check_point                     ");
        query.append("     FROM TASTDCHECKPOINT a                   ");
        query.append("     WHERE a.std_check_point_id = y.stwrk_point_id)        stwrkPointDesc          ");
        query.append("      , y.check_type									checkTypeId		          	");
        query.append("FROM TAPMINSDPOINT x  JOIN TAPMPOINT y                                            ");
        query.append("ON x.pm_point_id = y.pm_point_id                                                  ");
        query.append("WHERE 1=1                                                                         ");

        query.append("AND    x.comp_no    = ?                                                           ");
        query.append("AND    x.pminsdpoint_id    = ?                                                       ");
        
        Object[] objects = new Object[] {
                 user.getLangId()
               , user.getCompNo()
               , user.getCompNo()
               , workPmiCInsCommonDTO.getPmInsDPointId()
        };
    
        WorkPmiCInsPointDetailDTO workPmiCInsPointDetailDTO = 
                (WorkPmiCInsPointDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkPmiCInsPointDetailDTO()));
        
        return workPmiCInsPointDetailDTO;
        
    }
    

    public int insertDetail(WorkPmiCInsPointDetailDTO workPmiCInsPointDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        int rtnValue2  = 0;

        query.append("INSERT INTO TAPMPOINT                      ");
        query.append("    (eqasmb_id,                            ");
        query.append("     step_num,        eqasmb_desc,         ");
        query.append("     check_point,     check_method,        ");
        query.append("     fit_basis,       check_type,          ");
        query.append("     check_min,       check_basis_val,     ");
        query.append("     check_max,       uom,                 ");
        query.append("     is_active,       remark,              ");
        query.append("     stwrk_point_id                        ");
        query.append("    )    VALUES    (                       ");
        query.append("     ?,                ?,                  ");
        query.append("     ?,                ?,                  ");
        query.append("     ?,                ?,                  ");
        query.append("     ?,                ?,                  ");
        query.append("     ?,                ?,                  ");
        query.append("     ?,                ?,                  ");
        query.append("     ?,                ?,                  ");
        query.append("     ?,                ?,                  ");
        query.append("     ?                                     ");
        query.append("    )                                      ");
        
        
        Object[] objects = new Object[] {
                workPmiCInsPointDetailDTO.getEqAsmbId()
              , workPmiCInsPointDetailDTO.getStepNum()
              , workPmiCInsPointDetailDTO.getEqAsmbDesc()
              , workPmiCInsPointDetailDTO.getCheckPoint()
              , workPmiCInsPointDetailDTO.getCheckMethod()
              , workPmiCInsPointDetailDTO.getFitBasis()
              , workPmiCInsPointDetailDTO.getCheckTypeId()
              , workPmiCInsPointDetailDTO.getCheckMin()
              , workPmiCInsPointDetailDTO.getCheckBasisVal()
              , workPmiCInsPointDetailDTO.getCheckMax()
              , workPmiCInsPointDetailDTO.getUom()
              , workPmiCInsPointDetailDTO.getIsActive()
              , workPmiCInsPointDetailDTO.getRemark()
              , workPmiCInsPointDetailDTO.getStwrkPointId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        
        query.append("INSERT INTO TAPMINSDPOINT                  ");
        query.append("    (comp_no,         pminsdpoint_id,      ");
        query.append("     pm_id,           pminsdlist_id,       ");
        query.append("     pm_point_id,     pm_point_rslt_status,");
        query.append("     result_value,   result_value2,       ");
        query.append("     result_value3,   remark               ");
        query.append("    )    VALUES    (                       ");
        query.append("     ?,                ?,                  ");
        query.append("     ?,                ?,                  ");
        query.append("     ?,                ?,                  ");
        query.append("     ?,                ?,                  ");
        query.append("     ?,                ?                   ");
        query.append("    )                                      ");
        
        
        objects = new Object[] {
                user.getCompNo()
                , workPmiCInsPointDetailDTO.getPmInsDPointId() 
                , workPmiCInsPointDetailDTO.getPmId()
                , workPmiCInsPointDetailDTO.getPmInsDListId()  
                , workPmiCInsPointDetailDTO.getPmPointId()
                , workPmiCInsPointDetailDTO.getPmPointRsltStatus()
                , workPmiCInsPointDetailDTO.getResultValue()
                , workPmiCInsPointDetailDTO.getResultValue2()
                , workPmiCInsPointDetailDTO.getResultValue3()
                , workPmiCInsPointDetailDTO.getInsDetail()
        };
        
        rtnValue2 =  getJdbcTemplate().update(query.toString(), objects);
        
        rtnValue = rtnValue*rtnValue2;
        
        return rtnValue;
    }

    public String getId(WorkPmiCInsPointDetailDTO workPmiCInsPointDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT eqasmb_id      ");
        query.append("FROM TAEQASMB         ");
        query.append("WHERE comp_no = ?     ");
        query.getLikeQuery("description", workPmiCInsPointDetailDTO.getEqAsmbDesc());
        
        Object[] objects = new Object[] {
                user.getCompNo()
        };
        
        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));

    }
    public int updateDetail(WorkPmiCInsPointDetailDTO workPmiCInsPointDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        int rtnValue  = 0;

        query.append("UPDATE TAPMINSDPOINT SET             ");
        query.append("  pm_point_rslt_status    = ?        ");
        query.append(", result_value           = ?        ");
        query.append(", result_value2           = ?        ");
        query.append(", result_value3           = ?        ");
        query.append(", remark                  = ?        ");
        query.append("WHERE  comp_no            = ?        ");
        query.append("  AND  pminsdpoint_id      = ?        ");

        Object[] objects = new Object[] {
                workPmiCInsPointDetailDTO.getPmPointRsltStatusId()
              , workPmiCInsPointDetailDTO.getResultValue()
              , workPmiCInsPointDetailDTO.getResultValue2()
              , workPmiCInsPointDetailDTO.getResultValue3()
              , workPmiCInsPointDetailDTO.getInsDetail()
              , user.getCompNo()
              , workPmiCInsPointDetailDTO.getPmInsDPointId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
}
