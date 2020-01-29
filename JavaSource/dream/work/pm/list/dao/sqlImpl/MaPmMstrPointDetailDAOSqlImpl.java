package dream.work.pm.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.work.pm.list.dao.MaPmMstrPointDetailDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPointDetailDTO;

/**
 * 검사항목 상세 dao
 * @author  jung7126
 * @version $Id: MaPmMstrPointDetailDAO.java,v 1.0 2015/12/04 08:10:27 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maPmMstrPointDetailDAOTarget"
 * @spring.txbn id="maPmMstrPointDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPmMstrPointDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPmMstrPointDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaPmMstrPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param pmPointId
     * @param compNo
     * @return
     */
    public MaPmMstrPointDetailDTO findDetail(String wkOrId, String pmPointId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String compNo = user.getCompNo();
        query.append("SELECT																					    	");
        query.append("		x.pm_point_id pmPointId,																	");
        query.append("		x.step_num stepNum,																			");
        query.append("        x.eqasmb_desc                            eqAsmbDesc,       ");
//        query.append("        (SELECT full_desc                                                                           ");
//        query.append("         FROM TAEQASMB                                                                                ");
//        query.append("         WHERE comp_no = x.comp_no                                                                    ");
//        query.append("         AND eqasmb_id = x.eqasmb_id)      eqAsmbToDesc,                                ");
        
        query.append("      CASE WHEN x.eqasmb_id IS NULL               ");
        query.append("        THEN x.eqasmb_desc                        ");
        query.append("        ELSE (SELECT a.full_desc                  ");
        query.append("            FROM TAEQASMB a                       ");
        query.append("            WHERE a.comp_no = x.comp_no           ");
        query.append("            AND a.eqasmb_id = x.eqasmb_id)        ");
        query.append("        END                  eqAsmbToDesc,       ");
        
        query.append("		x.check_point 'CHECKPOINT',																	");
        query.append("		x.check_method checkMethod,																	");
        query.append("		x.fit_basis fitBasis,																		");
        query.append("		dbo.SFACODE_TO_DESC(x.check_type,'CHECK_TYPE','SYS','','"+user.getLangId()+"') checkTypeDesc,	");
        query.append("      x.check_type checkType,                                                                     ");
        query.append("      x.eqasmb_id eqasmbId,                                                                       ");
        query.append("      x.fit_basis fitBasis,                                                                       ");
        query.append("      x.check_min checkMin,                                                                       ");
        query.append("      x.check_max checkMax,                                                                       ");
        query.append("      x.uom,                                                                                      ");
        query.append("      x.check_basis_val checkBasisVal,                                                            ");
        query.append("      x.is_active isActive,                                                                       ");
        query.append("		x.remark remark																			    ");
        query.append("  , x.stwrk_point_id          stwrkPointId                                                        ");
        query.append("  , (SELECT a.check_point                                                                         ");
        query.append("     FROM TASTDCHECKPOINT a                                                                       ");
        query.append("     WHERE a.std_check_point_id = x.stwrk_point_id)       stwrkPointDesc                          ");
        query.append("     ,x.is_run_value    									isRunValue								");
        query.append("     ,x.fit_rate    										fitRate									");
        query.append("FROM  TAPMPOINT x																					");
        query.append("WHERE x.comp_no = '"+compNo+"'																	");
        query.append("  AND x.pm_point_id = "+pmPointId+"																");
    
        MaPmMstrPointDetailDTO maPmMstrPointDetailDTO = 
        		(MaPmMstrPointDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPmMstrPointDetailDTO()));
        
        return maPmMstrPointDetailDTO;
    }
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaPmMstrPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrPointDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int updateDetail(MaPmMstrPointDetailDTO maPmMstrPointDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	Object[] objects;
    	
    	query.append("UPDATE TAPMPOINT SET      ");
        query.append("    step_num        = ?,  ");
        query.append("    eqasmb_id       = ?,  ");
        query.append("    eqasmb_desc     = ?,  ");
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
        query.append("    stwrk_point_id  = ?,  ");
        query.append("    is_run_value    = ?,  ");
        query.append("    fit_rate        = ?   ");
        query.append("WHERE pm_point_id   = ?   ");
        query.append("  AND comp_no       = ?   ");
        
            objects = new Object[] {
                    maPmMstrPointDetailDTO.getStepNum(),
                    maPmMstrPointDetailDTO.getEqAsmbId(),
                    maPmMstrPointDetailDTO.getEqAsmbDesc(),
                    maPmMstrPointDetailDTO.getCheckPoint(),
                    maPmMstrPointDetailDTO.getCheckMethod(),
                    maPmMstrPointDetailDTO.getFitBasis(),
                    maPmMstrPointDetailDTO.getCheckType(),
                    maPmMstrPointDetailDTO.getCheckMin(),
                    maPmMstrPointDetailDTO.getCheckBasisVal(),
                    maPmMstrPointDetailDTO.getCheckMax(),
                    maPmMstrPointDetailDTO.getUom(),
                    maPmMstrPointDetailDTO.getIsActive(),
                    maPmMstrPointDetailDTO.getRemark(),
                    maPmMstrPointDetailDTO.getStwrkPointId(),
        	        maPmMstrPointDetailDTO.getIsRunValue(),
        	        maPmMstrPointDetailDTO.getFitRate(),
                    maPmMstrPointDetailDTO.getPmPointId(),
                    maPmMstrMstrCommonDTO.getCompNo()
            };
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaPmMstrPointDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrPointDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int insertDetail(MaPmMstrPointDetailDTO maPmMstrPointDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPMPOINT                                ");
        query.append("    (comp_no,           pm_point_id,                 ");
        query.append("     pm_id,             pmEquip_id,                  ");
        query.append("     step_num,          eqasmb_id,                   ");
        query.append("     check_point,       check_method,                ");
        query.append("     fit_basis,         check_type,                  ");
        query.append("     check_min,         check_basis_val,             ");
        query.append("     check_max,         uom,                         ");
        query.append("     is_active,         REMARK,                      ");
        query.append("     eqasmb_desc,       stwrk_point_id,              ");
    	query.append("     is_run_value,      fit_rate                	   ");
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
        query.append("     ?,                ?                             ");
        query.append("    )                                                ");

        
        Object[] objects = new Object[] {
                maPmMstrMstrCommonDTO.getCompNo(),
                maPmMstrPointDetailDTO.getPmPointId(),
                maPmMstrMstrCommonDTO.getPmId(),
                maPmMstrMstrCommonDTO.getPmEquipId(),
                maPmMstrPointDetailDTO.getStepNum(),
                maPmMstrPointDetailDTO.getEqAsmbId(),
                maPmMstrPointDetailDTO.getCheckPoint(),
                maPmMstrPointDetailDTO.getCheckMethod(),
                maPmMstrPointDetailDTO.getFitBasis(),
                maPmMstrPointDetailDTO.getCheckType(),
                maPmMstrPointDetailDTO.getCheckMin(),
                maPmMstrPointDetailDTO.getCheckBasisVal(),
                maPmMstrPointDetailDTO.getCheckMax(),
                maPmMstrPointDetailDTO.getUom(),
                maPmMstrPointDetailDTO.getIsActive(),
                maPmMstrPointDetailDTO.getRemark(),
                maPmMstrPointDetailDTO.getEqAsmbDesc(),
                maPmMstrPointDetailDTO.getStwrkPointId(),
                maPmMstrPointDetailDTO.getIsRunValue(),
                maPmMstrPointDetailDTO.getFitRate()

        };
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    }
    @Override
	public int insertLovDetail(MaPmMstrPointDetailDTO maPmMstrPointDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO, User user) {
		 
    	QuerySqlBuffer query = new QuerySqlBuffer();
		 
		 query.append("INSERT INTO TAPMPOINT (comp_no, pm_point_id, step_num, pm_id, eqasmb_id, check_point, check_method, fit_basis, check_type, check_min, check_basis_val, check_max, uom, is_active, is_deleted, remark)");
		 query.append(" SELECT                                                                            								");
		 query.append(" comp_no                                																			");
		 query.append(" , ?                                                                                       						");//상위list-key
		 query.append(" , ?                                                                                           					");
		 query.append(" , ?                                                                                           					");//최상위list-key
		 query.append(" , (SELECT top 1 aa.EQASMB_ID																					");
		 query.append("    FROM taeqasmb aa 																							");
		 query.append("    WHERE aa.comp_no = x.comp_no																					");
		 query.append("    AND aa.eq_ctg_asmb_id = x.eq_ctg_asmb_id )	eqasmb_id														");
		 query.append(" , x.check_point                                                                                      			");
		 query.append(" , x.check_method                                                                                     			");
		 query.append(" , x.fit_basis                                                                                                   ");
		 query.append(" , x.check_type    																								");
		 query.append(" , x.check_min																									");
		 query.append(" , x.check_basis_val																								");
		 query.append(" , x.check_max                                        															");
		 query.append(" , x.uom                                                                                                         ");
		 query.append(" , x.is_use        																								");
		 query.append(" , ? 	                                       																	");
		 query.append(" , x.remark                                     																	");
		 query.append(" FROM TAEQCTGPMPOINT x                                                                                           ");
		 query.append(" WHERE eqctgpmpoint_id = ?																						");//lov-key
		 query.append("   AND comp_no = ?																								");
		 
	        Object[] objects = new Object[] {
	        		maPmMstrPointDetailDTO.getPmPointId()
	        		, maPmMstrPointDetailDTO.getStepNum()
	        		, maPmMstrMstrCommonDTO.getPmId()
	        		, "N"
	        		, maPmMstrPointDetailDTO.getEqCtgPmPointId()
	        		, user.getCompNo()
	        		
	        };
	        
	        return getJdbcTemplate().update(query.toString(), getObject(objects)); 
	}
}