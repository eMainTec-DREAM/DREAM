package dream.work.pm.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.pm.list.dao.WorkPmListDInsPointDetailDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPointDetailDTO;
import dream.work.pm.list.dto.WorkPmListDInsPointDetailDTO;

/**
 * WorkPmListDInsPoint Page - Detail DAO implements
 * @author youngjoo38
 * @version $Id: WorkPmListDInsPointDetailDAOOraImpl.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workPmListDInsPointDetailDAOTarget"
 * @spring.txbn id="workPmListDInsPointDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkPmListDInsPointDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkPmListDInsPointDetailDAO
{

    public WorkPmListDInsPointDetailDTO findDetail(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) 
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                        ");
        query.append("    x.uom                     uom             ");
        query.append("  , x.pm_point_id             pmPointId       ");
        query.append("  , x.pm_id                   pmId            ");
        query.append("  , x.step_num                stepNum         ");
        query.append("  , x.check_point             checkPoint      ");
        query.append("  , x.check_method            checkMethod     ");
        query.append("  , CASE WHEN x.eqasmb_id IS NULL             ");
        query.append("    THEN x.eqasmb_desc                        ");
        query.append("    ELSE (SELECT NVL(a.full_desc, a.description)");
        query.append("          FROM TAEQASMB a                     ");
        query.append("          WHERE a.comp_no = x.comp_no         ");
        query.append("            AND a.eqasmb_id = x.eqasmb_id)    ");
        query.append("    END                       eqAsmbDesc      ");

        query.append("  , x.fit_basis               fitBasis        ");
        query.append("  , x.check_type              checkTypeId     ");
        query.append("  , SFACODE_TO_DESC(x.check_type, 'CHECK_TYPE', 'SYS', ?, ?)        checkType      ");
        query.append("  , x.check_min               checkMin        ");
        query.append("  , x.check_basis_val         checkBasisVal   ");
        query.append("  , x.check_max               checkMax        ");
        query.append("  , x.is_active               isActive        ");
        query.append("  , x.remark                  remark          ");
        query.append("  , x.stwrk_point_id          stwrkPointId    ");
        query.append("  , x.stwrk_point_id          stwrkPointId    ");
        query.append("  , (SELECT a.check_point                     ");
        query.append("     FROM TASTDCHECKPOINT a                   ");
        query.append("     WHERE a.std_check_point_id = x.stwrk_point_id)        stwrkPointDesc          ");
        query.append("FROM TAPMPOINT x                              ");
        query.append("WHERE  1=1                                    ");
        query.append("AND    x.comp_no    = ?                       ");
        query.append("AND    x.pm_point_id    = ?                   ");
        
        
        Object[] objects = new Object[] {
                user.getCompNo()
               , user.getLangId()
               , user.getCompNo()
               , maPmMstrCommonDTO.getPmPointId()
        };
    
        WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO = 
                (WorkPmListDInsPointDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new WorkPmListDInsPointDetailDTO()));
        
        return workPmListDInsPointDetailDTO;
        
    }
    

    public int insertDetail(WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;

        query.append("INSERT INTO TAPMPOINT                      ");
        query.append("    (comp_no,         pm_point_id,         ");
        query.append("     pm_id,           eqasmb_id,           ");
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
                user.getCompNo()
              , workPmListDInsPointDetailDTO.getPmPointId()
              , workPmListDInsPointDetailDTO.getPmId()
              , workPmListDInsPointDetailDTO.getEqAsmbId()
              , workPmListDInsPointDetailDTO.getStepNum()
              , workPmListDInsPointDetailDTO.getEqAsmbDesc()
              , workPmListDInsPointDetailDTO.getCheckPoint()
              , workPmListDInsPointDetailDTO.getCheckMethod()
              , workPmListDInsPointDetailDTO.getFitBasis()
              , workPmListDInsPointDetailDTO.getCheckTypeId()
              , workPmListDInsPointDetailDTO.getCheckMin()
              , workPmListDInsPointDetailDTO.getCheckBasisVal()
              , workPmListDInsPointDetailDTO.getCheckMax()
              , workPmListDInsPointDetailDTO.getUom()
              , workPmListDInsPointDetailDTO.getIsActive()
              , workPmListDInsPointDetailDTO.getRemark()
              , workPmListDInsPointDetailDTO.getStwrkPointId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }

    public int updateDetail(WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        int rtnValue  = 0;

        query.append("UPDATE TAPMPOINT SET             ");
        query.append("  comp_no             = ?        ");
        query.append(", step_num            = ?        ");
        query.append(", eqasmb_id           = ?        ");
        query.append(", eqasmb_desc         = ?        ");
        query.append(", check_point         = ?        ");
        query.append(", check_method        = ?        ");
        query.append(", fit_basis           = ?        ");
        query.append(", check_type          = ?        ");
        query.append(", check_min           = ?        ");
        query.append(", check_basis_val     = ?        ");
        query.append(", check_max           = ?        ");
        query.append(", uom                 = ?        ");
        query.append(", is_active           = ?        ");
        query.append(", remark              = ?        ");
        query.append(", stwrk_point_id      = ?        ");
        query.append("WHERE  comp_no        = ?        ");
        query.append("  AND  pm_point_id    = ?        ");

        Object[] objects = new Object[] {
                user.getCompNo()
              , workPmListDInsPointDetailDTO.getStepNum()
              , workPmListDInsPointDetailDTO.getEqAsmbId()
              , workPmListDInsPointDetailDTO.getEqAsmbDesc()
              , workPmListDInsPointDetailDTO.getCheckPoint()
              , workPmListDInsPointDetailDTO.getCheckMethod()
              , workPmListDInsPointDetailDTO.getFitBasis()
              , workPmListDInsPointDetailDTO.getCheckTypeId()
              , workPmListDInsPointDetailDTO.getCheckMin()
              , workPmListDInsPointDetailDTO.getCheckBasisVal()
              , workPmListDInsPointDetailDTO.getCheckMax()
              , workPmListDInsPointDetailDTO.getUom()
              , workPmListDInsPointDetailDTO.getIsActive()
              , workPmListDInsPointDetailDTO.getRemark()
              , workPmListDInsPointDetailDTO.getStwrkPointId()
              , user.getCompNo()
              , workPmListDInsPointDetailDTO.getPmPointId()
        };
        
        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    @Override
	public int insertLovDetail(WorkPmListDInsPointDetailDTO workPmListDInsPointDetailDTO, MaPmMstrCommonDTO maPmMstrCommonDTO, User user) {
		 
		 QueryBuffer query = new QueryBuffer();
		 
		 query.append("INSERT INTO TAPMPOINT (comp_no, pm_point_id, step_num, pm_id, eqasmb_id, check_point, check_method, fit_basis, check_type, check_min, check_basis_val, check_max, uom, is_active, is_deleted)");
		 query.append(" SELECT                                                                            								");
		 query.append(" comp_no                                																			");
		 query.append(" , ?                                                                                       						");//상위list-key
		 query.append(" , ?                                                                                           					");
		 query.append(" , ?                                                                                           					");//최상위list-key
		 query.append(" , x.eq_ctg_asmb_id 																									");
		 query.append(" , x.check_point                                                                                      			");
		 query.append(" , x.check_method                                                                                     			");
		 query.append(" , x.fit_basis                                                                                                   ");
		 query.append(" , x.check_type    																								");
		 query.append(" , x.check_min																									");
		 query.append(" , x.check_basis_val																								");
		 query.append(" , x.check_max                                        															");
		 query.append(" , x.uom                                                                                                         ");
		 query.append(" , x.is_use        																								");
		 query.append(" , ?        																										");
		 query.append(" FROM TAEQCTGPMPOINT x                                                                                           ");
		 query.append(" WHERE eqctgpmpoint_id = ?																						");//lov-key
		 query.append("   AND comp_no = ?																								");
		  
	        Object[] objects = new Object[] {
	        		maPmMstrCommonDTO.getPmPointId()
	        		, workPmListDInsPointDetailDTO.getStepNum()
	        		, maPmMstrCommonDTO.getPmId()
	        		, "N"
	        		, workPmListDInsPointDetailDTO.getEqCtgPmPointId()
	        		, user.getCompNo()
	        };
	        
	        return getJdbcTemplate().update(query.toString(), objects); 
	}
    
}
