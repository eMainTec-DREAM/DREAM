package dream.asset.list.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.asset.list.dao.LovEqToolAcListDAO;
import dream.asset.list.dto.LovEqToolAcListDTO;

/**
 * LOV - List DAO implements
 * @author youngjoo38
 * @version $Id: LovEqToolAcListDAOSqlImpl.java,v 1.0 2015/12/02 09:12:40 youngjoo38 Exp $
 * @since 1.0
 * 
 * @spring.bean id="lovEqToolAcListDAOTarget"
 * @spring.txbn id="lovEqToolAcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovEqToolAcListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovEqToolAcListDAO
{
	public List findList(LovEqToolAcListDTO lovEqToolAcListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                                                ");
        query.append("		 x.equip_id                                     ");
        query.append("		 ,y.description                                 ");
	 	query.append("       ,y.item_no 					AS item_no		");
	 	query.append("       ,y.serial_no 					AS serial_no	");
	 	query.append("       ,(SELECT        ");
        query.getDate("first_Value(z.next_calib_date) OVER (ORDER BY wkor_date DESC)", "next_calib_date");
        query.append("         FROM TAWORKORDER A INNER JOIN TAWOEQUIP b ON A.wkor_id = b.wkor_id   ");
        query.append("                    INNER JOIN TAWOCALIB z ON A.wkor_id = z.wkor_id   ");
        query.append("         WHERE  b.equip_id = y.equip_id   ");
        query.append("           AND a.wo_status ='C'   ");
        query.append("        ) next_calib_date ");
	 	query.append("       ,(SELECT   ");
	 	query.append("                    ISNULL(first_value(z.calib_cert_no) OVER (ORDER BY wkor_date DESC), first_value(A.wkor_id) OVER (ORDER BY wkor_date DESC))   ");
	 	query.append("         FROM TAWORKORDER A INNER JOIN TAWOEQUIP b ON A.wkor_id = b.wkor_id   ");
	 	query.append("                    INNER JOIN TAWOCALIB z ON A.wkor_id = z.wkor_id   ");
	 	query.append("         WHERE  b.equip_id = y.equip_id   ");
	 	query.append("           AND a.wo_status ='C'   ");
	 	query.append("        ) calib_cert_no   ");
	 	query.append("        ,(SELECT description           ");
        query.append("         FROM TAEQLOC z   ");
        query.append("         WHERE z.eqloc_id = y.eqloc_id    ");
        query.append("        ) eqLocDesc   ");
        query.append("        ,(SELECT A.description    ");
        query.append("          FROM TAPLANT A  ");
        query.append("          WHERE A.plant = y.plant ");
        query.append("          ) plantDesc ");
        query.append("        ,(SELECT A.description   ");
        query.append("          FROM TAEQUIPMENT A  ");
        query.append("          WHERE A.equip_id = y.p_equip_id ");
        query.append("         ) pequipDesc                 ");
        query.append("        ,(SELECT A.item_no   ");
        query.append("           FROM TAEQUIPMENT A ");
        query.append("           WHERE A.equip_id = y.p_equip_id    ");
        query.append("         ) pitemNo                           ");
        query.append("        ,eqctg_type eqctgType                         ");
        query.append("FROM   TAEQTOOL x, TAEQUIPMENT y       				");
        query.append("WHERE  1=1                             				");
        query.append("AND     x.comp_no = y.comp_no          				");
        query.append("AND     x.equip_id = y.equip_id        				");
        query.getStringEqualQuery("y.comp_no", user.getCompNo());
    	query.append(this.getWhere(lovEqToolAcListDTO, user, columnMap, conditionMap));
        query.getOrderByQuery("y.equip_id", "y.description", lovEqToolAcListDTO.getOrderBy(), lovEqToolAcListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovEqToolAcListDTO.getIsLoadMaxCount(), lovEqToolAcListDTO.getFirstRow()));
    } 

	private String getWhere(LovEqToolAcListDTO lovEqToolAcListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap)
    {        
	    QuerySqlBuffer query = new QuerySqlBuffer();
        
//        query.getAndQuery("x.is_standard_eq", "Y");
	    query.getStringEqualQuery("y.is_last_version", "Y");
        query.getStringEqualQuery("y.IS_DELETED", "N");
                
        //설비코드명
        query.getLikeQuery("y.description", lovEqToolAcListDTO.getEquipDesc());
        //설비코드
        query.getLikeQuery("x.equip_id", lovEqToolAcListDTO.getEquipId());
        
        query.getLikeQuery("y.item_no", lovEqToolAcListDTO.getToolNo());
        query.getLikeQuery("y.description", lovEqToolAcListDTO.getToolDesc());
        query.getEqLocLevelQuery("y.eqloc_id", lovEqToolAcListDTO.getEqlocId(), lovEqToolAcListDTO.getEqlocDesc(), user.getCompNo());

        // 상위 설비번호
        if(!"".equals(lovEqToolAcListDTO.getItemNo()) || !"".equals(lovEqToolAcListDTO.getEqDesc()))
        {
            query.append("AND y.p_equip_id IN (SELECT a.equip_id FROM taequipment a             ");
            query.append("                      WHERE a.comp_no = x.comp_no                     ");
            query.getLikeQuery("a.item_no", lovEqToolAcListDTO.getItemNo() );
            query.getLikeQuery("a.description", lovEqToolAcListDTO.getEqDesc() );
            query.append("                                )                                     ");
        }
        
        query.getAndQuery("x.is_standard_eq", conditionMap);
        query.getAndQuery("y.eqctg_type", conditionMap);
        query.getAndQuery("y.eq_status", conditionMap);

    	return query.toString();
    }

    public String findTotalCount(LovEqToolAcListDTO lovEqToolAcListDTO, User user, Map<String, String> columnMap,
			Map<String, String> conditionMap) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT					             ");
        query.append("		COUNT(1)			             ");
        query.append("FROM TAEQTOOL x , TAEQUIPMENT y  		 ");
    	query.append("WHERE  1=1				             ");
        query.append("AND     x.comp_no = y.comp_no          ");
        query.append("AND     x.equip_id = y.equip_id        ");
        query.getStringEqualQuery("y.comp_no", user.getCompNo());
    	query.append(this.getWhere(lovEqToolAcListDTO, user, columnMap, conditionMap));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}