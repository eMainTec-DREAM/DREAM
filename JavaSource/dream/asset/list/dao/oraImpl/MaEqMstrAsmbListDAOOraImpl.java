package dream.asset.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.list.dao.MaEqMstrAsmbListDAO;
import dream.asset.list.dto.MaEqMstrAsmbListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 설비부위 목록 dao
 * @author  kim21017
 * @version $Id: MaEqMstrAsmbListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrAsmbListDAOTarget"
 * @spring.txbn id="maEqMstrAsmbListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrAsmbListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqMstrAsmbListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMstrAsmbListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param maEqMstrAsmbListDTO
     * @param loginUser
     * @return List
     */
    public List findAsmbList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrAsmbListDTO maEqMstrAsmbListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT											");
        query.append("       ''						 seqNo,				");
        query.append("       '' isDelCheck,								");
        query.append("       x.equip_id 			equipId,			");
        query.append("       x.eqasmb_id 			eqAsmbId,			");
        query.append("       x.description 			eqAsmbDesc,			");
        query.append("       x.full_desc 			fullDesc,			");
        query.append("       x.maker                maker,              ");
        query.append("       x.model_no             modelNo,            ");
        query.append("       x.buy_amt              buyAmt,             ");
        query.append("       x.spec                 spec,               ");
        query.append("       x.setup_date           setupDate,          ");
        query.append("       x.is_use 				isUse,				");
        query.append("       x.ord_no 				ordNo				");
        query.append("     , x.eqasmb_id            ID                  ");
        query.append("     , x.eqasmb_no            eqAsmbNo            ");
        query.append("     , x.is_eqtype_asmb       isEqAsmb            ");
        query.append("     , x.p_eqasmb_id          pEqAsmbId           ");
        query.append("     , (SELECT full_desc                          ");
        query.append("        FROM TAEQASMB                             ");
        query.append("        WHERE comp_no = x.comp_no                 ");
        query.append("        AND eqasmb_id = x.p_eqasmb_id) pEqAsmbDesc");
        query.append("	   , x.remark   			REMARK				");
        query.append("	   , x.tag_no   			TAGNO				");
        query.append("     , x.guaranty_date		guarantyDate		");
        query.append("     , x.serial_no 			serialNo			");
        query.append("     , x.eq_size 				eqSize				");
        query.append("     , x.weight 				weight				");
        query.append("	   , x.as_vendor   			ASINFO				");
        query.append("	   , x.eq_ctg_asmb_id   	EQCTGASMBID			");
        query.append("	   , x.is_eqtype_asmb   	ISEQTYPEASMB		");
        query.append("	   , x.eq_ctg_asmb_no   	EQCTGASMBNO			");
        query.append("	   , x.p_eq_ctg_asmb_id   	PEQCTGASMBID		");
        query.append("     , x.as_vendor   			asVendor   			");
        query.append("     , (SELECT a.part_no FROM TAPARTS a WHERE a.comp_no = x.comp_no AND a.part_id = x.part_id)		partNo		");
        query.append("     , (SELECT a.description FROM TAPARTS a WHERE a.comp_no = x.comp_no AND a.part_id = x.part_id) 	partName	");
        query.append("     , (SELECT a.pt_size FROM TAPARTS a WHERE a.comp_no = x.comp_no AND a.part_id = x.part_id) 		partSize	");
        query.append("     , x.cre_date 			creDate				");
        query.append("     , (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = x.cre_by) 			creBy		");
        query.append("     , x.upd_date 			updDate				");
        query.append("     , (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = x.upd_by) 			updBy 		");
        query.append("FROM   TAEQASMB x									");
        query.append("WHERE 1=1											");
        query.append(this.getWhere(maEqMstrCommonDTO,maEqMstrAsmbListDTO,loginUser));
        query.append("ORDER BY equip_id, x.ord_no						");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    private String getWhere(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrAsmbListDTO maEqMstrAsmbListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.equip_id", maEqMstrCommonDTO.getEquipId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(maEqMstrAsmbListDTO.getPmId()))
        {
    		query.append(" AND x.equip_id IN (SELECT DISTINCT a.equip_id							");
            query.append("     			 	  FROM TAPMEQUIP a										");
            query.append("     			 	  WHERE a.comp_no=x.comp_no								");
            query.append("     			 	   AND a.pm_id='" + maEqMstrAsmbListDTO.getPmId() + "')	");
            
        }
    	if (!"".equals(maEqMstrAsmbListDTO.getEqAsmbId()))
        {
            query.getAndQuery("x.eqasmb_id", maEqMstrAsmbListDTO.getEqAsmbId());
            return query.toString();
        }
    	query.getAndQuery("x.eq_ctg_asmb_id", maEqMstrAsmbListDTO.getEqCtgAsmbId());
    	
    	return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqMstrAsmbListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteAsmbList(String id, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAEQASMB  WHERE eqasmb_id IN(		");
    	query.append("	SELECT x.eqasmb_id		");
    	query.append("	FROM TAEQASMB x			");
    	query.append("	WHERE  1=1              ");
    	query.append("	  AND  comp_no 	   	 = ?");
    	query.append("  START WITH eqasmb_id = ?");
    	query.append("  CONNECT BY PRIOR eqasmb_id = p_eqasmb_id	");
    	query.append(") 		");
    	
    	Object[] objects = new Object[] {
    			  compNo
    			, id
        };
    	
    	return this.getJdbcTemplate().update(query.toString(),objects);
    }
}