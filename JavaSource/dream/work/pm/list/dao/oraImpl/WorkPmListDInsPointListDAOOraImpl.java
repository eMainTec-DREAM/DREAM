package dream.work.pm.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.pm.list.dao.WorkPmListDInsPointListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;

/**
 * WorkPmListDInsPoint Page - List DAO implements
 * @author youngjoo38
 * @version $Id: WorkPmListDInsPointListDAOOraImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmListDInsPointListDAOTarget"
 * @spring.txbn id="workPmListDInsPointListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmListDInsPointListDAOOraImpl  extends BaseJdbcDaoSupportOra implements WorkPmListDInsPointListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id: workPmListDInsPointListDAO.java,v 1.0 2017/08/22 15:20:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workPmListDInsPointListDTO
     * @return List
     */
    public List findList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                    ");
        query.append("      ''                                              seqNo               ");
        query.append("      , ''                                            isDelCheck          ");
        query.append("      , x.step_num                                    stepNum             ");
        query.append("      , CASE WHEN x.eqasmb_id IS NULL                                     ");
        query.append("           THEN x.eqasmb_desc                                             ");
        query.append("           ELSE (SELECT NVL(a.full_desc, a.description)                   ");
        query.append("                   FROM TAEQASMB a                                        ");
        query.append("                  WHERE a.comp_no = x.comp_no                             ");
        query.append("                    AND a.eqasmb_id = x.eqasmb_id)                        ");
        query.append("           END                                        eqAsmbDesc          ");
        query.append("      , x.check_point                                 CHECKPOINT          ");
        query.append("      , x.check_method                                checkMethod         ");
        query.append("      , x.fit_basis                                   fitBasis            ");
        query.append("      , SFACODE_TO_DESC(x.check_type,'CHECK_TYPE','SYS','','"+user.getLangId()+"')   checkTypeDesc   ");
        query.append("      , x.check_min                                   checkMin            ");
        query.append("      , x.check_basis_val                             checkBasisVal       ");
        query.append("      , x.check_max                                   checkMax            ");
        query.append("      , x.uom                                         uom                 ");
        query.append("      , x.is_active                                   isActive            ");
        query.append("      , x.pmequip_id                                  pmEquipId           ");
        query.append("      , x.remark                                      remark              ");
        query.append("      , x.pm_point_id                                 pmPointId           ");
        query.append("		,(SELECT a.param1 													");
        query.append("			FROM TACDSYSD a													");
        query.append("			WHERE 1=1														");
        query.append("			AND a.list_type='CHECK_TYPE'									");
        query.append("			AND a.cdsysd_no = x.check_type) AS detailPage					");
        query.append("		,x.check_type AS checkTypeId										");
        query.append("FROM TAPMPOINT x                                                          ");
        query.append("WHERE 1=1                                                                 ");
        query.append(this.getWhere(maPmMstrCommonDTO, user));
                
        query.getOrderByQuery("x.step_num", maPmMstrCommonDTO.getOrderBy(), maPmMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPmMstrCommonDTO.getIsLoadMaxCount(), maPmMstrCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param workPmListDInsPointListDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPmMstrCommonDTO maPmMstrCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndNumKeyQuery("x.pm_id", maPmMstrCommonDTO.getPmId());
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x.IS_DELETED", "N");
        
        query.getAndQuery("x.pm_point_id", maPmMstrCommonDTO.getPmPointId());
        if (!"".equals(maPmMstrCommonDTO.getPmEquipId()))
        {
            query.getAndNumKeyQuery("x.pmequip_id", maPmMstrCommonDTO.getPmEquipId());
            return query.toString();
        }
        
        return query.toString();
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: workPmListDInsPointListDAO.java,v 1.0 2017/08/22 15:20:12 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param workPmListDInsPointListDTO
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("UPDATE  TAPMPOINT SET   ");
        query.append("         IS_DELETED = 'Y'     ");
        query.append("        ,DELETE_BY  = ?       ");
        query.append("        ,DELETE_TIME = ?      ");
        query.append("WHERE pm_point_id = ?   ");
        query.append("  AND comp_no = ?       ");
        
        Object[] objects = new Object[] {  
        		user.getEmpId()
        		,DateUtil.getDate()
                ,id
                ,user.getCompNo()
        };
        this.getJdbcTemplate().update(query.toString(), objects);
        
        query.setClear();
        query.append(" DELETE FROM TAPMINSPOINT           ");
        query.append(" WHERE comp_no =?                   ");
        query.append("      and pm_point_id = ?           ");
        query.append("      and pmsched_status != 'C'     ");
        objects = new Object[] {  
        		user.getCompNo()
        		,id
        };
        this.getJdbcTemplate().update(query.toString(), objects);
        
        
        query.setClear();
        query.append(" DELETE FROM  TAPMINSDPOINT         ");
        query.append(" WHERE comp_no =?                   ");
        query.append("      and pm_point_id = ?           ");
        query.append("      and pmsched_status != 'C'     ");
        objects = new Object[] {  
        		user.getCompNo()
        		,id
        };
        this.getJdbcTemplate().update(query.toString(), objects);
        
        query.setClear();
        query.append(" DELETE FROM  TAPMPTRLRSLTPOINT     ");
        query.append(" WHERE comp_no =?                   ");
        query.append("      and pm_point_id = ?           ");
        query.append("      and pmsched_status != 'C'     ");
        objects = new Object[] {  
        		user.getCompNo()
        		,id
        };
        this.getJdbcTemplate().update(query.toString(), objects);
        
        query.setClear();
        query.append(" DELETE FROM  TAWOPOINT             ");
        query.append(" WHERE comp_no =?                   ");
        query.append("      and pm_point_id = ?           ");
        query.append("      and pmsched_status != 'C'     ");
        objects = new Object[] {  
        		user.getCompNo()
        		,id
        };
        this.getJdbcTemplate().update(query.toString(), objects);
        
        
        
        return 0;
        
    }

    public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAPMPOINT x                                        ");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(maPmMstrCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
