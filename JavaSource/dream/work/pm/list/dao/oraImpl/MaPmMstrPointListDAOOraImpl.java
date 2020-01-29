package dream.work.pm.list.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.pm.list.dao.MaPmMstrPointListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPointDetailDTO;

/**
 * 검사항목 목록 dao
 * @author  jung7126
 * @version $Id: MaPmMstrPointListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maPmMstrPointListDAOTarget"
 * @spring.txbn id="maPmMstrPointListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPmMstrPointListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPmMstrPointListDAO
{
    /**
     * grid find
     * @author  jung7126
     * @version $Id: MaPmMstrPointListDAO.java,v 1.0 2015/12/02 09:14:12 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrCommonDTO
     * @param loginUser
     * @return List
     */
    public List findPointList(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT																	");
        query.append("		''	 						seqNo									");
        query.append("		,'' 							isDelCheck								");
        query.append("		,x.step_num stepNum													");
        query.append("      ,x.eqasmb_id eqAsmbId                                  				");
        query.append("      ,CASE WHEN x.eqasmb_id IS NULL               ");
        query.append("        THEN x.eqasmb_desc                        ");
        query.append("        ELSE (SELECT a.full_desc                  ");
        query.append("            FROM TAEQASMB a                       ");
        query.append("            WHERE a.comp_no = x.comp_no           ");
        query.append("            AND a.eqasmb_id = x.eqasmb_id)        ");
        query.append("        END                  eqAsmbDesc           ");

        query.append("		,x.check_point CHECKPOINT											");
        query.append("		,x.check_method checkMethod											");
        query.append("		,x.fit_basis fitBasis												");
        query.append("		,SFACODE_TO_DESC(x.check_type,'CHECK_TYPE','SYS','','"+loginUser.getLangId()+"') checkTypeDesc	");
        query.append("		,x.check_min checkMin												");
        query.append("		,x.check_basis_val checkBasisVal									");
        query.append("		,x.check_max checkMax												");
        query.append("		,x.uom uom															");
        query.append("		,x.is_active isActive												");
        query.append("      ,x.pmequip_id pmEquipId                                             ");
        query.append("      ,x.remark remark                                                    ");
        query.append("		,x.pm_point_id pmPointId											");
        query.append("		,(SELECT a.param1 													");
        query.append("			FROM TACDSYSD a													");
        query.append("			WHERE 1=1														");
        query.append("			AND a.list_type='CHECK_TYPE'									");
        query.append("			AND a.cdsysd_no = x.check_type) AS detailPage					");
        query.append("		,x.check_type AS checkTypeId										");
        query.append("FROM TAPMPOINT x															");
        query.append("WHERE 1=1																	");
        query.append(this.getWhere(maPmMstrCommonDTO,loginUser));
        query.getOrderByQuery("x.step_num", maPmMstrCommonDTO.getOrderBy(), maPmMstrCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPmMstrCommonDTO.getIsLoadMaxCount(), maPmMstrCommonDTO.getFirstRow()));
    }
    
    private String getWhere(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndNumKeyQuery("x.pm_id", maPmMstrCommonDTO.getPmId());
    	query.getStringEqualQuery("x.comp_no", loginUser.getCompNo());
    	query.getStringEqualQuery("x.IS_DELETED", "N");
    	query.getAndQuery("x.pmequip_id", maPmMstrCommonDTO.getPmEquipId());
    	if (!"".equals(maPmMstrCommonDTO.getPmPointId()))
        {
            query.getAndNumKeyQuery("x.pm_point_id", maPmMstrCommonDTO.getPmPointId());
            return query.toString();
        }
    	
    	return query.toString();
    }
    
    /**
     * delete
     * @author jung7126
     * @version $Id: MaPmMstrPointListDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int[] updateDeleteTag(final List<MaPmMstrPointDetailDTO> list, final User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	final String deleteTime = DateUtil.getTimeStamp(user.getOffset(), "yyyyMMddHHmmss");

    	query.append("UPDATE  TAPMPOINT SET   ");
        query.append("         IS_DELETED = 'Y'     ");
        query.append("        ,DELETE_BY  = ?       ");
        query.append("        ,DELETE_TIME = ?      ");
        query.append("WHERE pm_point_id = ?   ");
        query.append("  AND comp_no = ?       ");
        
        return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter()
        {

            @Override
            public int getBatchSize()
            {
                return list.size();
            }

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                MaPmMstrPointDetailDTO maPmMstrPointDetailDTO = list.get(i);

                Object[] objects = new Object[] {  
                        user.getEmpId()
                        ,deleteTime
                        ,maPmMstrPointDetailDTO.getPmPointId()
                        ,user.getCompNo()
                };
                
                for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
            }
            
        });
    }

	@Override
	public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser) throws Exception {

QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT					");
        query.append("		count(1)			");
        query.append("FROM TAPMPOINT x			");
        query.append("WHERE 1=1					");
        query.append(this.getWhere(maPmMstrCommonDTO,loginUser));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);

	}
	
	//select insert
}