package dream.work.rpt.mapmtrend.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.rpt.mapmtrend.dao.MaPmTrendListDAO;
import dream.work.rpt.mapmtrend.dto.MaPmTrendCommonDTO;

/**
 * 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPmTrendListDAOTarget"
 * @spring.txbn id="maPmTrendListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPmTrendListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPmTrendListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPmTrendCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(MaPmTrendCommonDTO maPmTrendCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT '' AS seqNo                                                            ");
        query.append("      ,y.pm_type              pmType                                          ");
        query.append("      ,y.description          pmInsDesc                                       ");
        query.append("      ,x.pm_point_id AS pmPointId                                             ");
        query.append("      ,(SELECT full_desc                                                      ");
        query.append("          FROM TAEQLOC                                                        ");
        query.append("         WHERE comp_no = b.comp_no                                            ");
        query.append("           AND eqloc_id = b.eqloc_id) AS eqLocDesc                            ");
        query.append("      ,(SELECT full_desc                                                      ");
        query.append("          FROM TAEQCTG                                                        ");
        query.append("         WHERE comp_no = b.comp_no                                            ");
        query.append("           AND eqctg_id = b.eqctg_id) AS eqCtgDesc                            ");
        query.append("      ,b.description AS equipDesc                                             ");
        query.append("      ,(SELECT description FROM TAEQASMB                                      ");
        query.append("          WHERE comp_no = x.comp_no AND eqasmb_id = x.eqasmb_id)||'/'||       ");
        query.append("       x.check_point AS asmbPoint                                             ");
        query.append("      ,x.check_min||' / '||x.check_basis_val||' / '||x.check_max AS checkVal  ");
        query.append("      ,x.uom AS uom                                                           ");
        query.append("      ,y.emp_id       AS empId                                                ");
        query.append("      ,(SELECT emp_name FROM TAEMP WHERE emp_id = y.emp_id) AS empDesc        ");
        query.append("      ,y.pm_id                         AS pmId                                ");
        query.append("      ,b.equip_id                      AS equipId                             ");
        query.append("FROM TAPMPOINT x INNER JOIN TAPMLST y                                         ");
        query.append("ON x.comp_no = y.comp_no                                                      ");
        query.append("  AND x.pm_id = y.pm_id                                                       ");
        query.append("INNER JOIN TAPMEQUIP a                                                        ");
        query.append("ON y.comp_no = a.comp_no                                                      ");
        query.append("AND y.pm_id = a.pm_id                                                         ");
        query.append("INNER JOIN TAEQUIPMENT b                                                      ");
        query.append("ON a.comp_no = b.comp_no                                                      ");
        query.append("AND a.equip_id = b.equip_id                                                   ");
        query.append("WHERE y.pm_type in ('INS','RINS','DINS')                                      ");
        
        query.append(this.getWhere(maPmTrendCommonDTO, loginUser));
        
        query.getOrderByQuery("b.equip_id, x.check_point", maPmTrendCommonDTO.getOrderBy(), maPmTrendCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(maPmTrendCommonDTO.getIsLoadMaxCount(), maPmTrendCommonDTO.getFirstRow()));
    }
    
    public String getWhere(MaPmTrendCommonDTO maPmTrendCommonDTO,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", maPmTrendCommonDTO.getCompNo());
        query.getAndQuery("x.check_type", "VAL");
        
        query.getLikeQuery("b.description", maPmTrendCommonDTO.getFilterEquipDesc());
        
        query.getEqLocLevelQuery("b.eqloc_id", maPmTrendCommonDTO.getFilterEqLocId(), maPmTrendCommonDTO.getFilterEqLocDesc(), maPmTrendCommonDTO.getCompNo());
        
        query.getEqCtgLevelQuery("b.eqctg_id", maPmTrendCommonDTO.getFilterEqCtgId(), maPmTrendCommonDTO.getFilterEqCtgDesc(), maPmTrendCommonDTO.getCompNo());
        
        query.getSysCdQuery("b.plf_type", maPmTrendCommonDTO.getFilterPlfTypeId(), maPmTrendCommonDTO.getFilterPlfTypeDesc(), "PLF_TYPE", maPmTrendCommonDTO.getCompNo(),loginUser.getLangId());
        
        query.getLikeQuery("b.is_law_eq", maPmTrendCommonDTO.getFilterIsLawEq());
        
        query.getLikeQuery("b.maker", maPmTrendCommonDTO.getFilterMaker());
        
        query.getLikeQuery("b.model_no", maPmTrendCommonDTO.getFilterModelNo());
        
        query.getCodeLikeQuery("b.main_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = b.main_mng_id AND a.comp_no=b.comp_no)", 
                maPmTrendCommonDTO.getFilterMainMngId(), maPmTrendCommonDTO.getFilterMainMngName());
        
        query.getCodeLikeQuery("b.sub_mng_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = b.sub_mng_id AND a.comp_no=b.comp_no)", 
                maPmTrendCommonDTO.getFilterSubMngId(), maPmTrendCommonDTO.getFilterSubMngName());
        
        query.getDeptLevelQuery("y.dept_id", maPmTrendCommonDTO.getFilterDeptId(),maPmTrendCommonDTO.getFilterDeptDesc(), maPmTrendCommonDTO.getCompNo());
        
        //담당자
        query.getCodeLikeQuery("y.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = y.emp_id AND a.comp_no='"+maPmTrendCommonDTO.getCompNo()+"')", 
                maPmTrendCommonDTO.getFilterEmpId(), maPmTrendCommonDTO.getFilterEmpDesc());

        return query.toString();
    }
    
    @Override
    public String findTotalCount(MaPmTrendCommonDTO maPmTrendCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                    ");
        query.append("       COUNT(1)                           ");
        query.append("FROM TAPMPOINT x INNER JOIN TAPMLST y     ");
        query.append("ON x.comp_no = y.comp_no                  ");
        query.append("  AND x.pm_id = y.pm_id                   ");
        query.append("INNER JOIN TAPMEQUIP a                    ");
        query.append("ON y.comp_no = a.comp_no                  ");
        query.append("AND y.pm_id = a.pm_id                     ");
        query.append("INNER JOIN TAEQUIPMENT b                  ");
        query.append("ON a.comp_no = b.comp_no                  ");
        query.append("AND a.equip_id = b.equip_id               ");
        query.append("WHERE y.pm_type in ('INS','RINS','DINS')  ");
        query.append(this.getWhere(maPmTrendCommonDTO, loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
}