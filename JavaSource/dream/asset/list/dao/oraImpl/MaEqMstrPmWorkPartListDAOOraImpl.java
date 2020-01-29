package dream.asset.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.asset.list.dao.MaEqMstrPmWorkPartListDAO;
import dream.asset.list.dto.MaEqMstrPmWorkDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkPartListDTO;

/**
 * 설비 예방작업 부품 목록 DAO IMPL
 * @author  kim21017
 * @version $Id: MaEqMstrPmWorkPartListDAOOraImpl.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrPmWorkPartListDAOTarget"
 * @spring.txbn id="maEqMstrPmWorkPartListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrPmWorkPartListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqMstrPmWorkPartListDAO
{

	@Override
	public List findEqPmWorkPartList(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, MaEqMstrPmWorkPartListDTO maEqMstrPmWorkPartListDTO, User user) throws Exception {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT '' AS isDelCheck							    ");
		query.append("		,'' AS seqNo								    ");
		query.append("		,x.pm_id AS pmId							    ");
		query.append("		,x.pm_part_id AS pmPartId					    ");
		query.append("		,x.part_id AS partId						    ");
		query.append("		,y.part_no AS partNo						    ");
		query.append("		,y.description AS partDesc					    ");
		query.append("		,y.pt_size AS partSize						    ");
		query.append("		,y.model AS model						        ");
		query.append("		,x.use_qty AS useQty						    ");
		query.append("      ,(SELECT b.full_desc                            ");
        query.append("        FROM TAPMEQUIP a INNER JOIN TAEQASMB b        ");
        query.append("        ON a.comp_no = b.comp_no                      ");
        query.append("        AND a.eqasmb_id = b.eqasmb_id                 ");
        query.append("        WHERE a.comp_no = x.comp_no                   ");
        query.append("        AND a.pmequip_id = x.pmequip_id) eqAsmbDesc   ");
		query.append("FROM TAPMPART x INNER JOIN TAPARTS y				    ");
		query.append("ON x.comp_no = y.comp_no							    ");
		query.append("AND x.part_id = y.part_id							    ");
		query.append("WHERE 1=1											    ");
		query.append(this.getWhere(maEqMstrPmWorkDetailDTO, maEqMstrPmWorkPartListDTO, user));
		query.getOrderByQuery("y.part_no", maEqMstrPmWorkPartListDTO.getOrderBy(), maEqMstrPmWorkPartListDTO.getDirection());
        
        
        return getJdbcTemplate().queryForList(query.toString(maEqMstrPmWorkPartListDTO.getIsLoadMaxCount(), maEqMstrPmWorkPartListDTO.getFirstRow()));
	}
	private String getWhere(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, MaEqMstrPmWorkPartListDTO maEqMstrPmWorkPartListDTO, User user)
    {
		QueryBuffer query = new QueryBuffer();
		
		query.getAndQuery("x.comp_no", user.getCompNo());
		query.getAndQuery("x.pm_id", maEqMstrPmWorkDetailDTO.getPmId());
		
		if(!"".equals(maEqMstrPmWorkPartListDTO.getPmPartId())){
			query.getAndQuery("x.pm_part_id", maEqMstrPmWorkPartListDTO.getPmPartId());
			return query.toString();
		}
		
		return query.toString();
    }
	@Override
	public int deleteEqPmWorkPartList(String pmId, String pmPointId, User user)
			throws Exception {
		
		QueryBuffer query = new QueryBuffer();
		
		query.append("UPDATE TAPMPART 				");
		query.append("SET 	is_deleted 		= ?		");
		query.append("		,delete_by 		= ?		");
		query.append("		,delete_time 	= ?		");
		query.append("WHERE 1=1						");
		query.append("AND comp_no 		= ?			");
		query.append("AND pm_id 		= ?			");
		query.append("AND pm_part_id 	= ?			");
		Object[] objects = new Object[]{
			"Y"
			,user.getUserId()
			,DateUtil.getDateTime()
			,user.getCompNo()
			,pmId
			,pmPointId
		};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
	}

	@Override
	public String findTotalCount(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO, MaEqMstrPmWorkPartListDTO maEqMstrPmWorkPartListDTO, User user) throws Exception {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT COUNT(*)					");
		query.append("FROM TAPMPART x					");
		query.append("WHERE 1=1							");
		query.append(this.getWhere(maEqMstrPmWorkDetailDTO, maEqMstrPmWorkPartListDTO, user));
        
		List resultList=  getJdbcTemplate().queryForList(query.toString());
		return QueryBuffer.listToString(resultList);
	}
}