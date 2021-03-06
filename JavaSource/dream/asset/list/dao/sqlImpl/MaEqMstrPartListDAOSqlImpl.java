package dream.asset.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.asset.list.dao.MaEqMstrPartListDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPartListDTO;

/**
 * 구성자재 목록 dao
 * @author  kim21017
 * @version $Id: MaEqMstrPartListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrPartListDAOTarget"
 * @spring.txbn id="maEqMstrPartListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrPartListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaEqMstrPartListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMstrPartListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param maEqMstrPartListDTO
     * @param loginUser
     * @return List
     */
    public List findPartList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPartListDTO maEqMstrPartListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT												");
        query.append("       '' seqNo,										");
        query.append("       '' isDelCheck,									");
        query.append("       x.equip_id 			equipId,				");
        query.append("       x.eqpart_id 			eqPartId,				");
        query.append("       y.part_no              partNo,			        ");
        query.append("       ISNULL(y.description,'')+', '+ISNULL(y.pt_size,'') partDesc, ");
        query.append("       y.description          partName,               ");
        query.append("       y.pt_size              ptSize,                 ");
        query.append("       y.model                model,                  ");
        query.append("       x.consist_qty			consistQty,				");
        query.append("       (SELECT ISNULL(full_desc,description)			");
        query.append("          FROM TAEQASMB								");
        query.append("         WHERE comp_no = x.comp_no					");
        query.append("           AND eqasmb_id = x.eqasmb_id) eqAsmbDesc,	");
        query.append("       x.use_qty 				useQty,					");
        query.append("       x.issue_time 			issueTime,				");
        query.append("       x.issue_first_date 	issueFirstDate,        	");
        query.append("       x.issue_last_date  	issueLastDate	        ");
        query.append("     , x.ord_no      			ordNo       			");
        query.append("     , x.is_use               isUse                   ");
        query.append("     , x.eq_ctg_part_id       eqCtgPartId             ");
        query.append("     , x.eq_ctg_asmb_id       eqCtgAsmbId             ");
        query.append("     , (SELECT a.description FROM TAEQASMB a WHERE x.eqasmb_id = a.eqasmb_id) workPart           ");
        query.append("     , x.is_eqtype_part       isEqTypePart            ");
        query.append("     ,  (SELECT A.EQCTG_ID FROM TAEQASMB a WHERE x.eqasmb_id = a.eqasmb_id) eqctgId              ");
        query.append("     , x.part_id        		partId					");
        query.append("     , x.eqasmb_id            eqAsmbId				");
        query.append("     , x.consist_nbr          consistNbr              ");
        query.append("     , x.dwg_no               dwgNo                   ");
        query.append("     , x.dwg_section_no       dwgSectionNo            ");
        query.append("FROM   TAEQPART x INNER JOIN TAPARTS y                ");
        query.append("ON x.comp_no = y.comp_no                              ");
        query.append("AND x.part_id = y.part_id                             ");
        query.append("WHERE 1=1												");
        query.append(this.getWhere(maEqMstrCommonDTO,maEqMstrPartListDTO,loginUser));

        query.getOrderByQuery("x.eqpart_id", "x.eqpart_id, x.ord_no", maEqMstrPartListDTO.getOrderBy(), maEqMstrPartListDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(maEqMstrPartListDTO.getIsLoadMaxCount(), maEqMstrPartListDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqMstrPartListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deletePartList(String id, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TAEQPART						");
    	query.append("WHERE  eqpart_id 		= '"+id+"'			");
    	query.append("  AND  comp_no		= '"+compNo+"'		");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPartListDTO maEqMstrPartListDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndQuery("x.equip_id", maEqMstrCommonDTO.getEquipId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(maEqMstrPartListDTO.getEqPartId()))
        {
            query.getAndQuery("x.eqpart_id", maEqMstrPartListDTO.getEqPartId());
            return query.toString();
        }
    	query.getAndQuery("x.eq_ctg_part_id", maEqMstrPartListDTO.getEqCtgPartId());
    	
    	return query.toString();
    }
    
    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO,
            MaEqMstrPartListDTO maEqMstrPartListDTO, User user) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                    ");
        query.append("       COUNT(1)           ");
        query.append("FROM   TAEQPART x INNER JOIN TAPARTS y ");
        query.append("ON x.comp_no = y.comp_no  ");
        query.append("AND x.part_id = y.part_id ");
        query.append("WHERE  1=1                ");
        query.append(this.getWhere(maEqMstrCommonDTO,maEqMstrPartListDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }

	public String validPart(String equipId, String partId, User user)
			throws Exception {
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("SELECT                    ");
		query.append("       COUNT(1)           ");
		query.append("FROM TAEQPART x			");
		query.append("WHERE 1=1					");
		query.getAndQuery("x.comp_no", user.getCompNo());
		query.getAndQuery("x.equip_id", equipId);
		query.getAndQuery("x.part_id", partId);
	
		return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
	}
}